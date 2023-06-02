package org.lsj.gs.mathBoardGtr.core;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtil;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.ConstStr;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.StnResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.utils.JsonUtil;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

// 抽象局產生器
public abstract class AbstractBoardGtr implements IBoardGtr {
    protected GamePlayerSimulation player; // 玩家資訊
    protected PoolCtr poolCtr; // 水池計算器
    protected TableFieldConfig tableFieldConfig; // 牌桌場域設定
    protected ControlAlgorithmConfig controlAlgorithmConfig; // 除錯設定
    protected JsonUtil jsonUtil; // 共用工具包
    protected static AtomicInteger lastTableId = new AtomicInteger(0); // 最後桌號

    public AbstractBoardGtr(GamePlayerSimulation player, PoolCtr poolCtr, TableFieldConfig tableFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig){
        this.player = player;
        this.poolCtr = poolCtr;
        this.tableFieldConfig = tableFieldConfig;
        this.controlAlgorithmConfig = controlAlgorithmConfig;
        this.jsonUtil = new JsonUtil();
    }

    // 產生局結果
    @Override
    public abstract BoardGtrResult calculateOneBoardResult();

    // 產生統計結果
    @Override
    public StnResult calculateStatisticResult(){
        return new StnResult(
                this.tableFieldConfig.getGameId(),
                this.tableFieldConfig.getFieldIndex(),
                player.getUser().getUid(),
                ConstStr.PoolStatisticType.NORMAL.getCode()
        );
    }

    // 更新局結果
    @Override
    public void update(BoardGtrResult boardGtrResult){
        // 1. 更新玩家餘額
        this.player.update(boardGtrResult);

        // 2. 更新水池
        this.poolCtr.update(boardGtrResult);
    }

    // 判斷能否繼續執行遊戲
    @Override
    public boolean canPlay(){
        return this.player.canPlay(this.tableFieldConfig.getLimitMin());
    }


    //* 共用工具 *//

    // 隨機倍數
    protected int calculateRandomIntElement(IRandomUtil randomUtil, List<Integer> rateList){
        if(Objects.isNull(rateList)){
            return -1;
        }

        return randomUtil.getRandomElement(rateList, ConstMathCommon.AccuracyType.MILLION);
    }
}
