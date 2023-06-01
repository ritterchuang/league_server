package org.lsj.gs.math.core.common.table;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.AbstractTableGameConfig;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.enterTableCheck.EnterTableCheckHlr;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.state.StateMgr;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableInfo.TableInfoHlr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.tableConfigMgr.TableConfigMgr;
import org.lsj.gs.user.IUser;
import org.lsj.utils.MathUtil;
import org.lsj.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 抽象遊戲桌
public abstract class AbstractTable implements ITableBase {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTable.class);
    protected final TableInfoHlr tableInfoHlr; // 遊戲桌資訊處理器
    protected final EnterTableCheckHlr enterTableCheckHlr; // 進入遊戲桌檢查處理器
    protected final TableConfigMgr tableConfigMgr; // 遊戲桌設定檔管理器
    protected final GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    protected final AlgorithmTypeCtr algorithmTypeCtr; // 演算法類型計算器
    protected final PoolCtr poolCtr; // 水池控制器
    protected final StateMgr stateMgr; // 狀態管理器

    public AbstractTable(
            TableFieldConfig tableFieldConfig,
            AbstractTableGameConfig tableGameConfig,
            int tableId,
            IPoolConfig poolConfig,
            IUser user,
            ConstMathCommon.GamePlayerHlrConsumeRnd gamePlayerHlrConsumeRnd,
            boolean isHumanEnterTable,
            ITableUtil tableUtil) throws TableException {
        this.tableInfoHlr = new TableInfoHlr(tableId);

        // 1. 入桌檢查
        this.enterTableCheckHlr = new EnterTableCheckHlr();
        this.enterTableCheckHlr.enterTableCheck(
                this.tableInfoHlr.getTableLogTitle(user.getSession(), user.getUid(), StringUtil.getInstance().getEmptyString()),
                tableFieldConfig,
                tableId,
                poolConfig,
                user);

        // 2. 模組初始化
        this.tableConfigMgr = new TableConfigMgr(tableFieldConfig);
        this.gamePlayerHlr = new GamePlayerHlr(this.tableConfigMgr.getModuleConfigMgrCore().createGamePlayerHlrConfig(), user, gamePlayerHlrConsumeRnd, isHumanEnterTable, tableUtil);
        this.algorithmTypeCtr = new AlgorithmTypeCtr(tableGameConfig.getGameAdjustConfig(), tableGameConfig.getRngAlgorithmConfig(), tableUtil);
        this.poolCtr = new PoolCtr(poolConfig, user, this.algorithmTypeCtr, tableUtil);
        this.stateMgr = new StateMgr(this);
    }

    //* 遊戲桌資訊處理器 *//

    // 取得桌號
    @Override
    public int getTableId() {
        return this.tableInfoHlr.getTableId();
    }

    // 取得起始時間(毫秒)
    @Override
    public long getBeginTimeMillis() {
        return this.tableInfoHlr.getBeginTimeMillis();
    }

    // 取得局號
    @Override
    public String getRoundId() {
        return this.tableInfoHlr.getRoundId();
    }

    // 牌桌日誌標題
    @Override
    public String getTableLogTitle() {
        return this.tableInfoHlr.getTableLogTitle(
                this.getGamePlayerHlr().getHumanGamePlayer().getSession(),
                this.getGamePlayerHlr().getHumanGamePlayer().getUid(),
                this.getRoundId());
    }

    // 計算花費時間(秒)
    protected long calculateSpendSec() {
        return this.tableInfoHlr.calculateSpendSec();
    }

    // 更新局號
    protected void updateRoundId(String roundId) {
        this.tableInfoHlr.updateRoundId(roundId);
    }


    //* 遊戲桌設定管理器 *//

    // 取得遊戲桌設定管理器
    @Override
    public TableConfigMgr getTableConfigMgr() {
        return this.tableConfigMgr;
    }

    // 取得遊戲編碼
    protected int getGameId() { return this.tableConfigMgr.getConfig().getGameId(); }


    //* 遊戲玩家處理器 *//

    // 取得遊戲玩家管理器
    @Override
    public GamePlayerHlr getGamePlayerHlr() { return this.gamePlayerHlr; }

    // 取得真人玩家
    @Override
    public GamePlayer getHumanGamePlayer() { return this.gamePlayerHlr.getHumanGamePlayer(); }

    // 遊戲前更新玩家餘額
    @Override
    public void updatePlayerBeginMoneyBeforeGame() { this.gamePlayerHlr.updatePlayerBeginMoneyBeforeGame();}

    // 取得真人使用者
    @Override
    public IUser getUser(){ return this.gamePlayerHlr.getHumanGamePlayer().getUser(); }

    // 更新真人資訊
    protected void updateHumanUser(IUser user) {this.gamePlayerHlr.updateHumanUser(user);}

    // 取得所有玩家資訊
    protected List<GamePlayer> getAllGamePlayerList() { return this.gamePlayerHlr.getAllGamePlayerList(); }

    // 取得調整至金錢單位的所有玩家初始金額
    protected double[] getScaledAllPlayerBeginMoneyArray() {
        return Arrays.stream(this.getAllPlayerBeginMoneyArray())
                .boxed().collect(Collectors.toList()).stream()
                .mapToDouble(MathUtil::moneyScale)
                .toArray();
    }

    // 檢查真人玩家初始金額足夠性
    protected boolean checkHumanBeginMoneyEnough(double cost) {
        return (this.getGamePlayerHlr().getHumanGamePlayer().getBeginMoney() >= cost);
    }

    // 更新真人玩家遊戲後 剩餘金額
    protected void updateHumanAfterMoney(double score){
        this.gamePlayerHlr.getHumanGamePlayer().setAfterMoney(
                MathUtil.add(this.getGamePlayerHlr().getHumanGamePlayer().getBeginMoney(), score)
        );
    }

    // 取得所有玩家初始金額
    private double[] getAllPlayerBeginMoneyArray() { return this.gamePlayerHlr.getAllGamePlayerBeginMoney(); }


    //* 演算法類型計算器 *//
    @Override
    public AlgorithmTypeCtr getAlgorithmTypeCtr() {
        return this.algorithmTypeCtr;
    }


    //* 水池控制器相關 *//

    // 取得水池控制器
    @Override
    public PoolCtr getPoolCtr() {
        return this.poolCtr;
    }

    // 取得水池
    @Override
    public IPoolConfig getPoolConfig(){ return this.poolCtr.getPoolConfig(); }

    // 計算個人控所有結果
    @Override
    public void calculatePersonControlAllResult() {
        this.poolCtr.calculatePersonControlAllResult(this.getUser());
    }

    // 更新水池資訊
    protected void updatePoolConfig(IPoolConfig poolConfig, String className){
        LOG.info(className + " updatePoolConfig tableId:{}, poolConfig:{}", this.getTableId(), poolConfig);
        this.poolCtr.updateConfig(poolConfig);
    }


    //* 狀態機相關 *//

    // 取得狀態管理器
    @Override
    public StateMgr getStateMgr() { return this.stateMgr; }

    // 註冊狀態機
    @Override
    public abstract void registerState();

    // 切換狀態
    @Override
    public abstract void switchState(int exitStateId);

    // 傳送狀態更新訊息
    @Override
    public abstract void sendUpdateState(int enterStateIndex);


    //* 重置相關 *//
    protected void reset() {
        this.tableInfoHlr.reset();
        this.enterTableCheckHlr.reset();
        this.tableConfigMgr.reset();
        this.gamePlayerHlr.reset();
        this.algorithmTypeCtr.reset();
        this.stateMgr.reset();
        this.poolCtr.reset();
    }
}
