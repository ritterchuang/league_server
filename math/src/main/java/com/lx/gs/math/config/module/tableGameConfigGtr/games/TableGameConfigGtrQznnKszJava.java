package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigQznnKszJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import com.lx.gs.user.IUser;

// 新看三張搶莊牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrQznnKszJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigQznnKszJava generateTableGameConfig(IUser user) {
        return (TableGameConfigQznnKszJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.QZNN_KSZ_JAVA_DEFAULT);
    }
}
