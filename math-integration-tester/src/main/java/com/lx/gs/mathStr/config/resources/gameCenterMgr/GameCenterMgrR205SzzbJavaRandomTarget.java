package com.lx.gs.mathStr.config.resources.gameCenterMgr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.module.PlayGameFieldConfigReader;
import com.lx.gs.mathStr.config.entity.GameCenterMgrConfig;

// 遊戲中心管理器設定資源 205 世足爭霸 正常模擬
public class GameCenterMgrR205SzzbJavaRandomTarget {
    public GameCenterMgrConfig create(){
        return new GameCenterMgrConfig(
            new PlayGameFieldConfig[]{
                    new PlayGameFieldConfigReader().getConfig(ConstPlayGameField.PlayGameFieldResource.R205_SZZB_JAVA_RANDOM_TARGET)
            }
        );
    }
}
