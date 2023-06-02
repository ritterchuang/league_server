package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr;

// 擊殺次數計算者介面
public interface IKillCountCtr {

    // 計算擊殺次數
    int calculateKillCount(double usedRtp, double odd, int hitCount);
}
