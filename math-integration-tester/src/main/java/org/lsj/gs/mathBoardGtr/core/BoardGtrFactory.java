package org.lsj.gs.mathBoardGtr.core;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.baiRen.BoardGtrFactoryBaiRen;
import org.lsj.gs.mathBoardGtr.core.baiRen.IBoardGtrBaiRen;
import org.lsj.gs.mathBoardGtr.core.card.BoardGtrFactoryCard;
import org.lsj.gs.mathBoardGtr.core.card.IBoardGtrCard;
import org.lsj.gs.mathBoardGtr.core.fish.BoardGtrFishFactory;
import org.lsj.gs.mathBoardGtr.core.fish.IBoardGtrFish;
import org.lsj.gs.mathBoardGtr.core.slot.BoardGtrFactorySlot;
import org.lsj.gs.mathBoardGtr.core.slot.IBoardGtrSlot;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

// 局產生器工廠
public class BoardGtrFactory {
    private final GamePlayerSimulation gamePlayerSimulation; // 模擬遊戲玩家
    private final PoolCtr poolCtr; // 水池計算器
    private final PlayGameFieldConfig playGameFieldConfig; // 遊戲房間設定
    private final ControlAlgorithmConfig controlAlgorithmConfig; // 除錯設定

    public BoardGtrFactory(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        this.gamePlayerSimulation = gamePlayerSimulation;
        this.poolCtr = poolCtr;
        this.playGameFieldConfig = playGameFieldConfig;
        this.controlAlgorithmConfig = controlAlgorithmConfig;
    }

    //* 卡牌局生產器 *//
    // 卡牌生成局產生器
    public IBoardGtrCard createBoardGtrCard(){
        return new BoardGtrFactoryCard().create(
                this.gamePlayerSimulation,
                this.poolCtr,
                this.playGameFieldConfig,
                this.controlAlgorithmConfig
        );
    }

    //* 百人局生產器 *//
    // 百人生成局產生器
    public IBoardGtrBaiRen createBoardGtrBaiRen(){
        return new BoardGtrFactoryBaiRen().create(
                this.gamePlayerSimulation,
                this.poolCtr,
                this.playGameFieldConfig,
                this.controlAlgorithmConfig
        );
    }

    //* 魚機局生產器 *//
    // 魚機生成局產生器
    public IBoardGtrFish createBoardGtrFish() {
        return new BoardGtrFishFactory().create(
                this.gamePlayerSimulation,
                this.poolCtr,
                this.playGameFieldConfig,
                this.controlAlgorithmConfig
        );
    }

    //* 老虎機局生產器 *//
    // 老虎機生成局產生器
    public IBoardGtrSlot createBoardGtrSlot() {
        return new BoardGtrFactorySlot().create(
                this.gamePlayerSimulation,
                this.poolCtr,
                this.playGameFieldConfig,
                this.controlAlgorithmConfig
        );
    }
}
