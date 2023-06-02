package org.lsj.gs.mathStr.core.module.agencyStr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.AgencyStrConfig;
import org.lsj.gs.mathStr.config.entity.GameCenterMgrConfig;
import org.lsj.gs.mathStr.config.entity.GamePlayerConfig;
import org.lsj.gs.mathStr.config.entity.GamePlayerFactoryConfig;
import org.lsj.gs.mathStr.core.entity.ConstStr;
import org.lsj.gs.mathStr.core.entity.exception.StrException;
import org.lsj.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResult;
import org.lsj.gs.mathStr.core.module.GameCenterMgr;
import org.lsj.gs.mathStr.core.module.GamePlayerFactory;
import org.lsj.gs.mathStr.core.module.ScenarioHlr;
import org.lsj.gs.mathStr.core.module.playStr.AbstractPlayStr;
import org.lsj.gs.mathStr.core.module.playStr.PlayStrFactory;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.AgencyStn;
import org.slf4j.event.Level;

import java.text.DecimalFormat;
import java.util.Arrays;

// 代理模擬器
public class AgencyStr {
    private final AgencyStrConfig agencyStrConfig; // 代理模擬器設定

    // 模組
    private final ScenarioHlr scenarioHlr; // 情境處理器
    private final GamePlayerFactory gamePlayerFactory; // 玩家工廠
    private final PoolCtr poolCtr; // 水池計算器
    private final GameCenterMgr gameCenterMgr; // 遊戲中心管理器
    private final PlayStrFactory playStrFactory; // 遊戲模擬器工廠
    private final AgencyStn agencyStn; // 代理統計者

    // 進度資訊
    private long startTimeMillis; // 起始時間
    private final int PRINT_PROCESS_POINT; // 打印進度點數
    private int accumulateProcessPoint; // 累積進度點數

    public AgencyStr(AgencyStrConfig agencyStrConfig) throws StrException {
        // 1. 紀錄設定檔
        this.agencyStrConfig = agencyStrConfig;

        // 2. 判斷合理設定
        GamePlayerFactoryConfig validGamePlayerFactoryConfig = this.calculateValidUserFactoryConfig(agencyStrConfig.getUserFactoryConfig(), agencyStrConfig.getGameCenterMgrConfig());
        GameCenterMgrConfig validGameCenterMgrConfig = this.calculateValidGameCenterMgrConfig(agencyStrConfig.getUserFactoryConfig(), agencyStrConfig.getGameCenterMgrConfig());
        this.checkConfigValidity(validGamePlayerFactoryConfig, validGameCenterMgrConfig);

        // 3. 建構模組
        this.scenarioHlr = new ScenarioHlr(agencyStrConfig.getScenarioHlrConfig());
        this.gamePlayerFactory = new GamePlayerFactory(validGamePlayerFactoryConfig);
        this.poolCtr = new PoolCtr(agencyStrConfig.getPoolCtrConfig());
        this.gameCenterMgr = new GameCenterMgr(validGameCenterMgrConfig);
        this.playStrFactory = new PlayStrFactory(ConstStr.gameEnum2GameTypeMap, agencyStrConfig.getControlAlgorithmConfig());
        this.agencyStn = new AgencyStn(agencyStrConfig.getAgencyStnConfig(), validGameCenterMgrConfig);

        // 4. 進度資訊
        this.PRINT_PROCESS_POINT = 10;
        this.accumulateProcessPoint = 0;
    }

    // 執行代理模擬
    public void run(){
        // 1. 紀錄起始時間
        this.startTimeMillis = System.currentTimeMillis();

        // 2. 執行遊戲
        while(this.scenarioHlr.getRemainBoardCount() > 0){
            // 3. 生成遊戲模擬器
            AbstractPlayStr playStr = this.playStrFactory.createPlayStr(this.gamePlayerFactory, this.poolCtr, this.gameCenterMgr);

            // 4. 執行遊戲模擬器
            PlayStrResult playStrResult = playStr.run();

            // 5. 更新情境處理器
            this.scenarioHlr.updatePlayedBoardCount(playStrResult.getBoardGtrResultList().size());

            // 6. 更新統計
            this.agencyStn.update(playStrResult);

            // 7. 印出進度資訊
            this.printProgress(this.scenarioHlr.getPlayedBoardCount(), this.agencyStrConfig.getScenarioHlrConfig().getSimulationBoard(), this.PRINT_PROCESS_POINT);
        }
    }

    // 取得當前公司利潤率
    public double getCurrentCompanyProfitRate(){
        return this.agencyStn.getCurrentCompanyProfitRate();
    }

    // 印出統計資訊
    public void print(){
        this.agencyStn.print();
    }

    // 列印進度表
    private void printProgress(int playedBoardCount, int simulationBoardPerTable, int printProcessPoint) {
        // 1. 計算累積進度點
        int accumulateProcessPoint = playedBoardCount / (simulationBoardPerTable / printProcessPoint);

        // 2. 是否須更新累積進度
        boolean needUpdateProcessPoint = (accumulateProcessPoint > this.accumulateProcessPoint);

        // 3. 更新累積進度
        this.accumulateProcessPoint = accumulateProcessPoint;

        // 4. 印出預計時間
        if(accumulateProcessPoint == 1 && needUpdateProcessPoint){
            System.out.println(
                    "預計耗時: " +
                    new DecimalFormat("#.#").format(((double)System.currentTimeMillis() - (double)this.startTimeMillis) / 1000.0 / 60.0 * printProcessPoint) +
                    " 分");
        }

        // 5. 列印進度
        if (needUpdateProcessPoint) {
            System.out.println(
                    "[進度資訊 " + (accumulateProcessPoint * (simulationBoardPerTable / printProcessPoint)) + "/" + simulationBoardPerTable + "]" +
                    " 累績公司利潤率: " + new DecimalFormat("#.##").format(this.agencyStn.getCurrentCompanyProfitRate() * 100) + "%" +
                    " 累績耗時: " + new DecimalFormat("#.#").format(((double)System.currentTimeMillis() - (double)this.startTimeMillis) / 1000.0 / 60.0) + " 分");
        }
    }


    //* 計算設定合理性相關 *//
    // 判斷設定合理性
    private void checkConfigValidity(GamePlayerFactoryConfig validGamePlayerFactoryConfig, GameCenterMgrConfig validGameCenterMgrConfig) throws StrException {
        // 1. 判斷不合理設定
        if( validGamePlayerFactoryConfig.gamePlayerConfigArray.length == 0 ||
            validGameCenterMgrConfig.getPlayGameFieldConfigArray().length == 0){
            throw new StrException(ConstStr.StrErrorCode.NO_SATISFY_SETTING, Level.INFO, "玩家與房間設定無滿足的匹配");
        }

        // 2. 印出合理設定列表
        System.out.println("<玩家X房間設定>");
        Arrays.stream(validGamePlayerFactoryConfig.gamePlayerConfigArray).forEach(gamePlayerConfig -> System.out.println(
                "[玩家] " + "玩家起始金額: " + gamePlayerConfig.getUserInitMoney() + "; " + "最大場次: " + gamePlayerConfig.getMaxBoard() + "; " + "權重: " + gamePlayerConfig.getWeight()));
        Arrays.stream(validGameCenterMgrConfig.getPlayGameFieldConfigArray()).forEach(playGameFieldConfig -> System.out.println(
                "[房間] " + "房間編碼: " + playGameFieldConfig.getTableFieldConfig().getFieldIndex() + "; " + "最低入桌門檻: " + playGameFieldConfig.getTableFieldConfig().getCompanyFieldObj().getLimitMin() + "; " + "權重: " + playGameFieldConfig.getWeight()));
    }

    // 計算合理的玩家工廠設定
    private GamePlayerFactoryConfig calculateValidUserFactoryConfig(GamePlayerFactoryConfig gamePlayerFactoryConfig, GameCenterMgrConfig gameCenterMgrConfig) {
        GamePlayerConfig[] validGamePlayerConfigArray = Arrays.stream(gamePlayerFactoryConfig.gamePlayerConfigArray).filter(gamePlayerConfig -> this.haveValidSpecifyFieldConfig(gamePlayerConfig, gameCenterMgrConfig)).toArray(GamePlayerConfig[]::new);
        return new GamePlayerFactoryConfig(validGamePlayerConfigArray);
    }

    // 指定玩家是否有合理的房間設定
    private boolean haveValidSpecifyFieldConfig(GamePlayerConfig gamePlayerConfig, GameCenterMgrConfig gameCenterMgrConfig){
        return Arrays.stream(gameCenterMgrConfig.getPlayGameFieldConfigArray()).anyMatch(playGameFieldConfig -> gamePlayerConfig.getUserInitMoney() >= playGameFieldConfig.getTableFieldConfig().getCompanyFieldObj().getLimitMin());
    }

    // 計算合理的遊戲中心管理器設定
    private GameCenterMgrConfig calculateValidGameCenterMgrConfig(GamePlayerFactoryConfig gamePlayerFactoryConfig, GameCenterMgrConfig gameCenterMgrConfig) {
        PlayGameFieldConfig[] validPlayGameFieldConfig = Arrays.stream(gameCenterMgrConfig.getPlayGameFieldConfigArray()).filter(playGameFieldConfig -> this.haveValidUserConfig(playGameFieldConfig.getTableFieldConfig(), gamePlayerFactoryConfig)).toArray(PlayGameFieldConfig[]::new);
        return new GameCenterMgrConfig(validPlayGameFieldConfig);
    }

    // 指定房間是否有合理的玩家設定
    private boolean haveValidUserConfig(TableFieldConfig tableFieldConfig, GamePlayerFactoryConfig gamePlayerFactoryConfig) {
        return Arrays.stream(gamePlayerFactoryConfig.gamePlayerConfigArray).anyMatch(gamePlayerConfig -> gamePlayerConfig.getUserInitMoney() >= tableFieldConfig.getCompanyFieldObj().getLimitMin());
    }
}
