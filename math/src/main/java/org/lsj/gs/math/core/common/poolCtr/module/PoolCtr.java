package org.lsj.gs.math.core.common.poolCtr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.PersonControlHlr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.user.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// 水池控制器
public class PoolCtr {
    private static final Logger LOG = LoggerFactory.getLogger(PoolCtr.class);

    private IPoolConfig poolConfig; // 水池設定
    private final AgencyPoolCtr agencyPoolCtr; // 代理水池計算器
    private final PersonControlHlr personControlHlr; // 個人控工具包

    public PoolCtr(IPoolConfig poolConfig, IUser user, AlgorithmTypeCtr algorithmTypeCtr, ITableUtil tableUtil) {
        this.poolConfig = poolConfig;
        this.agencyPoolCtr = new AgencyPoolCtr(poolConfig.getAgencyPool());
        this.personControlHlr = new PersonControlHlr(poolConfig.getPersonControlConfig(), user, algorithmTypeCtr, tableUtil);
    }

    /* 水池設定相關 */
    // 取得水池設定
    public IPoolConfig getPoolConfig() {
        return poolConfig;
    }

    // 更新水池設定
    public void updateConfig(IPoolConfig poolConfig){
        this.poolConfig = poolConfig;
        this.agencyPoolCtr.updateAgencyPool(poolConfig.getAgencyPool());
        this.personControlHlr.updateConfig(poolConfig.getPersonControlConfig());
    }


    /* 代理水池相關 */
    public double calculateCurrentCompanyProfitRate(double humanValidBet, double humanScore){
        return this.agencyPoolCtr.calculateCurrentCompanyProfitRate(humanValidBet, humanScore);
    }

    public double getFeeRate(){
        return this.agencyPoolCtr.getFeeRate();
    }

    public double getFeeRateTop(){
        return this.agencyPoolCtr.getFeeRateTop();
    }


    /* 計算個人控相關 */
    // 計算個人控所有結果
    public void calculatePersonControlAllResult(IUser user) {
        this.personControlHlr.calculatePersonControlAllResult(user);
    }


    /* 取得個人控結果 */
    // 取得個人控結果字串格式
    public String getAdjustInfoString() {
        return this.personControlHlr.getAdjustInfoString();
    }

    // 取得個人控類型
    public ConstMathCommon.PersonAdjustType getPersonAdjustType() {
        return this.getPersonAdjustType(true);
    }

    // 取得個人控類型
    private ConstMathCommon.PersonAdjustType getPersonAdjustType(boolean needControl) {
        return this.personControlHlr.getPersonAdjustType(needControl);
    }

    // 取得公司個人控設定
    public ConstMathCommon.PersonAdjustType getCompanyPersonAdjustType() {
        return this.personControlHlr.getCompanyPersonAdjustType();
    }

    // 取得個人控次數紀錄字串格式
    public String getPersonControlLogString() {
        return this.personControlHlr.getPersonControlLogString();
    }

    // 重設
    public void reset() {}
}
