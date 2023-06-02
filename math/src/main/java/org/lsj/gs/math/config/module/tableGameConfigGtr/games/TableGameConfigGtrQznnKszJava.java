package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQznnKszJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import org.lsj.gs.user.IUser;

// 新看三張搶莊牛牛 牌桌遊戲設定產生器
public class TableGameConfigGtrQznnKszJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigQznnKszJava generateTableGameConfig(IUser user) {
        return (TableGameConfigQznnKszJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.QZNN_KSZ_JAVA_DEFAULT);
    }
}
