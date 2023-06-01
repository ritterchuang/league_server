package org.lsj.gs.math.core.common.table.module.tableInfo;

import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.utils.LogUtil;
import org.lsj.utils.StringUtil;

import javax.websocket.Session;

// 遊戲桌資訊處理器
public class TableInfoHlr {
    private final int tableId; // 桌號
    private long beginTimeMillis; // 遊戲起始時間(毫秒)
    private String roundId; // 局號

    public TableInfoHlr(int tableId) throws TableException {
        this.tableId = tableId;
        this.beginTimeMillis = this.calculateCurrentTimeMillis();
        this.roundId = StringUtil.getInstance().getEmptyString();
    }

    // 取得桌號
    public int getTableId() { return this.tableId; }

    // 取得起始時間(毫秒)
    public long getBeginTimeMillis() { return this.beginTimeMillis; }

    // 計算花費時間(秒)
    public long calculateSpendSec(){
        return (System.currentTimeMillis() - this.beginTimeMillis) / 1000;
    }

    // 計算當前時間
    public long calculateCurrentTimeMillis() { return System.currentTimeMillis(); }

    // 更新局號
    public void updateRoundId(String roundId) {
        this.roundId = roundId;
    }

    // 計算局號(秒)
    public String calculateRoundIdSec(int fieldIndex){
        return "tw" +
                "_" +
                fieldIndex +
                "_" +
                this.calculateCurrentTimeMillis() / 1000 +
                "_" +
                this.tableId;
    }

    // 計算局號(微秒)
    public String calculateRoundIdMillis(int fieldIndex){
        return "tw" +
                "_" +
                fieldIndex +
                "_" +
                this.calculateCurrentTimeMillis() +
                "_" +
                this.tableId;
    }

    // 取得局號
    public String getRoundId() {
        return this.roundId;
    }

    // 遊戲桌日誌標題
    public String getTableLogTitle(Session session, int uid, String roundId) {
        return LogUtil.getLogPrefix(
                session,
                uid,
                (StringUtil.getInstance().isEmpty(roundId)) ? "NULL" : roundId
        );
    }

    // 重置
    public void reset() {
        this.beginTimeMillis = this.calculateCurrentTimeMillis();
        this.roundId = StringUtil.getInstance().getEmptyString();
    }
}
