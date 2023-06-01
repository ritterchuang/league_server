package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigMybjlJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrBaiRen;
import org.lsj.gs.user.IUser;

// 新免傭百家樂 牌桌遊戲設定產生器
public class TableGameConfigGtrMybjlJava extends AbstractTableGameConfigGtrBaiRen {
    public TableGameConfigMybjlJava generateTableGameConfig(IUser user) {
        return (TableGameConfigMybjlJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.MYBJL_JAVA_DEFAULT);
    }
}
