package com.lx.gs.mathStr.config.resources.gamePlayerFactory;

import com.lx.gs.mathStr.config.entity.GamePlayerConfig;
import com.lx.gs.mathStr.config.entity.GamePlayerFactoryConfig;

// 玩家工廠設定資源 200萬 50場
public class GamePlayerFactoryR00M200WB50 {
    public GamePlayerFactoryConfig create(){
        return new GamePlayerFactoryConfig(new GamePlayerConfig[]{
            new GamePlayerConfig(
                    2000000,
                    50,
                    100
            )
        });
    }
}
