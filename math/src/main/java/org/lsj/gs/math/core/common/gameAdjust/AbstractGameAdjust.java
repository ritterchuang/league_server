package org.lsj.gs.math.core.common.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmType;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.dealCtr.DealCtrMgr;
import org.lsj.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.GameAdjustParameterCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.poolControlHlr.IPoolControlHlr;
import org.lsj.gs.math.core.common.gameAdjust.module.poolControlHlr.PoolControlHlrMgr;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象遊戲調控
public abstract class AbstractGameAdjust implements IGameAdjust {
    protected final AlgorithmTypeCtr algorithmTypeCtr; // 遊戲調控設定
    protected final GameAdjustParameterCtr gameAdjustParameterCtr; // 遊戲調控參數計算器
    protected final PoolControlHlrMgr poolControlHlrMgr; // 水池控制處理器管理者
    protected final DealCtrMgr dealCtrMgr; // 發牌器管理者
    protected final PoolCtr poolCtr; // 水池控制器
    protected final ITableUtil tableUtil; // 牌桌工具包

    public AbstractGameAdjust(AlgorithmTypeCtr algorithmTypeCtr, IShuffle shuffle, PoolCtr poolCtr, ITableUtil tableUtil) {
        this.algorithmTypeCtr = algorithmTypeCtr;
        this.gameAdjustParameterCtr = new GameAdjustParameterCtr(poolCtr);
        this.poolControlHlrMgr = new PoolControlHlrMgr(poolCtr);
        this.dealCtrMgr = new DealCtrMgr(shuffle, tableUtil);
        this.poolCtr = poolCtr;
        this.tableUtil = tableUtil;
    }

    // 開啟調控
    @Override
    public void startGameAdjust() {
        this.startGameAdjust(true);
    }

    // 開啟調控
    public void startGameAdjust(boolean needControl){
        // 1. 計算演算法類型
        AlgorithmType algorithmType = this.algorithmTypeCtr.calculateAlgorithmType(needControl, this.poolCtr.getCompanyPersonAdjustType());

        // 2. 計算遊戲調控參數
        GameAdjustParameter gameAdjustParameter = this.gameAdjustParameterCtr.calculateGameAdjustParameter(algorithmType.getPersonAdjustType());

        // 3. 建立水池控制處理器
        IPoolControlHlr poolControlHlr = this.poolControlHlrMgr.createPoolControlHlr(algorithmType.getPoolControlType());

        // 4. 建立開牌器
        IDealCtr dealCtr = this.dealCtrMgr.createDealCtr(algorithmType.getDealType());

        // 5. 開牌器 設定需要調控標誌
        dealCtr.setShuffleType(algorithmType.getShuffleType());

        // 6. 組合開牌
        poolControlHlr.control(dealCtr, gameAdjustParameter);
    }

    @Override
    public void reset(){
        this.algorithmTypeCtr.reset();
        this.gameAdjustParameterCtr.reset();
        this.poolControlHlrMgr.reset();
        this.dealCtrMgr.reset();
    }
}