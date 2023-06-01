package org.lsj.gs.math.core.common.table.module.tableUtil.random.wpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

// 數值封裝亂數產生器
public class MathRandomWpr implements IRandomWprMain {
    private final Random random; // 亂數產生器 TODO 建立SecurityRandom
    private List<Integer> randomNumberList; // 隨機亂數列表
    private final List<Integer> randomNumberUsedList; // 使用過的隨機亂數列表

    public MathRandomWpr() {
        this.random = new Random();
        this.randomNumberList = new ArrayList<>();
        this.randomNumberUsedList = new ArrayList<>();
    }


    //* 生產亂數相關 *//
    // 產生上界整數 [0, bound)
    @Override
    public int nextInt(int bound) {
        // 1. 取用隨機亂數列表
        if(Objects.nonNull(this.randomNumberList) && this.randomNumberList.size() >= 1){
            // 2. 取得此次亂數
            Integer randomNumberUsed = this.randomNumberList.get(0);

            // 3. 移除隨機亂數
            this.randomNumberList.remove(0);

            // 4. 加入使用過列表
            this.randomNumberUsedList.add(randomNumberUsed);

            // 5. 回傳亂數
            return randomNumberUsed % bound;
        }

        // 6. 隨機生成
        int randomNumberUsed = this.random.nextInt(bound);

        // 7. 加入使用過列表
        this.randomNumberUsedList.add(randomNumberUsed);

        // 8. 回傳
        return randomNumberUsed;
    }


    //* 設定亂數相關 *//

    // 設定亂數陣列
    @Override
    public void setRandomNumberList(ArrayList<Integer> randomNumberList) {
        this.randomNumberList = (Objects.nonNull(randomNumberList)) ? randomNumberList: new ArrayList<>();
    }

    // 取得隨機亂數列表
    @Override
    public List<Integer> getRandomNumberList() {
        return this.randomNumberList;
    }

    // 取得使用過的隨機亂數列表
    @Override
    public List<Integer> getRandomNumberUsedList() {
        return randomNumberUsedList;
    }


    //* 重置相關 *//

    // 重置
    @Override
    public void reset(){
        this.randomNumberUsedList.clear();
    }
}
