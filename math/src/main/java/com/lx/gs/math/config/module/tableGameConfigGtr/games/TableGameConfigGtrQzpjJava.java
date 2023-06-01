package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigQzpjJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import com.lx.gs.user.IUser;

// 新搶莊牌九 牌桌遊戲設定產生器
public class TableGameConfigGtrQzpjJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigQzpjJava generateTableGameConfig(IUser user) {
        return (TableGameConfigQzpjJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.QZPJ_JAVA_DEFAULT);
    }
}
