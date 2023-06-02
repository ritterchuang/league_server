package org.lsj.gs.math.core.slot.moduleConfigMgr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.config.GameFlowHlrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationCtrConfig;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.ClientSpinRequestHlrConfig;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.config.CommonInputHlrConfig;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfig;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputHlrMgrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.MathSlotConfigHlrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.paymentHlrMgr.enity.PaymentHlrConfig;
import org.lsj.gs.math.core.slot.paymentHlrMgr.enity.PaymentHlrMgrConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressHlrMgrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfigCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrMgrConfig;
import org.lsj.gs.math.core.slot.reelRtpStateHlr.ReelRtpStateHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrMgrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfigCluster;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrMgrConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 抽象老虎機模組設定管理器 父類別
public abstract class AbstractModuleConfigMgrSlot {
    protected final TableFieldConfig config; // 牌桌設定
    protected final SlotDetailHlrConfigExtendUtil slotDetailHlrConfigExtendUtil; // 虎機詳細記錄處理者額外設定工具包
    protected final CascadeClusterHlrConfigExtendUtil cascadeClusterHlrConfigExtendUtil; //虎機消除集合處理者額外設定工具包
    protected final CascadeHlrConfigExtendUtil cascadeHlrConfigExtendUtil; //虎機消除處理者額外設定工具包

    public AbstractModuleConfigMgrSlot(TableFieldConfig config) {
        this.config = config;
        this.slotDetailHlrConfigExtendUtil = new SlotDetailHlrConfigExtendUtil();
        this.cascadeClusterHlrConfigExtendUtil = new CascadeClusterHlrConfigExtendUtil();
        this.cascadeHlrConfigExtendUtil = new CascadeHlrConfigExtendUtil();
    }


    // 建立虎機數值設定處理器的設定
    public MathSlotConfigHlrConfig createMathSlotConfigHlrConfig(TableGameConfigSlot tableGameConfigSlot) {

        return new MathSlotConfigHlrConfig(
                tableGameConfigSlot.getBetTypeList(),
                tableGameConfigSlot.getSpinTypeList(),
                tableGameConfigSlot.getBetSpinConfigCluster(),
                tableGameConfigSlot.getGameStateConfigCluster(),
                this.config.getBase()
        );
    }


    /* 遊戲押注相關設定 */
    // 計算客端押注請求設定
    public ClientSpinRequestHlrConfig createClientSpinRequestHlrConfig(TableGameConfigSlot tableGameConfigSlot) {
        return new ClientSpinRequestHlrConfig(
                this.config.getBase(),
                tableGameConfigSlot.getBetTypeList(),
                tableGameConfigSlot.getSpinTypeList(),
                tableGameConfigSlot.getBetSpinConfigCluster());
    }

    // 取得虎機成本處理器管理者設定
    public PaymentHlrMgrConfig createPaymentHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, PaymentHlrConfig>> betSpinToPaymentHlrConfig = new HashMap<>();

        tableGameConfigSlot.getBetSpinConfigCluster().getBetSpinToBetConfigMap().forEach(
                (betType, spinTypeToBetConfigMap) -> betSpinToPaymentHlrConfig.put(betType, this.calculateSpinTypeToPaymentHlrConfigMap(
                        tableGameConfigSlot.getBetSpinConfigCluster().getCreditList(),
                        this.config.getBase(),
                        spinTypeToBetConfigMap)));

        return new PaymentHlrMgrConfig(betSpinToPaymentHlrConfig);
    }

    // 計算 <玩法類型, 成本設定> 對應表
    private Map<ConstMathSlot.SpinType, PaymentHlrConfig> calculateSpinTypeToPaymentHlrConfigMap(List<Double> creditList, double base, Map<ConstMathSlot.SpinType, BetSpinConfig> spinTypeToBetSpinConfigMap) {
        Map<ConstMathSlot.SpinType, PaymentHlrConfig> result = new HashMap<>();

        spinTypeToBetSpinConfigMap.forEach((spinType, betSpinConfig) -> result.put(spinType, new PaymentHlrConfig(creditList, base, betSpinConfig.getPaymentConfig())));

        return result;
    }


    /* 遊戲流程相關設定 */
    // 取得虎機流程處理者設定
    public GameFlowHlrConfig createGameFlowHlrConfig(TableGameConfigSlot tableGameConfigSlot) {
        return new GameFlowHlrConfig(tableGameConfigSlot.getGameFlowConfig(), tableGameConfigSlot.getGameStateConfigCluster());
    }


    /* 遊戲計算內容相關設定 */
    // 高低表處理者設定
    public ReelRtpStateHlrConfig createReelRtpStateHlrConfig(TableGameConfigSlot tableGameConfigSlot) {
        return new ReelRtpStateHlrConfig(tableGameConfigSlot.getGameStateConfigCluster().getReelRtpTypeToDoubleMap());
    }

    // 取得虎機進度處理者管理器設定 TODO 新增 progressHlrConfig
    public ProgressHlrMgrConfig createProgressHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, ProgressConfig> conditionIdToProgressConfigMap = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateIndex = 0; gameStateIndex < gameStateConfigList.size(); gameStateIndex++) {
            RoundConfig roundConfig = gameStateConfigList.get(gameStateIndex).getRoundConfig();
            conditionIdToProgressConfigMap.put(gameStateIndex + 1, roundConfig.getProgressConfig());
        }

        return new ProgressHlrMgrConfig(conditionIdToProgressConfigMap);
    }

    // 取得虎機特殊事件處理者管理器設定
    public SpecialFeatureHlrMgrConfig createSpecialFeatureHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, SpecialFeatureHlrConfigCluster> conditionIdToSpecialFeatureHlrConfigClusterMap = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateIndex = 0; gameStateIndex < gameStateConfigList.size(); gameStateIndex++) {
            RoundConfig roundConfig = gameStateConfigList.get(gameStateIndex).getRoundConfig();
            conditionIdToSpecialFeatureHlrConfigClusterMap.put(gameStateIndex + 1,
                    this.calculateSpecialFeatureHlrConfigCluster(roundConfig.getSpecialFeatureConfigCluster(), roundConfig.getFrameConfig(), roundConfig.getSymbolConfig()));
        }

        return new SpecialFeatureHlrMgrConfig(conditionIdToSpecialFeatureHlrConfigClusterMap);
    }

    // 取得虎機聽牌處理者管理器設定
    public ReadyHandHlrMgrConfig createReadyHandHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, ReadyHandHlrConfigCluster> conditionIdToReadyHandHlrConfigClusterMap = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateIndex = 0; gameStateIndex < gameStateConfigList.size(); gameStateIndex++) {
            RoundConfig roundConfig = gameStateConfigList.get(gameStateIndex).getRoundConfig();
            conditionIdToReadyHandHlrConfigClusterMap.put(gameStateIndex + 1,
                    this.calculateReadyHandHlrConfigCluster(roundConfig.getReadyHandConfigCluster(), roundConfig.getFrameConfig(), roundConfig.getSymbolConfig()));
        }

        return new ReadyHandHlrMgrConfig(conditionIdToReadyHandHlrConfigClusterMap);
    }

    // 取得下一State 遊戲額外輸入處理者管理器設定
    public CommonInputHlrConfig createCommonInputHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        return new CommonInputHlrConfig(tableGameConfigSlot.getGameStateConfigCluster().getCommonInputConfig());
    }

    // 取得下一State 遊戲額外輸入處理者管理器設定
    public GameStateInputHlrMgrConfig createGameInputHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, GameStateInputConfig> conditionIdToGameStateInputConfigMap = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateIndex = 0; gameStateIndex < gameStateConfigList.size(); gameStateIndex++) {
            GameStateConfig gameStateConfig =  gameStateConfigList.get(gameStateIndex);
            conditionIdToGameStateInputConfigMap.put(gameStateIndex + 1, gameStateConfig.getGameStateInputConfig());
        }

        return new GameStateInputHlrMgrConfig(conditionIdToGameStateInputConfigMap);
    }

    // 取得虎機倍數大獎計算者設定
    public AnimationCtrConfig createAnimationConfig(TableGameConfigSlot tableGameConfigSlot) {
        return new AnimationCtrConfig(tableGameConfigSlot.getAnimationConfig());
    }

    // 計算詳細記錄處理者設定
    public SlotDetailHlrMgrConfig createSlotDetailHlrMgrConfig(TableGameConfigSlot tableGameConfigSlot) {
        Map<Integer, SlotDetailHlrConfig> conditionIdToSlotDetailCtrConfigMap = new HashMap<>();

        List<GameStateConfig> gameStateConfigList = tableGameConfigSlot.getGameStateConfigCluster().getGameStateConfigList();
        for (int gameStateId = 0; gameStateId < gameStateConfigList.size(); gameStateId++) {
            conditionIdToSlotDetailCtrConfigMap.put(
                    gameStateId + 1,
                    new SlotDetailHlrConfig(
                            gameStateConfigList.get(gameStateId).getSlotDetailType(),
                            this.slotDetailHlrConfigExtendUtil.calculateSlotDetailCtrConfigExtend(gameStateConfigList.get(gameStateId).getSlotDetailType(), gameStateConfigList.get(gameStateId))));
        }

        return new SlotDetailHlrMgrConfig(conditionIdToSlotDetailCtrConfigMap);
    }


    // 取得特殊事件處理者設定
    private SpecialFeatureHlrConfigCluster calculateSpecialFeatureHlrConfigCluster(SpecialFeatureConfigCluster specialFeatureConfigCluster, FrameConfig frameConfig, SymbolConfig symbolConfig) {
        List<SpecialFeatureHlrConfig> specialFeatureHlrConfigList = new ArrayList<>();

        specialFeatureConfigCluster.getSpecialFeatureConfigList().forEach(config -> specialFeatureHlrConfigList.add(new SpecialFeatureHlrConfig(frameConfig, symbolConfig, config)));

        return new SpecialFeatureHlrConfigCluster(specialFeatureHlrConfigList);
    }

    // 取得聽牌處理者設定
    private ReadyHandHlrConfigCluster calculateReadyHandHlrConfigCluster(ReadyHandConfigCluster readyHandConfigCluster, FrameConfig frameConfig, SymbolConfig symbolConfig) {
        List<ReadyHandHlrConfig> readyHandHlrConfigList = new ArrayList<>();

        readyHandConfigCluster.getReadyHandConfigList().forEach(config -> readyHandHlrConfigList.add(new ReadyHandHlrConfig(frameConfig, symbolConfig, config)));

        return new ReadyHandHlrConfigCluster(readyHandHlrConfigList);
    }
}
