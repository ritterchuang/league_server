package com.lx.gs.mathBoardGtr.core.slot;

import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import com.lx.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import com.lx.gs.math.core.common.table.TableCommandSlot;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import com.lx.gs.math.core.common.table.module.tableUtil.TableUtilSlot;
import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.core.AbstractBoardGtr;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.module.EmptyWebSocketUtil;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 老虎機局產生器工廠
public abstract class AbstractBoardGtrSlot extends AbstractBoardGtr implements IBoardGtrSlot {
    protected ITableUtilSlot tableUtil; // 牌桌工具包
    protected TableCommandSlot table; // 牌桌

    public AbstractBoardGtrSlot(
            GamePlayerSimulation player,
            PoolCtr poolCtr,
            PlayGameFieldConfig playGameFieldConfig,
            ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig.getTableFieldConfig(), controlAlgorithmConfig);
        // 1. 建構牌桌工具包
        this.tableUtil = new TableUtilSlot(
                new EmptyWebSocketUtil(),
                new MathRandomUtil(),
                new ControlAlgorithmUtil(
                        controlAlgorithmConfig.getControlAlgorithmUtil().isControlFlag(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlPoolControlType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlDealType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlShuffleType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlPersonAdjustType()));

        // 2. 建構遊戲桌
        try {
            this.table = new TableCommandSlot(
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigSlot) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
                    super.poolCtr.createPoolConfig(),
                    player.getUser(),
                    this.tableUtil);
        } catch (TableException e) {
            e.printStackTrace();
        }
    }
}
