package org.lsj.gs.mathBoardGtr.games.tbnn_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigTbnnJava;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableEvent.EmptyTableFinishedEvent;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsSelectQznnJava;
import org.lsj.gs.math.games.tbnn_java.TableTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.ConstMessageTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.CtsBetTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.CtsSelectTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.state.StateBetTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.state.StateSelectTbnnJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.card.AbstractBoardGtrCard;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;
import java.util.stream.Collectors;

// 抽象通比牛牛局產生器
public abstract class AbstractBoardGtrCardTbnnJava extends AbstractBoardGtrCard {
    protected TableTbnnJava table; // 牌桌

    public AbstractBoardGtrCardTbnnJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);

        // 2. 產生牌桌
        try {
            this.table = new TableTbnnJava(
                    new EmptyTableFinishedEvent(),
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigTbnnJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
                    super.poolCtr.createPoolConfig(),
                    player.getUser(),
                    super.tableUtil);
        } catch (TableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract BoardGtrResult calculateOneBoardResult();


    //* 下注相關 *//
    // 真人下注
    protected void humanOnMessageBet(){
        // 1. 計算玩家座位
        int humanChairIndex = this.table.getGamePlayerHlr().getHumanChairIndex();

        // 2. 真人下注
        this.table.receivedGameCommand(new ReceiveGameCommand(
                super.player.getUser().getUid(),
                ConstMessageTbnnJava.MessageEnum.CTS_BET.getSub(),
                super.jsonUtil.writeValueAsStringWithoutException(new CtsBetTbnnJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanBetRateList(humanChairIndex))))));
    }

    // 機器人下注
    protected void robotOnMessageBet(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人下注
        for (Integer robotChairIndex: robotChairIndexList.stream().collect(Collectors.toList())) {
            ((StateBetTbnnJava)this.table.getStateMgr().getStateMap().get(ConstTbnnJava.StateEnumTbnnJava.BET.getCode())).onMessageBet(
                    robotChairIndex,
                    new CtsBetTbnnJava(this.table.getRobotLogic().getRobotBetRateResultMap().get(robotChairIndex).getRate()));
        }
    }


    //* 選牌相關 *//
    // 真人選牌
    protected void humanOnMessageSelect(){
        this.table.receivedGameCommand(new ReceiveGameCommand(super.player.getUser().getUid(), ConstMessageTbnnJava.MessageEnum.CTS_SELECT.getSub(), super.jsonUtil.writeValueAsStringWithoutException(new CtsSelectQznnJava(new int[]{},1))));
    }

    // 機器人選牌
    protected void robotOnMessageSelect(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人選牌
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateSelectTbnnJava)this.table.getStateMgr().getStateMap().get(ConstTbnnJava.StateEnumTbnnJava.SELECT.getCode())).onMessageSelect(
                    robotChairIndex,
                    new CtsSelectTbnnJava(null, ConstTbnnJava.SelectTypeEnumTbnnJava.SHOWDOWN.getCode()));
        }
    }
}
