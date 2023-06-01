package org.lsj.gs.mathStr.config.resources.gameCenterMgr;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.module.PlayGameFieldConfigReader;
import org.lsj.gs.mathStr.config.entity.GameCenterMgrConfig;

// 遊戲中心管理器設定資源 314 歐賴利的寶藏 正常模擬
public class GameCenterMgrR314OlldbzJavaNormal {
    public GameCenterMgrConfig create(){
        return new GameCenterMgrConfig(
            new PlayGameFieldConfig[]{
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R314_OLLDBZ_JAVA_NORMAL)
            }
        );
    }
}
