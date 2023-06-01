package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrSlot;
import com.lx.gs.user.IUser;

// 旺來 牌桌遊戲設定產生器
public class TableGameConfigGtrWlJava extends AbstractTableGameConfigGtrSlot {
    public TableGameConfigSlot generateTableGameConfig(IUser user) {
        return (TableGameConfigSlot) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.WL_JAVA_DEFAULT);
    }
}
