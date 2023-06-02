package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.db.CompanyNewPersonControlObj;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.AdjustInfo;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.NumericalObj;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.pool.PersonControlConfig;
import org.lsj.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 個人控類型計算器
public class PersonAdjustTypeCtr {
    private PersonControlConfig config; // 個人控設定檔
    private final AlgorithmTypeCtr algorithmTypeCtr; // 演算法類型計算器
    private final ITableUtil tableUtil; // 牌桌工具包
    private ConstMathCommon.PersonAdjustType companyPersonAdjustType; // 個人控類型

    public PersonAdjustTypeCtr(PersonControlConfig config, AlgorithmTypeCtr algorithmTypeCtr, ITableUtil tableUtil) {
        this.config = config;
        this.algorithmTypeCtr = algorithmTypeCtr;
        this.tableUtil = tableUtil;
        this.companyPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;
    }

    /* 計算個人控類型相關 */
    // 計算個人控類型
    public void calculatePersonAdjustType(AdjustInfo adjustInfo) {
        // 1. 防呆設定檔
        if (Objects.isNull(this.config.getCompanyNewPersonControlObjList()) || this.config.getCompanyNewPersonControlObjList().size() == 0) {
            this.companyPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;
            return;
        }

        // 2. 防呆個人控結果
        if (Objects.isNull(adjustInfo)) {
            this.companyPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;
            return;
        }

        // 3. 取得指定個人控設定
        CompanyNewPersonControlObj targetCompanyNewPersonControlObj = this.getTargetCompanyNewPersonControlObj(adjustInfo.getId(), this.config.getCompanyNewPersonControlObjList());

        // 4. 讀取數控物件
        List<NumericalObj> numericalObjList = this.readNumericalObjList(targetCompanyNewPersonControlObj);

        // 5. 隨機決定個人控類型
        this.companyPersonAdjustType = this.calculateRandomPersonAdjustType(numericalObjList);
    }

    // 取得指定個人控設定
    private CompanyNewPersonControlObj getTargetCompanyNewPersonControlObj(int adjustInfoIndex, List<CompanyNewPersonControlObj> companyNewPersonControlObjList) {
        for (CompanyNewPersonControlObj companyNewPersonControlObj : companyNewPersonControlObjList) {
            if (companyNewPersonControlObj.getId() == adjustInfoIndex) {
                return companyNewPersonControlObj;
            }
        }

        return null;
    }

    // 讀取數控物件
    private List<NumericalObj> readNumericalObjList(CompanyNewPersonControlObj companyNewPersonControlObj) {
        // 1. 防呆公司個人控設定
        if (Objects.isNull(companyNewPersonControlObj)) { return new ArrayList<>();}

        // 2. 讀取數控設定
        JsonNode numericalJsonNode = JsonUtil.getInstance().readTreeWithoutException(companyNewPersonControlObj.getNumericalJson());

        // 3. 防呆數控設定
        if(Objects.isNull(numericalJsonNode)){ return new ArrayList<>(); }

        // 4. 創建空間
        List<NumericalObj> result = new ArrayList<>();

        // 5. 轉成結構
        for (int subNumericalJsonNodeIndex = 0; subNumericalJsonNodeIndex < numericalJsonNode.size(); subNumericalJsonNodeIndex++) {
            NumericalObj numericalObj = JsonUtil.getInstance().readValueWithoutException(numericalJsonNode.get(subNumericalJsonNodeIndex).toString(), NumericalObj.class);
            if(!Objects.isNull(numericalObj)){
                result.add(numericalObj);
            }
        }

        // 6. 回傳
        return result;
    }

    // 隨機選出個人控類型
    private ConstMathCommon.PersonAdjustType calculateRandomPersonAdjustType(List<NumericalObj> numericalObjList) {
        // 1. 防呆
        if (numericalObjList.size() == 0) {
            return ConstMathCommon.PersonAdjustType.NORMAL;
        }

        // 2. 判斷是否進行調控 TODO 先恆 true; 之後再看要不要依照遊戲類型決定執行率
        boolean isControl = true;

        // 3. 隨機決定調控結果索引
        int numericalIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(this.calculateNumericalObjWeightArray(numericalObjList), ConstMathCommon.AccuracyType.A32768);

        // 4. 回傳
        return isControl ? ConstMathCommon.PersonAdjustType.fromName(numericalObjList.get(numericalIndex).getName()) : ConstMathCommon.PersonAdjustType.NORMAL;
    }

    // 計算數控物件的權重陣列
    private List<Integer> calculateNumericalObjWeightArray(List<NumericalObj> numericalObjList) {
        List<Integer> result = new ArrayList<>();

        for (NumericalObj numericalObj: numericalObjList) {
            result.add(numericalObj.getValue());
        }

        return result;
    }


    /* 更新設定相關　*/
    public void updateConfig(PersonControlConfig personControlConfig) {
        this.config = personControlConfig;
    }


    /* 取得個人控類型相關 */
    // 取得個人控類型
    public ConstMathCommon.PersonAdjustType getPersonAdjustType(boolean needControl) {
        return this.algorithmTypeCtr.calculateAlgorithmType(needControl, this.companyPersonAdjustType).getPersonAdjustType();
    }

    // 取得公司個人控設定
    public ConstMathCommon.PersonAdjustType getCompanyPersonAdjustType() {
        return this.companyPersonAdjustType;
    }
}
