package org.lsj.gs.math.core.card.resultCtr.bjlResultCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractCardTypeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

// 百家樂結果工具包
public class BjlResultUtil extends AbstractCardTypeUtil {

    // 計算 贏下注區域
    public List<Integer> calculateWinBetAreaIdList(Map<Integer, List<ICard>> dealAreaIdToCardListMap, int[] areaBetArray) {
        // 1. 宣告陣列
        List<Integer> winBetAreaIdList = new ArrayList<>();

        // 2. 添加 庄、閒、和、上庄輸贏
        winBetAreaIdList.addAll(this.calculateWinBankPlayTieResult(dealAreaIdToCardListMap, areaBetArray));

        // 3. 添加 大、小
        winBetAreaIdList.addAll(this.calculateWinBigSmallResult(dealAreaIdToCardListMap));

        // 4. 添加 庄對、閒對
        winBetAreaIdList.addAll(this.calculateWinPairResult(dealAreaIdToCardListMap));

        return winBetAreaIdList;
    }

    // 計算 返還下注區域
    public List<Integer> calculateReturnBetAreaIdList(List<Integer> winBetAreaIdList) {
        List<Integer> returnBetAreaIdList = new ArrayList<>();

        // 1. 計算和局不計分 返還押注區域
        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.TIE.getCode())) {
            returnBetAreaIdList.addAll(
                    List.of(
                            ConstMathBjl.BetAreaEnum.BANK.getCode(),
                            ConstMathBjl.BetAreaEnum.PLAY.getCode(),
                            ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode(),
                            ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode()
                    )
            );
        }

        // 2. 計算庄閒下注相同不計分，返還押注區域
        if (!winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.TIE.getCode())
            && !winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode())
            && !winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode())
        ) {
            returnBetAreaIdList.addAll(
                    List.of(
                            ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode(),
                            ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode()
                    )
            );
        }

        return returnBetAreaIdList;
    }


    //* 計算贏的下注區域*//

    // 添加 庄、閒、和、上庄輸贏 結果
    private List<Integer> calculateWinBankPlayTieResult(Map<Integer, List<ICard>> dealAreaIdToCardListMap, int[] areaBetArray) {
        // 1. 創建空間
        List<Integer> result = new ArrayList<>();

        // 2. 防呆回傳
        if (dealAreaIdToCardListMap.isEmpty() || areaBetArray.length == 0) {
            return result;
        }

        // 3. 準備資訊
        int bankPoint = super.calculateCardPointSumModuleBy10(dealAreaIdToCardListMap.getOrDefault(ConstMathBjl.DealAreaEnum.BANK.getCode(), new ArrayList<>()));
        int playPoint = super.calculateCardPointSumModuleBy10(dealAreaIdToCardListMap.getOrDefault(ConstMathBjl.DealAreaEnum.PLAY.getCode(), new ArrayList<>()));
        int bankAreaChips = this.getAreaBetChipByType(areaBetArray, ConstMathBjl.BetAreaTypeEnum.BANK);
        int playAreaChips = this.getAreaBetChipByType(areaBetArray, ConstMathBjl.BetAreaTypeEnum.PLAY);

        // 4. 添加和局結果
        result.addAll(this.calculateWinTieResult(bankPoint, playPoint));

        // 5. 添加庄贏、上庄輸贏
        result.addAll(this.calculateWinBankResult(bankPoint, playPoint, bankAreaChips, playAreaChips));

        // 6. 添加閒贏、上庄輸贏
        result.addAll(this.calculateWinPlayResult(bankPoint, playPoint, bankAreaChips, playAreaChips));

        return result;
    }

    // 添加 和 結果
    List<Integer> calculateWinTieResult(int bankPoint, int playPoint) {
        if (bankPoint == playPoint) {
            return List.of(ConstMathBjl.BetAreaEnum.TIE.getCode());
        }

        return new ArrayList<>();
    }

    // 添加 庄、上庄輸贏 結果
    List<Integer> calculateWinBankResult(int bankPoint, int playPoint, int bankAreaChips, int playAreaChips) {
        List<Integer> result = new ArrayList<>();

        if (bankPoint > playPoint) {
            result.add(ConstMathBjl.BetAreaEnum.BANK.getCode());
        }

        if (bankPoint > playPoint && bankAreaChips > playAreaChips) {
            result.add(ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode());
        }

        if (bankPoint > playPoint && bankAreaChips < playAreaChips) {
            result.add(ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode());
        }

        return result;
    }

    // 添加 閒、上庄輸贏 結果
    List<Integer> calculateWinPlayResult(int bankPoint, int playPoint, int bankAreaChips, int playAreaChips) {
        List<Integer> result = new ArrayList<>();

        if (playPoint > bankPoint) {
            result.add(ConstMathBjl.BetAreaEnum.PLAY.getCode());
        }

        if (playPoint > bankPoint && playAreaChips > bankAreaChips) {
            result.add(ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode());
        }

        if (playPoint > bankPoint && playAreaChips < bankAreaChips) {
            result.add(ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode());
        }

        return result;
    }

    // 計算 大、小 結果
    List<Integer> calculateWinBigSmallResult(Map<Integer, List<ICard>> dealAreaIdToCardListMap) {
        if (dealAreaIdToCardListMap.isEmpty()) {
            return new ArrayList<>();
        }

        if (dealAreaIdToCardListMap.values().stream().mapToInt(List::size).sum() >= 5) {
            return List.of(ConstMathBjl.BetAreaEnum.BIG.getCode());
        }else {
            return List.of(ConstMathBjl.BetAreaEnum.SMALL.getCode());
        }
    }

    // 計算 庄對、閒對 結果
    List<Integer> calculateWinPairResult(Map<Integer, List<ICard>> dealAreaIdToCardListMap) {
        List<Integer> result = new ArrayList<>();

        if (dealAreaIdToCardListMap.isEmpty()) {
            return result;
        }

        if (this.checkInitial2CardsPair(dealAreaIdToCardListMap.getOrDefault(ConstMathBjl.DealAreaEnum.BANK.getCode(), new ArrayList<>()))) {
            result.add(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode());
        }

        if (this.checkInitial2CardsPair(dealAreaIdToCardListMap.getOrDefault(ConstMathBjl.DealAreaEnum.PLAY.getCode(), new ArrayList<>()))) {
            result.add(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode());
        }

        return result;
    }

    // 檢查前兩張是否為對子
    private boolean checkInitial2CardsPair(List<ICard> cardList) {
        if (cardList.isEmpty()) {
            return false;
        }

        return cardList.get(0).getPoint() == cardList.get(1).getPoint();
    }


    //* 押注資訊 *//

    // 依照 庄、閒 計算押注總額
    private int getAreaBetChipByType(int[] areaBetArray, ConstMathBjl.BetAreaTypeEnum betAreaType) {
        return IntStream.range(0, areaBetArray.length)
                .filter(areaId -> ConstMathBjl.BetAreaEnum.fromCode(areaId).getBetAreaType().equals(betAreaType))
                .map(areaId -> areaBetArray[areaId]).sum();
    }
}
