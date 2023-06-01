package org.lsj.gs.mathStr.config.resources.gameCenterMgr;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.module.PlayGameFieldConfigReader;
import org.lsj.gs.mathStr.config.entity.GameCenterMgrConfig;

// 遊戲中心管理器設定資源 混房 正常模擬
public class GameCenterMgrR99MixGame {
    public GameCenterMgrConfig create(){
        return new GameCenterMgrConfig(
            new PlayGameFieldConfig[]{
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R113_QZNN_JAVA_NORMAL),
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R115_QZNN_KSZ_JAVA_NORMAL),
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R201_JCBY_JAVA_RANDOM_TARGET)
            }
        );
    }
}
