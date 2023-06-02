package org.lsj.gs.mathBoardGtr.games.zjh_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigZjhJava;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableEvent.EmptyTableFinishedEvent;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.ConstMessageQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsBetQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsSelectQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsVieBankerQznnJava;
import org.lsj.gs.math.games.zjh_java.TableZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.message.CtsBetZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.message.CtsSelectZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.message.CtsVieBankerZjhJava;
import org.lsj.gs.math.games.zjh_java.module.state.StateBetZjhJava;
import org.lsj.gs.math.games.zjh_java.module.state.StateSelectZjhJava;
import org.lsj.gs.math.games.zjh_java.module.state.StateVieBankerZjhJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.card.AbstractBoardGtrCard;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;
import java.util.stream.Collectors;

// 抽象炸金花局產生器
public abstract class AbstractBoardGtrCardZjhJava extends AbstractBoardGtrCard {
    protected TableZjhJava table; // 牌桌

    public AbstractBoardGtrCardZjhJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);

        // 2. 產生牌桌
        try {
            this.table = new TableZjhJava(
                    new EmptyTableFinishedEvent(),
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigZjhJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
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
                ConstMessageQznnJava.MessageEnum.CTS_VIE_BANKER.getSub(),
                super.jsonUtil.writeValueAsStringWithoutException(new CtsVieBankerQznnJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanVieRateList(humanChairIndex))))));
    }

    // 機器人搶莊
    protected void robotOnMessageVieBanker(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人搶莊
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateVieBankerZjhJava)this.table.getStateMgr().getStateMap().get(ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode())).onMessageVieBanker(
                    robotChairIndex,
                    new CtsVieBankerZjhJava(this.table.getRobotLogic().getRobotVieBankerRateResultMap().get(robotChairIndex).getRate()));
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
                    ConstMessageQznnJava.MessageEnum.CTS_BET.getSub(),
                    super.jsonUtil.writeValueAsStringWithoutException(new CtsBetQznnJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanBetRateList(humanChairIndex))))));
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
            ((StateBetZjhJava)this.table.getStateMgr().getStateMap().get(ConstQznnJava.StateEnumQznnJava.BET.getCode())).onMessageBet(
                    robotChairIndex,
                    new CtsBetZjhJava(this.table.getRobotLogic().getRobotBetRateResultMap().get(robotChairIndex).getRate()));
        }
    }


    //* 選牌相關 *//
    // 真人選牌
    protected void humanOnMessageSelect(){
        this.table.receivedGameCommand(new ReceiveGameCommand(super.player.getUser().getUid(), ConstMessageQznnJava.MessageEnum.CTS_SELECT.getSub(), super.jsonUtil.writeValueAsStringWithoutException(new CtsSelectQznnJava(new int[]{},1))));
    }

    // 機器人選牌
    protected void robotOnMessageSelect(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人選牌
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateSelectZjhJava)this.table.getStateMgr().getStateMap().get(ConstQznnJava.StateEnumQznnJava.SELECT.getCode())).onMessageSelect(
                    robotChairIndex,
                    new CtsSelectZjhJava(null, ConstQznnJava.SelectTypeEnumQznnJava.SHOWDOWN.getCode()));
        }
    }
}
