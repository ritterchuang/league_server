package org.lsj.gs.math.core.common.robotLogic.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Objects;

// 抽象機器人邏輯
public class AbstractRobotLogic {
    protected final GamePlayerHlr gamePlayerHlr; // 遊戲玩家管理器
    protected final ITableUtil tableUtil; // 牌桌工具包

    public AbstractRobotLogic(GamePlayerHlr gamePlayerHlr, ITableUtil tableUtil) {
        this.gamePlayerHlr = gamePlayerHlr;
        this.tableUtil = tableUtil;
    }

    //* 共用工具 *//
    // 隨機整數
    protected int calculateRandomElementInt(List<Integer> elementList){
        if(Objects.isNull(elementList) || elementList.isEmpty()){
            return -1;
        }

        return this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(elementList, ConstMathCommon.AccuracyType.MILLION);
    }
}
