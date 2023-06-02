package org.lsj.gs.mathStr.config.entity;

import java.util.List;

// 代理模擬器叢集設定
public class AgencyStrClusterConfig {
    private final List<AgencyStrConfig> agencyStrConfigList; // 模擬執行器設定陣列

    public AgencyStrClusterConfig(List<AgencyStrConfig> agencyStrConfigList) {
        this.agencyStrConfigList = agencyStrConfigList;
    }

    public List<AgencyStrConfig> getAgencyStrConfigList() {
        return agencyStrConfigList;
    }
}
