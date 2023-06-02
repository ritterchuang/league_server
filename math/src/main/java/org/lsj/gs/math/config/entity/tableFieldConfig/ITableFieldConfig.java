package org.lsj.gs.math.config.entity.tableFieldConfig;

import org.lsj.db.CompanyFieldObj;
import org.lsj.gs.FieldConfig;

public interface ITableFieldConfig {
    int getGameId();
    String getGameName();
    String getGameNameCN();
    short getMinUser();
    short getMaxUser();
    int getFieldIndex();
    String getFieldName();
    String getFieldNameCn();
    double getLimitMin();
    double getLimitMax();
    double getLimitKick();
    double getBase();
    double getGameRate();
    short getPlay();
    short getFeeType();
    CompanyFieldObj getCompanyFieldObj();
    FieldConfig getFieldConfig();
}
