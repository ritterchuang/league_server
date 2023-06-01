package com.lx.gs.mathStr.core.module.stn;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;

// 統計者介面
public interface IStn {
    void update(BoardGtrResult boardGtrResult); // 更新統計資訊
    void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult); // 更新返還資訊
    void print(); // 印出統計資訊
}
