package org.lsj.gs.math.core.common.gameAdjust.module.poolControlHlr;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;

// 抽象水池控制處理器
public abstract class AbstractPoolControlHlr implements IPoolControlHlr {
    protected final PoolCtr poolCtr; // 水池控制器

    public AbstractPoolControlHlr(PoolCtr poolCtr) {
        this.poolCtr = poolCtr;
    }

    /* 保證控制相關 */
    // 保證控制
    protected void controlGuarantee(IDealCtr dealCtr, GameAdjustParameter gameAdjustParameter){
        // 1. 預設發牌
        GameAdjustResult gameAdjustResult = dealCtr.deal(gameAdjustParameter);

        // 2. 保頂發牌
        if(this.poolCtr.getFeeRateTop() < this.calculateCurrentCompanyProfitRate(dealCtr, gameAdjustResult)){
            gameAdjustResult = dealCtr.dealGameResultBigger(gameAdjustResult, gameAdjustParameter);
        }

        // 3. 保底發牌
        if(this.calculateCurrentCompanyProfitRate(dealCtr, gameAdjustResult) < this.poolCtr.getFeeRate()){
            gameAdjustResult = dealCtr.dealGameResultAbsoluteLoss(gameAdjustParameter);
        }

        // 4. 應用
        dealCtr.applyPreGameResult(gameAdjustResult.getPreGameResult());
    }

    // 保底控制
    protected void controlGuaranteeBottom(IDealCtr dealCtr, GameAdjustParameter gameAdjustParameter) {
        // 1. 預設發牌
        GameAdjustResult gameAdjustResult = dealCtr.deal(gameAdjustParameter);

        // 2. 保底發牌
        if(this.calculateCurrentCompanyProfitRate(dealCtr, gameAdjustResult) < this.poolCtr.getFeeRate()){
            gameAdjustResult = dealCtr.dealGameResultAbsoluteLoss(gameAdjustParameter);
        }

        // 3. 應用
        dealCtr.applyPreGameResult(gameAdjustResult.getPreGameResult());
    }



    /* 公司利潤率相關 */
    // 計算當前公司利潤率
    protected double calculateCurrentCompanyProfitRate(IDealCtr dealCtr, GameAdjustResult gameAdjustResult){
        return this.poolCtr.calculateCurrentCompanyProfitRate(
                dealCtr.calculateHumanValidBet(gameAdjustResult),
                dealCtr.calculateHumanScore(gameAdjustResult)
        );
    }
}
