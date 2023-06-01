package org.lsj.gs.math.games.ebg_java.module.logic;

import org.lsj.gs.math.core.common.logic.ILogic;

// 搶莊二八槓邏輯介面
public interface ILogicEbgJava extends ILogic {
    // 取得所有玩家牌型
    int[] getAllPlayerSelectTypeArray();

    // 取得指定位置的選牌牌型
    int getPlayerSelectType(int chairIndex);
}
