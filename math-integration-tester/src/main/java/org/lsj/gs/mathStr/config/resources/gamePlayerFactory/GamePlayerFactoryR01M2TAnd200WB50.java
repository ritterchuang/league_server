package org.lsj.gs.mathStr.config.resources.gamePlayerFactory;

import org.lsj.gs.mathStr.config.entity.GamePlayerConfig;
import org.lsj.gs.mathStr.config.entity.GamePlayerFactoryConfig;

// 玩家工廠設定資源 2000元 50場 與 200萬 50場
public class GamePlayerFactoryR01M2TAnd200WB50 {
    public GamePlayerFactoryConfig create(){
        return new GamePlayerFactoryConfig(new GamePlayerConfig[]{
                new GamePlayerConfig(
                        200,
                        50,
                        80
                ),
                new GamePlayerConfig(
                        2000000,
                        50,
                        20
                )
        });
    }
}
