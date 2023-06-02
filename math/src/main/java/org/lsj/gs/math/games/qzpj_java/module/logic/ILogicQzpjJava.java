package org.lsj.gs.math.games.qzpj_java.module.logic;

import org.lsj.gs.math.core.common.logic.ILogic;

// 搶莊牌九邏輯介面
public interface ILogicQzpjJava extends ILogic {
    // 取得所有玩家牌型
    int[] getAllPlayerSelectTypeArray();

    // 取得指定位置的選牌牌型
    int getPlayerSelectType(int chairIndex);
}
