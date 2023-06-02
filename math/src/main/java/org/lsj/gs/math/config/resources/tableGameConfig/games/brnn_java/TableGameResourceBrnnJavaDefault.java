package org.lsj.gs.math.config.resources.tableGameConfig.games.brnn_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigBrnnJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenTimeConfig;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBaiRenResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr.NiuBaiRenStackCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.games.brnn_java.entity.ConstBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.niu.NiuStackTmrConfigBrnnJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 新百人牛牛預設 牌桌遊戲設定
public class TableGameResourceBrnnJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigBrnnJava create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 數值設定
        BaiRenMathConfigHlrConfig mathConfigHlrConfig = new BaiRenMathConfigHlrConfig(
                new BaiRenTimeConfig(25.0, 12.0),
                new BaiRenTimeConfig(15.0, 12.0)
        );

        // 4. 牌牆計算器遊戲設定
        CardWallCtrGameConfig cardWallCtrGameConfig = new CardWallCtrGameConfig(
                ConstMathCard.CardType.POKER,
                new int[][]{
                        {101,1}, {102,1}, {103,1}, {104,1}, {105,1}, {106,1}, {107,1}, {108,1}, {109,1}, {110,1}, {111,1}, {112,1}, {113,1}, // 方塊
                        {201,1}, {202,1}, {203,1}, {204,1}, {205,1}, {206,1}, {207,1}, {208,1}, {209,1}, {210,1}, {211,1}, {212,1}, {213,1}, // 梅花
                        {301,1}, {302,1}, {303,1}, {304,1}, {305,1}, {306,1}, {307,1}, {308,1}, {309,1}, {310,1}, {311,1}, {312,1}, {313,1}, // 紅心
                        {401,1}, {402,1}, {403,1}, {404,1}, {405,1}, {406,1}, {407,1}, {408,1}, {409,1}, {410,1}, {411,1}, {412,1}, {413,1}},// 黑桃
                new HashMap<>(){{
                    put(1,1);
                    put(2,2);
                    put(3,3);
                    put(4,4);
                    put(5,5);
                    put(6,6);
                    put(7,7);
                    put(8,8);
                    put(9,9);
                    put(10,10);
                    put(11,11);
                    put(12,12);
                    put(13,13);
                }}
        );

        // 5. 押注區域計算器遊戲設定
        BetAreaCtrGameConfig betAreaCtrGameConfig = new BetAreaCtrGameConfig(
                new ArrayList<>(){{
                    add(1);
                    add(2);
                    add(10);
                    add(20);
                    add(100);
                    add(200);
                }},
                new HashMap<>(){{
                    put(ConstMathNiu.BetAreaEnum.TIAN.getCode(), 1000);
                    put(ConstMathNiu.BetAreaEnum.DI.getCode(), 1000);
                    put(ConstMathNiu.BetAreaEnum.XUAN.getCode(), 1000);
                    put(ConstMathNiu.BetAreaEnum.HUANG.getCode(), 1000);
                }},
                new int[][]{}
        );

        // 6. 牛型計算器遊戲設定
        NiuBaiRenStackCtrGameConfig niuBaiRenStackCtrGameConfig = new NiuBaiRenStackCtrGameConfig(this.getNiuTypeRateConfig());

        // 7. 牛牛結果計算器設定
        NiuBaiRenResultCtrGameConfig niuBaiRenResultCtrGameConfig = new NiuBaiRenResultCtrGameConfig(this.getNiuTypeRateConfig());

        // 8. 計算牛型轉換器設定
        NiuStackTmrConfigBrnnJava niuStackTmrConfigBrnnJava = new NiuStackTmrConfigBrnnJava(this.getNiuStackTmrConfig());

        // 7. 封裝
        return new TableGameConfigBrnnJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                mathConfigHlrConfig,
                cardWallCtrGameConfig,
                betAreaCtrGameConfig,
                niuBaiRenStackCtrGameConfig,
                niuStackTmrConfigBrnnJava,
                niuBaiRenResultCtrGameConfig
        );
    }

    // 牛型賠率設定 <牛型識別碼, 牛型倍數>
    private Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeRateConfig(){
        return new HashMap<>(){{
            put(ConstNiu.NiuTypeEnumCommon.INVALID, 0);
            put(ConstNiu.NiuTypeEnumCommon.NIU_0        , 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_1        , 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_2        , 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_3        , 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_4        , 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_5        , 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_6        , 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_7        , 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_8        , 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_9        , 3);
            put(ConstNiu.NiuTypeEnumCommon.NIU_NIU      , 4);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_4     , 4);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_5     , 4);
            put(ConstNiu.NiuTypeEnumCommon.BOMB         , 4);
            put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU    , 4);
        }};
    }

    // 牛型轉換器設定
    private Map<ConstNiu.NiuTypeEnumCommon, ConstBrnnJava.NiuTypeEnumBrnnJava> getNiuStackTmrConfig() {
        return new HashMap<>(){
            {
                put(ConstNiu.NiuTypeEnumCommon.INVALID,     ConstBrnnJava.NiuTypeEnumBrnnJava.INVALID);
                put(ConstNiu.NiuTypeEnumCommon.NIU_0,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_0);
                put(ConstNiu.NiuTypeEnumCommon.NIU_1,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_1);
                put(ConstNiu.NiuTypeEnumCommon.NIU_2,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_2);
                put(ConstNiu.NiuTypeEnumCommon.NIU_3,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_3);
                put(ConstNiu.NiuTypeEnumCommon.NIU_4,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_4);
                put(ConstNiu.NiuTypeEnumCommon.NIU_5,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_5);
                put(ConstNiu.NiuTypeEnumCommon.NIU_6,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_6);
                put(ConstNiu.NiuTypeEnumCommon.NIU_7,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_7);
                put(ConstNiu.NiuTypeEnumCommon.NIU_8,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_8);
                put(ConstNiu.NiuTypeEnumCommon.NIU_9,       ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_9);
                put(ConstNiu.NiuTypeEnumCommon.NIU_NIU,     ConstBrnnJava.NiuTypeEnumBrnnJava.NIU_NIU);
                put(ConstNiu.NiuTypeEnumCommon.FLOWER_4,    ConstBrnnJava.NiuTypeEnumBrnnJava.FLOWER_4);
                put(ConstNiu.NiuTypeEnumCommon.FLOWER_5,    ConstBrnnJava.NiuTypeEnumBrnnJava.FLOWER_5);
                put(ConstNiu.NiuTypeEnumCommon.BOMB,        ConstBrnnJava.NiuTypeEnumBrnnJava.BOMB);
                put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU,   ConstBrnnJava.NiuTypeEnumBrnnJava.SMALL_NIU);
            }
        };
    }
}
