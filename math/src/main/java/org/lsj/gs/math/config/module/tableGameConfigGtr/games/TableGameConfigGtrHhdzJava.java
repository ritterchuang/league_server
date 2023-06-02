package org.lsj.gs.math.config.module.tableGameConfigGtr.games;

import org.lsj.gs.math.config.entity.tableGameConfig.ConstTableGameConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigHhdzJava;
import org.lsj.gs.math.config.module.TableGameConfigReader;
import org.lsj.gs.math.config.module.tableGameConfigGtr.AbstractTableGameConfigGtrBaiRen;
import org.lsj.gs.user.IUser;

// 新紅黑大戰 牌桌遊戲設定產生器
public class TableGameConfigGtrHhdzJava extends AbstractTableGameConfigGtrBaiRen {
    public TableGameConfigHhdzJava generateTableGameConfig(IUser user) {
        return (TableGameConfigHhdzJava)TableGameConfigReader.getInstance().getTableGameConfig(
                ConstTableGameConfig.TableGameConfigResource.HHDZ_JAVA_DEFAULT);
    }
}
