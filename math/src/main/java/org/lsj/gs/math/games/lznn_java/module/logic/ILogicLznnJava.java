package org.lsj.gs.math.games.lznn_java.module.logic;

import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.games.lznn_java.entity.NiuStackLznnJava;

// 賴子牛牛邏輯介面
public interface ILogicLznnJava extends ILogic {
    // 取得所有玩家牌型
    int[] getAllPlayerSelectTypeArray();

    // 取得指定位置的選牌牌型
    int getPlayerSelectType(int chairIndex);

    // 取得真人選牌結果
    NiuStackLznnJava getHumanPlayerSelectResult();

    // 取得真人選牌牌型
    int getHumanPlayerSelectType();

    // 取得選牌結果
    NiuStackLznnJava getPlayerSelectResult(int chairIndex);
}
