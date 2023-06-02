package org.lsj.gs.math.core.common.gameAdjust.module.dealCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 梯度發牌器
public class DealCtrGradient extends AbstractDealCtr {
    public DealCtrGradient(IShuffle shuffle, ITableUtil tableUtil) {
        super(shuffle, tableUtil);
    }

    // 開牌
    @Override
    public GameAdjustResult deal(GameAdjustParameter gameAdjustParameter) {
        // 1. 必贏遊戲結果
        GameAdjustResult winGameAdjustResult = super.dealGameResultAbsoluteWin(gameAdjustParameter);

        // 2. 必輸遊戲結果
        GameAdjustResult lossGameAdjustResult = super.dealGameResultAbsoluteLoss(gameAdjustParameter);

        // 3. 計算出必贏遊戲結果機率
        double winGameResultProb = this.calculateChooseGameResult1Prob(winGameAdjustResult, lossGameAdjustResult, gameAdjustParameter.getAdjustParameter().getCompanyProfitRateMean() * -1);

        // 4. 決定遊戲結果
        if(this.tableUtil.getMainRandomUtil().isHitWithAccuracy(winGameResultProb, ConstMathCommon.AccuracyType.MILLION)){
            return winGameAdjustResult;
        }
        return lossGameAdjustResult;
    }

    // 計算出一號結果機率值
    private double calculateChooseGameResult1Prob(GameAdjustResult gameAdjustResult1, GameAdjustResult gameAdjustResult2, double profitRate){
        // 1. 準備參數
        double w1 = this.shuffle.calculateHumanScore(gameAdjustResult1);
        double w2 = this.shuffle.calculateHumanScore(gameAdjustResult2);
        double b1 = this.shuffle.calculateHumanValidBet(gameAdjustResult1);
        double b2 = this.shuffle.calculateHumanValidBet(gameAdjustResult2);
        double numerator = (w2 - b2 * profitRate);
        double denominator = ((w2 - w1) - (b2 - b1) * profitRate);

        // 2. 分母零防呆
        if(denominator == 0.0){ return 0.0;}

        // 3. 計算機率
        double prob = numerator / denominator;

        // 4. 回傳
        if(prob < 0.0){ return 0.0;}
        return Math.min(prob, 1.0);
    }
}
