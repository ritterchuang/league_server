package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigBjlJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrBaiRen;
import com.lx.gs.user.IUser;

// 新百家樂 牌桌遊戲設定產生器
public class TableGameConfigGtrBjlJava extends AbstractTableGameConfigGtrBaiRen {
    public TableGameConfigBjlJava generateTableGameConfig(IUser user) {
        return (TableGameConfigBjlJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.BJL_JAVA_DEFAULT);
    }
}
