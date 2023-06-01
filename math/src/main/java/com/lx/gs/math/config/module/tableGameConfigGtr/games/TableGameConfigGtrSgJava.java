package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSgJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import com.lx.gs.user.IUser;

// 新搶莊三公 牌桌遊戲設定產生器
public class TableGameConfigGtrSgJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigSgJava generateTableGameConfig(IUser user) {
        return (TableGameConfigSgJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.SG_JAVA_DEFAULT);
    }
}
