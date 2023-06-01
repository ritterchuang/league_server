package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSgJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import org.lsj.gs.user.IUser;

// 新搶莊三公 牌桌遊戲設定產生器
public class TableGameConfigGtrSgJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigSgJava generateTableGameConfig(IUser user) {
        return (TableGameConfigSgJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.SG_JAVA_DEFAULT);
    }
}
