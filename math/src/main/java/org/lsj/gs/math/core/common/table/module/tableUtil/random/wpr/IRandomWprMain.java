package org.lsj.gs.math.core.common.table.module.tableUtil.random.wpr;

import java.util.ArrayList;
import java.util.List;

// 主要封裝亂數產生器介面
public interface IRandomWprMain extends IRandomWpr {
    void setRandomNumberList(ArrayList<Integer> randomNumberList); // 設定亂數陣列
    List<Integer> getRandomNumberList(); // 取得隨機亂數列表
    List<Integer> getRandomNumberUsedList(); // 取得使用過的隨機亂數列表
    void reset(); // 重置隨機亂數
}
