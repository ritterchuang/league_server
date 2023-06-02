package org.lsj.gs.mathBoardGtr.games.qznn_k4z_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQznnK4zJava;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableEvent.EmptyTableFinishedEvent;
import org.lsj.gs.math.games.qznn_k4z_java.TableQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.ConstMessageQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.CtsBetQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.CtsSelectQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.CtsVieBankerQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.state.StateBetQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.state.StateSelectQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.state.StateVieBankerQznnK4zJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.card.AbstractBoardGtrCard;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;
import java.util.stream.Collectors;

// 抽象看四張搶莊牛牛局產生器
public abstract class AbstractBoardGtrCardQznnK4zJava extends AbstractBoardGtrCard {
    protected TableQznnK4zJava table; // 牌桌

    public AbstractBoardGtrCardQznnK4zJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);

        // 2. 產生牌桌
        try {
            this.table = new TableQznnK4zJava(
                    new EmptyTableFinishedEvent(),
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigQznnK4zJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
                    super.poolCtr.createPoolConfig(),
                    player.getUser(),
                    super.tableUtil);
        } catch (TableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract BoardGtrResult calculateOneBoardResult();

    //* 搶莊相關 *//
    // 真人搶莊
    protected void humanOnMessageVieBanker(){
        // 1. 計算玩家座位
        int humanChairIndex = this.table.getGamePlayerHlr().getHumanChairIndex();

        // 2. 真人搶莊
        this.table.receivedGameCommand(new ReceiveGameCommand(
                super.player.getUser().getUid(),
                ConstMessageQznnK4zJava.MessageEnum.CTS_VIE_BANKER.getSub(),
                super.jsonUtil.writeValueAsStringWithoutException(new CtsVieBankerQznnK4zJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanVieRateList(humanChairIndex))))));
    }

    // 機器人搶莊
    protected void robotOnMessageVieBanker(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人搶莊
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateVieBankerQznnK4zJava)this.table.getStateMgr().getStateMap().get(ConstQznnK4zJava.StateEnumQznnK4zJava.VIE_BANKER.getCode())).onMessageVieBanker(
                    robotChairIndex,
                    new CtsVieBankerQznnK4zJava(this.table.getRobotLogic().getRobotVieBankerRateResultMap().get(robotChairIndex).getRate()));
        }
    }


    //* 下注相關 *//
    // 真人下注
    protected void humanOnMessageBet(){
        // 1. 取得莊家座位
        int bankChairIndex = this.table.getLogic().getBankerChairIndex();

        // 2. 計算玩家座位
        int humanChairIndex = this.table.getGamePlayerHlr().getHumanChairIndex();

        // 3. 真人下注
        if(humanChairIndex != bankChairIndex) {
            this.table.receivedGameCommand(new ReceiveGameCommand(
                    super.player.getUser().getUid(),
                    ConstMessageQznnK4zJava.MessageEnum.CTS_BET.getSub(),
                    super.jsonUtil.writeValueAsStringWithoutException(new CtsBetQznnK4zJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanBetRateList(humanChairIndex))))));
        }
    }

    // 機器人下注
    protected void robotOnMessageBet(){
        // 1. 取得莊家座位
        int bankChairIndex = this.table.getLogic().getBankerChairIndex();

        // 2. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 3. 機器人下注
        for (Integer robotChairIndex: robotChairIndexList.stream().filter(chairIndex -> chairIndex != bankChairIndex).collect(Collectors.toList())) {
            ((StateBetQznnK4zJava)this.table.getStateMgr().getStateMap().get(ConstQznnK4zJava.StateEnumQznnK4zJava.BET.getCode())).onMessageBet(
                    robotChairIndex,
                    new CtsBetQznnK4zJava(this.table.getRobotLogic().getRobotBetRateResultMap().get(robotChairIndex).getRate()));
        }
    }


    //* 選牌相關 *//
    // 真人選牌
    protected void humanOnMessageSelect(){
        this.table.receivedGameCommand(new ReceiveGameCommand(super.player.getUser().getUid(), ConstMessageQznnK4zJava.MessageEnum.CTS_SELECT.getSub(), super.jsonUtil.writeValueAsStringWithoutException(new CtsSelectQznnK4zJava(new int[]{}))));
    }

    // 機器人選牌
    protected void robotOnMessageSelect(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人選牌
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateSelectQznnK4zJava)this.table.getStateMgr().getStateMap().get(ConstQznnK4zJava.StateEnumQznnK4zJava.SELECT.getCode())).onMessageSelect(
                    robotChairIndex,
                    new CtsSelectQznnK4zJava(new int[]{})
            );
        }
    }
}
