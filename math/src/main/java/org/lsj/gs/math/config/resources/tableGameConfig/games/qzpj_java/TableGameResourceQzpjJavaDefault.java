package org.lsj.gs.math.config.resources.tableGameConfig.games.qzpj_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQzpjJava;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfigBetType01;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfigRateList;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// 新搶莊牌九預設 牌桌遊戲設定
public class TableGameResourceQzpjJavaDefault extends AbstractTableGameResourceCard {
    public TableGameConfigQzpjJava create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        // 3. 計算搶莊遊戲設定
        VieBankerCtrGameConfig vieBankerCtrGameConfig = new VieBankerCtrGameConfigRateList(
                ConstMathCard.BankerType.RATE_LIST,
                Arrays.stream((new int[]{0, 1, 2, 3, 4})).boxed().collect(Collectors.toList()),
                Arrays.stream((new int[]{0, 30, 60, 90, 120})).boxed().collect(Collectors.toList())
        );

        // 4. 下注計算器遊戲設定
        QzBetCtrGameConfig qzBetCtrGameConfig = new QzBetCtrGameConfigBetType01(ConstMathCard.QzBetType.BET_01, 30, 1, 1, 4);

        // 5. 牌牆計算器遊戲設定 TODO 依照 point、type、決定 weight
        CardWallCtrGameConfig cardWallCtrGameConfig = new CardWallCtrGameConfig(
                ConstMathCard.CardType.PAIGOW,

                new int[][]{
                        {606,2},{101,2},{404,2},{103,2},{505,2},{303,2},{202,2},{506,2},{406,2},
                        {106,2},{105,2},{306,1},{405,1},{305,1},{206,1},{304,1},{205,1},{302,1},
                        {104,1},{204,1},{102,1}},

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

        return new TableGameConfigQzpjJava(
                gameAdjustConfig,
                rngAlgorithmConfig,
                vieBankerCtrGameConfig,
                qzBetCtrGameConfig,
                cardWallCtrGameConfig,
                robotLogicConfigBanker
        );
    }
}
