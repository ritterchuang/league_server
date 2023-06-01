package org.lsj.gs.mathBoardGtr.core.baiRen;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;
import org.lsj.gs.math.core.common.table.module.tableUtil.TableUtilBaiRen;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.AbstractBoardGtr;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.module.EmptyTableTimer;
import org.lsj.gs.mathStr.core.module.EmptyWebSocketUtil;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.utils.StringUtil;

import java.util.List;

// 百人抽象局產生器工廠
public abstract class AbstractBoardGtrBaiRen extends AbstractBoardGtr implements IBoardGtrBaiRen {
    protected ITableUtilBaiRen tableUtil; // 牌桌工具包

    public AbstractBoardGtrBaiRen(
            GamePlayerSimulation player,
            PoolCtr poolCtr,
            PlayGameFieldConfig playGameFieldConfig,
            ControlAlgorithmConfig controlAlgorithmConfig){
        super(player, poolCtr, playGameFieldConfig.getTableFieldConfig(), controlAlgorithmConfig);
        this.tableUtil = new TableUtilBaiRen(
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

    public ITableUtilBaiRen getTableUtil() {
        return tableUtil;
    }

    //* 共用工具 *//

    // 隨機倍數
    protected int calculateRandomIntElement(List<Integer> rateList){
        return super.calculateRandomIntElement(this.tableUtil.getNotSupportSetRngDataRandomUtil(), rateList);
    }
}
