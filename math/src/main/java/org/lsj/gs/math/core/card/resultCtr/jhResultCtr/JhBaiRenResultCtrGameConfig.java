package org.lsj.gs.math.core.card.resultCtr.jhResultCtr;

import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.ConstJhStack;

import java.util.Map;

// 金花百人結果計算器遊戲設定
public class JhBaiRenResultCtrGameConfig {
    private final Map<ConstJhStack.JhTypeEnumCommon, Integer> cardTypeRateConfig; // 金花設定

    public JhBaiRenResultCtrGameConfig(Map<ConstJhStack.JhTypeEnumCommon, Integer> cardTypeRateConfig) {
        this.cardTypeRateConfig = cardTypeRateConfig;
    }

    public Map<ConstJhStack.JhTypeEnumCommon, Integer> getCardTypeRateConfig() {
        return cardTypeRateConfig;
    }
}
