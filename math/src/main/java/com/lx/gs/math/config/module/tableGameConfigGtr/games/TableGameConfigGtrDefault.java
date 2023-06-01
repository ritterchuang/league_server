package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigDefault;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.user.IUser;

// 預設 牌桌遊戲設定產生器
public class TableGameConfigGtrDefault {
    public TableGameConfigDefault generateTableGameConfig(IUser user) {
        return (TableGameConfigDefault) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.DEFAULT);
    }
}
