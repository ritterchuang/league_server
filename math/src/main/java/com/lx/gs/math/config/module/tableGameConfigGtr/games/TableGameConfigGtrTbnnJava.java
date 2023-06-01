package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigTbnnJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrSlot;
import com.lx.gs.user.IUser;

// 通比牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrTbnnJava extends AbstractTableGameConfigGtrSlot {
    public TableGameConfigTbnnJava generateTableGameConfig(IUser user) {
        return (TableGameConfigTbnnJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.TBNN_JAVA_DEFAULT);
    }
}
