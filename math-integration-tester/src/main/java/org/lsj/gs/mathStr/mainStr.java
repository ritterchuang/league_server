package org.lsj.gs.mathStr;

import org.lsj.gs.mathStr.config.entity.AgencyStrClusterConfig;
import org.lsj.gs.mathStr.config.module.ConfigReader;
import org.lsj.gs.mathStr.core.module.agencyStr.AgencyStrCluster;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class mainStr {
    public static String[] scenarioConfigContainStringArray = new String[]{"02_"};
    public static String[] gameCenterMgrConfigContainStringArray = new String[]{"001_"};
    public static String[] gamePlayerFactoryConfigContainStringArray = new String[]{"00_"};
    public static String[] poolCtrConfigContainStringArray = new String[]{"01_R25"};
    public static String[] stnConfigContainStringArray = new String[]{"00_"};
    public static String[] controlConfigContainStringArray = new String[]{"10_"};

    public static void main(String... args) {
        System.setProperty("quarkus.profile", "test");
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) {
            // 1. 印出起始訊息
            System.out.println("<<模擬開始>>");

            // 2. 讀取設定檔
            AgencyStrClusterConfig agencyStrClusterConfig = new ConfigReader().read(
                    scenarioConfigContainStringArray,
                    gameCenterMgrConfigContainStringArray,
                    gamePlayerFactoryConfigContainStringArray,
                    poolCtrConfigContainStringArray,
                    stnConfigContainStringArray,
                    controlConfigContainStringArray);

            // 3. 建立模擬器
            AgencyStrCluster agencyStrCluster = new AgencyStrCluster(agencyStrClusterConfig);

            // 4. 執行模擬器
            agencyStrCluster.run();

            // 5. 模擬結束
            simulationFinished();
            Quarkus.asyncExit();
            return 0;
        }

        // 模擬結束
        private static void simulationFinished() {
            // 1. 清空定時器
            System.gc();

            // 2. 印出結束訊息
            System.out.println("<<模擬結束>>");
        }
    }
}
