package org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;

import java.util.HashMap;
import java.util.Map;

// 房間統計者
public class StnTemplateMapField extends AbstractStnTemplateMap {
    private final Map<Integer, TemplateStn> stnFieldMap; // 各房間統計 <房間索引, 基礎統計物件>

    public StnTemplateMapField(AgencyStnConfig config) {
        super(config);
        this.stnFieldMap = new HashMap<>();
    }

    // 更新統計物件
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 取得統計物件
        TemplateStn stn = super.getStn(stnFieldMap, boardGtrResult.getStnResult().getFieldIndex());

        // 2. 更新統計物件
        stn.update(boardGtrResult);

        // 3. 更新各統計
        this.stnFieldMap.put(boardGtrResult.getStnResult().getFieldIndex(), stn);
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        // 1. 取得統計物件
        TemplateStn stn = super.getStn(stnFieldMap, boardGtrReturnResult.getStnResult().getFieldIndex());

        // 2. 更新統計物件
        stn.updateReturnResult(boardGtrReturnResult);

        // 3. 更新各統計
        this.stnFieldMap.put(boardGtrReturnResult.getStnResult().getFieldIndex(), stn);
    }

    // 印出統計資訊
    @Override
    public void print() {
        this.stnFieldMap.forEach((stnIndex, stn) -> super.printTitleStn("房間資訊", stnIndex, stn));
    }
}
