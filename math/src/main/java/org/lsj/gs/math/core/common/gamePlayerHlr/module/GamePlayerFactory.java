package org.lsj.gs.math.core.common.gamePlayerHlr.module;

import org.lsj.ConstCommon;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtil;
import org.lsj.gs.user.AbstractUser;
import org.lsj.gs.user.IUser;
import org.lsj.gs.user.User;
import org.lsj.gs.user.UserBdr;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// 遊戲玩家工廠
public class GamePlayerFactory {
    private static final int ROBOT_UID_MIN_VALUE = 200000; // 機器人識別碼最小值
    private static final int ROBOT_UID_RANGE_VALUE = 100000; // 機器人識別碼隨機範圍
    private static final int MAX_HEAD_IMAGE_COUNT = 24; // 頭像最大上限數
    private static final int MAX_VIP_LEVEL = 25; // VIP等級最大上限數(範圍0~49)
    private static final int DEFAULT_BOX_ID = 0; // 預設包廂編碼
    private final int minUser; // 最小玩家數
    private final int maxUser; // 最大玩家數
    private final double baseScore; // 底注
    private final IRandomUtil randomUtilMain; // 主要亂數工具包
    private final IRandomUtil randomUtilNotSupportSetRnd; // 不支援亂數設定亂數工具包

    public GamePlayerFactory(int minUser, int maxUser, double baseScore, ConstMathCommon.GamePlayerHlrConsumeRnd gamePlayerHlrConsumeRnd, ITableUtil tableUtil) {
        this.minUser = minUser;
        this.maxUser = maxUser;
        this.baseScore = baseScore;
        this.randomUtilMain = (gamePlayerHlrConsumeRnd.equals(ConstMathCommon.GamePlayerHlrConsumeRnd.YES)) ? tableUtil.getMainRandomUtil() : tableUtil.getNotSupportSetRngDataRandomUtil();
        this.randomUtilNotSupportSetRnd = tableUtil.getNotSupportSetRngDataRandomUtil();
    }

    /* 建立玩家對應流程 */
    // 建立遊戲玩家對應表
    public Map<Integer, GamePlayer> createGamePlayerMap(IUser user) {
        // 1. 決定座位
        List<Integer> chairIndexList = this.calculateChairIndexList();

        // 2. 生成真人遊戲玩家
        GamePlayer humanPlayer = createHumanGamePlayer(user, chairIndexList.get(0));

        // 3. 生成機器遊戲玩家
        List<GamePlayer> robotPlayerList = this.createRobotGamePlayerList(chairIndexList.subList(1, chairIndexList.size()));

        // 4. 封裝結果
        return this.combineGamePlayerMap(humanPlayer, robotPlayerList);
    }

    // 計算座位順序
    private List<Integer> calculateChairIndexList() {
        List<Integer> result = new ArrayList<>();

        // 1. 決定玩家人數
        int playerCount = this.minUser + this.randomUtilMain.getRandomIntWithAccuracy(this.maxUser - this.minUser + 1, ConstMathCommon.AccuracyType.A32768);

        // 2. 計算全部位置數
        Stream.iterate(0, i -> i + 1).limit(this.maxUser).forEach(result::add);

        // 3. 決定位置
        this.randomUtilMain.shuffleList(result);

        return result.subList(0, playerCount);
    }

    // 建立機器人列表
    private List<GamePlayer> createRobotGamePlayerList(List<Integer> chairIndexList) {
        List<GamePlayer> result = new ArrayList<>();
        chairIndexList.forEach(chairIndex -> result.add(this.createRobotGamePlayer(chairIndex)));
        return result;
    }

    // 組合遊戲玩家對應表
    private Map<Integer, GamePlayer> combineGamePlayerMap(GamePlayer humanPlayer, List<GamePlayer> robotPlayerList) {
        Map<Integer, GamePlayer> result = new HashMap<>();
        result.put(humanPlayer.getChairIndex(), humanPlayer);
        robotPlayerList.forEach(robotPlayer -> result.put(robotPlayer.getChairIndex(), robotPlayer));
        return result;
    }


    /* 建立遊戲玩家物件相關 */
    // 建立真人玩家
    public static GamePlayer createHumanGamePlayer(IUser user, int chairIndex){
        return new GamePlayer((AbstractUser) user, chairIndex, MathUtil.moneyScale(user.getBalance()));
    }

    // 建立機器人玩家
    public GamePlayer createRobotGamePlayer(int chairIndex) {
        // 1. 準備參數
        int uid = this.calculateRobotUid();

        // 2. 建立機器使用者
        User robotUser = (User) new UserBdr()
                .setUid(uid)
                .setIp(this.calculateRobotIp())
                .setRobot(ConstCommon.UserType.ROBOT.getCode())
                .setNickName(this.calculateRobotNickName())
                .setAccount(this.calculateRobotAccount(uid))
                .setHeadImgUrl(this.calculateRobotHeadImgUrl())
                .setSex(this.calculateRobotSex())
                .setBoxid(DEFAULT_BOX_ID)
                .setRole(ConstCommon.RoleType.PLAYER.getCode())
                .setChair(chairIndex)
                .setState(ConstCommon.UserStateType.WAIT.getCode())
                .setVipLevel(this.calculateRobotVipLevel())
                .createUser();

        return new GamePlayer(robotUser, chairIndex, MathUtil.moneyScale(this.calculateRobotBeginMoney()));
    }

    // 計算機器人起始金額
    private double calculateRobotBeginMoney() {
        double bottomValue = MathUtil.multiply(this.baseScore, 160 * 100);
        double topValue = MathUtil.multiply(this.baseScore, 600 * 100);
        double diffValue = MathUtil.subtract(topValue, bottomValue);

        double randomValue = this.randomUtilMain.getRandomDoubleWithAccuracy(ConstMathCommon.AccuracyType.A32768);

        return MathUtil.divide(
                MathUtil.add(bottomValue, MathUtil.multiply(diffValue, randomValue)),
                100.0);
    }


    /* 建立機器使用者相關 */
    // 計算機器人使用者標誌
    private int calculateRobotUid() {
        return ROBOT_UID_MIN_VALUE + this.randomUtilNotSupportSetRnd.getRandomIntWithAccuracy(ROBOT_UID_RANGE_VALUE, ConstMathCommon.AccuracyType.MILLION);
    }

    // 建立機器人帳號
    private String calculateRobotAccount(int uid) {
        return "robot_" + uid;
    }

    // 建立機器人暱稱
    private String calculateRobotNickName() {
       return "老板" + this.randomUtilNotSupportSetRnd.randomAlphanumeric(9);
    }

    // 計算機器人頭像項目
    private String calculateRobotHeadImgUrl() {
        return Integer.toString(this.randomUtilNotSupportSetRnd.getRandomIntWithAccuracy(MAX_HEAD_IMAGE_COUNT, ConstMathCommon.AccuracyType.A32768) + 1);
    }

    // 計算機器人IP位置
    private String calculateRobotIp() {
        return "127.0.0.1";
    }

    // 計算機器人性別
    private int calculateRobotSex() {
        return this.randomUtilNotSupportSetRnd.isHitWithAccuracy(0.5, ConstMathCommon.AccuracyType.A32768)
                ? ConstCommon.SexType.FEMALE.getCode()
                : ConstCommon.SexType.MALE.getCode();
    }

    // 計算機器人VIP等級
    private int calculateRobotVipLevel() {
        return this.randomUtilNotSupportSetRnd.getRandomIntWithAccuracy(MAX_VIP_LEVEL, ConstMathCommon.AccuracyType.A32768);
    }
}
