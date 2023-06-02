package org.lsj.gs.event;

// 牌桌結束事件
public class TableFinishedEvent {
    private final int uid;
    private final String roundId;

    public TableFinishedEvent(int uid, String roundId) {
        this.uid = uid;
        this.roundId = roundId;
    }

    public int getUid() {
        return uid;
    }

    public String getRoundId() { return roundId; }
}
