package org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientTargetGtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendFish;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.TargetInfo;

import java.util.Arrays;
import java.util.stream.Collectors;

// 客製目標產生者
public class ClientTargetGtr implements IClientTargetGtr {
    private final ITableUtil tableUtil; // 牌桌工具包
    private final TargetInfo[] targetInfoArray; // 目標資訊陣列

    public ClientTargetGtr(ITableUtil tableUtil, GameTypeConfigExtendFish gameTypeConfigExtendFish) {
        this.tableUtil = tableUtil;
        this.targetInfoArray = gameTypeConfigExtendFish.getPlayTypeFishConfigExtend().getTargetInfoArray();
    }

    // 依照權重取得目標
    @Override
    public ClientTarget generateWeightedClientTarget() {
        // 1. 取得目標Index
        int index = this.tableUtil.getNotSupportSetRngDataRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.targetInfoArray).map(TargetInfo::getWeight).collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 2. 回傳
        return new ClientTarget(this.targetInfoArray[index].getTargetIndex());
    }
}
