package org.lsj.gs.math.games.qznn_k4z_java.module.logic;

import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.games.qznn_k4z_java.entity.NiuStackQznnK4zJava;

// 新看四張搶莊牛牛邏輯介面
public interface ILogicQznnK4zJava extends ILogic {
    // 取得所有玩家牌型
    int[] getAllPlayerSelectTypeArray();

    // 取得指定位置的選牌牌型
    int getPlayerSelectType(int chairIndex);

    // 取得真人選牌結果
    NiuStackQznnK4zJava getHumanPlayerSelectResult();

    // 取得真人選牌牌型
    int getHumanPlayerSelectType();

    // 是否過早選牌
    boolean isEarlyPlayerSelect(int chairIndex);
}
