package org.lsj.gs.math.core.card.cardWallCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 牌牆計算器
public class CardWallCtr extends AbstractModule {
    private final CardWallCtrConfig config; // 設定檔
    private List<ICard> cardWallList; // 牌牆
    int currentFrontIndex; // 前指針
    int currentBackIndex; // 後指針
    Map<Integer, List<ICard>> areaCardListMap; // 區域牌
    Map<Integer, List<ICard>> handCardListMap; // 手牌

    public CardWallCtr(CardWallCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.cardWallList = this.generateCardWallList(this.config.getInitWallList(), this.config.getCardValueWeightMap());
        this.currentFrontIndex = 0;
        this.currentBackIndex = this.cardWallList.size() - 1;
        this.areaCardListMap = new HashMap<>();
        this.handCardListMap = new HashMap<>();
    }

    //* 發牌功能相關 *//

    // 洗牌
    public void shuffle() {
        super.tableUtil.getMainRandomUtil().shuffleList(this.cardWallList);
    }

    // 取得未取走牌個數
    public int getLeftWallNum() {
        return (int) this.cardWallList.stream().filter(card -> card.getCardState() == ConstMathCard.CardState.UN_TAKEN).count();
    }

    // 取得指定張數的未被取走牌
    public List<ICard> getUnTakenCardList(int count) {
        List<ICard> result = new ArrayList<>();

        if (count > this.getLeftWallNum()) {
            return result;
        }

        int currentFrontIndex = this.currentFrontIndex;
        for (int cardIndex = 0; cardIndex < count; cardIndex++) {
            if (currentFrontIndex <= this.currentBackIndex) {
                if (this.cardWallList.get(currentFrontIndex).isState(ConstMathCard.CardState.TAKEN)) {
                    for (int nextIndex = currentFrontIndex; nextIndex <= this.currentBackIndex; nextIndex++) {
                        if (this.cardWallList.get(nextIndex).isState(ConstMathCard.CardState.UN_TAKEN)) {
                            currentFrontIndex = nextIndex;
                            break;
                        }
                    }
                }
                result.add(this.cardWallList.get(currentFrontIndex));
                currentFrontIndex++;
            }
        }

        if (result.size() == count) {
            return result;
        }

        return new ArrayList<>();
    }

    // 取出指定牌
    public void getAppointObjCard(ICard card) {
        // 1. 牌牆防呆
        if (this.getLeftWallNum() == 0 || this.currentFrontIndex > this.currentBackIndex) {
            return;
        }

        // 2. 搜尋卡牌
        int targetIndex = -1;
        for (int index = this.currentFrontIndex; index <= this.currentBackIndex; index++) {
            if (this.cardWallList.get(index).isState(ConstMathCard.CardState.UN_TAKEN) && card.getValue() == this.cardWallList.get(index).getValue()) {
                targetIndex = index;
                break;
            }
        }

        // 3. 找不到牌防呆
        if (targetIndex == -1) {
            return;
        }

        // 4. 設定卡牌狀態為已取走
        this.cardWallList.get(targetIndex).setCardState(ConstMathCard.CardState.TAKEN);
    }

    // 添加玩家手牌
    public void addPlayerHandCardListMap(Map<Integer, List<ICard>> addHandCardListMap){
        addHandCardListMap.forEach((chairIndex, handCardList)-> this.addCardList(this.handCardListMap , chairIndex, handCardList));
    }

    // 添加牌
    private void addCardList(Map<Integer, List<ICard>> cardListMap, Integer index, List<ICard> cardList){
        if(cardListMap.containsKey(index)){
            // 1. 已有該鑑值則新增牌
            cardListMap.get(index).addAll(cardList);
        }else{
            // 2. 尚未有該鑑值則直接設定
            cardListMap.put(index, cardList);
        }
    }

    // 設定區域牌
    public void setAreaCardListMap(Map<Integer, List<ICard>> areaCardList) {
        this.areaCardListMap = new HashMap<>(areaCardList);
    }

    // 建立牌牆
    private List<ICard> generateCardWallList(int[][] initWallList, Map<Integer, Integer> cardValueWeightMap) {
        List<ICard> result = new ArrayList<>();

        for (int[] cardSettingArray : initWallList) {
            for (int countIndex = 1; countIndex <= cardSettingArray[1]; countIndex++) {
                result.add(this.generateCard(cardSettingArray[0], countIndex, cardValueWeightMap));
            }
        }

        return result;
    }

    // 建立牌
    private ICard generateCard(int value, int countIndex, Map<Integer, Integer> cardValueWeightMap) {

        switch (this.config.getCardType()) {
            case PAIGOW: return new CardPaiGow(value, countIndex);
            case MAHJONG: return new CardMahjong(value, countIndex);
            default: return new CardPoker(value, countIndex, cardValueWeightMap);
        }
    }

    // 建立手牌空間
    private List<List<Integer>> generateHandCardListSpace(int maxPlayerCount) {
        return IntStream.range(0, maxPlayerCount)
                .mapToObj(chairId -> new ArrayList<Integer>())
                .collect(Collectors.toList());
    }


    //* 發牌結果相關 *//

    // 取得所有區域牌
    public Map<Integer, List<ICard>> getAllAreaCardListMap() {
        return this.areaCardListMap;
    }

    // 取得所有區域牌陣列 [area, cardIndex] = cardNumber
    public int[][] getAllAreaCardNumber2dArray(){
        return this.areaCardListMap.keySet().stream()
                .map(areaId -> this.changeCardList2CardNumberArray(this.areaCardListMap.getOrDefault(areaId, new ArrayList<>())))
                .toArray(int[][]::new);
    }

    // 取得所有區域牌陣列 [area, cardIndex] = cardNo
    public int[][] getAllAreaCardPoint2dArray(){
       return this.areaCardListMap.keySet().stream()
               .map(areaId -> this.changeCardList2CardPointArray(this.areaCardListMap.getOrDefault(areaId, new ArrayList<>())))
               .toArray(int[][]::new);
    }

    // 取得所有玩家手牌
    public Map<Integer, List<ICard>> getAllPlayerHandCardListMap() {
        return this.handCardListMap;
    }

    // 取得所有玩家手牌牌號
    public int[][] getAllPlayerHandCardNumberArray() {
        return IntStream.range(0, this.config.getMaxUser())
                .mapToObj(chairId -> this.handCardListMap.getOrDefault(chairId, Collections.emptyList()))
                .map(this::changeCardList2CardNumberArray)
                .toArray(int[][]::new);
    }

    // 取得所有玩家手牌
    public Map<Integer, List<ICard>> getHandCardListMap() {
        return this.handCardListMap;
    }

    // 取得指定玩家手牌
    public List<ICard> getHandCardList(int chairIndex) {
        return this.handCardListMap.getOrDefault(chairIndex, new ArrayList<>());
    }

    // 取得指定玩家手牌牌號
    public int[] getHandCardNumberArray(int chairIndex) {
        return this.handCardListMap.getOrDefault(chairIndex, new ArrayList<>()).stream()
                .mapToInt(ICard::getNumber)
                .toArray();
    }

    // 取得真人手牌
    public int[] getHumanHandCardListArray() {
        return this.handCardListMap.getOrDefault(super.gamePlayerHlr.getHumanChairIndex(), new ArrayList<>()).stream()
                .mapToInt(ICard::getNumber)
                .toArray();
    }

    // 檢查玩家手牌是否存在
    public boolean isInPlayerHandCard(int chairIndex, int[] cardNumberArray) {
        // 1. 檢查傳進手牌張數
        if (cardNumberArray.length == 0) {
            return false;
        }

        // 2. 檢查玩家座位是否正常
        if (Objects.isNull(this.handCardListMap.get(chairIndex))) {
            return false;
        }

        // 3. 檢查傳入手牌是否正常
        List<ICard> playerHandCardList = this.handCardListMap.get(chairIndex);
        return playerHandCardList.stream().filter(card -> Arrays.stream(cardNumberArray).anyMatch(number -> number == card.getNumber())).count() == cardNumberArray.length;
    }

    // 發蓋牌
    public List<Integer> getBackCardList(int backCardsCount) {
        return IntStream.range(0, backCardsCount)
                .mapToObj(index -> ConstMathCard.CardFaceEnum.BACK.getCode())
                .collect(Collectors.toList());
    }


    //* 斷線重連相關 *//

    // 計算斷線回復手牌資訊
    public int[][] calculateCutReturnHandCardListArray(List<Integer> playerList) {
        // 1. 建立空間
        List<List<Integer>> result = this.generateHandCardListSpace(this.config.getMaxUser());

        // 2. 對應位置放入手牌
        this.handCardListMap.keySet().forEach(chairIndex -> result.set(chairIndex, this.getCutReturnHandCardList(chairIndex, playerList)));

        // 3. 回傳
        return result.stream().map(integerList -> integerList.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
    }

    // 取得指定座位的手牌陣列
    private List<Integer> getCutReturnHandCardList(int chairIndex, List<Integer> playerList) {
        // 1. 防呆
        if (Objects.isNull(this.handCardListMap.get(chairIndex))) {
            return new ArrayList<>();
        }

        // 2. 取得真人手牌
        if (chairIndex == super.gamePlayerHlr.getHumanChairIndex()) {
            return this.handCardListMap.get(chairIndex).stream().map(ICard::getNumber).collect(Collectors.toList());
        }

        // 3. 取得指定機器人手牌
        if (playerList.contains(chairIndex)) {
            return this.handCardListMap.get(chairIndex).stream().map(ICard::getNumber).collect(Collectors.toList());
        }

        // 4. 未指定機器人手牌回傳牌背
        return this.handCardListMap.get(chairIndex).stream().map(card -> ConstMathCard.CardFaceEnum.BACK.getCode()).collect(Collectors.toList());
    }

    // 計算斷線回復手牌資訊 [選完牌仍蓋牌使用]
    public int[][] calculateCutReturnHandCardListArray(Map<Integer, List<ICard>> allPlayerSelectCardList, int dealCount) {
        // 1. 宣告空間
        List<List<Integer>> result = new ArrayList<>();

        // 2. 遍歷所有位置
        for (int chairIndex = 0; chairIndex < super.gamePlayerHlr.getMaxPlayerCount(); chairIndex++) {
            // 2.1 正常位置，添加手牌
            if (super.gamePlayerHlr.isValidChairIndex(chairIndex)) {

                if (Objects.isNull(allPlayerSelectCardList.get(chairIndex))) {
                    result.add(this.getBackCardList(dealCount));
                }else {
                    List<Integer> handCards = new ArrayList<>();
                    allPlayerSelectCardList.get(chairIndex).forEach(card -> handCards.add(card.getNumber()));
                    result.add(handCards);
                }

            }
            // 2.2 位置無人，添加空集合
            else {
                result.add(new ArrayList<>());
            }
        }

        // 3. 回傳
        return result.stream().map(integerList -> integerList.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
    }

    // 計算斷線回復手牌資訊 [賴子專用]
    public int[][] calculateCutReturnHandCardListArray(List<Integer> playerList, Map<Integer, List<ICard>> allPlayerSelectCardList) {
        // 1. 建立空間
        List<List<Integer>> result = this.generateHandCardListSpace(this.config.getMaxUser());

        // 2. 對應位置放入手牌
        this.handCardListMap.keySet().forEach(chairIndex -> result.set(chairIndex, this.getLaiziCutReturnHandCardList(chairIndex, playerList, allPlayerSelectCardList)));

        // 3. 回傳
        return result.stream().map(integerList -> integerList.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
    }

    // 取得指定座位的手牌陣列 [賴子專用]
    private List<Integer> getLaiziCutReturnHandCardList(int chairIndex, List<Integer> playerList, Map<Integer, List<ICard>> allPlayerSelectCardList) {
        // 1. 防呆
        if (Objects.isNull(this.handCardListMap.get(chairIndex))) {
            return new ArrayList<>();
        }

        // 2. 真人未翻牌，回傳未轉譯
        if (chairIndex == super.gamePlayerHlr.getHumanChairIndex() && !playerList.contains(chairIndex)) {
            return this.handCardListMap.get(chairIndex).stream().map(ICard::getNumber).collect(Collectors.toList());
        }

        // 3. 真人已翻牌，回傳轉譯
        if (chairIndex == super.gamePlayerHlr.getHumanChairIndex() && playerList.contains(chairIndex)) {
            return allPlayerSelectCardList.get(chairIndex).stream().map(ICard::getNumber).collect(Collectors.toList());
        }

        // 4. 機器人已翻牌
        if (playerList.contains(chairIndex)) {
            return allPlayerSelectCardList.get(chairIndex).stream().map(ICard::getNumber).collect(Collectors.toList());
        }

        // 5. 機器人未翻牌，回傳牌背
        return this.handCardListMap.get(chairIndex).stream().map(card -> ConstMathCard.CardFaceEnum.BACK.getCode()).collect(Collectors.toList());
    }


    //* 轉換卡牌相關 *//

    // 牌號陣列轉換為卡牌陣列
    public List<ICard> changeCardNumberArray2CardList(int[] cardNumberArray) {
        return this.cardWallList.stream()
                .filter(card ->
                        Arrays.stream(cardNumberArray).anyMatch(number -> number == card.getNumber()))
                .collect(Collectors.toList());
    }

    // 卡牌陣列轉換為牌號陣列
    public int[] changeCardList2CardNumberArray(List<ICard> cardList) {
        return cardList.stream()
                .mapToInt(ICard::getNumber)
                .toArray();
    }

    // 卡牌陣列轉換為牌點陣列
    public int[] changeCardList2CardPointArray(List<ICard> cardList) {
        return cardList.stream()
                .mapToInt(ICard::getPoint)
                .toArray();
    }

    // 轉換所有玩家卡牌到牌號
    public int[][] changeAllPlayerCardListMap2allPlayerCardNumberArray(Map<Integer, List<ICard>> playerCardListMap) {
        return IntStream.range(0, this.config.getMaxUser())
                .mapToObj(playerChairIndex ->
                        playerCardListMap.getOrDefault(playerChairIndex, Collections.emptyList()))
                .map(this::changeCardList2CardNumberArray)
                .toArray(int[][]::new);
    }

    // 轉換所有玩家卡牌到牌號
    public int[][] changeAllAreaCardListMap2allPlayerCardNumberArray(Map<Integer, List<ICard>> areaLiftCardListMap) {
        return areaLiftCardListMap.keySet().stream()
                .map(areaIndex ->
                        this.changeCardList2CardNumberArray(areaLiftCardListMap.getOrDefault(areaIndex, Collections.emptyList())))
                .toArray(int[][]::new);
    }


    //* 產賴子牌 *//

    // 列出所有賴子轉換卡牌列表
    public List<ICard> generateLaiziCardList(int targetCardType, ICard laiziCard) {

        List<ICard> targetTypCardList = this.cardWallList.stream().filter(card -> card.getType() == targetCardType).collect(Collectors.toList());

        List<ICard> result = new ArrayList<>();
        for (ICard card : targetTypCardList) {
            result.add(this.generateLaiziTransCard(card.getValue(), laiziCard));
        }

        return result;
    }

    // 產出賴子轉換牌
    private ICard generateLaiziTransCard(int cardValue, ICard laiziCard) {
        int countIndex = (laiziCard.getValue() + 2) % 10;
        return this.generateCard(cardValue, countIndex, this.config.getCardValueWeightMap());
    }


    // 重設
    @Override
    public void reset() {
        this.cardWallList = this.generateCardWallList(this.config.getInitWallList(), this.config.getCardValueWeightMap());
        this.currentFrontIndex = 0;
        this.currentBackIndex = this.cardWallList.size() - 1;
        this.areaCardListMap.clear();
        this.handCardListMap.clear();
    }
}
