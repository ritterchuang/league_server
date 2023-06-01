package org.lsj.gs.math.core.card.resultCtr.sgResultCtr;

import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.ConstSgStack;

import java.util.Map;

// 三公計算器遊戲設定
public class SgBankerResultCtrGameConfig {
    private final Map<ConstSgStack.SgTypeEnumCommon, Integer> sgTypeConfig; // 三公牌型設定 <三公牌型識別碼, 三公型倍數>

    public SgBankerResultCtrGameConfig(Map<ConstSgStack.SgTypeEnumCommon, Integer> sgTypeConfig) {
        this.sgTypeConfig = sgTypeConfig;
    }

    public Map<ConstSgStack.SgTypeEnumCommon, Integer> getSgTypeConfig() {
        return sgTypeConfig;
    }
}
