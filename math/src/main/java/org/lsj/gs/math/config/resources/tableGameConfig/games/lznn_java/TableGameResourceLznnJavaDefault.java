package org.lsj.gs.math.config.resources.tableGameConfig.games.lznn_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigLznnJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfigBetType01;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBankerResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfigRateList;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.math.games.lznn_java.module.niu.NiuStackTmrConfigLznnJava;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// 新賴子牛牛預設 牌桌遊戲設定
public class TableGameResourceLznnJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigLznnJava create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 計算搶莊遊戲設定
        VieBankerCtrGameConfig vieBankerCtrGameConfig = new VieBankerCtrGameConfigRateList(
                ConstMathCard.BankerType.RATE_LIST,
                Arrays.stream((new int[]{0, 1, 2, 3})).boxed().collect(Collectors.toList()),
                Arrays.stream((new int[]{0, 18, 54, 90})).boxed().collect(Collectors.toList())
        );

        // 4. 下注計算器遊戲設定
        QzBetCtrGameConfig qzBetCtrGameConfig = new QzBetCtrGameConfigBetType01(ConstMathCard.QzBetType.BET_01, 15, 1, 3, 4);

        // 5. 牌牆計算器遊戲設定
        CardWallCtrGameConfig cardWallCtrGameConfig = new CardWallCtrGameConfig(
                ConstMathCard.CardType.POKER,
                new int[][]{
                        {101,1}, {102,1}, {103,1}, {104,1}, {105,1}, {106,1}, {107,1}, {108,1}, {109,1}, {110,1}, {111,1}, {112,1}, {113,1}, // 方塊
                        {201,1}, {202,1}, {203,1}, {204,1}, {205,1}, {206,1}, {207,1}, {208,1}, {209,1}, {210,1}, {211,1}, {212,1}, {213,1}, // 梅花
                        {301,1}, {302,1}, {303,1}, {304,1}, {305,1}, {306,1}, {307,1}, {308,1}, {309,1}, {310,1}, {311,1}, {312,1}, {313,1}, // 紅心
                        {401,1}, {402,1}, {403,1}, {404,1}, {405,1}, {406,1}, {407,1}, {408,1}, {409,1}, {410,1}, {411,1}, {412,1}, {413,1}, // 黑桃
                        {501,1}, {502,1}
                },
                new HashMap<>(){{
                    put(ConstMathCard.PointType.P_A.getPoint(),1);
                    put(ConstMathCard.PointType.P_2.getPoint(),2);
                    put(ConstMathCard.PointType.P_3.getPoint(),3);
                    put(ConstMathCard.PointType.P_4.getPoint(),4);
                    put(ConstMathCard.PointType.P_5.getPoint(),5);
                    put(ConstMathCard.PointType.P_6.getPoint(),6);
                    put(ConstMathCard.PointType.P_7.getPoint(),7);
                    put(ConstMathCard.PointType.P_8.getPoint(),8);
                    put(ConstMathCard.PointType.P_9.getPoint(),9);
                    put(ConstMathCard.PointType.P_T.getPoint(),10);
                    put(ConstMathCard.PointType.P_J.getPoint(),11);
                    put(ConstMathCard.PointType.P_Q.getPoint(),12);
                    put(ConstMathCard.PointType.P_K.getPoint(),13);
                }}
        );

        // 6. 牛型計算器遊戲設定
        NiuStackCtrGameConfig niuStackCtrGameConfig = new NiuStackCtrGameConfig(new HashMap<>(this.getNiuTypeRateConfig()));

        // 7. 牛牛結果計算器設定
        NiuBankerResultCtrGameConfig niuBankerResultCtrGameConfig = new NiuBankerResultCtrGameConfig(new HashMap<>(this.getNiuTypeRateConfig()));

        // 8. 計算牛型轉換器設定
        NiuStackTmrConfigLznnJava niuStackTmrConfigQznnJava = new NiuStackTmrConfigLznnJava(
                new HashMap<>() {{
                    put(ConstNiu.NiuTypeEnumCommon.INVALID, ConstLznnJava.NiuTypeEnumLznnJava.INVALID);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_0, ConstLznnJava.NiuTypeEnumLznnJava.NIU_0);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_1, ConstLznnJava.NiuTypeEnumLznnJava.NIU_1);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_2, ConstLznnJava.NiuTypeEnumLznnJava.NIU_2);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_3, ConstLznnJava.NiuTypeEnumLznnJava.NIU_3);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_4, ConstLznnJava.NiuTypeEnumLznnJava.NIU_4);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_5, ConstLznnJava.NiuTypeEnumLznnJava.NIU_5);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_6, ConstLznnJava.NiuTypeEnumLznnJava.NIU_6);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_7, ConstLznnJava.NiuTypeEnumLznnJava.NIU_7);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_8, ConstLznnJava.NiuTypeEnumLznnJava.NIU_8);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_9, ConstLznnJava.NiuTypeEnumLznnJava.NIU_9);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_NIU, ConstLznnJava.NiuTypeEnumLznnJava.NIU_NIU);
                    put(ConstNiu.NiuTypeEnumCommon.FLOWER_4, ConstLznnJava.NiuTypeEnumLznnJava.FLOWER_4);
                    put(ConstNiu.NiuTypeEnumCommon.SHUNZI_NIU, ConstLznnJava.NiuTypeEnumLznnJava.STRAIGHT);
                    put(ConstNiu.NiuTypeEnumCommon.TONGHUA_NIU, ConstLznnJava.NiuTypeEnumLznnJava.TONGHUA);
                    put(ConstNiu.NiuTypeEnumCommon.HULU_NIU, ConstLznnJava.NiuTypeEnumLznnJava.HULU);
                    put(ConstNiu.NiuTypeEnumCommon.FLOWER_5, ConstLznnJava.NiuTypeEnumLznnJava.FLOWER_5);
                    put(ConstNiu.NiuTypeEnumCommon.BOMB, ConstLznnJava.NiuTypeEnumLznnJava.BOMB);
                    put(ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU, ConstLznnJava.NiuTypeEnumLznnJava.STRAIGHTFLUSH);
                    put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, ConstLznnJava.NiuTypeEnumLznnJava.SMALL_NIU);
                    put(ConstNiu.NiuTypeEnumCommon.HYDROGEN_BOMB, ConstLznnJava.NiuTypeEnumLznnJava.HYDROGENBOMB);
                }}
        );

        // 9. 搶莊類機器人邏輯設定
        RobotLogicConfigBanker robotLogicConfigBanker = new RobotLogicConfigBanker(
                new ArrayList<>(){{
                    add(new ArrayList<>(){{add(100);}});
                    add(new ArrayList<>(){{add(50);add(50);}});
                    add(new ArrayList<>(){{add(50);add(20);add(30);}});
                    add(new ArrayList<>(){{add(20);add(20);add(20);add(40);}});
                }},
                new ArrayList<>(){{
                    add(new ArrayList<>(){{add(100);}});
                    add(new ArrayList<>(){{add(50);add(50);}});
                    add(new ArrayList<>(){{add(25);add(25);add(50);}});
                    add(new ArrayList<>(){{add(15);add(15);add(30);add(40);}});
                    add(new ArrayList<>(){{add(12);add(12);add(24);add(26);add(26);}});
                }},
                ConstQznnJava.TimeEnumQznnJava.VIE_BANKER.getMilliTimeSec(),
                ConstQznnJava.TimeEnumQznnJava.BET.getMilliTimeSec(),
                ConstQznnJava.TimeEnumQznnJava.SELECT.getMilliTimeSec()
        );

        return new TableGameConfigLznnJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                vieBankerCtrGameConfig,
                qzBetCtrGameConfig,
                cardWallCtrGameConfig,
                niuStackCtrGameConfig,
                niuBankerResultCtrGameConfig,
                niuStackTmrConfigQznnJava,
                robotLogicConfigBanker
        );
    }

    // 牛型賠率設定 <牛型識別碼, 牛型倍數>
    private Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeRateConfig(){
        return new HashMap<>(){{
            put(ConstNiu.NiuTypeEnumCommon.INVALID, 0);
            put(ConstNiu.NiuTypeEnumCommon.NIU_0, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_1, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_2, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_3, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_4, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_5, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_6, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_7, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_8, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_9, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_NIU, 3);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_4, 4);
            put(ConstNiu.NiuTypeEnumCommon.SHUNZI_NIU, 4);
            put(ConstNiu.NiuTypeEnumCommon.TONGHUA_NIU, 4);
            put(ConstNiu.NiuTypeEnumCommon.HULU_NIU, 4);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_5, 4);
            put(ConstNiu.NiuTypeEnumCommon.BOMB, 4);
            put(ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU, 5);
            put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, 5);
            put(ConstNiu.NiuTypeEnumCommon.HYDROGEN_BOMB, 5);
        }};
    }
}
