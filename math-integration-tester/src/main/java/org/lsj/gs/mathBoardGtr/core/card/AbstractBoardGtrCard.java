package org.lsj.gs.mathBoardGtr.core.card;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilCard;
import org.lsj.gs.math.core.common.table.module.tableUtil.TableUtilCard;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.AbstractBoardGtr;
import org.lsj.gs.mathBoardGtr.core.IBoardGtr;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.module.EmptyTableTimer;
import org.lsj.gs.mathStr.core.module.EmptyWebSocketUtil;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.utils.StringUtil;

import java.util.List;

// 棋牌抽象局產生器工廠
public abstract class AbstractBoardGtrCard extends AbstractBoardGtr implements IBoardGtr, IBoardGtrCard {
    protected ITableUtilCard tableUtil; // 牌桌工具包

    public AbstractBoardGtrCard(
            GamePlayerSimulation player,
            PoolCtr poolCtr,
            PlayGameFieldConfig playGameFieldConfig,
            ControlAlgorithmConfig controlAlgorithmConfig){
        super(player, poolCtr, playGameFieldConfig.getTableFieldConfig(), controlAlgorithmConfig);
        this.tableUtil = new TableUtilCard(
                new EmptyWebSocketUtil(),
                new MathRandomUtil(),
                new EmptyTableTimer(),
                new ControlAlgorithmUtil(
                        controlAlgorithmConfig.getControlAlgorithmUtil().isControlFlag(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlPoolControlType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlDealType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlShuffleType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlPersonAdjustType()),
                StringUtil.getInstance().getEmptyString());
    }


    //* 共用工具 *//

    // 隨機倍數
    protected int calculateRandomIntElement(List<Integer> rateList){
        return super.calculateRandomIntElement(this.tableUtil.getNotSupportSetRngDataRandomUtil(), rateList);
    }
}
