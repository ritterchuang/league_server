package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigEbgJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrCard;
import org.lsj.gs.user.IUser;

// 新搶莊二八槓 牌桌遊戲設定產生器
public class TableGameConfigGtrEbgJava extends AbstractTableGameConfigGtrCard {
    public TableGameConfigEbgJava generateTableGameConfig(IUser user) {
        return (TableGameConfigEbgJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.EBG_JAVA_DEFAULT);
    }
}
