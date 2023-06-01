package com.lx.gs.math.config.module.tableGameConfigGtr.games;

import com.lx.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigLznnJava;
import com.lx.gs.math.config.module.TableGameConfigReader;
import com.lx.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import com.lx.gs.user.IUser;

// 新賴子牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrLznnJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigLznnJava generateTableGameConfig(IUser user) {
        return (TableGameConfigLznnJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.LZNN_JAVA_DEFAULT);
    }
}
