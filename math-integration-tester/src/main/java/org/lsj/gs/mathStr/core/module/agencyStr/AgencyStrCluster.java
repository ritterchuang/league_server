package org.lsj.gs.mathStr.core.module.agencyStr;

import org.lsj.gs.mathStr.config.entity.AgencyStrClusterConfig;
import org.lsj.gs.mathStr.core.entity.exception.StrException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// 代理模擬器叢集
public class AgencyStrCluster {
    private final AgencyStrClusterConfig agencyStrClusterConfig; // 模擬器設定檔
    private final List<String> agencyStrSummaryStringList; // 模擬器總結字串列表

    public AgencyStrCluster(AgencyStrClusterConfig agencyStrClusterConfig) {
        this.agencyStrClusterConfig = agencyStrClusterConfig;
        this.agencyStrSummaryStringList = new ArrayList<>();
    }

    public void run() {
        for(int agencyStrIndex = 0; agencyStrIndex < this.agencyStrClusterConfig.getAgencyStrConfigList().size(); agencyStrIndex++){
            // 1. 印出代理模擬器設定路徑
            this.agencyStrClusterConfig.getAgencyStrConfigList().get(agencyStrIndex).printPath();

            // 2. 生成代理模擬器
            AgencyStr agencyStr = null;
            try {
                agencyStr = new AgencyStr(this.agencyStrClusterConfig.getAgencyStrConfigList().get(agencyStrIndex));
            } catch (StrException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // 3. 執行代理模擬
            agencyStr.run();

            // 4. 印出統計資訊
            agencyStr.print();

            // 5. 紀錄簡易資訊
            this.agencyStrSummaryStringList.add(new StringBuilder()
                    .append("公司利潤率:" + new DecimalFormat("#.##").format(agencyStr.getCurrentCompanyProfitRate() * 100) + "%").append("; ")
                    .append(this.agencyStrClusterConfig.getAgencyStrConfigList().get(agencyStrIndex).getFlatPath())
                    .toString()
            );

            // 6. 印出所有簡易資訊
            this.printAgencyStrSummaryStringList();
        }
    }

    private void printAgencyStrSummaryStringList(){
        System.out.println("----------總結開始----------");
        for (String agencyStrSummaryString: this.agencyStrSummaryStringList) {
            System.out.println(agencyStrSummaryString);
        }
        System.out.println("----------總結結束----------");
    }
}
