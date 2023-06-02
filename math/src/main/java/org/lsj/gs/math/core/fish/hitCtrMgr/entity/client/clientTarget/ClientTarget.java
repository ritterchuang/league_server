package org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget;

// 目標訊息
public class ClientTarget {
    private final int targetIndex; // 目標編碼

    // 原始建構子提供JSON解析用
    public ClientTarget() {
        this.targetIndex = -1;
    }

    public ClientTarget(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public int getTargetIndex() {
        return targetIndex;
    }
}
