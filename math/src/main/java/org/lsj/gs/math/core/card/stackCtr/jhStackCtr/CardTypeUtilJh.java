package org.lsj.gs.math.core.card.stackCtr.jhStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractCardTypeUtil;

import java.util.List;
import java.util.Map;

// 金花牌型工具包
public class CardTypeUtilJh extends AbstractCardTypeUtil {
    // 判斷豹子
    public boolean checkBaoZi(List<ICard> cardList) {
        // 1. 取得參數
        int categorySize = 1;
        long baoZiCount = 3L;

        // 2. 點數分類
        Map<Integer, Long> pointCategoryCountMap = super.calculatePointCategoryCount(cardList);

        // 3. 判斷
        return (pointCategoryCountMap.size() == categorySize &&
                pointCategoryCountMap.values().stream().anyMatch(count -> count == baoZiCount)
        );
    }

    // 判斷順金
    public boolean checkTongHuaShun(List<ICard> cardList) {
        return super.checkShunJin(cardList);
    }

    // 判斷金花
    public boolean checkTongHua(List<ICard> cardList) {
        return super.checkTongHua(cardList);
    }

    // 判斷順子
    public boolean checkShunZi(List<ICard> cardList) {
        return super.checkShunZi(cardList);
    }

    // 判斷對子
    public boolean checkDuiZi(List<ICard> cardList) {
        // 1. 取得參數
        int categorySize = 2;
        long duiZiCount = 2L;

        // 2. 點數分類
        Map<Integer, Long> pointCategoryCountMap = super.calculatePointCategoryCount(cardList);

        // 3. 判斷
        return (pointCategoryCountMap.size() == categorySize &&
                pointCategoryCountMap.values().stream().anyMatch(count -> count == duiZiCount)
        );
    }
}
