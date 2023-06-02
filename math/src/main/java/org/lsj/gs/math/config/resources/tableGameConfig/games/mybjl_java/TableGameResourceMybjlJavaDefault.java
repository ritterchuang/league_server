package org.lsj.gs.math.config.resources.tableGameConfig.games.mybjl_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigMybjlJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenTimeConfig;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.bjlResultCtr.BjlBaiRenResultCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

import java.util.ArrayList;
import java.util.HashMap;

// 新免傭百家樂預設 牌桌遊戲設定
public class TableGameResourceMybjlJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigMybjlJava create() {
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
                        {101,8}, {102,8}, {103,8}, {104,8}, {105,8}, {106,8}, {107,8}, {108,8}, {109,8}, {110,8}, {111,8}, {112,8}, {113,8}, // 方塊
                        {201,8}, {202,8}, {203,8}, {204,8}, {205,8}, {206,8}, {207,8}, {208,8}, {209,8}, {210,8}, {211,8}, {212,8}, {213,8}, // 梅花
                        {301,8}, {302,8}, {303,8}, {304,8}, {305,8}, {306,8}, {307,8}, {308,8}, {309,8}, {310,8}, {311,8}, {312,8}, {313,8}, // 紅心
                        {401,8}, {402,8}, {403,8}, {404,8}, {405,8}, {406,8}, {407,8}, {408,8}, {409,8}, {410,8}, {411,8}, {412,8}, {413,8}},// 黑桃
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
                    put(ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode() , 1000);
                    put(ConstMathBjl.BetAreaEnum.PLAY.getCode()        , 1000);
                    put(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode()   , 1000);
                    put(ConstMathBjl.BetAreaEnum.SMALL.getCode()       , 1000);

                    put(ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode()  , 1000);
                    put(ConstMathBjl.BetAreaEnum.BANK.getCode()        , 1000);
                    put(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode()   , 1000);
                    put(ConstMathBjl.BetAreaEnum.BIG.getCode()         , 1000);

                    put(ConstMathBjl.BetAreaEnum.TIE.getCode()         , 1000);
                }},
                new int[][]{{
                        ConstMathBjl.BetAreaEnum.PLAY.getCode(),
                        ConstMathBjl.BetAreaEnum.BANK.getCode()
                }}
        );

        // 6. 免傭百家樂百人結果計算器遊戲設定
        BjlBaiRenResultCtrGameConfig bjlBaiRenResultCtrGameConfig = new BjlBaiRenResultCtrGameConfig(
            new HashMap<>(){{
                // 闲家
                put(ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode() , 0.95);   // 上庄输
                put(ConstMathBjl.BetAreaEnum.PLAY.getCode()        , 1.0);    // 闲
                put(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode()   , 11.0);   // 闲对
                put(ConstMathBjl.BetAreaEnum.SMALL.getCode()       , 1.5);    // 小
                // 庄家
                put(ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode()  , 0.95);   // 上庄赢
                put(ConstMathBjl.BetAreaEnum.BANK.getCode()        , 1.0);   // 庄
                put(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode()   , 11.0);   // 庄对
                put(ConstMathBjl.BetAreaEnum.BIG.getCode()         , 0.54);   // 大
                // 和
                put(ConstMathBjl.BetAreaEnum.TIE.getCode()         , 8.0);    // 和
            }}
        );

        // 7. 封裝
        return new TableGameConfigMybjlJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                mathConfigHlrConfig,
                cardWallCtrGameConfig, betAreaCtrGameConfig,
                bjlBaiRenResultCtrGameConfig
        );
    }
}
