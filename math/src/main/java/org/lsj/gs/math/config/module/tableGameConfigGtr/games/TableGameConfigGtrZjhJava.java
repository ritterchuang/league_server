package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigZjhJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import org.lsj.gs.user.IUser;

// 新炸金花 牌桌遊戲設定產生器
public class TableGameConfigGtrZjhJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigZjhJava generateTableGameConfig(IUser user) {
        return (TableGameConfigZjhJava) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.ZJH_JAVA_DEFAULT);
    }
}
