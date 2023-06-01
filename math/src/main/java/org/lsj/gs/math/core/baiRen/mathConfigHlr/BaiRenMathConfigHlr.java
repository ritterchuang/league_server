package org.lsj.gs.math.core.baiRen.mathConfigHlr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.AbstractTableGameConfigBaiRen;
import org.lsj.gs.math.core.baiRen.baseCtr.BaseCtr;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 數值設定處理器
public class BaiRenMathConfigHlr {
    private final BaseCtr baseCtr; // 底注計算器
    private final TableFieldConfig tableFieldConfig; // 牌桌房間設定
    private final AbstractTableGameConfigBaiRen tableGameConfig; // 牌桌遊戲設定

    public BaiRenMathConfigHlr(TableFieldConfig tableFieldConfig, AbstractTableGameConfigBaiRen tableGameConfig) {
        this.baseCtr = new BaseCtr();
        this.tableFieldConfig = tableFieldConfig;
        this.tableGameConfig = tableGameConfig;
    }

    public BaiRenMathConfig getBaiRenMathConfig(){
        if(this.tableFieldConfig.getPlay() == 1){
            return new BaiRenMathConfig(
                    this.tableGameConfig.getMathConfigHlrConfig().getBaiRenTimeConfigPlay1().getBetTimeSec(),
                    this.tableGameConfig.getMathConfigHlrConfig().getBaiRenTimeConfigPlay1().getEndTimeSec(),
                    this.calculateRoomBetTopLimitList());
        }

        return new BaiRenMathConfig(
                this.tableGameConfig.getMathConfigHlrConfig().getBaiRenTimeConfigPlay2().getBetTimeSec(),
                this.tableGameConfig.getMathConfigHlrConfig().getBaiRenTimeConfigPlay2().getEndTimeSec(),
                this.calculateRoomBetTopLimitList()
        );
    }

    // 計算房間押注上下限陣列
    private List<Integer> calculateRoomBetTopLimitList() {
        // 1. 計算合理的底注
        int validBase = this.baseCtr.calculateValidBaseInt(this.tableFieldConfig.getBase());

        // 2. 計算房間押注下限
        int roomBetLowerLimit = this.calculateRoomBetLowerLimit(validBase);

        // 3. 計算房間押注上限
        int roomBetUpperLimit = this.calculateRoomBetUpperLimit(validBase);

        // 4. 封裝
        return new ArrayList<>(){{
            add(roomBetLowerLimit);
            add(roomBetUpperLimit);
        }};
    }

    // 計算房間押注下限
    private int calculateRoomBetLowerLimit(int base) {
        // 計算押注籌碼列表
        List<Integer> chipsList = new ArrayList<>();
        this.tableGameConfig.getBetAreaCtrGameConfig().getChipsOddsList().forEach(d ->
                chipsList.add(Math.max((int) Math.round(MathUtil.multiply(d, base)), 1))
        );

        return Collections.min(chipsList);
    }

    // 計算房間押注上限
    private int calculateRoomBetUpperLimit(int base) {
        // 計算押注區域上限
        List<Integer> areasTopLimitList = new ArrayList<>();
        this.tableGameConfig.getBetAreaCtrGameConfig().getAreasTopLimitOddsMap().forEach((areaId, odds) ->
                areasTopLimitList.add(Math.max((int) Math.round(MathUtil.multiply(odds, base)), 1))
        );

        return Collections.max(areasTopLimitList);
    }

    public void reset() {
    }
}
