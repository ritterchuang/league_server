package org.lsj.gs.math.core.common.table.module.tableUtil.random;

import java.util.ArrayList;
import java.util.List;

// 主要隨機工具包介面
public interface IRandomUtilMain extends IRandomUtil{
    void setRandomNumberList(ArrayList<Integer> randomNumberList); // 設定隨機亂數
    List<Integer> getRandomNumberList(); // 取得隨機亂數列表
    List<Integer> getRandomNumberUsedList(); // 取得使用過的隨機亂數列表
    void setRandomNumberListByString(String rngDataString); // 設定隨機亂數字串
    boolean isSetRngDataState(); // 是否為設定亂數狀態
}
