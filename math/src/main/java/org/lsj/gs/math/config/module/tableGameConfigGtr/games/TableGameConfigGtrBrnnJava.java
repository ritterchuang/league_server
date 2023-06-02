package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigBrnnJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrBaiRen;
import org.lsj.gs.user.IUser;

// 新百人牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrBrnnJava extends AbstractTableGameConfigGtrBaiRen {
    public TableGameConfigBrnnJava generateTableGameConfig(IUser user) {
        return (TableGameConfigBrnnJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.BRNN_JAVA_DEFAULT);
    }
}
