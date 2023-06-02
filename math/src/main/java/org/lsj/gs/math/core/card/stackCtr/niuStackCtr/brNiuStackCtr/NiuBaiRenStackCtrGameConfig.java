package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 牛型計算器遊戲設定
public class NiuBaiRenStackCtrGameConfig {
    private final Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeConfig; // 牛型設定 <牛型識別碼, 牛型倍數>

    public NiuBaiRenStackCtrGameConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeConfig) {
        this.niuTypeConfig = niuTypeConfig;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeConfig() {
        return niuTypeConfig;
    }
}
