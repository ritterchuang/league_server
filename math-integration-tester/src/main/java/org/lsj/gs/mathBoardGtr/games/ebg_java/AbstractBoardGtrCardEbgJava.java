package org.lsj.gs.mathBoardGtr.games.ebg_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigEbgJava;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableEvent.EmptyTableFinishedEvent;
import org.lsj.gs.math.games.ebg_java.TableEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.ConstEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.ConstMessageEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.CtsBetEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.CtsVieBankerEbgJava;
import org.lsj.gs.math.games.ebg_java.module.state.StateBetEbgJava;
import org.lsj.gs.math.games.ebg_java.module.state.StateVieBankerEbgJava;
import org.lsj.gs.math.games.qzpj_java.entity.message.CtsBetQzpjJava;
import org.lsj.gs.math.games.qzpj_java.entity.message.CtsVieBankerQzpjJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.card.AbstractBoardGtrCard;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;
import java.util.stream.Collectors;

// 抽象搶莊二八槓局產生器
public abstract class AbstractBoardGtrCardEbgJava extends AbstractBoardGtrCard {
    protected TableEbgJava table; // 牌桌

    public AbstractBoardGtrCardEbgJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);

        // 2. 產生牌桌
        try {
            this.table = new TableEbgJava(
                    new EmptyTableFinishedEvent(),
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigEbgJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
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
                ConstMessageEbgJava.MessageEnum.CTS_VIE_BANKER.getSub(),
                super.jsonUtil.writeValueAsStringWithoutException(new CtsVieBankerQzpjJava(super.calculateRandomIntElement(this.table.getLogic().getPlayerCanVieRateList(humanChairIndex))))));
    }

    // 機器人搶莊
    protected void robotOnMessageVieBanker(){
        // 1. 計算機器人座位
        List<Integer> robotChairIndexList = this.table.getGamePlayerHlr().getRobotChairIndexList();

        // 2. 機器人搶莊
        for (Integer robotChairIndex: robotChairIndexList) {
            ((StateVieBankerEbgJava)this.table.getStateMgr().getStateMap().get(ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode())).onMessageVieBanker(
                    robotChairIndex,
                    new CtsVieBankerEbgJava(this.table.getRobotLogic().getRobotVieBankerRateResultMap().get(robotChairIndex).getRate()));
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
                    ConstMessageEbgJava.MessageEnum.CTS_BET.getSub(),
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
            ((StateBetEbgJava)this.table.getStateMgr().getStateMap().get(ConstEbgJava.StateEnumEbgJava.BET.getCode())).onMessageBet(
                    robotChairIndex,
                    new CtsBetEbgJava(this.table.getRobotLogic().getRobotBetRateResultMap().get(robotChairIndex).getRate()));
        }
    }
}
