package org.lsj.gs.math.games.dgry_java.module.gameStateHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.AbstractGameStateHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.IGameStateHlr;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.LogicSlotCascade;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResultExtend;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendDgryBonusGame;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.dgry_java.entity.ConstDgryJava;
import org.lsj.gs.math.games.dgry_java.entity.config.DgryBonusGameDisplayType;
import org.lsj.gs.math.games.dgry_java.entity.config.DgryBonusGameResult;
import org.lsj.gs.math.games.dgry_java.entity.config.GameStateConfigExtendDgryBonusGame;
import org.lsj.gs.math.games.dgry_java.entity.result.gameStateResult.GameStateHlrResultExtendDgryBonusGame;
import org.lsj.gs.math.games.dgry_java.entity.result.roundResult.RoundHlrResultExtendDgryBonusGame;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 帝国榮耀 額外遊戲處理者
public class GameStateHlrDgryBonusGame extends AbstractGameStateHlr implements IGameStateHlr {
    private final LogicSlotCascade logic; // 牌桌邏輯
    private final GameStateConfigExtendDgryBonusGame gameStateConfigExtend; // 遊戲狀態額外設定
    private final RoundCascadeGameConfigExtendDgryBonusGame roundConfigExtend; // 消除局額外設定

    public GameStateHlrDgryBonusGame(int conditionId, int gameStateId, ITableUtilSlot tableUtil, ILogicSlot logic, GameStateConfig gameStateConfig) {
        super(conditionId, gameStateId, gameStateConfig, tableUtil);

        this.logic = (LogicSlotCascade) logic;
        this.gameStateConfigExtend = (GameStateConfigExtendDgryBonusGame) gameStateConfig.getGameStateConfigExtend();
        this.roundConfigExtend = (RoundCascadeGameConfigExtendDgryBonusGame) ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getRoundCascadeGameConfigExtend();
    }

    // 計算遊戲狀態結果
    @Override
    public GameStateHlrResult calculateGameStateHlrResult(int gameStateResultIndex, ConstMathSlot.ReelRtpType reelRtpType, SpinRequest spinRequest, GameStateHlrResult beforeGameStateHlrResult) {

        ArrayList<RoundHlrResult> roundHlrResultList = new ArrayList<>();

        // 1. 計算得分結果
        DgryBonusGameResult bonusGameResult = this.calculateResult(reelRtpType);

        // 2. 複製一份結果列表
        List<DgryBonusGameDisplayType> displayTypeList = JsonUtil.getInstance().deepCopyList(bonusGameResult.getOutcomeList(), DgryBonusGameDisplayType.class);

        // 3. 計算插入表演數量
        int insertDisplayCount = this.calculateInsertCount(displayTypeList);

        // 4. 整理結果列表
        this.reorganizeDisplayResult(displayTypeList, insertDisplayCount);

        // 5. 計算生命值表演
        List<DgryBonusGameDisplayType> result = this.calculateLifePoint(displayTypeList);

        // 6. 遍歷結果列表
        for(int resultIndex = 0; resultIndex < result.size(); resultIndex++) {

            // 7. 計算一局得分
            double roundWin = MathUtil.moneyScale(MathUtil.multiply(result.get(resultIndex).getScore(), spinRequest.getPlayerCreditBase()));

            // 8. 計算 局額外類型
            ConstMathSlot.RoundCascadeGameType roundCascadeGameType = ConstMathSlot.RoundCascadeGameType.DGRY_BONUSGAME;

            // 9. 計算 局額外資訊
            RoundHlrResultExtend roundHlrResultExtend = this.calculateResultExtend(spinRequest, result.get(resultIndex), roundWin);

            // 10. 計算累積得分結果
            AccumulateWinCtrResult accumulateWinCtrResult = this.logic.getAccumulateWinCtrResult(0, roundWin, beforeGameStateHlrResult, roundHlrResultList);

            // 11. 封裝 局結果
            RoundHlrResultCascade roundHlrResultCascade = new RoundHlrResultCascade(
                    0,
                    roundWin,
                    new SpecialFeatureHlrResultCluster(),
                    new ProgressHlrResult(Boolean.FALSE, ConstMathSlot.ProgressType.TRIGGER_ROUND, new ProgressHlrResultExtendTriggerRound(new RoundProgress((resultIndex+1), 0, result.size()))),
                    new ReadyHandHlrResultUnionCluster(),
                    accumulateWinCtrResult,
                    new CascadeClusterHlrResult(0,
                            ConstMathSlot.CascadeClusterType.DGRY,
                            new CascadeClusterHlrResultExtend(),
                            new ArrayList<>()),
                    roundCascadeGameType,
                    roundHlrResultExtend
            );

            // 12. 加入 局結果
            roundHlrResultList.add(roundHlrResultCascade);
        }

        // 13. 計算共同輸入結果
        CommonInputResult commonInputResult = this.logic.getCommonInputResult(roundHlrResultList);

        // 14. 計算遊戲輸入結果
        GameStateInputResult gameStateInputResult = this.logic.getGameStateInputResult(super.conditionId, roundHlrResultList);

        // 15. 回傳 遊戲狀態結果
        return new GameStateHlrResult(
                super.conditionId,
                gameStateResultIndex,
                super.gameStateId,
                MathUtil.moneyScale(super.calculateStateTotalWin(roundHlrResultList)),
                super.config.getGameStateType(),
                new GameStateHlrResultExtendDgryBonusGame(),
                super.config.getRoundType(),
                roundHlrResultList,
                commonInputResult,
                gameStateInputResult);
    }

    //* 結果相關 *//

    // 計算插入局數
    private int calculateInsertCount(List<DgryBonusGameDisplayType> bonusGameResult){

        // 1. 計算隨機插入數量
        int randomCount = this.tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(this.gameStateConfigExtend.getDisplayTimesRange().get(1)-this.gameStateConfigExtend.getDisplayTimesRange().get(0), ConstMathCommon.AccuracyType.MILLION);

        // 2. 如果結果列表大小少於最小表演數量
        if(bonusGameResult.size() <= this.gameStateConfigExtend.getDisplayTimesRange().get(0)){
            int insertCount = this.gameStateConfigExtend.getDisplayTimesRange().get(0) - bonusGameResult.size();

            return insertCount + randomCount;
        }

        // 3. 如果結果列表大小+插入局數大於最大表演數量
        if((bonusGameResult.size() + randomCount) >= this.gameStateConfigExtend.getDisplayTimesRange().get(1)){
            return randomCount - ((bonusGameResult.size() + randomCount) - this.gameStateConfigExtend.getDisplayTimesRange().get(1));
        }

        return randomCount;
    }

    // 計算得分結果
    private DgryBonusGameResult calculateResult(ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 建立表演空陣列
        List<DgryBonusGameDisplayType> displayList= new ArrayList<>();

        // 2. 取得起始血量
        int lifeCounter = this.gameStateConfigExtend.getLifePoint();

        // 3. 計算要幾個不會扣血的表演
        int noDamageDisplayCountIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.gameStateConfigExtend.getNoDamageDisplayCountWeightList().get(reelRtpType).stream().mapToInt(Integer::intValue).toArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
        int noDamageDisplayCount = this.gameStateConfigExtend.getNoDamageDisplayCountList().get(noDamageDisplayCountIndex);

        // 4. 塞入不扣血表演
        insertNoDamageDisplay(reelRtpType, displayList, lifeCounter, noDamageDisplayCount);

        // 5. 塞入足夠讓玩家死亡的傷害
        insertTakeDamageDisplay(reelRtpType, displayList, lifeCounter);

        // 6. 回傳結果
        return new DgryBonusGameResult(displayList);
    }

    // 塞入扣血的表演結果
    private void insertTakeDamageDisplay(ConstMathSlot.ReelRtpType reelRtpType, List<DgryBonusGameDisplayType> displayList, int lifeCounter) {

        for(int aCounter = lifeCounter; aCounter > 0; aCounter--){
            // 1. 取得此次表演倍數
            int multiplierIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.gameStateConfigExtend.getMultiplierWeightList().get(reelRtpType).stream().mapToInt(Integer::intValue).toArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
            int multiplier = this.gameStateConfigExtend.getMultiplierList().get(multiplierIndex);

            // 2. 取得表演類型
            int takeDamageDisplayIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.gameStateConfigExtend.getTakeDamageDisplayWeightList().get(reelRtpType).stream().mapToInt(Integer::intValue).toArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
            DgryBonusGameDisplayType takeDamageDisplay = this.gameStateConfigExtend.getTakeDamageDisplayList().get(takeDamageDisplayIndex);

            // 3. 組合結果
            DgryBonusGameDisplayType result = new DgryBonusGameDisplayType(
                    takeDamageDisplay.getOutcomeType(),
                    takeDamageDisplay.getIconType(),
                    takeDamageDisplay.isGetHit(),
                    MathUtil.multiply(takeDamageDisplay.getScore(), multiplier),
                    multiplier,
                    lifeCounter,
                    lifeCounter,
                    takeDamageDisplay.getOtherIcons()
            );

            // 4. 添加結果
            displayList.add(result);
        }
    }

    // 塞入不扣血表演結果
    private void insertNoDamageDisplay(ConstMathSlot.ReelRtpType reelRtpType, List<DgryBonusGameDisplayType> displayList, int lifeCounter, int noDamageDisplayCount) {

        for(int aCounter = noDamageDisplayCount; aCounter > 0; aCounter--){

            // 1. 取得此次表演倍數
            int multiplierIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.gameStateConfigExtend.getMultiplierWeightList().get(reelRtpType).stream().mapToInt(Integer::intValue).toArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
            int multiplier = this.gameStateConfigExtend.getMultiplierList().get(multiplierIndex);

            // 2. 取得表演類型
            int noDamageDisplayIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.gameStateConfigExtend.getNoDamageDisplayWeightList().get(reelRtpType).stream().mapToInt(Integer::intValue).toArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
            DgryBonusGameDisplayType noDamageDisplay = this.gameStateConfigExtend.getNoDamageDisplayList().get(noDamageDisplayIndex);

            // 3. 組合結果
            DgryBonusGameDisplayType result = new DgryBonusGameDisplayType(
                    noDamageDisplay.getOutcomeType(),
                    noDamageDisplay.getIconType(),
                    noDamageDisplay.isGetHit(),
                    MathUtil.multiply(noDamageDisplay.getScore(), multiplier),
                    multiplier,
                    lifeCounter,
                    lifeCounter,
                    noDamageDisplay.getOtherIcons()
            );

            // 4. 添加結果
            displayList.add(result);
        }
    }

    // 整理得分結果
    private void reorganizeDisplayResult(List<DgryBonusGameDisplayType> result, int insertRoundCount){

        // 1. 抽出最後一個結果
        DgryBonusGameDisplayType lastDisplay = this.withdrawLastDisplay(result);

        // 2. 插入指定數量的插入表演結果
        this.insertDisplayResult(result, insertRoundCount);

        // 3. 將表演結果隨機排序
        this.tableUtil.getMainRandomUtil().shuffleList(result);

        // 4. 確保反擊不會是第一個結果
        this.shiftRetaliate(result);

        // 5. 將最後一個表演結果插回結尾
        result.add(lastDisplay);
    }

    // 抽出最後一個結果
    private DgryBonusGameDisplayType withdrawLastDisplay(List<DgryBonusGameDisplayType> result){

        // 1. 複製最後一個結果
        DgryBonusGameDisplayType lastDisplay = result.get(result.size() - 1);

        // 2. 從列表中移除最後一個結果
        result.remove(result.size() - 1);

        // 3. 回傳
        return lastDisplay;
    }

    // 插入指定數量的插入表演結果
    private void insertDisplayResult(List<DgryBonusGameDisplayType> result, int insertRoundCount){
        for (int insertIndex = 0; insertIndex < insertRoundCount; insertIndex++) {

            result.add(this.gameStateConfigExtend.getInsertDisplayList().get(this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.gameStateConfigExtend.getInsertDisplayWeightList().stream().mapToInt(Integer::intValue).toArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)));
        }
    }

    // 如第一個結果為被老虎反擊，則將之與第一個非反擊的結果做位置交換
    private void shiftRetaliate(List<DgryBonusGameDisplayType> result){
        if(result.get(0).getOutcomeType().equals(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.RETALIATE)){

            for(int listIndex = 1; listIndex < result.size(); listIndex++){

                if(!result.get(listIndex).getOutcomeType().equals(ConstDgryJava.BonusGameOutcomeTypeEnumDgryJava.RETALIATE)) {
                    this.tableUtil.getMainRandomUtil().swapList(result, 0, listIndex);
                    break;
                }
            }
        }
    }

    // 計算生命值
    private List<DgryBonusGameDisplayType> calculateLifePoint(List<DgryBonusGameDisplayType> displayTypeList){

        // 取得預設血量
        int beforeLife = this.gameStateConfigExtend.getLifePoint();
        int afterLife = this.gameStateConfigExtend.getLifePoint();

        List<DgryBonusGameDisplayType> result = new ArrayList<>();
        for (DgryBonusGameDisplayType displayType : displayTypeList) {

            if(displayType.isGetHit()) {
                afterLife--;
            }

            result.add(new DgryBonusGameDisplayType(displayType.getOutcomeType(), displayType.getIconType(), displayType.isGetHit(), displayType.getScore(), displayType.getMultiplier(), beforeLife, afterLife, displayType.getOtherIcons()));

            if(displayType.isGetHit()) {
                beforeLife--;
            }
        }

        return result;
    }

    //* 局額外資訊相關 *//

    private RoundHlrResultExtend calculateResultExtend(SpinRequest spinRequest, DgryBonusGameDisplayType displayType, double roundWin){

        // 1. 將表演結果中的分數轉換成實際獎金金額

        return new RoundHlrResultExtendDgryBonusGame(roundWin, new DgryBonusGameDisplayType(displayType.getOutcomeType(), displayType.getIconType(), displayType.isGetHit(), MathUtil.moneyScale(displayType.getScore()*spinRequest.getPlayerCreditBase()), displayType.getMultiplier(), displayType.getBeforeLifePoint(), displayType.getAfterLifePoint(), displayType.getOtherIcons()));
    }
}
