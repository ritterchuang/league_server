package com.lx.gs.mathStr.config.resources.gameCenterMgr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.module.PlayGameFieldConfigReader;
import com.lx.gs.mathStr.config.entity.GameCenterMgrConfig;

// 遊戲中心管理器設定資源 113 新搶莊牛牛 正常模擬
public class GameCenterMgrR113QznnJavaNormal {
    public GameCenterMgrConfig create(){
        return new GameCenterMgrConfig(
            new PlayGameFieldConfig[]{
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R113_QZNN_JAVA_NORMAL)
            }
        );
    }
}
