package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQznnJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import org.lsj.gs.user.IUser;

// 新搶莊牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrQznnJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigQznnJava generateTableGameConfig(IUser user) {
        return (TableGameConfigQznnJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.QZNN_JAVA_DEFAULT);
    }
}
