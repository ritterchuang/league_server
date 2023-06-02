package org.lsj.gs.math.config.resources.tableGameConfig.games.lhd_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigLhdJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenTimeConfig;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;

import java.util.ArrayList;
import java.util.HashMap;

// 新龍虎鬥預設 牌桌遊戲設定
public class TableGameResourceLhdJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigLhdJava create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 數值設定
        BaiRenMathConfigHlrConfig mathConfigHlrConfig = new BaiRenMathConfigHlrConfig(
                new BaiRenTimeConfig(25.0, 6.0),
                new BaiRenTimeConfig(15.0, 6.0)
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
                    put(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode(), 1000);
                    put(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode(), 1000);
                    put(ConstLhdJava.BetAreaIdLhdJava.TIE.getCode(), 1000);
                }},
                new int[][]{{
                        ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode(),
                        ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()
                }}
        );

        return new TableGameConfigLhdJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                mathConfigHlrConfig,
                betAreaCtrGameConfig,
                cardWallCtrGameConfig
        );
    }
}
