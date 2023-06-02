package org.lsj.gs.rngDataGtr;

import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.module.RngDataGtrConfigFactory;
import org.lsj.gs.rngDataGtr.config.resources.ConstRndGtrConfig.RngDataGtrResourceEnum;
import org.lsj.gs.rngDataGtr.core.module.rngDataGtr.IRngDataGtr;
import org.lsj.gs.rngDataGtr.core.module.rngDataGtr.RngDataGtrConfigPrinter;
import org.lsj.gs.rngDataGtr.core.module.rngDataGtr.RngDataGtrFactory;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseGameResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.TestCaseGameResultPrinter;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import java.util.List;

public class mainRnd {
    public static RngDataGtrResourceEnum rngDataGtrResourceEnum = RngDataGtrResourceEnum.R_115_QZNN_KSZ_JAVA;

    public static void main(String... args) {
        System.setProperty("quarkus.profile", "test");
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) {
            // 1. 印出起始訊息
            System.out.println("<<生產亂數開始>>");

            // 2. 取得亂數產生器設定檔
            RngDataGtrConfig rngDataGtrConfig = new RngDataGtrConfigFactory().getRngDataGtrConfig(rngDataGtrResourceEnum);

            // 3. 建立亂數產生器
            IRngDataGtr rngDataGtr = new RngDataGtrFactory().create(rngDataGtrConfig);

            // 4. 執行亂數產生器
            List<TestCaseGameResult> testCaseGameResultList = rngDataGtr.run();

            // 5. 印出結果
            new RngDataGtrConfigPrinter().print(rngDataGtrConfig);
            new TestCaseGameResultPrinter().print(testCaseGameResultList);

            // 6. 模擬結束
            simulationFinished();
            Quarkus.asyncExit();
            return 0;
        }
    }

    // 模擬結束
    private static void simulationFinished() {
        // 1. 清空定時器
        System.gc();

        // 2. 印出結束訊息
        System.out.println("<<生產亂數結束>>");
    }
}
