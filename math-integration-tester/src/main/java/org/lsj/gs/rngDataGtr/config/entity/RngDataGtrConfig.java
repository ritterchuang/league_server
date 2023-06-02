package org.lsj.gs.rngDataGtr.config.entity;

import org.lsj.enums.GameId;
import org.lsj.gs.mathBoardGtr.config.entity.BoardGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.utils.JsonUtil;

// 亂數產生器設定
public class RngDataGtrConfig {
    private final RngDataScenarioHlrConfig rngDataScenarioHlrConfig; // 亂數情境處理器設定
    private final BoardGtrConfig boardGtrConfig; // 局產生器設定
    private final RngDataTesterConfig rngDataTesterConfig; // 測項設定

    public RngDataGtrConfig(RngDataScenarioHlrConfig rngDataScenarioHlrConfig, BoardGtrConfig boardGtrConfig, RngDataTesterConfig rngDataTesterConfig) {
        this.rngDataScenarioHlrConfig = rngDataScenarioHlrConfig;
        this.boardGtrConfig = boardGtrConfig;
        this.rngDataTesterConfig = rngDataTesterConfig;
    }

    public RngDataScenarioHlrConfig getRngDataScenarioHlrConfig() {
        return rngDataScenarioHlrConfig;
    }

    public BoardGtrConfig getBoardGtrConfig() {
        return boardGtrConfig;
    }

    public RngDataTesterConfig getTestCaseConfig() {
        return rngDataTesterConfig;
    }

    public String getSystemPrintString() {
        return new StringBuilder()
                .append("[測項情境]").append("\n")
                .append("遊戲識別: " + this.boardGtrConfig.getPlayGameFieldConfig().getTableFieldConfig().getGameId()).append("\n")
                .append("遊戲名稱: " + GameId.fromId(this.boardGtrConfig.getPlayGameFieldConfig().getTableFieldConfig().getGameId())).append("\n")
                .append("手續費率: " + this.boardGtrConfig.getPoolCtrConfig().getAgencyPool().getFeeRate()).append("\n")
                .append("模擬器類型: " + this.getBoardGtrConfig().getPlayGameFieldConfig().getGameType().name()).append("\n")
                .append("模擬器參數: " + JsonUtil.getInstance().writeValueAsStringWithoutException(this.getBoardGtrConfig().getPlayGameFieldConfig().getGameTypeConfigExtend())).append("\n")
                .append("強控水池算法: " + this.getBoardGtrConfig().getControlAlgorithmConfig().getControlAlgorithmUtil().getControlPoolControlType().name()).append("\n")
                .append("強控開牌算法: " + this.getBoardGtrConfig().getControlAlgorithmConfig().getControlAlgorithmUtil().getControlDealType().name()).append("\n")
                .append("強控發牌算法: " + this.getBoardGtrConfig().getControlAlgorithmConfig().getControlAlgorithmUtil().getControlShuffleType().name()).append("\n")
                .append("強個人控算法: " + this.getBoardGtrConfig().getControlAlgorithmConfig().getControlAlgorithmUtil().getControlPersonAdjustType().name())
                .toString();
    }
}
