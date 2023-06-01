package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.db.CompanyNewPersonControlObj;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.AdjustInfo;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy.AbstractPersonControlStrategy;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy.AgentPersonControlStrategy;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy.IpPersonControlStrategy;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy.ThirdAccountIdPersonControlStrategy;
import org.lsj.gs.pool.PersonControlConfig;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 個人控結果計算器
public class AdjustInfoCtr {
    private PersonControlConfig config; // 個人控設定檔
    private AdjustInfo adjustInfo; // 個人控結果(紀錄於注單;調控報表聚合用)

    public AdjustInfoCtr(PersonControlConfig personControlConfig) {
        this.config = personControlConfig;
        this.adjustInfo = null;
    }

    // 更新設定檔
    public void updateConfig(PersonControlConfig personControlConfig) {
        this.config = personControlConfig;
    }

    /* 計算個人控結果相關 */
    // 計算個人控結果
    public void calculateAdjustInfo(Map<Integer, Integer> personControlLogObj, IUser user) {
        // 1. 防呆
        if (Objects.isNull(this.config.getCompanyNewPersonControlObjList()) || this.config.getCompanyNewPersonControlObjList().size() == 0) {
            this.adjustInfo = null;
            return;
        }

        // 2. 創立結果物件
        AdjustInfo result = null;

        // 3. 檢驗個人控符合性
        for (CompanyNewPersonControlObj companyNewPersonControlObj: this.config.getCompanyNewPersonControlObjList()) {
            if(this.checkUserSatisfyCompanyNewPersonControlObj(companyNewPersonControlObj, personControlLogObj, user)) {
                result = new AdjustInfo(companyNewPersonControlObj.getId(), companyNewPersonControlObj.getName(), ConstMathCommon.AdjustType.PERSON.getCode());
                break;
            }
        }

        // 4. 紀錄結果
        this.adjustInfo = result;
    }

    // 檢驗玩家個人控的滿足性
    private boolean checkUserSatisfyCompanyNewPersonControlObj(CompanyNewPersonControlObj companyNewPersonControlObj,
                                                                  Map<Integer, Integer> personControlLogObj,
                                                                  IUser user){
        // 1. 檢查個人控是否開啟
        if (companyNewPersonControlObj.getStatus() == 0) {
            return false;
        }

        // 2. 檢驗調控次數合法性
        if (!this.isValidExecuteTime(companyNewPersonControlObj, personControlLogObj)) {
            return false;
        }

        // 3. 讀取個人控策略設定 TODO 讀取僅先支援 IP,UID,Agent 判斷成立條件僅 IP,UID
        List<AbstractPersonControlStrategy> personControlStrategyList = this.readPersonControlStrategy(companyNewPersonControlObj.getControlStrategyJson());

        // 4. 防呆個人控策略設定
        if(personControlStrategyList.size() == 0){
            return false;
        }

        // 5. 檢驗策略符合性
        for (AbstractPersonControlStrategy personControlStrategy : personControlStrategyList) {
            if (!personControlStrategy.isSatisfyStrategy(user)) {
                return false;
            }
        }

        return true;
    }

    // 檢驗個人控次數合法性
    private boolean isValidExecuteTime(CompanyNewPersonControlObj companyNewPersonControlObj, Map<Integer, Integer> personControlLogObj) {
        // 1. 取得調控最大執行次數
        int maxExecTimes = companyNewPersonControlObj.getMaxExecTimes();

        // 2. 取得當前調控使用次數
        int usedExecTimes = (!Objects.isNull(personControlLogObj.get(companyNewPersonControlObj.getId()))) ? personControlLogObj.get(companyNewPersonControlObj.getId()) : 0;

        return (maxExecTimes != 0 && usedExecTimes < maxExecTimes);
    }

    // 讀取個人控策略設定
    private List<AbstractPersonControlStrategy> readPersonControlStrategy(String controlStrategy) {
        // 1. 讀取字串
        JsonNode controlStrategyJsonNode = JsonUtil.getInstance().readTreeWithoutException(controlStrategy);

        // 2. 防呆讀取錯誤
        if(Objects.isNull(controlStrategyJsonNode)){
            return new ArrayList<>();
        }

        // 3. 創建空間
        List<AbstractPersonControlStrategy> result = new ArrayList<>();

        // 4. 轉成物件
        for (int subControlStrategyJsonNodeIndex = 0; subControlStrategyJsonNodeIndex < controlStrategyJsonNode.size(); subControlStrategyJsonNodeIndex++) {
            AbstractPersonControlStrategy personControlStrategy = this.readAbstractPersonControlStrategy(controlStrategyJsonNode.get(subControlStrategyJsonNodeIndex));
            if(!Objects.isNull(personControlStrategy)){
                result.add(this.readAbstractPersonControlStrategy(controlStrategyJsonNode.get(subControlStrategyJsonNodeIndex)));
            }
        }

        // 5. 回傳
        return result;
    }

    private AbstractPersonControlStrategy readAbstractPersonControlStrategy(JsonNode subControlStrategyJsonNodeIndex){
        switch (subControlStrategyJsonNodeIndex.get("key").asText()) {
            case "limitIps":
                return JsonUtil.getInstance().readValueWithoutException(subControlStrategyJsonNodeIndex.toString(), IpPersonControlStrategy.class);
            case "userIds":
                return JsonUtil.getInstance().readValueWithoutException(subControlStrategyJsonNodeIndex.toString(), ThirdAccountIdPersonControlStrategy.class);
            case "agents":
                return JsonUtil.getInstance().readValueWithoutException(subControlStrategyJsonNodeIndex.toString(), AgentPersonControlStrategy.class);
            default:
                return null;
        }
    }



    /* 取得結果相關 */
    // 取得個人控結果
    public AdjustInfo getAdjustInfo() {
        return adjustInfo;
    }

    // 取得個人控結果字串格式
    public String getAdjustInfoString() {
        return (Objects.isNull(this.adjustInfo)) ?
            JsonUtil.getInstance().writeValueAsStringWithoutException(new ArrayList<>()):
            this.adjustInfo.calculateAdjustInfoString();
    }
}
