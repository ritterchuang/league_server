package org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.fish.GameResultExtendFishJava;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;
import org.lsj.gs.mathStr.core.module.stn.TemplateStnFish;

import java.util.HashMap;
import java.util.Map;

// 捕魚遊戲統計者
public class GameStnExtendFish extends GameStnExtend {
    private final Map<Integer, Map<Integer, TemplateStnFish>> bulletTargetStnExtendMap; // 客製子彈目標統計對應表 <子彈索引, 目標索引, 基礎統計物件>
    private final TemplateStn returnResultStn; // 返還統計者
    private final TemplateStn totalStn; // 整體統計者

    public GameStnExtendFish(AgencyStnConfig config) {
        super(config);
        this.bulletTargetStnExtendMap = new HashMap<>();
        this.returnResultStn = new TemplateStn();
        this.totalStn = new TemplateStn();
    }

    // 更新統計資訊
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 客製遊戲結果轉型
        GameResultExtendFishJava gameResultExtend = (GameResultExtendFishJava) boardGtrResult.getGameResultExtend();

        // 2. 取得統計物件
        TemplateStnFish stn = this.getStn(gameResultExtend.getBulletIndex(), gameResultExtend.getTargetIndex());

        // 3. 更新統計物件
        stn.update(boardGtrResult);
        this.totalStn.update(boardGtrResult);

        // 4. 更新各統計
        this.bulletTargetStnExtendMap.get(gameResultExtend.getBulletIndex()).put(gameResultExtend.getTargetIndex(), stn);
    }

    // 更新返還統計
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        this.returnResultStn.updateReturnResult(boardGtrReturnResult);
        this.totalStn.updateReturnResult(boardGtrReturnResult);
    }

    private TemplateStnFish getStn(int bulletIndex, int targetIndex){
        // 1. 取得目標對應統計物件
        Map<Integer, TemplateStnFish> targetIndexStnMap = this.bulletTargetStnExtendMap.computeIfAbsent(bulletIndex, key -> new HashMap<>());

        // 2. 取得統計物件
        return targetIndexStnMap.computeIfAbsent(targetIndex, key -> new TemplateStnFish());
    }

    // 印出統計資訊
    @Override
    public void print() {
        this.bulletTargetStnExtendMap.forEach(this::printBulletTargetStnExtendMap);
        this.printReturnResult();
    }

    // 印出子彈目標統計對應表
    private void printBulletTargetStnExtendMap(int bulletIndex, Map<Integer, TemplateStnFish> targetIndexStnMap){
        // 1. 印出子彈標題
        super.printTitle("子彈統計", bulletIndex);

        // 2. 印出目標標題
        System.out.printf("%4s %7s %10s %10s %9s %7s %8s %7s %8s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s%n",
                "目標索引",
                "打擊次數",
                "總押碼",
                "總投注",
                "總玩家淨利",
                "公司利潤率",
                "子彈Rtp",
                "獲獎次數",
                "獲獎率",
                "平均獲獎倍數",
                "最大倍數",
                "倍數標準差",
                "目標擊殺次數",
                "目標擊殺率",
                "目標平均擊殺倍數",
                "特殊事件觸發數",
                "特殊事件觸發率",
                "特數事件平均倍數",
                "特殊事件Rtp",
                "特殊事件Rtp占比",
                "獲得獎勵子彈次數",
                "獲得獎勵子彈總顆數",
                "獎勵子彈獲取率",
                "獎勵子彈平均獲取數");

        // 3. 印出目標內容
        targetIndexStnMap.forEach((targetIndex, templateStnFish) ->
            System.out.printf("%6d %10d %12.2f %12.2f %12.2f %8.2f%% %8.2f%% %10d %8.2f%% %14.2f %14.2f %14.2f %14d %14.2f%% %14.2f %14d %14.2f%% %14.2f %12.2f%% %12.2f%% %14d %14d %14.2f%% %14.2f%n",
                    targetIndex, // 目標索引 %6d
                    templateStnFish.getTotalRound(), // 打擊次數 %10d
                    templateStnFish.getTotalBet(), // 總押碼 %12.2f
                    templateStnFish.getTotalCredit(), // 總投注 %12.2f
                    templateStnFish.getTotalScore(), // 總玩家淨利 %12.2f
                    templateStnFish.getTotalScore() / templateStnFish.getTotalBet() * -1.0 * 100.0, // 公司利潤率 %8.2f%%
                    ((templateStnFish.getTotalScore() + templateStnFish.getTotalBet()) / templateStnFish.getTotalCredit()) * 100.0, // 子彈Rtp %8.2f%%
                    templateStnFish.getTotalWinRound(), // 獲獎次數 %10d%
                    (double)templateStnFish.getTotalWinRound() / (double)templateStnFish.getTotalRound() * 100.0, // 獲獎率 %8.2f%%
                    templateStnFish.getTotalOdds() / (double)templateStnFish.getTotalWinRound() , // 平均獲獎倍數 %14.2f
                    templateStnFish.getMaxOdds(), // 最大倍數
                    Math.sqrt(templateStnFish.getTotalOddsSquare() / (double)templateStnFish.getTotalRound() - (templateStnFish.getTotalOdds() / (double)templateStnFish.getTotalRound()) * (templateStnFish.getTotalOdds() / (double)templateStnFish.getTotalRound())), // 倍數標準差 %14.2f
                    templateStnFish.getKillCount(), // 擊殺次數 %14d
                    (double)templateStnFish.getKillCount() / (double)templateStnFish.getTotalRound() * 100.0, // 擊殺率 %14.2f%%
                    templateStnFish.getTargetTotalOdds() / (double)templateStnFish.getKillCount() , // 平均擊殺倍數 %14.2f
                    templateStnFish.getTotalSpecialFeatureCount(), // 特殊事件觸發數 %14d
                    (double)templateStnFish.getTotalSpecialFeatureCount() / (double)templateStnFish.getTotalRound() * 100.0, // 特殊事件觸發率 %14.2f%%
                    templateStnFish.getTotalSpecialFeatureOdds() / (double)templateStnFish.getTotalSpecialFeatureCount() , // 特數事件平均倍數 %14.2f
                    (templateStnFish.getTotalSpecialFeatureWin() / templateStnFish.getTotalCredit()) * 100.0, // 特殊事件Rtp %12.2f%%
                    (templateStnFish.getTotalSpecialFeatureWin() / (templateStnFish.getTotalScore() + templateStnFish.getTotalBet())), // 特殊事件Rtp占比 %12.2f%% [特殊事件RTP / 子彈RTP]
                    templateStnFish.getTotalAwardBulletCount(), // 獲得獎勵子彈次數 %14d
                    templateStnFish.getTotalAwardBulletAmount(), // 獲得獎勵子彈總顆數 %14d
                    (double)templateStnFish.getTotalAwardBulletCount() / (double)templateStnFish.getTotalRound(), // 獎勵子彈獲取率 %14.2f%%
                    (double)templateStnFish.getTotalAwardBulletAmount() / (double)templateStnFish.getTotalAwardBulletCount() // 獎勵子彈平均獲取數 %14.2f
            )
        );
    }

    // 印出返還統計
    private void printReturnResult() {
        System.out.println("[" + "返還統計" + "]");
        System.out.printf("%4s %8s%n", "返還金額", "返還Rtp");
        System.out.printf("%6.2f %8.2f%%%n%n",
                this.returnResultStn.getTotalScore(),
                this.returnResultStn.getTotalScore() / this.totalStn.getTotalBet());
    }
}
