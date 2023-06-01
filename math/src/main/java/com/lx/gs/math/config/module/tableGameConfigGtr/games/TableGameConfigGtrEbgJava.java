package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigEbgJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import com.lx.gs.user.IUser;

// 新搶莊二八槓 牌桌遊戲設定產生器
public class TableGameConfigGtrEbgJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigEbgJava generateTableGameConfig(IUser user) {
        return (TableGameConfigEbgJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.EBG_JAVA_DEFAULT);
    }
}
