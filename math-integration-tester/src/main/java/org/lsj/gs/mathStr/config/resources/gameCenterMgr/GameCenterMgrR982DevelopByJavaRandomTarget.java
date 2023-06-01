package org.lsj.gs.mathStr.config.resources.gameCenterMgr;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.module.PlayGameFieldConfigReader;
import org.lsj.gs.mathStr.config.entity.GameCenterMgrConfig;

// 遊戲中心管理器設定資源 982 模板捕魚 正常模擬
public class GameCenterMgrR982DevelopByJavaRandomTarget {
    public GameCenterMgrConfig create(){
        return new GameCenterMgrConfig(
            new PlayGameFieldConfig[]{
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R982_DEVELOP_BY_JAVA_RANDOM_TARGET)
            }
        );
    }
}
