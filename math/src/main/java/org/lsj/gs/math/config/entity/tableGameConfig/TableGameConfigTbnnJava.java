package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.card.betCtr.tbBetCtr.TbBetCtrGameConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuTonbiResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigTonbi;
import org.lsj.gs.math.games.tbnn_java.module.niu.NiuStackTmrConfigTbnnJava;

// 新通比牛牛牌桌遊戲設定
public class TableGameConfigTbnnJava extends AbstractTableGameConfig {
    private final TbBetCtrGameConfig tbBetCtrGameConfig; // 下注計算器遊戲設定
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final NiuStackCtrGameConfig niuStackCtrGameConfig; // 牛型計算器遊戲設定
    private final NiuStackTmrConfigTbnnJava niuStackTmrConfigTbnnJava; // 牛型轉換器設定
    private final NiuTonbiResultCtrGameConfig niuTonbiResultCtrGameConfig; // 牛牛結果計算器遊戲設定

    private final RobotLogicConfigTonbi robotLogicConfigTonbi; // 搶莊類機器人邏輯設定

    public TableGameConfigTbnnJava(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            TbBetCtrGameConfig tbBetCtrGameConfig,
            CardWallCtrGameConfig cardWallCtrGameConfig,
            NiuStackCtrGameConfig niuStackCtrGameConfig,
            NiuTonbiResultCtrGameConfig niuTonbiResultCtrGameConfig,
            NiuStackTmrConfigTbnnJava niuStackTmrConfigTbnnJava,
            RobotLogicConfigTonbi robotLogicConfigTonbi) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.tbBetCtrGameConfig = tbBetCtrGameConfig;
        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.niuStackCtrGameConfig = niuStackCtrGameConfig;
        this.niuTonbiResultCtrGameConfig = niuTonbiResultCtrGameConfig;
        this.niuStackTmrConfigTbnnJava = niuStackTmrConfigTbnnJava;
        this.robotLogicConfigTonbi = robotLogicConfigTonbi;
    }

    public TbBetCtrGameConfig getTbBetCtrGameConfig() {
        return tbBetCtrGameConfig;
    }

    public CardWallCtrGameConfig getCardWallCtrGameConfig() {
        return cardWallCtrGameConfig;
    }

    public NiuStackCtrGameConfig getNiuStackCtrGameConfig() {
        return niuStackCtrGameConfig;
    }

    public NiuStackTmrConfigTbnnJava getNiuStackTmrConfigTbnnJava() {
        return niuStackTmrConfigTbnnJava;
    }

    public NiuTonbiResultCtrGameConfig getNiuTonbiResultCtrGameConfig() {
        return niuTonbiResultCtrGameConfig;
    }

    public RobotLogicConfigTonbi getRobotLogicConfigTonbi() {
        return robotLogicConfigTonbi;
    }
}
