package org.lsj.gs.math.config.resources.tableGameConfig.games.tbnn_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigTbnnJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.tbBetCtr.TbBetCtrGameConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuTonbiResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigTonbi;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;
import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.niu.NiuStackTmrConfigTbnnJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 新通比牛牛預設 牌桌遊戲設定
public class TableGameResourceTbnnJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigTbnnJava create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 4. 下注計算器遊戲設定
        TbBetCtrGameConfig tbBetCtrGameConfig = new TbBetCtrGameConfig(List.of(1, 2, 3, 4, 5));

        // 5. 牌牆計算器遊戲設定
        CardWallCtrGameConfig cardWallCtrGameConfig = new CardWallCtrGameConfig(
                ConstMathCard.CardType.POKER,
                new int[][]{
                        {101,1}, {102,1}, {103,1}, {104,1}, {105,1}, {106,1}, {107,1}, {108,1}, {109,1}, {110,1}, {111,1}, {112,1}, {113,1}, // 方塊
                        {201,1}, {202,1}, {203,1}, {204,1}, {205,1}, {206,1}, {207,1}, {208,1}, {209,1}, {210,1}, {211,1}, {212,1}, {213,1}, // 梅花
                        {301,1}, {302,1}, {303,1}, {304,1}, {305,1}, {306,1}, {307,1}, {308,1}, {309,1}, {310,1}, {311,1}, {312,1}, {313,1}, // 紅心
                        {401,1}, {402,1}, {403,1}, {404,1}, {405,1}, {406,1}, {407,1}, {408,1}, {409,1}, {410,1}, {411,1}, {412,1}, {413,1}},// 黑桃
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
        NiuTonbiResultCtrGameConfig niuTonbiResultCtrGameConfig = new NiuTonbiResultCtrGameConfig(new HashMap<>(this.getNiuTypeRateConfig()));

        // 8. 計算牛型轉換器設定
        NiuStackTmrConfigTbnnJava niuStackTmrConfigTbnnJava = new NiuStackTmrConfigTbnnJava(
                new HashMap<>() {{
                    put(ConstNiu.NiuTypeEnumCommon.INVALID, ConstTbnnJava.NiuTypeEnumTbnnJava.INVALID);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_0, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_0);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_1, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_1);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_2, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_2);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_3, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_3);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_4, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_4);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_5, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_5);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_6, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_6);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_7, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_7);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_8, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_8);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_9, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_9);
                    put(ConstNiu.NiuTypeEnumCommon.NIU_NIU, ConstTbnnJava.NiuTypeEnumTbnnJava.NIU_NIU);
                    put(ConstNiu.NiuTypeEnumCommon.FLOWER_4, ConstTbnnJava.NiuTypeEnumTbnnJava.FLOWER_4);
                    put(ConstNiu.NiuTypeEnumCommon.FLOWER_5, ConstTbnnJava.NiuTypeEnumTbnnJava.FLOWER_5);
                    put(ConstNiu.NiuTypeEnumCommon.BOMB, ConstTbnnJava.NiuTypeEnumTbnnJava.BOMB);
                    put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, ConstTbnnJava.NiuTypeEnumTbnnJava.SMALL_NIU);
                }}
        );

        // 9. 搶莊類機器人邏輯設定
        RobotLogicConfigTonbi robotLogicConfigTonbi = new RobotLogicConfigTonbi(
                new ArrayList<>(){{
                    add(new ArrayList<>(){{add(100);}});
                    add(new ArrayList<>(){{add(50);add(50);}});
                    add(new ArrayList<>(){{add(25);add(25);add(50);}});
                    add(new ArrayList<>(){{add(15);add(15);add(30);add(40);}});
                    add(new ArrayList<>(){{add(12);add(12);add(24);add(26);add(26);}});
                }},
                ConstQznnJava.TimeEnumQznnJava.BET.getMilliTimeSec(),
                ConstQznnJava.TimeEnumQznnJava.SELECT.getMilliTimeSec()
        );

        return new TableGameConfigTbnnJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                tbBetCtrGameConfig,
                cardWallCtrGameConfig,
                niuStackCtrGameConfig,
                niuTonbiResultCtrGameConfig,
                niuStackTmrConfigTbnnJava,
                robotLogicConfigTonbi
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
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_5, 4);
            put(ConstNiu.NiuTypeEnumCommon.BOMB, 4);
            put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, 4);
        }};
    }
}
