package org.lsj.gs.math.core.baiRen.baseCtr;

public class BaseCtr {

    // 計算合法的整數底注
    public int calculateValidBaseInt(double base){
        // 1. 轉換為整數
        int baseInt = (int)base;

        // 2. 底注防呆
        if(baseInt <= 1){
            return 1;
        }

        // 3. 個數位
        if(baseInt <= 9){
            return baseInt;
        }

        // 4. 十位數
        if(baseInt <= 99){
            return (baseInt/10) * 10;
        }

        // 5. 百位數
        if(baseInt <= 999){
            return (baseInt/100) * 100;
        }

        // 6. 千位數以上
        return (baseInt/1000) * 1000;
    }
}
