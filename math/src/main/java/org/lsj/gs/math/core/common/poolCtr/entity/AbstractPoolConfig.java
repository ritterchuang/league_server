package org.lsj.gs.math.core.common.poolCtr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.table.entity.message.AbstractToStringStruct;
import org.lsj.gs.pool.AgencyPool;
import org.lsj.gs.pool.PersonControlConfig;

import java.util.Objects;

// 抽象水池設定
public class AbstractPoolConfig extends AbstractToStringStruct implements IPoolConfig{
    private final AgencyPool agencyPool; // 代理水池資訊
    @JsonIgnore
    private final PersonControlConfig personControlConfig; // 個人控設定

    public AbstractPoolConfig(AgencyPool agencyPool, PersonControlConfig personControlConfig){
        this.agencyPool = (Objects.isNull(agencyPool)) ? new AgencyPool() : new AgencyPool().checkConstructor(agencyPool);
        this.personControlConfig = personControlConfig;
    }

    // 取得代理水池
    @Override
    public AgencyPool getAgencyPool(){
        return this.agencyPool;
    }

    @Override
    public PersonControlConfig getPersonControlConfig() {
        return this.personControlConfig;
    }

}
