package org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr;

import org.lsj.utils.JsonUtil;

// 個人控結果
public class AdjustInfo {
    private final int id;  // 調控策略索引
    private final String name; // 調控策略名稱
    private final int type; // 調控策略類型; 1: 個人控; 2: 大盤控

    public AdjustInfo(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // 計算個人控結果字串格式
    public String calculateAdjustInfoString() {
        return JsonUtil.getInstance().writeValueAsStringWithoutException(new AdjustInfo[]{this});
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
