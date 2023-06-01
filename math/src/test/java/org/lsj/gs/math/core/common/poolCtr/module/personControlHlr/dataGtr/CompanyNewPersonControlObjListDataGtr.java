package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr;

import org.lsj.db.CompanyNewPersonControlObj;

import java.util.ArrayList;
import java.util.List;

// 公司個人控設定列表資料產生器
public class CompanyNewPersonControlObjListDataGtr {
    private final ControlStrategyJsonDataGtr controlStrategyJsonDataGtr; // 個人控策略JSON格式資料產生器
    private final NumericalObjDataGtr numericalObjDataGtr; // 數控物件資料產生器

    public CompanyNewPersonControlObjListDataGtr() {
        this.controlStrategyJsonDataGtr = new ControlStrategyJsonDataGtr();
        this.numericalObjDataGtr = new NumericalObjDataGtr();
    }

    // 建立預設僅一個公司個人控設定列表(指定個人控策略字串格式)
    public List<CompanyNewPersonControlObj> createOneCompanyNewPersonControlObjListDefault() {
        return this.createOneCompanyNewPersonControlObjListWithAllNeedParameter(
                1,
                1,
                100,
                this.controlStrategyJsonDataGtr.getDefaultControlStrategyJson(),
                this.numericalObjDataGtr.getDefaultNumericalJsonString());
    }

    // 建立僅一個公司個人控設定列表(指定個人控策略字串格式)
    public List<CompanyNewPersonControlObj> createOneCompanyNewPersonControlObjListWithControlStrategyJson(int id, int status, int maxExecuteTime, String controlStrategyJson) {
        return this.createOneCompanyNewPersonControlObjListWithAllNeedParameter(
                id,
                status,
                maxExecuteTime,
                controlStrategyJson,
                this.numericalObjDataGtr.getDefaultNumericalJsonString());
    }

    // 建立僅一個公司個人控設定列表(指定數控設定字串格式)
    public List<CompanyNewPersonControlObj> createOneCompanyNewPersonControlObjListWithNumericalJsonString(int id, int status, int maxExecuteTime, String numericalJsonString) {
        return this.createOneCompanyNewPersonControlObjListWithAllNeedParameter(
                id,
                status,
                maxExecuteTime,
                this.controlStrategyJsonDataGtr.getDefaultControlStrategyJson(),
                numericalJsonString);
    }

    // 建立僅一個公司個人控設定列表(所有需要參數)
    private List<CompanyNewPersonControlObj> createOneCompanyNewPersonControlObjListWithAllNeedParameter(
            int id,
            int status,
            int maxExecuteTime,
            String controlStrategyJson,
            String numericalJsonString) {
        return new ArrayList<>(){{
            add(new CompanyNewPersonControlObj(
                    null,
                    0,
                    controlStrategyJson ,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    null,
                    status,
                    maxExecuteTime,
                    numericalJsonString,
                    null,
                    0,
                    0.0,
                    0,
                    0,
                    0.0,
                    0.0,
                    0.0,
                    null,
                    null,
                    "test01",
                    1002,
                    id
            ));
        }};
    }

    public NumericalObjDataGtr getNumericalObjDataGtr() {
        return numericalObjDataGtr;
    }
}
