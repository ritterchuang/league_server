package com.lx.gs.math.core.card.cardWallCtr;

import com.lx.gs.math.core.card.ConstMathCard;

import java.util.Map;

// 牌牆計算器遊戲設定
public class CardWallCtrGameConfig {
    private final ConstMathCard.CardType cardType; // 牌種類型
    private final int[][] initWallList; // 初始牌牆設定 [第N張牌][牌值, 張數]
    private final Map<Integer, Integer> cardValueWeightMap; // 點數與權重對應表 <點數, 權重>

    public CardWallCtrGameConfig(ConstMathCard.CardType cardType, int[][] initWallList, Map<Integer, Integer> cardValueWeightMap) {
        this.cardType = cardType;
        this.initWallList = initWallList;
        this.cardValueWeightMap = cardValueWeightMap;
    }

    public ConstMathCard.CardType getCardType() {
        return cardType;
    }

    public int[][] getInitWallList() {
        return initWallList;
    }

    public Map<Integer, Integer> getCardValueWeightMap() {
        return cardValueWeightMap;
    }
}
