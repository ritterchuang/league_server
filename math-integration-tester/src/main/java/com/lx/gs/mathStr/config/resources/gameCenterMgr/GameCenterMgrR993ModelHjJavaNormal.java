package com.lx.gs.mathStr.config.resources.gameCenterMgr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.module.PlayGameFieldConfigReader;
import com.lx.gs.mathStr.config.entity.GameCenterMgrConfig;

// 遊戲中心管理器設定資源 993 模板虎機 正常模擬
public class GameCenterMgrR993ModelHjJavaNormal {
    public GameCenterMgrConfig create(){
        return new GameCenterMgrConfig(
            new PlayGameFieldConfig[]{
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R993_MODEL_HJ_JAVA_NORMAL)
            }
        );
    }
}
