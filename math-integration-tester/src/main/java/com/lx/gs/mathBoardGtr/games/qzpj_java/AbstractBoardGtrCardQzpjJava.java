package com.lx.gs.mathBoardGtr.games.qzpj_java;

import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigQzpjJava;
import com.lx.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.core.common.table.module.tableEvent.EmptyTableFinishedEvent;
import com.lx.gs.math.games.qzpj_java.TableQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.ConstQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.message.ConstMessageQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.message.CtsBetQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.message.CtsVieBankerQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.state.StateBetQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.state.StateVieBankerQzpjJava;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.core.card.AbstractBoardGtrCard;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;
import com.lx.websocket.ReceiveGameCommand;

import java.util.List;
import java.util.stream.Collectors;

// 抽象搶莊牌九局產生器
public abstract class AbstractBoardGtrCardQzpjJava extends AbstractBoardGtrCard {
    protected TableQzpjJava table; // 牌桌

    public AbstractBoardGtrCardQzpjJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);

        // 2. 產生牌桌
        try {
            this.table = new TableQzpjJava(
                    new EmptyTableFinishedEvent(),
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigQzpjJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
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
                ConstMessageQzpjJava.MessageEnum.CTS_VIE_BANKER.getSub(),
                super.jsonUtil.writeValueAsStringWithoutException(new CtsVieBankerQzpjJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanVieRateList(humanChairIndex))))));
    }

    // 機器人搶莊
    protected void robotOnMessageVieBanker(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人搶莊
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateVieBankerQzpjJava)this.table.getStateMgr().getStateMap().get(ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode())).onMessageVieBanker(
                    robotChairIndex,
                    new CtsVieBankerQzpjJava(this.table.getRobotLogic().getRobotVieBankerRateResultMap().get(robotChairIndex).getRate()));
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
                    ConstMessageQzpjJava.MessageEnum.CTS_BET.getSub(),
                    super.jsonUtil.writeValueAsStringWithoutException(new CtsBetQzpjJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanBetRateList(humanChairIndex))))));
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
            ((StateBetQzpjJava)this.table.getStateMgr().getStateMap().get(ConstQzpjJava.StateEnumQzpjJava.BET.getCode())).onMessageBet(
                    robotChairIndex,
                    new CtsBetQzpjJava(this.table.getRobotLogic().getRobotBetRateResultMap().get(robotChairIndex).getRate()));
        }
    }
}
