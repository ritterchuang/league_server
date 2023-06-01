package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDragonTreasure;

import java.util.List;
import java.util.Map;

// 機器人打擊結果計算者設定滾輪
public class RobotResultCtrConfigExtendDragonTreasure extends RobotResultCtrConfigExtend{
    private final int[] dragonBallOddsArray; // 龍珠倍數列表
    private final int[][] dragonBallEndCountAndWeightArray; // 神龍結束顆數與權重陣列
    private final Map<Integer, List<int[]>> endBallCountToShowOddsListMap; // <結束顆數, 龍珠表演陣列列表>
    private final Map<Integer, int[]> endBallCountToShowOddsWeightArrayMap; // <結束顆數, 龍珠表演陣列權重列表>
    private final int[][] dragonExtraMultiplierAndWeightArray; // 神龍額外倍數與權重陣列

    public RobotResultCtrConfigExtendDragonTreasure(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendDragonTreasure hitTypeConfigExtendDragonTreasure = (HitTypeConfigExtendDragonTreasure)HitTypeConfigExtend;
        this.dragonBallOddsArray = hitTypeConfigExtendDragonTreasure.getDragonBallOddsArray();
        this.dragonBallEndCountAndWeightArray = hitTypeConfigExtendDragonTreasure.getDragonBallEndCountAndWeightArray();
        this.endBallCountToShowOddsListMap = hitTypeConfigExtendDragonTreasure.getEndBallCountToShowOddsListMap();
        this.endBallCountToShowOddsWeightArrayMap = hitTypeConfigExtendDragonTreasure.getEndBallCountToShowOddsWeightArrayMap();
        this.dragonExtraMultiplierAndWeightArray = hitTypeConfigExtendDragonTreasure.getDragonExtraMultiplierAndWeightArray();
    }

    public int[] getDragonBallOddsArray() {
        return dragonBallOddsArray;
    }

    public int[][] getDragonBallEndCountAndWeightArray() {
        return dragonBallEndCountAndWeightArray;
    }

    public Map<Integer, List<int[]>> getEndBallCountToShowOddsListMap() {
        return endBallCountToShowOddsListMap;
    }

    public Map<Integer, int[]> getEndBallCountToShowOddsWeightArrayMap() {
        return endBallCountToShowOddsWeightArrayMap;
    }

    public int[][] getDragonExtraMultiplierAndWeightArray() {
        return dragonExtraMultiplierAndWeightArray;
    }
}
