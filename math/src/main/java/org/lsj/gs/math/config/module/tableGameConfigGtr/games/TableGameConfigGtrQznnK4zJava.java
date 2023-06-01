package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQznnK4zJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import org.lsj.gs.user.IUser;

// 新看四張搶莊牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrQznnK4zJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigQznnK4zJava generateTableGameConfig(IUser user) {
        return (TableGameConfigQznnK4zJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.QZNN_K4Z_JAVA_DEFAULT);
    }
}
