package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr;

import com.fasterxml.jackson.core.type.TypeReference;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.AdjustInfo;
import org.lsj.gs.pool.PersonControlConfig;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 個人控次數紀錄計算器
public class PersonControlLogCtr {
    private PersonControlConfig config; // 個人控設定檔
    private String personControlLogMapString; // 個人控次數紀錄字串格式(紀錄於Redis;個人控最大上限次數用)
    private Map<Integer, Integer> personControlLogMap; // 個人控次數紀錄對應表 <個人控索引, 使用次數>

    public PersonControlLogCtr(PersonControlConfig personControlConfig, IUser user) {
        this.config = personControlConfig;
        this.personControlLogMapString = (!Objects.isNull(user.getPersonControlLog())) ?
                user.getPersonControlLog() :
                JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>());
        this.personControlLogMap = this.initialPersonControlLogObj(this.personControlLogMapString);
    }

    /* 初始化相關 */
    // 初始化個人控次數紀錄對應表
    private Map<Integer, Integer> initialPersonControlLogObj(String personControlLog) {
        // 1. 定義型態
        TypeReference<Map<Integer, Integer>> typeRef = new TypeReference<>(){};

        // 2. 讀取物件
        Map<Integer, Integer> result = JsonUtil.getInstance().readValueWithoutException(personControlLog, typeRef);

        // 3. 回傳與防呆
        return (!Objects.isNull(result)) ? result : new HashMap<>();
    }

    /* 更新設定相關 */
    // 更新設定
    public void updateConfig(PersonControlConfig personControlConfig) {
        this.config = personControlConfig;
    }

    /* 計算個人控次數紀錄相關 */
    // 計算個人控次數紀錄
    public void calculatePersonControlLog(AdjustInfo adjustInfo) {
        // 1. 防呆設定檔
        if (Objects.isNull(this.config.getCompanyNewPersonControlObjList()) || this.config.getCompanyNewPersonControlObjList().size() == 0) {
            return;
        }

        // 2. 防呆個人控結果
        if (Objects.isNull(adjustInfo)) {
            return;
        }

        // 3. 更新個人控次數紀錄對應表
        int adjustId = adjustInfo.getId();
        if (Objects.isNull(this.personControlLogMap.get(adjustId))) {
            this.personControlLogMap.put(adjustId, 1);
        }else {
            this.personControlLogMap.put(adjustId, this.personControlLogMap.get(adjustId) + 1);
        }

        // 4. 更新個人控次數紀錄字串格式
        this.personControlLogMapString = JsonUtil.getInstance().writeValueAsStringWithoutException(this.personControlLogMap);
    }

    /* 取得個人控次數紀錄相關 */
    // 取得個人控次數紀錄字串格式
    public String getPersonControlLogMapString() {
        return personControlLogMapString;
    }

    // 取得個人控次數紀錄對應表
    public Map<Integer, Integer> getPersonControlLogMap() {
        return personControlLogMap;
    }
}
