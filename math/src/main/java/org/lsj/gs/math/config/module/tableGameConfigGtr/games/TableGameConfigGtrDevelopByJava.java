package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.user.IUser;

// 開發魚機 牌桌遊戲設定產生器
public class TableGameConfigGtrDevelopByJava {
    public TableGameConfigFish generateTableGameConfig(IUser user) {
        return (TableGameConfigFish) TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.DEVELOP_BY_JAVA_DEFAULT);
    }
}
