package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigQznnK4zJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import com.lx.gs.user.IUser;

// 新看四張搶莊牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrQznnK4zJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigQznnK4zJava generateTableGameConfig(IUser user) {
        return (TableGameConfigQznnK4zJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.QZNN_K4Z_JAVA_DEFAULT);
    }
}
