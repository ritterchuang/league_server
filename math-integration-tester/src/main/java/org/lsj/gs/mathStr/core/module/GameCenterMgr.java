package org.lsj.gs.mathStr.core.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.JavaRandomUtil;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.GameCenterMgrConfig;
import org.lsj.gs.user.IUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 遊戲中心管理器
public class GameCenterMgr {
    private final GameCenterMgrConfig gameCenterMgrConfig; // 遊戲中心管理器設定
    private final JavaRandomUtil randomUtil; // 隨機工具包

    public GameCenterMgr(GameCenterMgrConfig gameCenterMgrConfig) {
        this.gameCenterMgrConfig = gameCenterMgrConfig;
        this.randomUtil = new JavaRandomUtil();
    }

    // 計算房間設定
    public PlayGameFieldConfig calculatePlayFieldConfig(IUser user) {
        // 1. 過濾可進入房間陣列
        List<PlayGameFieldConfig> canEnterPlayGameFieldConfigList = Arrays.stream(this.gameCenterMgrConfig.getPlayGameFieldConfigArray()).filter(
                playGameFieldConfig -> user.getBalance() >= playGameFieldConfig.getTableFieldConfig().getCompanyFieldObj().getLimitMin()).collect(Collectors.toList());

        // 2. 決定房間設定索引
        List<Integer> weightList = new ArrayList<>();
        canEnterPlayGameFieldConfigList.forEach(playGameFieldConfig -> weightList.add(playGameFieldConfig.getWeight()));
        int playFieldArrayIndex = this.randomUtil.getArrayIndexByWeightWithAccuracy(weightList, ConstMathCommon.AccuracyType.A32768);

        // 3. 回傳房間設定
        return this.gameCenterMgrConfig.getPlayGameFieldConfigArray()[playFieldArrayIndex];
    }

    public GameCenterMgrConfig getGameCenterMgrConfig() {
        return gameCenterMgrConfig;
    }
}
