package org.lsj.gs.pool;

import org.lsj.db.CompanyNewPersonControlObj;
import org.lsj.db.NumericalConfigObj;

import java.util.List;
import java.util.Map;

// 個人控設定
public class PersonControlConfig {
    private final List<CompanyNewPersonControlObj> companyNewPersonControlObjList; // 個人控清單
    private final Map<Integer, NumericalConfigObj> idToNumericalConfigObjMap; // 數控設定(依遊戲類型決定執行機率)

    public PersonControlConfig(List<CompanyNewPersonControlObj> companyNewPersonControlObjList, Map<Integer, NumericalConfigObj> idToNumericalConfigObjMap) {
        this.companyNewPersonControlObjList = companyNewPersonControlObjList;
        this.idToNumericalConfigObjMap = idToNumericalConfigObjMap;
    }

    public List<CompanyNewPersonControlObj> getCompanyNewPersonControlObjList() {
        return companyNewPersonControlObjList;
    }

    public Map<Integer, NumericalConfigObj> getIdToNumericalConfigObjMap() {
        return idToNumericalConfigObjMap;
    }
}
