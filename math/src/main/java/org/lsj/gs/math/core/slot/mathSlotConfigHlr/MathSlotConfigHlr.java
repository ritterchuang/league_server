package org.lsj.gs.math.core.slot.mathSlotConfigHlr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.MathSlotConfigHlrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.IClientBetSpinConfigHlr;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.config.ClientBetSpinConfigHlrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.module.ClientBetSpinConfigHlrFactory;
import org.lsj.gs.math.core.slot.paymentHlrMgr.PaymentHlrMgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 數值設定處理器
public class MathSlotConfigHlr extends AbstractModule {
    private final MathSlotConfigHlrConfig config; // 數值老虎機設定處理器的設定
    private final Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, IClientBetSpinConfigHlr>> betSpinTypeToBetSpinConfigHlrMap; // <押注類型, <玩法類型, 客端押注玩法設定處理者>> 對應表

    public MathSlotConfigHlr(MathSlotConfigHlrConfig mathSlotConfigHlrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = mathSlotConfigHlrConfig;
        this.betSpinTypeToBetSpinConfigHlrMap = this.createBetSpinTypeToBetSpinConfigHlrMap();
    }

    @Override
    public void reset() {
    }

    //* 取得虎機設定相關 *//
    // 取得老虎機客端數值設定
    public MathSlotConfig getMathSlotConfig(PaymentHlrMgr paymentHlrMgr) {
        // 1. 取得押注類型列表
        List<ConstMathSlot.BetType> betTypeList = this.config.getBetTypeList();

        // 2. 取得玩法類型列表
        List<ConstMathSlot.SpinType> spinTypeList = this.config.getSpinTypeList();

        // 3. 計算押注玩法成本
        ClientBetSpinConfigCluster clientBetSpinConfigCluster = this.calculateClientBetSpinConfigCluster(paymentHlrMgr);

        // 4. 取得遊戲狀態設定
        GameStateConfigCluster gameStateConfigCluster = this.config.getGameStateConfigCluster();

        // 5. 封裝
        return new MathSlotConfig(betTypeList, spinTypeList, clientBetSpinConfigCluster, gameStateConfigCluster);
    }

    // 計算押注玩法成本集合
    private ClientBetSpinConfigCluster calculateClientBetSpinConfigCluster(PaymentHlrMgr paymentHlrMgr) {
        // 1. 取得設定 BetConfig
        BetSpinConfigCluster betSpinConfigCluster = this.config.getBetConfigCluster();

        // 2. for Map
        Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, ClientBetSpinConfig>> result = new HashMap<>();
        betSpinConfigCluster.getBetSpinToBetConfigMap().forEach(((betType, spinTypeBetConfigMap) ->
                result.put(betType, this.calculateClientBetSpinConfigMap(betType, paymentHlrMgr, betSpinConfigCluster))));

        // 3. 回傳
        return new ClientBetSpinConfigCluster(this.config.getBetConfigCluster().getDefaultBetType(), this.config.getBetConfigCluster().getDefaultSpinType(), result);
    }

    // 計算 <玩法, 客端押注玩法設定> 對應表
    private Map<ConstMathSlot.SpinType, ClientBetSpinConfig> calculateClientBetSpinConfigMap(
            ConstMathSlot.BetType betType,
            PaymentHlrMgr paymentHlrMgr,
            BetSpinConfigCluster betSpinConfigCluster) {

        // 1. 取得<玩法, 押注玩法設定> 對應表
        Map<ConstMathSlot.SpinType, BetSpinConfig> spinTypeToBetSpinConfigMap = betSpinConfigCluster.getBetSpinToBetConfigMap().get(betType);

        // 2. 計算 <玩法, 客端押注玩法設定> 對應表
        Map<ConstMathSlot.SpinType, ClientBetSpinConfig> result = new HashMap<>();
        spinTypeToBetSpinConfigMap.forEach((spinType, betSpinConfig) ->
                result.put(spinType, this.betSpinTypeToBetSpinConfigHlrMap.get(betType).get(spinType).calculateClientBetSpinConfig(
                        betSpinConfig.getBetSpinType(),
                        betType,
                        spinType,
                        paymentHlrMgr)));

        // 3. 回傳
        return result;
    }

    //* 初始化模組 *//
    // 建立押注玩法類型設定處理者對應表
    private Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, IClientBetSpinConfigHlr>> createBetSpinTypeToBetSpinConfigHlrMap() {
        Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, IClientBetSpinConfigHlr>> result = new HashMap<>();

        this.config.getBetConfigCluster().getBetSpinToBetConfigMap().forEach(
                (betType, spinTypeToBetConfigMap) -> result.put(betType, this.createSpinTypeToConfigHlrMap(spinTypeToBetConfigMap, super.tableUtil)));

        return result;
    }

    // 建立 玩法類型 押注玩法設定處理者
    private Map<ConstMathSlot.SpinType, IClientBetSpinConfigHlr> createSpinTypeToConfigHlrMap(Map<ConstMathSlot.SpinType, BetSpinConfig> spinTypeToBetSpinConfigMap, ITableUtil tableUtil) {
        Map<ConstMathSlot.SpinType, IClientBetSpinConfigHlr> result = new HashMap<>();

        spinTypeToBetSpinConfigMap.forEach(
                (key, value) -> result.put(key,
                        new ClientBetSpinConfigHlrFactory().create(new ClientBetSpinConfigHlrConfig(value), tableUtil)));

        return result;
    }
}
