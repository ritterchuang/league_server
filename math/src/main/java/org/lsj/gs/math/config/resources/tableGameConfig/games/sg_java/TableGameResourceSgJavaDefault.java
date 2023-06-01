package org.lsj.gs.math.config.resources.tableGameConfig.games.sg_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSgJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfigBetType01;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.sgResultCtr.SgBankerResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.ConstSgStack;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfigRateList;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

// 新看四張搶莊牛牛預設 牌桌遊戲設定
public class TableGameResourceSgJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigSgJava create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 計算搶莊遊戲設定
        VieBankerCtrGameConfig vieBankerCtrGameConfig = new VieBankerCtrGameConfigRateList(
                ConstMathCard.BankerType.RATE_LIST,
                Arrays.stream((new int[]{0, 1})).boxed().collect(Collectors.toList()),
                Arrays.stream((new int[]{0, 8})).boxed().collect(Collectors.toList())
        );

        // 4. 下注計算器遊戲設定
        QzBetCtrGameConfig qzBetCtrGameConfig = new QzBetCtrGameConfigBetType01(ConstMathCard.QzBetType.BET_01, 35, 1, 2, 5);

        // 5. 牌牆計算器遊戲設定
        CardWallCtrGameConfig cardWallCtrGameConfig = new CardWallCtrGameConfig(
                ConstMathCard.CardType.POKER,
                new int[][]{
                        {101,1}, {102,1}, {103,1}, {104,1}, {105,1}, {106,1}, {107,1}, {108,1}, {109,1}, {110,1}, {111,1}, {112,1}, {113,1}, // 方塊
                        {201,1}, {202,1}, {203,1}, {204,1}, {205,1}, {206,1}, {207,1}, {208,1}, {209,1}, {210,1}, {211,1}, {212,1}, {213,1}, // 梅花
                        {301,1}, {302,1}, {303,1}, {304,1}, {305,1}, {306,1}, {307,1}, {308,1}, {309,1}, {310,1}, {311,1}, {312,1}, {313,1}, // 紅心
                        {401,1}, {402,1}, {403,1}, {404,1}, {405,1}, {406,1}, {407,1}, {408,1}, {409,1}, {410,1}, {411,1}, {412,1}, {413,1}},// 黑桃
                new HashMap<>(){{
                    put(ConstMathCard.PointType.P_A.getPoint(), 1);
                    put(ConstMathCard.PointType.P_2.getPoint(), 2);
                    put(ConstMathCard.PointType.P_3.getPoint(), 3);
                    put(ConstMathCard.PointType.P_4.getPoint(), 4);
                    put(ConstMathCard.PointType.P_5.getPoint(), 5);
                    put(ConstMathCard.PointType.P_6.getPoint(), 6);
                    put(ConstMathCard.PointType.P_7.getPoint(), 7);
                    put(ConstMathCard.PointType.P_8.getPoint(), 8);
                    put(ConstMathCard.PointType.P_9.getPoint(), 9);
                    put(ConstMathCard.PointType.P_T.getPoint(), 10);
                    put(ConstMathCard.PointType.P_J.getPoint(), 11);
                    put(ConstMathCard.PointType.P_Q.getPoint(), 12);
                    put(ConstMathCard.PointType.P_K.getPoint(), 13);
                }}
        );

        // 9. 搶莊類機器人邏輯設定
        RobotLogicConfigBanker robotLogicConfigBanker = new RobotLogicConfigBanker(
                new ArrayList<>(){{
                    add(new ArrayList<>(){{add(100);}});
                    add(new ArrayList<>(){{add(60);add(40);}});
                }},
                new ArrayList<>(){{
                    add(new ArrayList<>(){{add(100);}});
                    add(new ArrayList<>(){{add(50);add(50);}});
                    add(new ArrayList<>(){{add(25);add(25);add(50);}});
                    add(new ArrayList<>(){{add(15);add(15);add(30);add(40);}});
                    add(new ArrayList<>(){{add(12);add(12);add(24);add(26);add(26);}});
                }},
                ConstSgJava.TimeEnumSgJava.VIE_BANKER.getMilliTimeSec(),
                ConstSgJava.TimeEnumSgJava.BET.getMilliTimeSec(),
                ConstSgJava.TimeEnumSgJava.SELECT.getMilliTimeSec()
        );

        SgBankerResultCtrGameConfig sgBankerResultCtrGameConfig = new SgBankerResultCtrGameConfig(
                new HashMap<>(){{
                    put(ConstSgStack.SgTypeEnumCommon.PT_0, 1);
                    put(ConstSgStack.SgTypeEnumCommon.PT_1, 1);
                    put(ConstSgStack.SgTypeEnumCommon.PT_2, 1);
                    put(ConstSgStack.SgTypeEnumCommon.PT_3, 1);
                    put(ConstSgStack.SgTypeEnumCommon.PT_4, 1);
                    put(ConstSgStack.SgTypeEnumCommon.PT_5, 1);
                    put(ConstSgStack.SgTypeEnumCommon.PT_6, 1);
                    put(ConstSgStack.SgTypeEnumCommon.PT_7, 2);
                    put(ConstSgStack.SgTypeEnumCommon.PT_8, 2);
                    put(ConstSgStack.SgTypeEnumCommon.PT_9, 2);
                    put(ConstSgStack.SgTypeEnumCommon.SANGONG, 3);
                    put(ConstSgStack.SgTypeEnumCommon.DASANGONG, 4);
                    put(ConstSgStack.SgTypeEnumCommon.ZHIZUN, 5);
                }}
        );


        return new TableGameConfigSgJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                vieBankerCtrGameConfig,
                qzBetCtrGameConfig,
                cardWallCtrGameConfig,
                sgBankerResultCtrGameConfig,
                robotLogicConfigBanker
        );
    }
}
