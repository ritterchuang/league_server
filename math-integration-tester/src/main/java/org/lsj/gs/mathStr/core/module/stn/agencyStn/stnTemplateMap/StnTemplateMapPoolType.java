package org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;

import java.util.HashMap;
import java.util.Map;

// 水池類型統計者
public class StnTemplateMapPoolType extends AbstractStnTemplateMap {
    private final Map<Integer, TemplateStn> stnPoolTypeMap; // 各水池類型統計 <水池索引, 基礎統計物件>

    public StnTemplateMapPoolType(AgencyStnConfig config) {
        super(config);
        this.stnPoolTypeMap = new HashMap<>();
    }

    // 更新統計物件
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 取得統計物件
        TemplateStn stn = super.getStn(stnPoolTypeMap, boardGtrResult.getStnResult().getPoolStatisticTypeIndex());

        // 2. 更新統計物件
        stn.update(boardGtrResult);

        // 3. 更新各統計
        this.stnPoolTypeMap.put(boardGtrResult.getStnResult().getPoolStatisticTypeIndex(), stn);
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        // 1. 取得統計物件
        TemplateStn stn = super.getStn(stnPoolTypeMap, boardGtrReturnResult.getStnResult().getPoolStatisticTypeIndex());

        // 2. 更新統計物件
        stn.updateReturnResult(boardGtrReturnResult);

        // 3. 更新各統計
        this.stnPoolTypeMap.put(boardGtrReturnResult.getStnResult().getPoolStatisticTypeIndex(), stn);
    }

    // 印出統計資訊
    @Override
    public void print() {
        this.stnPoolTypeMap.forEach((stnIndex, stn) -> super.printTitleStn("水池類型資訊", stnIndex, stn));
    }
}
