package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigDefault;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.user.IUser;

// 預設 牌桌遊戲設定產生器
public class TableGameConfigGtrDefault {
    public TableGameConfigDefault generateTableGameConfig(IUser user) {
        return (TableGameConfigDefault) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.DEFAULT);
    }
}
