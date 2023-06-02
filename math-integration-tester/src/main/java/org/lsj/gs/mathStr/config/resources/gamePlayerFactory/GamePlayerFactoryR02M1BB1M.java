package org.lsj.gs.mathStr.config.resources.gamePlayerFactory;

import org.lsj.gs.mathStr.config.entity.GamePlayerConfig;
import org.lsj.gs.mathStr.config.entity.GamePlayerFactoryConfig;

// 玩家工廠設定資源 10億 100萬場
public class GamePlayerFactoryR02M1BB1M {
    public GamePlayerFactoryConfig create(){
        return new GamePlayerFactoryConfig(new GamePlayerConfig[]{
            new GamePlayerConfig(
                    1_000_000_000,
                    1_000_000,
                    100
            )
        });
    }
}
