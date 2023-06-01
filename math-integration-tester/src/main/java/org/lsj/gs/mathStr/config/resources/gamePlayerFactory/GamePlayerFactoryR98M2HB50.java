package org.lsj.gs.mathStr.config.resources.gamePlayerFactory;

import org.lsj.gs.mathStr.config.entity.GamePlayerConfig;
import org.lsj.gs.mathStr.config.entity.GamePlayerFactoryConfig;

// 玩家工廠設定資源 200元 50場
public class GamePlayerFactoryR98M2HB50 {
    public GamePlayerFactoryConfig create(){
        return new GamePlayerFactoryConfig(new GamePlayerConfig[]{
            new GamePlayerConfig(
                    200,
                    50,
                    100
            )
        });
    }
}
