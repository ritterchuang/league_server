package org.lsj.gs.math.core.card.stackCtr.jhStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// 金花牌型計算器
public class JhStackCtr {
    private Map<Integer, JhStack> stackMap; // 金花牌型對應表
    private final CardTypeUtilJh cardTypeUtil; // 牌型工具包

    public JhStackCtr() {
        this.stackMap = new HashMap<>();
        this.cardTypeUtil = new CardTypeUtilJh();
    }

    // 計算對應位置牌型對應表
    public Map<Integer, JhStack> calculateStackMap(Map<Integer, List<ICard>> unTakenCardListMap) {
        return unTakenCardListMap.keySet().stream().collect(Collectors.toMap(
                positionId -> positionId, positionId -> this.calculateStack(unTakenCardListMap.get(positionId))
        ));
    }

    // 計算金花牌型
    private JhStack calculateStack(List<ICard> cardList) {
        // 1. 防呆
        if(Objects.isNull(cardList) || cardList.size() != 3){
            return new JhStack(cardList, ConstJhStack.JhTypeEnumCommon.INVALID);
        }

        // 2. 判斷豹子
        if(this.cardTypeUtil.checkBaoZi(cardList)){
            return new JhStack(cardList, ConstJhStack.JhTypeEnumCommon.BAO_ZI);
        }

        // 3. 判斷順金
        if(this.cardTypeUtil.checkTongHuaShun(cardList)){
            return new JhStack(cardList, ConstJhStack.JhTypeEnumCommon.TONG_HUA_SHUN);
        }

        // 4. 判斷金花
        if(this.cardTypeUtil.checkTongHua(cardList)){
            return new JhStack(cardList, ConstJhStack.JhTypeEnumCommon.TONG_HUA);
        }

        // 5. 判斷順子
        if(this.cardTypeUtil.checkShunZi(cardList)){
            return new JhStack(cardList, ConstJhStack.JhTypeEnumCommon.SHUN_ZI);
        }

        // 6. 判斷對子
        if(this.cardTypeUtil.checkDuiZi(cardList)){
            return new JhStack(cardList, ConstJhStack.JhTypeEnumCommon.DUI_ZI);
        }

        return new JhStack(cardList, ConstJhStack.JhTypeEnumCommon.DAN_ZHANG);
    }

    // 取得區域金花牌型
    public Map<Integer, JhStack> getStackMap() {
        return stackMap;
    }

    // 設定區域金花牌型
    public void setStackMap(Map<Integer, JhStack> stackMap) {
        this.stackMap = new HashMap<>(stackMap);
    }

    // 重設
    public void reset() {
        this.stackMap.clear();
    }
}
