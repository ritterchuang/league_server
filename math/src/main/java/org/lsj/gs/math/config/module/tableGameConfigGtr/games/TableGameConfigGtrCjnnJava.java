package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigCjnnJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrBaiRen;
import org.lsj.gs.user.IUser;

// 新超級牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrCjnnJava extends AbstractTableGameConfigGtrBaiRen {
    public TableGameConfigCjnnJava generateTableGameConfig(IUser user) {
        return (TableGameConfigCjnnJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.CJNN_JAVA_DEFAULT);
    }
}
