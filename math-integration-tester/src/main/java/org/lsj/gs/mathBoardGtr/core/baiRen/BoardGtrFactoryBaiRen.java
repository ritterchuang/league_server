package org.lsj.gs.mathBoardGtr.core.baiRen;

import org.lsj.enums.GameId;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendBaiRen;
import org.lsj.gs.mathBoardGtr.games.bjl_java.BoardGtrBaiRenBjlJavaNormal;
import org.lsj.gs.mathBoardGtr.games.brnn_java.BoardGtrBaiRenBrnnJavaNormal;
import org.lsj.gs.mathBoardGtr.games.cjnn_java.BoardGtrBaiRenCjnnJavaNormal;
import org.lsj.gs.mathBoardGtr.games.hhdz_java.BoardGtrBaiRenHhdzJavaNormal;
import org.lsj.gs.mathBoardGtr.games.lhd_java.BoardGtrBaiRenLhdJavaNormal;
import org.lsj.gs.mathBoardGtr.games.mybjl_java.BoardGtrBaiRenMybjlJavaNormal;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Objects;

// 百人局產生器工廠
public class BoardGtrFactoryBaiRen {

    public IBoardGtrBaiRen create(GamePlayerSimulation gamePlayerSimulation,
                                PoolCtr poolCtr,
                                PlayGameFieldConfig playGameFieldConfig,
                                ControlAlgorithmConfig controlAlgorithmConfig){
        // 1. 取出棋牌額外設定
        GameTypeConfigExtendBaiRen gameTypeConfigExtendBaiRen = (GameTypeConfigExtendBaiRen) playGameFieldConfig.getGameTypeConfigExtend();

        // 2. 依照設定產局產生器
        switch (gameTypeConfigExtendBaiRen.getPlayTypeBaiRen()) {
            case NORMAL: return this.createBoardGtrNormal(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            default: return new BoardGtrBaiRenDefault(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }

    // 生成一般模式局產生器
    private IBoardGtrBaiRen createBoardGtrNormal(GamePlayerSimulation player,
                                               PoolCtr poolCtr,
                                               PlayGameFieldConfig playGameFieldConfig,
                                               ControlAlgorithmConfig controlAlgorithmConfig){
        switch (Objects.requireNonNull(GameId.fromId(playGameFieldConfig.getTableFieldConfig().getGameId()))) {
            case HHDZ_JAVA: return new BoardGtrBaiRenHhdzJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case BJL_JAVA: return new BoardGtrBaiRenBjlJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case BRNN_JAVA: return new BoardGtrBaiRenBrnnJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case LHD_JAVA: return new BoardGtrBaiRenLhdJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case MYBJL_JAVA: return new BoardGtrBaiRenMybjlJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case CJNN_JAVA: return new BoardGtrBaiRenCjnnJavaNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            default: return new BoardGtrBaiRenDefault(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }
}
