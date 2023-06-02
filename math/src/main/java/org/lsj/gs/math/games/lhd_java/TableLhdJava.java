package org.lsj.gs.math.games.lhd_java;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigLhdJava;
import org.lsj.gs.math.core.baiRen.ConstMathBaiRen;
import org.lsj.gs.math.core.card.stackCtr.lhStackCtr.LhStack;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.AbstractTableMessageBaiRen;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.baiRen.CtsBetBaiRenJava;
import org.lsj.gs.math.core.common.table.entity.message.core.ConstMessageCore;
import org.lsj.gs.math.core.common.table.entity.message.core.StcEnterFiled;
import org.lsj.gs.math.core.common.table.entity.message.core.data.StcEnterFieldMsgData;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;
import org.lsj.gs.math.games.lhd_java.entity.message.StcChartLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.message.StcCutReturnLhdJava;
import org.lsj.gs.math.games.lhd_java.module.chartCtr.ChartCtrLhdJava;
import org.lsj.gs.math.games.lhd_java.module.gameAdjust.GameAdjustLhdJava;
import org.lsj.gs.math.games.lhd_java.module.logic.LogicLhdJava;
import org.lsj.gs.math.games.lhd_java.module.robotLogic.RobotLogicLhdJava;
import org.lsj.gs.math.games.lhd_java.module.state.StateGameBeginLhdJava;
import org.lsj.gs.math.games.lhd_java.module.state.StateGameEndLhdJava;
import org.lsj.gs.math.games.lhd_java.module.state.StateWaitBeginLhdJava;
import org.lsj.gs.state.BaiRenGameState;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.websocket.ProtocolCode;
import org.lsj.websocket.ReceiveGameCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

// 新龍虎鬥遊戲桌
public class TableLhdJava extends AbstractTableMessageBaiRen {
    private static final Logger LOG = LoggerFactory.getLogger(TableLhdJava.class);

    private final ChartCtrLhdJava chartCtr; // 路圖計算器
    private final LogicLhdJava logic; // 遊戲邏輯
    private final RobotLogicLhdJava robotLogic; // 機器人遊戲邏輯
    private final GameAdjustLhdJava gameAdjust; // 遊戲調控

    public TableLhdJava(int tableId,
                        TableFieldConfig tableFieldConfig,
                        TableGameConfigLhdJava tableGameConfig,
                        IPoolConfig poolConfig,
                        IUser user,
                        ITableUtilBaiRen tableUtil) throws TableException {
        // 1. 初始化
        super(  tableFieldConfig,
                tableGameConfig,
                tableId,
                poolConfig,
                user,
                tableUtil);
        this.chartCtr = new ChartCtrLhdJava(tableUtil);
        this.logic = new LogicLhdJava(this,
                tableFieldConfig,
                tableGameConfig,
                super.getGamePlayerHlr(),
                super.getPoolCtr(),
                new GameBetLogResultCtrBaiRen(
                        this.getBeginTimeMillis(),
                        this.getTableId(),
                        super.getTableConfigMgr().getConfig(),
                        super.getGamePlayerHlr(),
                        super.getPoolCtr(),
                        tableUtil),
                tableUtil);
        this.robotLogic = new RobotLogicLhdJava(
                super.getGamePlayerHlr(),
                super.getPoolCtr(),
                tableUtil,
                this.logic);
        this.gameAdjust = new GameAdjustLhdJava(
                super.getAlgorithmTypeCtr(),
                this.logic,
                super.getPoolCtr(),
                tableUtil);

        // 2. 註冊狀態機
        this.registerState();
    }

    //* 路圖相關 *//

    // 取得路圖列表
    public List<Object> getChartList(){
        return this.chartCtr.getChartList();
    }

    // 更新路圖列表
    public void updateChart(Map<Integer, LhStack> stackMap){
        this.chartCtr.updateChart(stackMap);
    }

    // 傳送路線圖
    @Override
    public void sendChart(){
        if(super.getGamePlayerHlr().isHumanEnterTable()) {
            StcChartLhdJava stcChartLhdJava = new StcChartLhdJava(this.chartCtr.getChartArray());
            LOG.info(super.getTableLogTitle() + " message: {}", stcChartLhdJava);
            this.sendMessageToHumanPlayer(stcChartLhdJava);
        }
    }


    //* 流程相關 *//

    // 真人入桌
    public void enterTable(IUser user, double leftTimeSec){
        // 1. 玩家入桌
        super.enterTable(user);

        // 2. 傳送進入房間封包
        super.sendMessageToHumanPlayer(new StcEnterFiled(
                ProtocolCode.SUCCESS.getCode(),
                new StcEnterFieldMsgData(
                        ProtocolCode.SUCCESS.getCode(),
                        super.getTableId(),
                        this.logic.getHumanChairIndex(),
                        ConstMathBaiRen.UserState.WAIT.getCode()
                )
        ));

        // 3. 傳送牌桌資訊流程
        this.sendTableInfoFlow(leftTimeSec);
    }

    // 傳送牌桌資訊流程
    private void sendTableInfoFlow(double leftTimeSec){
        // 1. 更新設定資訊
        super.sendConfig(super.getTableConfigMgr().composedConfig(
                this.logic.getAreasTopLimitList(),
                this.logic.getChipsList()
        ));

        // 2. 傳送玩家列表
        super.sendUserList();

        // 3. 傳送榜單資訊
        super.sendRichList();

        // 4. 傳送玩家人數資訊
        super.sendUserNum();

        // 5. 傳送回復資訊
        super.sendMessageToHumanPlayer(this.calculateCutReturn(leftTimeSec));

        // 6. 傳送路線圖
        this.sendChart();

        // 7. 傳送局號
        super.sendRoundLogId();

        // 8. 傳送真人下注資訊
        super.sendOwnBet();
    }

    // 斷線重連資訊
    private StcCutReturnLhdJava calculateCutReturn(double leftTimeSec){
        // 1. 計算當前狀態
        int stage = super.stateMgr.getCurrentStateId();

        // 2. 計算剩餘時間秒數
        double leftTime = Math.floor(leftTimeSec);

        // 3. 計算區域所有押注金額
        int[] areas = this.logic.getAreaBetArray();

        // 4. 計算幸運星區域押注金額
        int[] luckyStarAreas = new int[]{};

        // 5. 計算卡牌
        int[] cards = this.logic.getAllAreaCardNumberArray();

        // 6. 計算比分點數
        int[] compareScore = this.logic.getAllAreaCardPointArray();

        return new StcCutReturnLhdJava(stage, leftTime, areas, luckyStarAreas, cards, compareScore);
    }

    // 真人離桌
    @Override
    public boolean leaveTable() {
        // 1. 確認玩家能否離桌
        boolean canLeaveTable = this.canLeaveTable();

        if(canLeaveTable){
            // 2. 玩家離桌
            super.gamePlayerHlr.leaveTable();
        }

        return canLeaveTable;
    }

    // 註冊狀態
    @Override
    public void registerState(){
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginLhdJava(this, this.logic, this.robotLogic, this.gameAdjust, BaiRenGameState.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginLhdJava(this, this.logic, this.robotLogic, this.gameAdjust, BaiRenGameState.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameEndLhdJava(this, this.logic, this.robotLogic, this.gameAdjust, BaiRenGameState.GAME_END.getCode()));

        // 2. 初始化狀態
        super.getStateMgr().setState(BaiRenGameState.WAIT_BEGIN.getCode());
    }

    // 更新遊戲狀態並回傳延遲時間(秒)
    public double updateGameState(BaiRenGameState gameState, double leftTimeSec) {
        // 1. 強制更新狀態
        super.stateMgr.setState(gameState.getCode(), leftTimeSec);

        // 2. 回傳延遲時間
        return 0.0;
    }

    // 切換頁籤處理
    public void switchBg(double leftTimeSec){
        this.sendTableInfoFlow(leftTimeSec);
    }

    // 玩家能否離桌
    private boolean canLeaveTable() {
        return !super.isHumanBet();
    }

    // 清空牌桌資訊
    @Override
    public void reset(){
        super.reset();
        this.chartCtr.reset();
        this.logic.reset();
        this.robotLogic.reset();
        this.gameAdjust.reset();
    }

    //* 傳輸協議相關 *//

    // 接收客戶端封包 TODO bairen
    @Override
    public void receivedGameCommand(ReceiveGameCommand receiveGameCommand){
        if (ConstMessageCore.MessageEnum.CTS_BET.getSub().equals(receiveGameCommand.getKey())) {
            this.receivedGameCommandBet(receiveGameCommand);
        }
    }

    // 接收下注客戶端封包
    private void receivedGameCommandBet(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != BaiRenGameState.GAME_BEGIN.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateGameBeginLhdJava)super.stateMgr.getStateMap().get(BaiRenGameState.GAME_BEGIN.getCode()))
                    .onRecvBetMsg(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsBetBaiRenJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }


    //* 牌桌資訊 *//
    public LogicLhdJava getLogic(){
        return this.logic;
    }

    // 取得遊戲結果
    public IGameBetLogResultBaiRen getResult() {
        return this.logic.getGameBetLogResult(this.logic.getHumanUidScore());
    }
}
