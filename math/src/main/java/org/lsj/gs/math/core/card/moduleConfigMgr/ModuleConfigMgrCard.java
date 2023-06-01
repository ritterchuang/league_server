package org.lsj.gs.math.core.card.moduleConfigMgr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.*;
import org.lsj.gs.math.core.card.betCtr.tbBetCtr.TbBetCtrConfig;
import org.lsj.gs.math.core.card.betCtr.tbBetCtr.TbBetCtrGameConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.ebgResultCtr.EbgBankerResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBankerResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuTonbiResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.pjResultCtr.PjBankerResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.sgResultCtr.SgBankerResultCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.ebgStackCtr.EbgStackCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.pjStackCtr.PjStackCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.ConstSgStack;
import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.SgStackCtrConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.*;

import java.util.Map;

// 卡牌模組設定管理器
public class ModuleConfigMgrCard {
    private final TableFieldConfig config; // 牌桌設定

    public ModuleConfigMgrCard(TableFieldConfig config) {
        this.config = config;
    }

    // 建立搶莊計算器設定
    public VieBankerCtrConfig createVieBankerCtrConfig(VieBankerCtrGameConfig vieBankerCtrGameConfig){
        return this.calculateVieBankerCtrConfig(vieBankerCtrGameConfig);
    }

    // 搶莊設定檔
    private VieBankerCtrConfig calculateVieBankerCtrConfig(VieBankerCtrGameConfig vieBankerCtrGameConfig) {
        if (vieBankerCtrGameConfig.getBankerType().equals(ConstMathCard.BankerType.MAX_RATE)) {
            VieBankerCtrGameConfigMaxRate configMaxRate = (VieBankerCtrGameConfigMaxRate) vieBankerCtrGameConfig;
            return new VieBankerCtrConfigMaxRate(
                    vieBankerCtrGameConfig.getBankerType(),
                    this.config.getMaxUser(), this.config.getBase(),
                    configMaxRate.getMinVieRate(),
                    configMaxRate.getMaxVieRate(),
                    configMaxRate.getVieRateGroup()
            );
        }

        VieBankerCtrGameConfigRateList configRateList = (VieBankerCtrGameConfigRateList) vieBankerCtrGameConfig;
        return new VieBankerCtrConfigRateList(
                vieBankerCtrGameConfig.getBankerType(),
                this.config.getMaxUser(), this.config.getBase(),
                configRateList.getVieRateList(),
                configRateList.getVieThresholdList()
        );
    }

    // 建立下注計算器設定
    public QzBetCtrConfig createQzBetCtrConfig(QzBetCtrGameConfig qzBetCtrGameConfig) {
        return this.calculateQzBetCtrConfig(qzBetCtrGameConfig);
    }

    // 下注設定檔
    private QzBetCtrConfig calculateQzBetCtrConfig(QzBetCtrGameConfig qzBetCtrGameConfig) {
        if (qzBetCtrGameConfig.getBetType().equals(ConstMathCard.QzBetType.BET_01)) {
            QzBetCtrGameConfigBetType01 configBetType01 = (QzBetCtrGameConfigBetType01) qzBetCtrGameConfig;
            return new QzBetCtrConfigBetType01(
                    configBetType01.getBetType()
                   , this.config.getMaxUser(), this.config.getBase()
                   , configBetType01.getMaxRate()
                   , configBetType01.getMinRate()
                   , configBetType01.getSpecifyRate()
                   , configBetType01.getThresholdValue());
        }

        QzBetCtrGameConfigBetType02 configBetType02 = (QzBetCtrGameConfigBetType02) qzBetCtrGameConfig;
        return new QzBetCtrConfigBetType02(
                configBetType02.getBetType()
                , this.config.getMaxUser(), this.config.getBase()
                , configBetType02.getMinRate()
                , configBetType02.getThresholdValue());
    }

    // 建立通比下注計算器設定
    public TbBetCtrConfig createTbBetCtrConfig(TbBetCtrGameConfig tbBetCtrGameConfig) {
        return new TbBetCtrConfig(this.config.getMaxUser(), this.config.getBase(), tbBetCtrGameConfig.getBetList());
    }

    // 建立牌牆計算器設定
    public CardWallCtrConfig createCardWallCtrConfig(ConstMathCard.CardType cardType, int[][] initWallList, Map<Integer, Integer> cardValueWeightMap) {
        return new CardWallCtrConfig(this.config.getMaxUser(), cardType, initWallList, cardValueWeightMap);
    }

    // 建立牛型計算器設定
    public NiuStackCtrConfig createNiuStackCtrConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeConfig) {
        return new NiuStackCtrConfig(this.config.getMaxUser(), niuTypeConfig);
    }

    // 建立算分結果計算器
    public NiuBankerResultCtrConfig createNiuBankerResultCtrConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        return new NiuBankerResultCtrConfig(
                this.config.getMaxUser(),
                this.config.getBase(),
                this.config.getFeeType(),
                this.config.getGameRate(),
                niuTypeRateConfig);
    }

    // 建立算分結果計算器
    public NiuTonbiResultCtrConfig createNiuTonbiResultCtrConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeConfig) {
        return new NiuTonbiResultCtrConfig(
                this.config.getMaxUser(),
                this.config.getBase(),
                this.config.getFeeType(),
                this.config.getGameRate(),
                niuTypeConfig);
    }

    //* 三公 *//

    // 建立算分結果計算器
    public SgBankerResultCtrConfig createSgResultCtrConfig(Map<ConstSgStack.SgTypeEnumCommon, Integer> sgTypeConfig) {
        return new SgBankerResultCtrConfig(this.config.getMaxUser(), this.config.getBase(), this.config.getFeeType(), this.config.getGameRate(), sgTypeConfig);
    }

    // 建立算分結果計算器
    public SgStackCtrConfig createSgStackCtrConfig() {
        return new SgStackCtrConfig(this.config.getMaxUser());
    }


    //* 牌九 *//

    // 建立算分結果計算器 TODO 先暫時
    public PjBankerResultCtrConfig createPjResultCtrConfig() {
        return new PjBankerResultCtrConfig(this.config.getMaxUser(), this.config.getBase(), this.config.getFeeType(), this.config.getGameRate());
    }

    // 建立算分結果計算器
    public PjStackCtrConfig createPjStackCtrConfig() {
        return new PjStackCtrConfig(this.config.getMaxUser());
    }


    //* 二八槓 *//

    // 建立算分結果計算器 TODO 先暫時
    public EbgBankerResultCtrConfig createEbgResultCtrConfig() {
        return new EbgBankerResultCtrConfig(this.config.getMaxUser(), this.config.getBase(), this.config.getFeeType(), this.config.getGameRate());
    }

    // 建立算分結果計算器
    public EbgStackCtrConfig createEbgStackCtrConfig() {
        return new EbgStackCtrConfig(this.config.getMaxUser());
    }



}
