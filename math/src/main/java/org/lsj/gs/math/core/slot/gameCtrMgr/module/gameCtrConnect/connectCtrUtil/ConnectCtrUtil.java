package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.connectCtrUtil;

import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.connect.ConnectCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil.ScreenPositionCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.utils.MathUtil;

import java.util.List;
import java.util.Optional;

// 連接計算者 工具包
public class ConnectCtrUtil {
    private ConnectCtrCommonUtil connectCtrCommonUtil; // 連接計算共同工具包

    public ConnectCtrUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        this.connectCtrCommonUtil = new ConnectCtrCommonUtil(symbolConfig, payTableConfig);
    }

    // 計算連接得分
    public Optional<ConnectCtrWinResult> calculateConnectCtrWinResult(
            List<Integer> symbolIdList,
            ScreenPositionCluster screenPositionCluster,
            double playerCreditBase,
            ScreenGtrResult screenGtrResult) {

        // 1. 取得賠率表Id
        int payComboId = this.connectCtrCommonUtil.getPayComboIdBySymbolList(symbolIdList);
        if (payComboId == -1) {
            return Optional.empty();
        }

        // 2. 依照傳入分類，取得連線數
        int hitNumber = screenPositionCluster.getSymbolPositionList().size();
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 3. 取得倍數，若為0回傳空
        int hitOdds = this.connectCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 4. 計算額外倍數
        int extraMultiplier = 1;

        // 5. 計算總得分
        double totalWin = MathUtil.multiply(playerCreditBase, hitOdds * extraMultiplier);

        // 6. 計算中獎畫面
        List<List<Boolean>> screenPosition = this.connectCtrCommonUtil.calculateScreenHitPosition(screenGtrResult.getScreenSymbol(), screenPositionCluster);

        // 7. 計算破框中獎畫面
        List<List<Boolean>> dampScreenPosition = this.connectCtrCommonUtil.calculateDampScreenHitPosition(screenPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        return Optional.of(new ConnectCtrWinResult(payComboId, hitNumber, hitOdds, extraMultiplier, totalWin, screenPosition, dampScreenPosition));
    }

    // 計算含倍數連接得分
    public Optional<ConnectCtrWinResult> calculateConnectCtrWinResultWithScreenMultiplier(
            List<Integer> symbolIdList,
            ScreenPositionCluster screenPositionCluster,
            int screenMultiplier,
            double playerCreditBase,
            ScreenGtrResult screenGtrResult) {

        // 1. 取得賠率表Id
        int payComboId = this.connectCtrCommonUtil.getPayComboIdBySymbolList(symbolIdList);
        if (payComboId == -1) {
            return Optional.empty();
        }

        // 2. 依照傳入分類，取得連線數
        int hitNumber = screenPositionCluster.getSymbolPositionList().size();
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 3. 取得倍數，若為0回傳空
        int hitOdds = this.connectCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 4. 計算額外倍數
        int extraMultiplier = screenMultiplier;

        // 5. 計算總得分
        double totalWin = MathUtil.multiply(playerCreditBase, hitOdds * extraMultiplier);

        // 6. 計算中獎畫面
        List<List<Boolean>> screenPosition = this.connectCtrCommonUtil.calculateScreenHitPosition(screenGtrResult.getScreenSymbol(), screenPositionCluster);

        // 7. 計算破框中獎畫面
        List<List<Boolean>> dampScreenPosition = this.connectCtrCommonUtil.calculateDampScreenHitPosition(screenPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        return Optional.of(new ConnectCtrWinResult(payComboId, hitNumber, hitOdds, extraMultiplier, totalWin, screenPosition, dampScreenPosition));
    }
}
