package org.lsj.gs.math.config.resources.tableGameConfig.games.ebg_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigEbgJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfigBetType02;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfigMaxRate;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 新搶莊二八槓預設 牌桌遊戲設定
public class TableGameResourceEbgJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigEbgJava create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 計算搶莊遊戲設定
        VieBankerCtrGameConfig vieBankerCtrGameConfig = new VieBankerCtrGameConfigMaxRate(
                ConstMathCard.BankerType.MAX_RATE,
                3,
                200,
                3
        );

        // 4. 下注計算器遊戲設定
        QzBetCtrGameConfig qzBetCtrGameConfig = new QzBetCtrGameConfigBetType02(ConstMathCard.QzBetType.BET_02, 3, 4);

        // 5. 牌牆計算器遊戲設定 TODO 依照 point、type、決定 weight
        CardWallCtrGameConfig cardWallCtrGameConfig = new CardWallCtrGameConfig(
                ConstMathCard.CardType.MAHJONG,

                new int[][]{
                        {11,0},{12,0},{13,0},{14,0},{15,0},{16,0},{17,0},{18,0},{19,0},    // 萬
                        {21,0},{22,0},{23,0},{24,0},{25,0},{26,0},{27,0},{28,0},{29,0},    // 條
                        {31,4},{32,4},{33,4},{34,4},{35,4},{36,4},{37,4},{38,4},{39,4},    // 筒
                        {41,0},{42,0},{43,0},{44,0},                                       // 東南西北
                        {45,0},{46,0},{47,4},                                              // 中發白
                        {51,0},{52,0},{53,0},{54,0},{55,0},{56,0},{57,0},{58,0}            // 春夏秋冬梅蘭竹菊
                },

                new HashMap<>(){{}}
        );

        // 6. 搶莊類機器人邏輯設定 TODO
        RobotLogicConfigBanker robotLogicConfigBanker = new RobotLogicConfigBanker(
                new ArrayList<>(){{
                    add(List.of(100));
                    add(List.of(50, 50));
                    add(List.of(50, 20, 30));
                    add(List.of(20, 20, 20, 40));
                    add(List.of(20, 15, 20, 25, 20));
                }},
                new ArrayList<>(){{
                    add(List.of(100));
                    add(List.of(50, 50));
                    add(List.of(25, 25, 50));
                    add(List.of(15, 15, 30, 40));
                    add(List.of(12, 12, 24, 26, 26));
                }},
                ConstQznnJava.TimeEnumQznnJava.VIE_BANKER.getMilliTimeSec(),
                ConstQznnJava.TimeEnumQznnJava.BET.getMilliTimeSec(),
                ConstQznnJava.TimeEnumQznnJava.SELECT.getMilliTimeSec()
        );

        return new TableGameConfigEbgJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                vieBankerCtrGameConfig,
                qzBetCtrGameConfig,
                cardWallCtrGameConfig,
                robotLogicConfigBanker
        );
    }
}
