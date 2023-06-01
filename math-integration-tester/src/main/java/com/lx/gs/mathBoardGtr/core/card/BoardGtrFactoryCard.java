package com.lx.gs.mathBoardGtr.core.card;

import com.lx.enums.GameId;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendCard;
import com.lx.gs.mathBoardGtr.games.ebg_java.BoardGtrCardEbgJavaNormal;
import com.lx.gs.mathBoardGtr.games.lznn_java.BoardGtrCardLznnJavaNormal;
import com.lx.gs.mathBoardGtr.games.qznn_java.BoardGtrCardQznnJavaNormal;
import com.lx.gs.mathBoardGtr.games.qznn_java.BoardGtrCardQznnJavaTimeOut;
import com.lx.gs.mathBoardGtr.games.qznn_k4z_java.BoardGtrCardQznnK4zJavaNormal;
import com.lx.gs.mathBoardGtr.games.qznn_k4z_java.BoardGtrCardQznnK4zJavaTimeOut;
import com.lx.gs.mathBoardGtr.games.qznn_ksz_java.BoardGtrCardQznnKszJavaNormal;
import com.lx.gs.mathBoardGtr.games.qznn_ksz_java.BoardGtrCardQznnKszJavaTimeOut;
import com.lx.gs.mathBoardGtr.games.qzpj_java.BoardGtrCardQzpjJavaNormal;
import com.lx.gs.mathBoardGtr.games.qzpj_java.BoardGtrCardQzpjJavaTimeOut;
import com.lx.gs.mathBoardGtr.games.sg_java.BoardGtrCardSgJavaNormal;
import com.lx.gs.mathBoardGtr.games.tbnn_java.BoardGtrCardTbnnJavaNormal;
import com.lx.gs.mathBoardGtr.games.zjh_java.BoardGtrCardZjhJavaNormal;
import com.lx.gs.mathBoardGtr.games.zjh_java.BoardGtrCardZjhJavaTimeOut;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Objects;

// 卡牌局產生器工廠
public class BoardGtrFactoryCard {

    public IBoardGtrCard create(GamePlayerSimulation gamePlayerSimulation,
                                PoolCtr poolCtr,
                                PlayGameFieldConfig playGameFieldConfig,
                                ControlAlgorithmConfig controlAlgorithmConfig){
        // 1. 取出棋牌額外設定
        GameTypeConfigExtendCard gameTypeConfigExtendCard = (GameTypeConfigExtendCard) playGameFieldConfig.getGameTypeConfigExtend();

        // 2. 依照設定產局產生器
        switch (gameTypeConfigExtendCard.getPlayTypeCard()) {
            case NORMAL: return this.createBoardGtrNormal(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case TIME_OUT: return this.createBoardGtrTimeout(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            default: return new BoardGtrCardDefault(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }

    // 生成一般模式局產生器
    private IBoardGtrCard createBoardGtrNormal(GamePlayerSimulation player,
                                               PoolCtr poolCtr,
                                               PlayGameFieldConfig playGameFieldConfig,
                                               ControlAlgorithmConfig controlAlgorithmConfig){
        switch (Objects.requireNonNull(GameId.fromId(playGameFieldConfig.getTableFieldConfig().getGameId()))) {
            case ZJH_JAVA: return new BoardGtrCardZjhJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZNN_JAVA: return new BoardGtrCardQznnJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case EBG_JAVA: return new BoardGtrCardEbgJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZNN_KSZ_JAVA: return new BoardGtrCardQznnKszJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case SG_JAVA: return new BoardGtrCardSgJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZPJ_JAVA: return new BoardGtrCardQzpjJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case TBNN_JAVA: return new BoardGtrCardTbnnJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZNN_K4Z_JAVA: return new BoardGtrCardQznnK4zJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case LZNN_JAVA: return new BoardGtrCardLznnJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            default: return new BoardGtrCardDefault(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }

    // 生成超時模式局產生器
    private IBoardGtrCard createBoardGtrTimeout(GamePlayerSimulation player,
                                                PoolCtr poolCtr,
                                                PlayGameFieldConfig playGameFieldConfig,
                                                ControlAlgorithmConfig controlAlgorithmConfig){
        switch (Objects.requireNonNull(GameId.fromId(playGameFieldConfig.getTableFieldConfig().getGameId()))) {
            case ZJH_JAVA: return new BoardGtrCardZjhJavaTimeOut(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZNN_JAVA: return new BoardGtrCardQznnJavaTimeOut(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZNN_KSZ_JAVA: return new BoardGtrCardQznnKszJavaTimeOut(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZPJ_JAVA: return new BoardGtrCardQzpjJavaTimeOut(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case QZNN_K4Z_JAVA: return new BoardGtrCardQznnK4zJavaTimeOut(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            default: return new BoardGtrCardDefault(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }
}
