package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.pool.PersonControlConfig;
import org.lsj.gs.user.IUser;

// 個人控處理器
public class PersonControlHlr {
    // 設定相關
    PersonControlConfig config; // 個人控設定檔
    final ITableUtil tableUtil; // 牌桌工具包

    // 模組相關
    private final AdjustInfoCtr adjustInfoCtr; // 個人控結果計算器
    private final PersonAdjustTypeCtr personAdjustTypeCtr; // 個人控類型計算器
    private final PersonControlLogCtr personControlLogCtr; // 個人控次數紀錄計算器

    public PersonControlHlr(PersonControlConfig config, IUser user, AlgorithmTypeCtr algorithmTypeCtr, ITableUtil tableUtil) {
        // 1. 初始化
        this.config = config;
        this.tableUtil = tableUtil;

        // 2. 建立模組
        this.adjustInfoCtr = new AdjustInfoCtr(config);
        this.personAdjustTypeCtr = new PersonAdjustTypeCtr(config, algorithmTypeCtr, tableUtil);
        this.personControlLogCtr = new PersonControlLogCtr(config, user);
    }

    /* 計算個人控所有結果 */
    // 計算個人控所有結果
    public void calculatePersonControlAllResult(IUser user) {
        this.adjustInfoCtr.calculateAdjustInfo(this.personControlLogCtr.getPersonControlLogMap(), user);
        this.personAdjustTypeCtr.calculatePersonAdjustType(this.adjustInfoCtr.getAdjustInfo());
        this.personControlLogCtr.calculatePersonControlLog(this.adjustInfoCtr.getAdjustInfo());
    }



    /* 更新設定檔相關 */
    // 更新設定檔
    public void updateConfig(PersonControlConfig personControlConfig) {
        this.config = personControlConfig;
        this.adjustInfoCtr.updateConfig(personControlConfig);
        this.personAdjustTypeCtr.updateConfig(personControlConfig);
        this.personControlLogCtr.updateConfig(personControlConfig);
    }



    /* 取得個人控所有結果相關 */
    // 取得個人控結果字串格式
    public String getAdjustInfoString() {
        return this.adjustInfoCtr.getAdjustInfoString();
    }

    // 取得個人控類型
    public ConstMathCommon.PersonAdjustType getPersonAdjustType(boolean needControl) {
        return this.personAdjustTypeCtr.getPersonAdjustType(needControl);
    }

    // 取得公司個人控設定
    public ConstMathCommon.PersonAdjustType getCompanyPersonAdjustType() {
        return this.personAdjustTypeCtr.getCompanyPersonAdjustType();
    }

    // 取得個人控次數紀錄
    public String getPersonControlLogString() { return this.personControlLogCtr.getPersonControlLogMapString(); }
}
