package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr;

// 個人控設定資料產生器
public class PersonControlConfigDataGtr {
    private final CompanyNewPersonControlObjListDataGtr companyNewPersonControlObjListDataGtr; // 公司個人控設定列表資料產生器

    public PersonControlConfigDataGtr() {
        this.companyNewPersonControlObjListDataGtr = new CompanyNewPersonControlObjListDataGtr();
    }

    public CompanyNewPersonControlObjListDataGtr getCompanyNewPersonControlObjListDataGtr() {
        return companyNewPersonControlObjListDataGtr;
    }
}
