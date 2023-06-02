package org.lsj.gs.mathStr.config.entity;

// 玩家工廠設定
public class GamePlayerFactoryConfig {
    public final GamePlayerConfig[] gamePlayerConfigArray; // 玩家設定陣列

    public GamePlayerFactoryConfig(GamePlayerConfig[] gamePlayerConfigArray){
        this.gamePlayerConfigArray = gamePlayerConfigArray;
    }
}
