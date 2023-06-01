package org.lsj.gs.mathBoardGtr.config.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.mathBoardGtr.config.entity.BoardGtrConfig;
import org.lsj.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.config.entity.GamePlayerConfig;
import org.lsj.gs.mathStr.config.entity.PoolCtrConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.user.UserSimulationBdr;
import org.lsj.gs.pool.AgencyPool;

// 局產生器設定工廠
public class BoardGtrConfigFactory {
    public BoardGtrConfig create(
            ConstPlayGameField.PlayGameFieldResource playGameFieldResource,
            ConstBoardGtr.BoardGtrControlAlgorithmType boardGtrControlAlgorithmType){
        return new BoardGtrConfig(
                this.createGamePlayer(),
                this.createPoolCtrConfig(),
                new PlayGameFieldConfigReader().getConfig(playGameFieldResource),
                this.createControlAlgorithmConfig(boardGtrControlAlgorithmType)
        );
    }

    private GamePlayerSimulation createGamePlayer(){
        return new GamePlayerSimulation(
                new GamePlayerConfig(
                        2000000,
                        20000000,
                        100),
                new UserSimulationBdr()
                        .setUid(1)
                        .setBalance(2000000)
                        .setAccount("user1")
                        .setHeadImgUrl("0")
                        .setIp("127.0.0.1")
                        .setNickName("user1")
                        .setRole(0)
                        .setBoxid(0)
                        .setSex(0)
                        .setState(1)
                        .setVipLevel(0)
                        .createUser());
    }

    private PoolCtrConfig createPoolCtrConfig(){
        return new PoolCtrConfig(
                new AgencyPool(
                        1000000000,
                        30000000,
                        0.025,
                        0,
                        0,
                        0,
                        true
                ),
                null);
    }

    private ControlAlgorithmConfig createControlAlgorithmConfig(ConstBoardGtr.BoardGtrControlAlgorithmType boardGtrControlAlgorithmType) {
        switch(boardGtrControlAlgorithmType){
            case NATURE: return new ControlAlgorithmConfig(new ControlAlgorithmUtil(
                    true,
                    ConstMathCommon.PoolControlType.NONE,
                    ConstMathCommon.DealType.NONE,
                    ConstMathCommon.ShuffleType.NATURE,
                    ConstMathCommon.PersonAdjustType.NORMAL
            ));
            case GRADIENT_NATURE: return new ControlAlgorithmConfig(new ControlAlgorithmUtil(
                    true,
                    ConstMathCommon.PoolControlType.NONE,
                    ConstMathCommon.DealType.GRADIENT,
                    ConstMathCommon.ShuffleType.NATURE,
                    ConstMathCommon.PersonAdjustType.NORMAL
            ));
            default: return new ControlAlgorithmConfig(new ControlAlgorithmUtil());
        }
    }
}
