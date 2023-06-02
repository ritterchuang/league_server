package org.lsj.gs.skillTreeGtr;

import com.fasterxml.jackson.core.type.TypeReference;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.JavaRandomUtil;
import org.lsj.gs.pool.AgencyPool;
import org.lsj.gs.skillTreeGtr.core.entity.node.NodeMapChildrenWpr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;
import org.lsj.gs.skillTreeGtr.output.SkillTree;
import org.lsj.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class mainSkillTreePool {
    private static int ROUND_COUNT = 10000000;
    private static int MAX_TREE_COUNT = 100;
    private static final int ABSOLUTE_RETRY_COUNT = 100; // 必系列嘗試次數
    private static final double PROFIT_RATE = -0.03; // 玩家利潤率
    private static AgencyPool agencyPool = new AgencyPool();
    private static PersonExpectDiffPoolCtr personExpectDiffPoolCtr = new PersonExpectDiffPoolCtr(0,0);
    private static IRandomUtil randomUtil = new JavaRandomUtil();

    public static void main(String[] args) {
        // 1. 讀取技巧樹
        String[] skillTreeString = new SkillTree().create();
        ArrayList<NodeMapChildrenWpr<TablePhaseModelSkill>> readSkillTreeList = new ArrayList<>();

        for (int treeIndex = 0; treeIndex < skillTreeString.length; treeIndex++) {
            readSkillTreeList.add(JsonUtil.getInstance().readValueWithoutException(skillTreeString[treeIndex], new TypeReference<>() {}));
        }

        // 2. 取得極值贏分列表
        List<Double> maxScoreList = calculateMaxScoreList(readSkillTreeList);
        List<Double> minScoreList = calculateMinScoreList(readSkillTreeList);

        for (int roundIndex = 0; roundIndex < ROUND_COUNT; roundIndex++) {
            // 計算贏輸分組和 [win, loss]
            int[] winAndLossRandomIndex = calculateWinAndLossIndex(maxScoreList);

            // 3. 選取贏分牌索引
            int winRandomIndex = winAndLossRandomIndex[0];

            // 4. 選取輸分牌索引
            int lossRandomIndex = winAndLossRandomIndex[1];

            // 5 計算梯度開獎機率
            double winProb = calculateWinProb(winRandomIndex, lossRandomIndex, maxScoreList);

            // 6. 校正期望差距機率
            double adjustWinProb = calculateAdjustWinProb(winProb, winRandomIndex, lossRandomIndex, maxScoreList);

            // 7. 計算得分標誌
            boolean winFlag = randomUtil.isHitWithAccuracy(adjustWinProb, ConstMathCommon.AccuracyType.MILLION);

            // 8. 模擬玩家操作 隨機得分 與 隨機押注 [win, bet]
            double[] humanResult = calculateHumanResult(winFlag, winRandomIndex, lossRandomIndex, maxScoreList, minScoreList);

            // 9. 更新代理水池
            agencyPool.setTotalScore(agencyPool.getTotalScore() + humanResult[0]);
            agencyPool.setTotalBet(agencyPool.getTotalBet() + humanResult[1]);

            // 10. 計算理論差距 [win, bet]
            double[] expectDiffResult = calculateExpectDiffResult(winFlag, humanResult, winRandomIndex, lossRandomIndex, maxScoreList);

            // 11. 更新 個人期望差水池
            personExpectDiffPoolCtr.updateExpectWinDiff(expectDiffResult[0]);
            personExpectDiffPoolCtr.updateExpectBetDiff(expectDiffResult[1]);
        }

        // 12. 打印結果
        System.out.println("profit rate: " + agencyPool.getTotalScore() / agencyPool.getTotalBet());
        System.out.println("total win: " + agencyPool.getTotalScore());
        System.out.println("total bet: " + agencyPool.getTotalBet());
        System.out.println("diff win: " + personExpectDiffPoolCtr.getExpectWinDiff());
        System.out.println("diff bet: " + personExpectDiffPoolCtr.getExpectBetDiff());
        System.out.println("adjust profit rate: " +
                (personExpectDiffPoolCtr.getExpectWinDiff() + agencyPool.getTotalScore()) /
                (personExpectDiffPoolCtr.getExpectBetDiff() + agencyPool.getTotalBet()));

    }

    // [win, loss]
    private static int[] calculateWinAndLossIndex(List<Double> maxScoreList) {
        // 1. 判斷目前水池
        double currentDiffWin = personExpectDiffPoolCtr.getExpectWinDiff();
        double currentDiffBet = personExpectDiffPoolCtr.getExpectBetDiff();

        // 2. 相同方向
        boolean sameFlag = (currentDiffWin * currentDiffBet > 0);
        boolean differentFlag = (currentDiffWin * currentDiffBet < 0);

        int winIndex = calculateWinIndex(maxScoreList);
        int lossIndex = calculateLossIndex(maxScoreList);

        // 3. 相同方向挑選
//        if(sameFlag){
//            for (int tryIndex = 0; tryIndex < ABSOLUTE_RETRY_COUNT; tryIndex++) {
//                winIndex = calculateWinIndex(maxScoreList);
//                lossIndex = calculateLossIndex(maxScoreList);
//
//                double adjustWin = maxScoreList.get(winIndex) - maxScoreList.get(lossIndex);
//                double adjustBet = Math.abs(maxScoreList.get(winIndex)) - Math.abs(maxScoreList.get(lossIndex));
//
//                if(adjustWin * adjustBet > 0){
//                    return new int[]{winIndex, lossIndex};
//                }
//            }
//        }
//
//        if(differentFlag){
//            for (int tryIndex = 0; tryIndex < ABSOLUTE_RETRY_COUNT; tryIndex++) {
//                winIndex = calculateWinIndex(maxScoreList);
//                lossIndex = calculateLossIndex(maxScoreList);
//
//                double adjustWin = maxScoreList.get(winIndex) - maxScoreList.get(lossIndex);
//                double adjustBet = Math.abs(maxScoreList.get(winIndex)) - Math.abs(maxScoreList.get(lossIndex));
//
//                if (adjustWin * adjustBet < 0) {
//                    return new int[]{winIndex, lossIndex};
//                }
//            }
//        }

        winIndex = calculateWinIndex(maxScoreList);
        lossIndex = calculateLossIndex(maxScoreList);

        double adjustWin = maxScoreList.get(winIndex) - maxScoreList.get(lossIndex);
        double adjustBet = Math.abs(maxScoreList.get(winIndex)) - Math.abs(maxScoreList.get(lossIndex));

        return new int[]{winIndex, lossIndex};
    }

    private static double calculateAdjustWinProb(double winProb, int winRandomIndex, int lossRandomIndex, List<Double> maxScoreList) {
        // 1. 計算全補差距
        double totalAdjustWin = maxScoreList.get(winRandomIndex) - maxScoreList.get(lossRandomIndex);
        double totalAdjustBet = Math.abs(maxScoreList.get(winRandomIndex)) - Math.abs(maxScoreList.get(lossRandomIndex));

        // 2. 取得當前差距
        double currentDiffWin = personExpectDiffPoolCtr.getExpectWinDiff();
        double currentDiffBet = personExpectDiffPoolCtr.getExpectBetDiff();

        // 3. 計算捕的差距
        double diffProbNumerator = (PROFIT_RATE * currentDiffBet - currentDiffWin);
        double diffProbDenominator = (PROFIT_RATE * totalAdjustBet - totalAdjustWin);
        double diffProb = (diffProbDenominator == 0) ? 0 : Math.max(Math.min(diffProbNumerator / diffProbDenominator, (1- winProb)), -1 * winProb);

        personExpectDiffPoolCtr.updateExpectWinDiff((-1) * diffProb * totalAdjustWin);
        personExpectDiffPoolCtr.updateExpectBetDiff((-1) * diffProb * totalAdjustBet);

        return winProb + diffProb;
    }

    // [win, bet]
    private static double[] calculateExpectDiffResult(boolean winFlag, double[] humanResult, int winRandomIndex, int lossRandomIndex, List<Double> maxScoreList) {
        double expectWin = (winFlag) ? maxScoreList.get(winRandomIndex) : maxScoreList.get(lossRandomIndex);
        double expectBet = Math.abs(expectWin);

        return new double[]{expectWin - humanResult[0], expectBet - humanResult[1]};
    }

    // [win, bet]
    private static double[] calculateHumanResult(boolean winFlag, int winRandomIndex, int lossRandomIndex, List<Double> maxScoreList, List<Double> minScoreList) {
        if(winFlag){
            double randomWin = minScoreList.get(winRandomIndex) +
                    (maxScoreList.get(winRandomIndex) - minScoreList.get(winRandomIndex)) * randomUtil.getRandomDoubleWithAccuracy(ConstMathCommon.AccuracyType.MILLION);

            return new double[]{randomWin, Math.abs(randomWin)};
        }

        double randomLoss = minScoreList.get(lossRandomIndex) +
                (maxScoreList.get(lossRandomIndex) - minScoreList.get(lossRandomIndex)) * randomUtil.getRandomDoubleWithAccuracy(ConstMathCommon.AccuracyType.MILLION);

        return new double[]{randomLoss, Math.abs(randomLoss)};
    }

    private static double calculateWinProb(int winRandomIndex, int lossRandomIndex, List<Double> maxScoreList) {
        // 1. 準備參數
        double w1 = maxScoreList.get(winRandomIndex);
        double w2 = maxScoreList.get(lossRandomIndex);
        double b1 = Math.abs(w1);
        double b2 = Math.abs(w2);
        double numerator = (w2 - b2 * PROFIT_RATE);
        double denominator = ((w2 - w1) - (b2 - b1) * PROFIT_RATE);

        // 2. 分母零防呆
        if(denominator == 0.0){ return 0.0;}

        // 3. 計算機率
        double prob = numerator / denominator;

        // 4. 回傳
        if(prob < 0.0){ return 0.0;}
        return Math.min(prob, 1.0);

    }

    private static int calculateWinIndex(List<Double> maxScoreList){
        int randomIndex = -1;

        for (int tryIndex = 0; tryIndex < ABSOLUTE_RETRY_COUNT; tryIndex++) {
            randomIndex = randomUtil.getRandomIntWithAccuracy(maxScoreList.size(), ConstMathCommon.AccuracyType.MILLION);
            if(maxScoreList.get(randomIndex) > 0){
                break;
            }
        }

        return randomIndex;
    }

    private static int calculateLossIndex(List<Double> maxScoreList){
        int randomIndex = -1;

        for (int tryIndex = 0; tryIndex < ABSOLUTE_RETRY_COUNT; tryIndex++) {
            randomIndex = randomUtil.getRandomIntWithAccuracy(maxScoreList.size(), ConstMathCommon.AccuracyType.A32768);
            if(maxScoreList.get(randomIndex) < 0){
                break;
            }
        }

        return randomIndex;
    }

    public static List<Double> calculateMaxScoreList(ArrayList<NodeMapChildrenWpr<TablePhaseModelSkill>> readSkillTreeList){
        List<Double> result = new ArrayList<>();

        for (NodeMapChildrenWpr<TablePhaseModelSkill> node: readSkillTreeList) {
            result.add(node.getMaxHumanScore());
        }

        return result;
    }

    public static List<Double> calculateMinScoreList(ArrayList<NodeMapChildrenWpr<TablePhaseModelSkill>> readSkillTreeList){
        List<Double> result = new ArrayList<>();

        for (NodeMapChildrenWpr<TablePhaseModelSkill> node: readSkillTreeList) {
            result.add(node.getMinHumanScore());
        }

        return result;
    }
}
