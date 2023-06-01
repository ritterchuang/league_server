package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigMybjlJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrBaiRen;
import com.lx.gs.user.IUser;

// 新免傭百家樂 牌桌遊戲設定產生器
public class TableGameConfigGtrMybjlJava extends AbstractTableGameConfigGtrBaiRen {
    public TableGameConfigMybjlJava generateTableGameConfig(IUser user) {
        return (TableGameConfigMybjlJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.MYBJL_JAVA_DEFAULT);
    }
}
