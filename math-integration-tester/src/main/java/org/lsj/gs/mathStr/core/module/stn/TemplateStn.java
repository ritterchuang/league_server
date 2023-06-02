package org.lsj.gs.mathStr.core.module.stn;

import org.lsj.enums.GameId;
import org.lsj.enums.GameType;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.fish.GameResultExtendFishJava;
import org.lsj.utils.MathUtil;

import java.text.DecimalFormat;

// 基礎統計物件
public class TemplateStn implements IStn {
    private int totalRound; // 總場次
    private double totalBet; // 總押注
    private double totalScore; // 玩家總淨利
    private int totalWinRound; // 贏場次
    private double totalWinBet; // 贏押注
    private double totalWinScore; // 玩家贏淨利
    private int totalLossRound; // 輸場次
    private double totalLossBet; // 輸押注
    private double totalLossScore; // 玩家輸淨利
    private double totalOdds; // 倍率和
    private double totalOddsSquare; // 倍率平方和
    private double maxOdds; // 最大倍率

    public TemplateStn() {
        this.totalRound = 0;
        this.totalBet = 0.0;
        this.totalScore = 0.0;
        this.totalWinRound = 0;
        this.totalWinBet = 0.0;
        this.totalWinScore = 0.0;
        this.totalLossRound = 0;
        this.totalLossBet = 0.0;
        this.totalLossScore = 0.0;
        this.totalOdds = 0.0;
        this.totalOddsSquare = 0.0;
        this.maxOdds = 0.0;
    }

    // 更新統計資訊
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 更新總體資訊
        this.totalRound += 1;
        this.totalBet += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet();
        this.totalScore += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore();

        // 2. 計算倍率和
        double odds = this.calculateOdds(boardGtrResult);
        this.totalOdds += odds;
        this.totalOddsSquare += MathUtil.multiply(odds, odds);

        // 3. 更新最大倍率
        if (odds > this.maxOdds) {
            this.maxOdds = odds;
        }

        // 4. 更新贏資訊
        if(boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore() > 0){
            this.totalWinRound += 1;
            this.totalWinBet += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet();
            this.totalWinScore += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore();
        // 5. 更新輸資訊
        }else if(boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore() < 0){
            this.totalLossRound += 1;
            this.totalLossBet += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet();
            this.totalLossScore += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore();
        }
    }

    // 更新和局
    protected void updateTie(BoardGtrResult boardGtrResult){
        // 更新和局資訊
        if(boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore() == 0 &&
                boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet() > 0){
            this.totalWinRound += 1;
            this.totalWinBet += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet();
            this.totalWinScore += boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore();
        }
    }

    // 計算倍數
    private double calculateOdds(BoardGtrResult boardGtrResult) {
        double odds = 0.0;
        GameType gameType = GameId.fromId(boardGtrResult.getStnResult().getGameIndex()).getGameType();

        switch (gameType) {
            case BANKER:
            case BAIREN:
            case BATTLE:
                return this.calculateCardOdds(boardGtrResult);
            case FISH:
                return this.calculateFishOdds(boardGtrResult);
            case SLOT:
                return this.calculateSlotOdds(boardGtrResult);
            default:
                return 0.0;
        }
    }

    // 計算棋牌倍數
    private double calculateCardOdds(BoardGtrResult boardGtrResult) {
        if (boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet() != 0.0) {
            return MathUtil.divide(boardGtrResult.getGameBetLogResult().getGameBetLogObj().getReturnAward(), boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet());
        } else {
            return 0.0;
        }
    }

    // 計算魚機倍數
    private double calculateFishOdds(BoardGtrResult boardGtrResult) {
        GameResultExtendFishJava gameResultExtendFishJava = (GameResultExtendFishJava) boardGtrResult.getGameResultExtend();
        return MathUtil.divide(boardGtrResult.getGameBetLogResult().getGameBetLogObj().getReturnAward(), gameResultExtendFishJava.getCredit());
    }

    // 計算虎機倍數
    private double calculateSlotOdds(BoardGtrResult boardGtrResult) {
        return MathUtil.divide(boardGtrResult.getGameBetLogResult().getGameBetLogObj().getReturnAward(), boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet());
    }

    // 計算標準差
    private double calculateStandardDeviation() {
        return Math.sqrt((this.totalOddsSquare / (double)this.totalRound) - (this.totalOdds / (double)this.totalRound) * (this.totalOdds / (double)this.totalRound));
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        // 1. 若無值，直接返還
        if (boardGtrReturnResult.getReturnResult().isEmpty()) {
            return;
        }

        // 2. 更新統計資訊
        this.totalBet += boardGtrReturnResult.getReturnResult().get().getGameBetLogObj().getValidBet();
        this.totalScore += boardGtrReturnResult.getReturnResult().get().getGameBetLogObj().getScore();
    }


    // 印出統計資訊
    @Override
    public void print() {
        System.out.println("公司總場次: " + this.totalRound);
        System.out.println("公司總押注: " + new DecimalFormat("#").format(this.totalBet));
        System.out.println("公司總淨利: " + new DecimalFormat("#").format(this.totalScore * -1.00));
        System.out.println("公司利潤率: " + new DecimalFormat("#.##").format(this.totalScore / this.totalBet * -1.00 * 100.00) + "%");
        System.out.println("玩家勝場率: " + new DecimalFormat("#.##").format(((double)(this.totalWinRound)) / ((double)(this.totalRound)) * 100.00) + "%");
        System.out.println("玩家均勝注: " + new DecimalFormat("#.##").format(this.totalWinBet / (double)(this.totalWinRound)));
        System.out.println("玩家均勝分: " + new DecimalFormat("#.##").format(this.totalWinScore / (double)(this.totalWinRound)));
        System.out.println("玩家輸場率: " + new DecimalFormat("#.##").format(((double)(this.totalLossRound)) / ((double)(this.totalRound)) * 100.00) + "%");
        System.out.println("玩家均輸注: " + new DecimalFormat("#.##").format(this.totalLossBet / (double)(this.totalWinRound)));
        System.out.println("玩家均輸分: " + new DecimalFormat("#.##").format(this.totalLossScore / (double)(this.totalRound - this.totalWinRound) * -1.00));
        System.out.println("玩家和場率: " + new DecimalFormat("#.##").format(((double)(this.totalRound - this.totalWinRound - this.totalLossRound)) / ((double)(this.totalRound)) * 100.00) + "%");
        System.out.println("倍率標準差: " + new DecimalFormat("#.##").format(this.calculateStandardDeviation()));
        System.out.println("最大倍數: " + new DecimalFormat("#.##").format(this.maxOdds));
    }

    public int getTotalRound() {return totalRound;}

    public int getTotalWinRound() {return totalWinRound;}

    public double getTotalBet() {
        return totalBet;
    }

    public double getTotalScore() {
        return totalScore;
    }
}
