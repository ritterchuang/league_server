package com.lx.gs.mathBoardGtr.games.sg_java;

import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSgJava;
import com.lx.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.core.common.table.module.tableEvent.EmptyTableFinishedEvent;
import com.lx.gs.math.games.sg_java.TableSgJava;
import com.lx.gs.math.games.sg_java.entity.ConstSgJava;
import com.lx.gs.math.games.sg_java.entity.message.ConstMessageSgJava;
import com.lx.gs.math.games.sg_java.entity.message.CtsBetSgJava;
import com.lx.gs.math.games.sg_java.entity.message.CtsSelectSgJava;
import com.lx.gs.math.games.sg_java.entity.message.CtsVieBankerSgJava;
import com.lx.gs.math.games.sg_java.module.state.StateBetSgJava;
import com.lx.gs.math.games.sg_java.module.state.StateSelectSgJava;
import com.lx.gs.math.games.sg_java.module.state.StateVieBankerSgJava;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.core.card.AbstractBoardGtrCard;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;
import com.lx.websocket.ReceiveGameCommand;

import java.util.List;
import java.util.stream.Collectors;

// 抽象新三公局產生器
public abstract class AbstractBoardGtrCardSgJava extends AbstractBoardGtrCard {
    protected TableSgJava table; // 牌桌

    public AbstractBoardGtrCardSgJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);

        // 2. 產生牌桌
        try {
            this.table = new TableSgJava(
                    new EmptyTableFinishedEvent(),
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigSgJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
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
                ConstMessageSgJava.MessageEnum.CTS_VIE_BANKER.getSub(),
                super.jsonUtil.writeValueAsStringWithoutException(new CtsVieBankerSgJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanVieRateList(humanChairIndex))))));
    }

    // 機器人搶莊
    protected void robotOnMessageVieBanker(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人搶莊
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateVieBankerSgJava)this.table.getStateMgr().getStateMap().get(ConstSgJava.StateEnumSgJava.VIE_BANKER.getCode())).onMessageVieBanker(
                    robotChairIndex,
                    new CtsVieBankerSgJava(this.table.getRobotLogic().getRobotVieBankerRateResultMap().get(robotChairIndex).getRate()));
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
                    ConstMessageSgJava.MessageEnum.CTS_BET.getSub(),
                    super.jsonUtil.writeValueAsStringWithoutException(new CtsBetSgJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanBetRateList(humanChairIndex))))));
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
            ((StateBetSgJava)this.table.getStateMgr().getStateMap().get(ConstSgJava.StateEnumSgJava.BET.getCode())).onMessageBet(
                    robotChairIndex,
                    new CtsBetSgJava(this.table.getRobotLogic().getRobotBetRateResultMap().get(robotChairIndex).getRate()));
        }
    }


    //* 選牌相關 *//
    // 真人選牌
    protected void humanOnMessageSelect(){
        this.table.receivedGameCommand(new ReceiveGameCommand(super.player.getUser().getUid(), ConstMessageSgJava.MessageEnum.CTS_SELECT.getSub(), super.jsonUtil.writeValueAsStringWithoutException(new CtsSelectSgJava())));
    }

    // 機器人選牌
    protected void robotOnMessageSelect(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人選牌
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateSelectSgJava)this.table.getStateMgr().getStateMap().get(ConstSgJava.StateEnumSgJava.SELECT.getCode())).onMessageSelect(
                    robotChairIndex,
                    new CtsSelectSgJava()
            );
        }
    }
}
