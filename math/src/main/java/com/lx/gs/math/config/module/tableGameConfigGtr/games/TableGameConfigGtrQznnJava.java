package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigQznnJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import com.lx.gs.user.IUser;

// 新搶莊牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrQznnJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigQznnJava generateTableGameConfig(IUser user) {
        return (TableGameConfigQznnJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.QZNN_JAVA_DEFAULT);
    }
}
