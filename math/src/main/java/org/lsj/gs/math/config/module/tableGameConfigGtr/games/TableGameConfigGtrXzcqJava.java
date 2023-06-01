package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrSlot;
import org.lsj.gs.user.IUser;

// 小豬傳奇 牌桌遊戲設定產生器
public class TableGameConfigGtrXzcqJava extends AbstractTableGameConfigGtrSlot {
    public TableGameConfigSlot generateTableGameConfig(IUser user) {
        return (TableGameConfigSlot) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.XZCQ_JAVA_DEFAULT);
    }
}
