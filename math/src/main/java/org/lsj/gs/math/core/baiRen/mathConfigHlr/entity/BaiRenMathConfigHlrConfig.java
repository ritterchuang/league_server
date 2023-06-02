package org.lsj.gs.math.core.baiRen.mathConfigHlr.entity;

// 數值設定處理器的設定
public class BaiRenMathConfigHlrConfig {
    private final BaiRenTimeConfig baiRenTimeConfigPlay1; // 百人玩法1下注時間
    private final BaiRenTimeConfig baiRenTimeConfigPlay2; // 百人玩法2下注時間

    public BaiRenMathConfigHlrConfig(BaiRenTimeConfig baiRenTimeConfigPlay1, BaiRenTimeConfig baiRenTimeConfigPlay2) {
        this.baiRenTimeConfigPlay1 = baiRenTimeConfigPlay1;
        this.baiRenTimeConfigPlay2 = baiRenTimeConfigPlay2;
    }

    public BaiRenTimeConfig getBaiRenTimeConfigPlay1() {
        return baiRenTimeConfigPlay1;
    }

    public BaiRenTimeConfig getBaiRenTimeConfigPlay2() {
        return baiRenTimeConfigPlay2;
    }
}
