package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrFish;
import com.lx.gs.user.IUser;

// 世足爭霸 牌桌遊戲設定產生器
public class TableGameConfigGtrSzzbJava extends AbstractTableGameConfigGtrFish {
    public TableGameConfigFish generateTableGameConfig(IUser user) {
        return (TableGameConfigFish) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.SZZB_JAVA_DEFAULT);
    }
}
