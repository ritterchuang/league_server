package org.lsj.gs.mathBoardGtr.games.lznn_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigLznnJava;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableEvent.EmptyTableFinishedEvent;
import org.lsj.gs.math.games.lznn_java.TableLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.ConstMessageLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.CtsBetLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.CtsSelectLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.CtsVieBankerLznnJava;
import org.lsj.gs.math.games.lznn_java.module.state.StateBetLznnJava;
import org.lsj.gs.math.games.lznn_java.module.state.StateSelectLznnJava;
import org.lsj.gs.math.games.lznn_java.module.state.StateVieBankerLznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsBetQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsSelectQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsVieBankerQznnJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.card.AbstractBoardGtrCard;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;
import java.util.stream.Collectors;

// 抽象賴子牛牛局產生器
public abstract class AbstractBoardGtrCardLznnJava extends AbstractBoardGtrCard {
    protected TableLznnJava table; // 牌桌

    public AbstractBoardGtrCardLznnJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);

        // 2. 產生牌桌
        try {
            this.table = new TableLznnJava(
                    new EmptyTableFinishedEvent(),
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigLznnJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
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
                ConstMessageLznnJava.MessageEnum.CTS_VIE_BANKER.getSub(),
                super.jsonUtil.writeValueAsStringWithoutException(new CtsVieBankerQznnJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanVieRateList(humanChairIndex))))));
    }

    // 機器人搶莊
    protected void robotOnMessageVieBanker(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人搶莊
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateVieBankerLznnJava)this.table.getStateMgr().getStateMap().get(ConstLznnJava.StateEnumLznnJava.VIE_BANKER.getCode())).onMessageVieBanker(
                    robotChairIndex,
                    new CtsVieBankerLznnJava(this.table.getRobotLogic().getRobotVieBankerRateResultMap().get(robotChairIndex).getRate()));
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
                    ConstMessageLznnJava.MessageEnum.CTS_BET.getSub(),
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
            ((StateBetLznnJava)this.table.getStateMgr().getStateMap().get(ConstLznnJava.StateEnumLznnJava.BET.getCode())).onMessageBet(
                    robotChairIndex,
                    new CtsBetLznnJava(this.table.getRobotLogic().getRobotBetRateResultMap().get(robotChairIndex).getRate()));
        }
    }


    //* 選牌相關 *//
    // 真人選牌
    protected void humanOnMessageSelect(){
        this.table.receivedGameCommand(new ReceiveGameCommand(super.player.getUser().getUid(), ConstMessageLznnJava.MessageEnum.CTS_SELECT.getSub(), super.jsonUtil.writeValueAsStringWithoutException(new CtsSelectQznnJava(new int[]{},1))));
    }

    // 機器人選牌
    protected void robotOnMessageSelect(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人選牌
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateSelectLznnJava)this.table.getStateMgr().getStateMap().get(ConstLznnJava.StateEnumLznnJava.SELECT.getCode())).onMessageSelect(
                    robotChairIndex,
                    new CtsSelectLznnJava(null, ConstLznnJava.SelectTypeEnumLznnJava.SHOWDOWN.getCode()));
        }
    }
}
