package org.lsj.gs.math.games.zjh_java.module.logic;

import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.games.zjh_java.entity.NiuStackZjhJava;

// 炸金花邏輯介面
public interface ILogicZjhJava extends ILogic {
    // 取得所有玩家牌型
    int[] getAllPlayerSelectTypeArray();

    // 取得指定位置的選牌牌型
    int getPlayerSelectType(int chairIndex);

    // 取得真人選牌結果
    NiuStackZjhJava getHumanPlayerSelectResult();

    // 取得真人選牌牌型
    int getHumanPlayerSelectType();
}
