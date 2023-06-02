package org.lsj.gs.math.core.common.gameAdjust.module.dealCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象發牌器
public abstract class AbstractDealCtr implements IDealCtr {
    protected final IShuffle shuffle; // 發牌器
    protected final ITableUtil tableUtil; // 牌桌工具包
    protected final int ABSOLUTE_RETRY_COUNT = 100; // 必系列嘗試次數
    private enum WinLossType{ WIN, LOSS} // 絕對輸贏類型

    public AbstractDealCtr(IShuffle shuffle, ITableUtil tableUtil) {
        this.shuffle = shuffle;
        this.tableUtil = tableUtil;
    }

    /* 發牌器相關 */
    // 設定開牌類型
    public void setShuffleType(ConstMathCommon.ShuffleType shuffleType) {
        this.shuffle.setShuffleType(shuffleType);
    }


    /* 預設開牌相關 */
    // 計算遊戲結果
    protected GameAdjustResult dealGameResult(GameAdjustParameter gameAdjustParameter){
        return this.shuffle.calculateGameAdjustResult(this.shuffle.dealPreGameAdjustResult(gameAdjustParameter));
    }


    /* 相對開牌相關 */
    // 開相對大牌
    public GameAdjustResult dealGameResultBigger(GameAdjustResult gameAdjustResult, GameAdjustParameter gameAdjustParameter) {
        // 1. 二次開牌
        GameAdjustResult secondGameAdjustResult = this.dealGameResult(gameAdjustParameter);

        // 2. 回傳相對大結果
        if (this.shuffle.calculateHumanScore(gameAdjustResult) >= this.shuffle.calculateHumanScore(secondGameAdjustResult)) {
            return gameAdjustResult;
        }

        return secondGameAdjustResult;
    }



    /* 絕對開牌相關 */
    // 絕對贏開牌
    public GameAdjustResult dealGameResultAbsoluteWin(GameAdjustParameter gameAdjustParameter){
        return this.dealGameResultAbsolute(WinLossType.WIN, gameAdjustParameter);
    }

    // 絕對輸開牌
    public GameAdjustResult dealGameResultAbsoluteLoss(GameAdjustParameter gameAdjustParameter){
        return this.dealGameResultAbsolute(WinLossType.LOSS, gameAdjustParameter);
    }

    // 絕對開牌
    private GameAdjustResult dealGameResultAbsolute(WinLossType winLossType, GameAdjustParameter gameAdjustParameter){
        GameAdjustResult result = null;

        for(int retryIndex = 0; retryIndex < this.ABSOLUTE_RETRY_COUNT; retryIndex++){
            result = this.dealGameResult(gameAdjustParameter);
            if(this.checkAbsolute(winLossType, this.shuffle.calculateHumanScore(result))){
                return result;
            }
        }
        return result;
    }

    // 檢驗絕對輸贏符合性
    private boolean checkAbsolute(WinLossType winLossType, double score){
        switch (winLossType){
            case WIN: return (score > 0.0);
            case LOSS: return (score <= 0.0);
            default: return false;
        }
    }



    /* 查詢相關 */
    // 計算真人淨利
    public double calculateHumanScore(GameAdjustResult gameAdjustResult){
        return this.shuffle.calculateHumanScore(gameAdjustResult);
    }

    // 計算真人有效投注
    public double calculateHumanValidBet(GameAdjustResult gameAdjustResult){
        return this.shuffle.calculateHumanValidBet(gameAdjustResult);
    }



    /* 應用相關 */
    // 應用預遊戲結果
    public void applyPreGameResult(PreGameAdjustResult preGameAdjustResult){
        this.shuffle.applyPreGameAdjustResult(preGameAdjustResult);
    }
}
