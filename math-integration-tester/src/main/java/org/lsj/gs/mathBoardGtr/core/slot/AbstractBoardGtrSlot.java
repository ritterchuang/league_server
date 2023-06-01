package org.lsj.gs.mathBoardGtr.core.slot;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.table.TableCommandSlot;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.common.table.module.tableUtil.TableUtilSlot;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.AbstractBoardGtr;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.module.EmptyWebSocketUtil;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

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
