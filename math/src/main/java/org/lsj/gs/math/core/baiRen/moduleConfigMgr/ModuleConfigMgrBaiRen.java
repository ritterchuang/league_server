package org.lsj.gs.math.core.baiRen.moduleConfigMgr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigBrnnJava;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigCjnnJava;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.bjlResultCtr.BjlBaiRenResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.jhResultCtr.JhBaiRenResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.lhdResultCtr.LhdResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBaiRenResultCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.ConstJhStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr.NiuBaiRenStackCtrConfig;

import java.util.List;
import java.util.Map;

// 百人模組設定管理器
public class ModuleConfigMgrBaiRen {
    private final TableFieldConfig config; // 牌桌設定

    public ModuleConfigMgrBaiRen(TableFieldConfig config) {
        this.config = config;
    }

    // 建立押注區域計算器設定
    public BetAreaCtrConfig createBetAreaCtrConfig(
            List<Integer> chipsOddsList,
            Map<Integer, Integer> areasTopLimitOddsMap,
            int[][] cantBetTogetherArray,
            int betAreaCount
    ) {
        return new BetAreaCtrConfig(this.config.getBase(), chipsOddsList, areasTopLimitOddsMap, cantBetTogetherArray, betAreaCount);
    }


    //* 結果計算器設定 *//

    // 建立龍虎結果計算器設定
    public LhdResultCtrConfig createLhResultCtrConfig(int betAreaCount) {
        return new LhdResultCtrConfig(betAreaCount, this.config.getFeeType(), this.config.getGameRate());
    }

    // 建立紅黑大戰結果計算器設定
    public JhBaiRenResultCtrConfig createHhdzBaiRenResultCtrConfig(int betAreaCount, int feeType, double gameRate, Map<ConstJhStack.JhTypeEnumCommon, Integer> cardTypeRateMap) {
        return new JhBaiRenResultCtrConfig(betAreaCount, feeType, gameRate, cardTypeRateMap);
    }

    // 建立百人牛牛結果計算器設定
    public NiuBaiRenResultCtrConfig createBrnnBaiRenResultCtrConfig(int betAreaCount, TableGameConfigBrnnJava tableGameConfigBrnnJava) {
        return new NiuBaiRenResultCtrConfig(betAreaCount, this.config.getFeeType(), this.config.getGameRate(), tableGameConfigBrnnJava.getNiuBaiRenResultCtrGameConfig().getNiuTypeRateConfig());
    }

    // 建立超級牛牛結果計算器設定
    public NiuBaiRenResultCtrConfig createCjnnBaiRenResultCtrConfig(int betAreaCount, TableGameConfigCjnnJava tableGameConfigCjnnJava) {
        return new NiuBaiRenResultCtrConfig(betAreaCount, this.config.getFeeType(), this.config.getGameRate(), tableGameConfigCjnnJava.getNiuBaiRenResultCtrGameConfig().getNiuTypeRateConfig());
    }

    // 建立百家樂結果計算器設定
    public BjlBaiRenResultCtrConfig createBjlBaiRenResultCtrConfig(int betAreaCount, int feeType, double gameRate, Map<Integer, Double> betAreaRateMap) {
        return new BjlBaiRenResultCtrConfig(betAreaCount, feeType, gameRate, betAreaRateMap);
    }


    //* 牌型計算器設定 *//

    // 建立百人牛牛牌型計算器設定
    public NiuBaiRenStackCtrConfig createNiuBaiRenStackCtrConfig(TableGameConfigBrnnJava tableGameConfigBrnnJava) {
        return new NiuBaiRenStackCtrConfig(tableGameConfigBrnnJava.getNiuBaiRenResultCtrGameConfig().getNiuTypeRateConfig());
    }

    // 建立百人牛牛牌型計算器設定
    public NiuBaiRenStackCtrConfig createNiuBaiRenStackCtrConfig(TableGameConfigCjnnJava tableGameConfigCjnnJava) {
        return new NiuBaiRenStackCtrConfig(tableGameConfigCjnnJava.getNiuBaiRenResultCtrGameConfig().getNiuTypeRateConfig());
    }
}
