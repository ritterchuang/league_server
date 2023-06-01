package com.lx.gs.mathBoardGtr.core.baiRen;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;
import com.lx.gs.math.core.common.table.module.tableUtil.TableUtilBaiRen;
import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.core.AbstractBoardGtr;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.module.EmptyTableTimer;
import com.lx.gs.mathStr.core.module.EmptyWebSocketUtil;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;
import com.lx.utils.StringUtil;

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
