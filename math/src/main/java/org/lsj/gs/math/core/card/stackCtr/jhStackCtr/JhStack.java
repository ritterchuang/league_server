package org.lsj.gs.math.core.card.stackCtr.jhStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 金花牌型
public class JhStack extends AbstractStack{
    private final ConstJhStack.JhTypeEnumCommon typeEnumCommon; // 金花牌型列舉
    private final CardTypeUtilJh cardTypeUtil; // 牌型工具包

    public JhStack(List<ICard> cardList, ConstJhStack.JhTypeEnumCommon typeEnumCommon) {
        super(cardList);
        this.typeEnumCommon = typeEnumCommon;
        this.cardTypeUtil = new CardTypeUtilJh();
    }

    @Override
    public int compareTo(AbstractStack abstractStack) {
        // 1. 牌型不同的比較
        if(this.typeEnumCommon.getType() != ((JhStack) abstractStack).typeEnumCommon.getType()){
            return this.compareToDifferentType(((JhStack) abstractStack));
        }else{
            // 2. 牌型相同的比較
            return this.compareToSameType(((JhStack) abstractStack));
        }
    }

    // 牌型不同的比較
    private int compareToDifferentType(JhStack stack) {
        return Integer.compare(this.typeEnumCommon.getType(), stack.typeEnumCommon.getType());
    }

    // 牌型相同的比較
    private int compareToSameType(JhStack stack) {
        switch(this.typeEnumCommon){
            case BAO_ZI: return this.compareBaoZi(stack);
            case TONG_HUA_SHUN: return this.compareTongHuaShun(stack);
            case TONG_HUA: return this.compareTongHua(stack);
            case SHUN_ZI: return this.compareShunZi(stack);
            case DUI_ZI: return this.compareDuiZi(stack);
            default: return this.compareMaxCard(stack);
        }
    }

    // 比較豹子
    private int compareBaoZi(JhStack stack) {
        return Integer.compare(super.handCardList.get(0).getWeight(), stack.handCardList.get(0).getWeight());
    }

    // 比較順金
    private int compareTongHuaShun(JhStack stack) {
        return this.compareShunZi(stack);
    }

    // 比較金花
    private int compareTongHua(JhStack stack) {
        return super.compareMaxCard(stack);
    }

    // 比較順子
    private int compareShunZi(AbstractStack stack) {
        return super.compareShunZi(stack, this.cardTypeUtil);
    }

    // 比較對子
    private int compareDuiZi(JhStack stack) {
        // 1. 複製
        List<ICard> mineCardListCopy = new ArrayList<>();
        List<ICard> theirCardListCopy = new ArrayList<>();
        super.handCardList.forEach(iCard -> mineCardListCopy.add(iCard));
        stack.handCardList.forEach(iCard -> theirCardListCopy.add(iCard));

        // 2. 計算對子點數
        int duiZiValueMine = super.calculateSpecifyCountPointValue(super.handCardList, 2);
        int duiZiValueTheirs = super.calculateSpecifyCountPointValue(stack.handCardList, 2);

        // 3. 取得對子牌
        List<ICard> duiZiCardListMine = mineCardListCopy.stream().filter(card -> card.getPoint() == duiZiValueMine).collect(Collectors.toList());
        List<ICard> duiZiCardListTheirs = theirCardListCopy.stream().filter(card -> card.getPoint() == duiZiValueTheirs).collect(Collectors.toList());

        // 4. 計算對子最大牌
        ICard duiZiMaxCardMine = super.calculateMaxCard(duiZiCardListMine);
        ICard duiZiMaxCardTheirs = super.calculateMaxCard(duiZiCardListTheirs);

        // 5. 比較對子
        if(this.compareCard(duiZiMaxCardMine, duiZiMaxCardTheirs) != 0){
            return this.compareCard(duiZiMaxCardMine, duiZiMaxCardTheirs);
        }

        // 6. 取得剩餘牌
        ICard remainCardListMine = mineCardListCopy.stream().filter(card -> card.getPoint() != duiZiValueMine).collect(Collectors.toList()).get(0);
        ICard remainCardListTheirs = theirCardListCopy.stream().filter(card -> card.getPoint() != duiZiValueTheirs).collect(Collectors.toList()).get(0);

        // 7. 比較剩餘牌
        return this.compareCard(remainCardListMine, remainCardListTheirs);
    }

    public int calculateDuiZiPoint() {
        if(!this.getTypeEnumCommon().equals(ConstJhStack.JhTypeEnumCommon.DUI_ZI)){
            return 0;
        }

        return super.calculateSpecifyCountPointValue(super.handCardList, 2);
    }

    public ConstJhStack.JhTypeEnumCommon getTypeEnumCommon() {
        return typeEnumCommon;
    }
}
