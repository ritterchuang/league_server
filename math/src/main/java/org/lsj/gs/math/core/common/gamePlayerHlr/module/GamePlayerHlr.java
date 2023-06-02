package org.lsj.gs.math.core.common.gamePlayerHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayerHlrConfig;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.user.AbstractUser;
import org.lsj.gs.user.IUser;
import org.lsj.utils.MathUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 遊戲玩家處理器
public class GamePlayerHlr {
    private final GamePlayerHlrConfig config; // 遊戲玩家處理器設定
    private final GamePlayerFactory gamePlayerFactory; // 遊戲玩家工廠
    private final Map<Integer, GamePlayer> gamePlayerMap; // 遊戲玩家 <玩家座位索引, 玩家資訊>
    private final int playerCount; // 玩家總數
    private boolean isHumanEnterTable; // 真人入桌標誌
    private final int humanChairIndex; // 真人座位索引

    public GamePlayerHlr(GamePlayerHlrConfig config, IUser user, ConstMathCommon.GamePlayerHlrConsumeRnd gamePlayerHlrConsumeRnd, boolean isHumanEnterTable, ITableUtil tableUtil) {
        this.config = config;
        this.gamePlayerFactory = new GamePlayerFactory(config.getMinUser(), config.getMaxUser(), config.getBaseScore(), gamePlayerHlrConsumeRnd, tableUtil);
        this.gamePlayerMap = this.gamePlayerFactory.createGamePlayerMap(user);
        this.playerCount = this.calculatePlayerCount();
        this.isHumanEnterTable = isHumanEnterTable;
        this.humanChairIndex = this.calculateHumanChairIndex();
    }

    //* 更新玩家資訊相關 *//
    // 更新所有玩家起始金額
    public void updateAllPlayerAfterMoney(Map<Integer, UidScore> uidScoreMap) {
        for (Map.Entry<Integer, UidScore> uidScoreEntry: uidScoreMap.entrySet()) {
            if(Objects.nonNull(this.getAllGamePlayerMap().get(uidScoreEntry.getKey()))){
                this.getAllGamePlayerMap().get(uidScoreEntry.getKey()).setAfterMoney(
                        MathUtil.add(
                                this.getAllGamePlayerMap().get(uidScoreEntry.getKey()).getBeginMoney(),
                                uidScoreEntry.getValue().getScore()
                        )
                );
            }
        }
    }


    //* 機器人相關 *//

    // 取得所有機器人座位
    public List<Integer> getRobotChairIndexList() {
        return new ArrayList<>(this.gamePlayerMap.keySet()).stream().filter(chairIndex -> this.gamePlayerMap.get(chairIndex).isRobot()).collect(Collectors.toList());
    }

    //  替換的機器人
    public void replaceRobot() {
        // 1. 取得所有機器人座位
        List<Integer> robotChairIndexList = this.getRobotChairIndexList();

        // 2. 遍歷機器人
        for (Integer robotChairIndex: robotChairIndexList) {
            // 3. 替換不足房間最低進入門檻的機器人
            if(this.gamePlayerMap.get(robotChairIndex).getBeginMoney() <= this.config.getBaseScore()){
                this.gamePlayerMap.put(robotChairIndex, this.gamePlayerFactory.createRobotGamePlayer(robotChairIndex));
            }
        }
    }


    //* 取得玩家資訊相關 *//

    // 取得所有玩家資訊
    public Map<Integer, GamePlayer> getAllGamePlayerMap() {
        return this.gamePlayerMap;
    }

    // 取得所有玩家資訊列表
    public List<GamePlayer> getAllGamePlayerList() {
        return new ArrayList<>(this.gamePlayerMap.values());
    }

    // 取得指定個數的依照起始金額排序後的玩家資訊列表
    public List<GamePlayer> getGamePlayerSortByBeginMoneyTopLimitList(int topLimit) {
        return new ArrayList<>(this.gamePlayerMap.values()).stream().sorted(Comparator.comparing(GamePlayer::getBeginMoney).reversed()).limit(topLimit).collect(Collectors.toList());
    }

    // 取得指定個數的依照起始金額排序後的機器人資訊列表
    public List<GamePlayer> getGamePlayerSortByBeginMoneyTopLimitRobotList(int topLimit) {
        return new ArrayList<>(this.gamePlayerMap.values()).stream().sorted(Comparator.comparing(GamePlayer::getBeginMoney).reversed()).limit(topLimit).filter(GamePlayer::isRobot).collect(Collectors.toList());
    }

    // 取得指定個數的依照剩餘金額排序後的玩家資訊列表
    public List<GamePlayer> getGamePlayerSortByAfterMoneyTopLimitList(int topLimit) {
        return new ArrayList<>(this.gamePlayerMap.values()).stream().sorted(Comparator.comparing(GamePlayer::getAfterMoney).reversed()).limit(topLimit).collect(Collectors.toList());
    }

    // 取得所有玩家座位索引陣列
    public List<Integer> getAllGamePlayerChairIndexList() {
        return new ArrayList<>(this.gamePlayerMap.keySet());
    }

    // 取得所有玩家初始金額
    public double[] getAllGamePlayerBeginMoney() {
        List<Double> result = Stream.iterate(0.0, i -> 0.0).limit(this.config.getMaxUser()).collect(Collectors.toList());
        this.gamePlayerMap.values().forEach(player -> result.set(player.getChairIndex(), player.getBeginMoney()));
        return result.stream().mapToDouble(i -> i).toArray();
    }

    // 取得指定玩家金額
    public double getPlayerBeginMoney(int chairIndex) { return this.gamePlayerMap.get(chairIndex).getBeginMoney(); }

    // 取得玩家數
    public int getPlayerCount() {
        return this.playerCount;
    }

    // 計算玩家數
    private int calculatePlayerCount(){
        return this.gamePlayerMap.size();
    }

    // 是否為合法座位
    public boolean isValidChairIndex(int chairIndex) {
        return !Objects.isNull(this.gamePlayerMap.get(chairIndex));
    }

    // 取得最大遊戲人數
    public int getMaxPlayerCount() {
        return this.config.getMaxUser();
    }

    // 更新玩家初始餘額
    public void updatePlayerBeginMoneyBeforeGame() {
        this.gamePlayerMap.values().forEach(GamePlayer::updateBeginMoneyBeforeGame);
    }

    //* 真人相關 *//

    // 真人入桌
    public void enterTable(IUser user){
        this.isHumanEnterTable = true;
        this.gamePlayerMap.put(this.humanChairIndex, GamePlayerFactory.createHumanGamePlayer(user, this.humanChairIndex));
    }

    // 計算相同真人標誌
    private boolean calculateSameHuman(IUser user) {
        return this.gamePlayerMap.get(this.humanChairIndex).getUid() == user.getUid();
    }

    // 真人離桌
    public void leaveTable(){
        this.isHumanEnterTable = false;
    }

    // 取得真人玩家
    public GamePlayer getHumanGamePlayer() { return this.gamePlayerMap.get(this.humanChairIndex); }

    // 取得真人玩家識別碼
    public int getHumanUid() { return this.gamePlayerMap.get(this.humanChairIndex).getUid(); }

    // 取得真人座位索引
    public int getHumanChairIndex() {
        return this.humanChairIndex;
    }

    // 取得真人起始金額
    public double getHumanBeginMoney() { return this.gamePlayerMap.get(this.humanChairIndex).getBeginMoney(); }

    // 取得真人剩餘金額
    public double getHumanAfterMoney() { return this.gamePlayerMap.get(this.humanChairIndex).getAfterMoney(); }

    // 更新真人
    public void updateHumanUser(IUser user) {
        this.gamePlayerMap.put(this.humanChairIndex, new GamePlayer((AbstractUser) user, this.humanChairIndex, MathUtil.moneyScale(user.getBalance())));
    }

    // 更新真人會話
    public void updateHumanSession(IUser user) {
        this.gamePlayerMap.get(this.humanChairIndex).setSession(user.getSession());
    }

    // 判斷是否為真人
    public boolean isHumanChairIndex(int chairIndex) {
        return !this.gamePlayerMap.get(chairIndex).isRobot();
    }

    // 計算真人座位
    private int calculateHumanChairIndex() {
        return this.gamePlayerMap.entrySet().stream().filter(entry -> !entry.getValue().isRobot()).findFirst().get().getKey();
    }

    public boolean isHumanEnterTable() {
        return isHumanEnterTable;
    }


    // 重設
    public void reset() {}
}
