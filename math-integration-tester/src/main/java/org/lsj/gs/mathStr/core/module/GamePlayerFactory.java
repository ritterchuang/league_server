package org.lsj.gs.mathStr.core.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.JavaRandomUtil;
import org.lsj.gs.mathStr.config.entity.GamePlayerConfig;
import org.lsj.gs.mathStr.config.entity.GamePlayerFactoryConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.user.UserSimulationBdr;
import org.lsj.gs.user.IUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 遊戲玩家生成工廠
public class GamePlayerFactory {
    private final GamePlayerFactoryConfig gamePlayerFactoryConfig; // 遊戲玩家生成工廠設定
    private final JavaRandomUtil randomUtil; // 隨機工具包
    private int usedUid; // 已使用過的玩家編碼

    public GamePlayerFactory(GamePlayerFactoryConfig gamePlayerFactoryConfig){
        this.gamePlayerFactoryConfig = gamePlayerFactoryConfig;
        this.randomUtil = new JavaRandomUtil();
        this.usedUid = 0;
    }

    // 生成遊戲玩家
    public GamePlayerSimulation createGamePlayer() {
        // 1. 決定模擬器遊戲玩家索引
        List<Integer> weightList = new ArrayList<>();
        Arrays.stream(this.gamePlayerFactoryConfig.gamePlayerConfigArray).forEach(gamePlayerConfig -> weightList.add(gamePlayerConfig.getWeight()));
        int gamePlayerIndex = this.randomUtil.getArrayIndexByWeightWithAccuracy(weightList, ConstMathCommon.AccuracyType.A32768);

        // 2. 紀錄模擬器遊戲玩家設定
        GamePlayerConfig gamePlayerConfig = this.gamePlayerFactoryConfig.gamePlayerConfigArray[gamePlayerIndex];

        // 3. 生成模擬器玩家
        IUser user = new UserSimulationBdr()
                .setUid(this.usedUid)
                .setBalance(gamePlayerConfig.getUserInitMoney())
                .setAccount("user" + ((Integer)this.usedUid))
                .setHeadImgUrl("0")
                .setIp("127.0.0.1")
                .setNickName("user" + ((Integer)this.usedUid))
                .setRole(0)
                .setBoxid(0)
                .setSex(0)
                .setState(1)
                .setVipLevel(0)
                .createUser();

        // 4. 更新已使用過的玩家編碼
        this.usedUid++;

        // 5. 封裝模擬器遊戲玩家
        return new GamePlayerSimulation(gamePlayerConfig, user);
    }
}
