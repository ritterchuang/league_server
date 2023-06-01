package org.lsj.gs.mathStr.config.module;

import org.lsj.gs.mathStr.config.entity.*;
import org.lsj.gs.mathStr.config.resources.ConstStrConfigResource;

import java.util.*;
import java.util.stream.Collectors;

// 設定檔讀取器
public class ConfigReader {
    // 讀取設定檔
    public AgencyStrClusterConfig read(String[] scenarioConfigContainStringArray,
                                       String[] gameCenterMgrConfigContainStringArray,
                                       String[] userFactoryConfigContainStringArray,
                                       String[] poolCtrConfigContainStringArray,
                                       String[] stnConfigContainStringArray,
                                       String[] controlConfigContainStringArray) {
        // 1. 讀取情境設定列表
        Map<String, ScenarioHlrConfig> scenarioHlrFileMap = this.readScenarioHlrConfigMap(scenarioConfigContainStringArray);

        // 2. 讀取遊戲中心管理器設定列表
        Map<String, GameCenterMgrConfig> gameCenterMgrFileMap = this.readGameCenterMgrConfigMap(gameCenterMgrConfigContainStringArray);

        // 3. 讀取玩家工廠設定列表
        Map<String, GamePlayerFactoryConfig> userFactoryFileMap = this.readGamePlayerFactoryConfigMap(userFactoryConfigContainStringArray);

        // 4. 讀取水池計算器設定列表
        Map<String, PoolCtrConfig> poolCtrFileMap = this.readPoolCtrConfigMap(poolCtrConfigContainStringArray);

        // 5. 讀取統計者設定
        Map<String, AgencyStnConfig> agencyStnFileMap = this.readAgencyStnConfigMap(stnConfigContainStringArray);

        // 6. 讀取除錯設定
        Map<String, ControlAlgorithmConfig> controlFileMap = this.readControlAlgorithmConfigMap(controlConfigContainStringArray);

        // 7. 組合執行設定檔
        List<AgencyStrConfig> agencyStrConfig = new ArrayList<>();
        for (Map.Entry<String, ScenarioHlrConfig> scenarioHlrEntry: scenarioHlrFileMap.entrySet()){
            for (Map.Entry<String, GameCenterMgrConfig> gameCenterMgrEntry: gameCenterMgrFileMap.entrySet()) {
                for (Map.Entry<String, GamePlayerFactoryConfig> userFactoryEntry: userFactoryFileMap.entrySet()) {
                    for (Map.Entry<String, PoolCtrConfig> poolCtrEntry: poolCtrFileMap.entrySet()) {
                        for (Map.Entry<String, AgencyStnConfig> agencyStnEntry: agencyStnFileMap.entrySet()) {
                            for (Map.Entry<String, ControlAlgorithmConfig> controlEntry: controlFileMap.entrySet()) {
                                agencyStrConfig.add(new AgencyStrConfig(
                                        scenarioHlrEntry.getKey(),
                                        gameCenterMgrEntry.getKey(),
                                        userFactoryEntry.getKey(),
                                        poolCtrEntry.getKey(),
                                        agencyStnEntry.getKey(),
                                        controlEntry.getKey(),
                                        scenarioHlrEntry.getValue(),
                                        gameCenterMgrEntry.getValue(),
                                        userFactoryEntry.getValue(),
                                        poolCtrEntry.getValue(),
                                        agencyStnEntry.getValue(),
                                        controlEntry.getValue()
                                ));
                            }
                        }
                    }
                }
            }
        }

        return new AgencyStrClusterConfig(agencyStrConfig);
    }

    private Map<String, ScenarioHlrConfig> readScenarioHlrConfigMap(String[] specifyStringArray){
        Map<String, ScenarioHlrConfig> result = new LinkedHashMap<>();

        // 1. 過濾符合指定字串列舉
        List<ConstStrConfigResource.ScenarioHlrConfigResourceEnum> filterResourceEnumList = EnumSet.allOf(ConstStrConfigResource.ScenarioHlrConfigResourceEnum.class).stream().filter(
                stnConfigResourceEnum -> this.stringContainSpecifyStringArray(stnConfigResourceEnum.name(), specifyStringArray)).collect(Collectors.toList());

        // 2. 紀錄列舉
        filterResourceEnumList.forEach(filterResourceEnum -> result.put(filterResourceEnum.name(), filterResourceEnum.getConfigResource()));

        return result;
    }

    private Map<String, GameCenterMgrConfig> readGameCenterMgrConfigMap(String[] specifyStringArray){
        Map<String, GameCenterMgrConfig> result = new LinkedHashMap<>();

        // 1. 過濾符合指定字串列舉
        List<ConstStrConfigResource.GameCenterMgrConfigResourceEnum> filterResourceEnumList = EnumSet.allOf(ConstStrConfigResource.GameCenterMgrConfigResourceEnum.class).stream().filter(
                stnConfigResourceEnum -> this.stringContainSpecifyStringArray(stnConfigResourceEnum.name(), specifyStringArray)).collect(Collectors.toList());

        // 2. 紀錄列舉
        filterResourceEnumList.forEach(filterResourceEnum -> result.put(filterResourceEnum.name(), filterResourceEnum.getConfigResource()));

        return result;
    }

    private Map<String, GamePlayerFactoryConfig> readGamePlayerFactoryConfigMap(String[] specifyStringArray){
        Map<String, GamePlayerFactoryConfig> result = new LinkedHashMap<>();

        // 1. 過濾符合指定字串列舉
        List<ConstStrConfigResource.UserFactoryConfigResourceEnum> filterResourceEnumList = EnumSet.allOf(ConstStrConfigResource.UserFactoryConfigResourceEnum.class).stream().filter(
                stnConfigResourceEnum -> this.stringContainSpecifyStringArray(stnConfigResourceEnum.name(), specifyStringArray)).collect(Collectors.toList());

        // 2. 紀錄列舉
        filterResourceEnumList.forEach(filterResourceEnum -> result.put(filterResourceEnum.name(), filterResourceEnum.getConfigResource()));

        return result;
    }

    private Map<String, PoolCtrConfig> readPoolCtrConfigMap(String[] specifyStringArray){
        Map<String, PoolCtrConfig> result = new LinkedHashMap<>();

        // 1. 過濾符合指定字串列舉
        List<ConstStrConfigResource.PoolCtrConfigResourceEnum> filterResourceEnumList = EnumSet.allOf(ConstStrConfigResource.PoolCtrConfigResourceEnum.class).stream().filter(
                stnConfigResourceEnum -> this.stringContainSpecifyStringArray(stnConfigResourceEnum.name(), specifyStringArray)).collect(Collectors.toList());

        // 2. 紀錄列舉
        filterResourceEnumList.forEach(filterResourceEnum -> result.put(filterResourceEnum.name(), filterResourceEnum.getConfigResource()));

        return result;
    }

    private Map<String, AgencyStnConfig> readAgencyStnConfigMap(String[] specifyStringArray){
        Map<String, AgencyStnConfig> result = new LinkedHashMap<>();

        // 1. 過濾符合指定字串列舉
        List<ConstStrConfigResource.StnConfigResourceEnum> filterResourceEnumList = EnumSet.allOf(ConstStrConfigResource.StnConfigResourceEnum.class).stream().filter(
                stnConfigResourceEnum -> this.stringContainSpecifyStringArray(stnConfigResourceEnum.name(), specifyStringArray)).collect(Collectors.toList());

        // 2. 紀錄列舉
        filterResourceEnumList.forEach(filterResourceEnum -> result.put(filterResourceEnum.name(), filterResourceEnum.getConfigResource()));

        return result;
    }

    private Map<String, ControlAlgorithmConfig> readControlAlgorithmConfigMap(String[] specifyStringArray){
        Map<String, ControlAlgorithmConfig> result = new LinkedHashMap<>();

        // 1. 過濾符合指定字串列舉
        List<ConstStrConfigResource.ControlAlgorithmConfigResourceEnum> filterResourceEnumList = EnumSet.allOf(ConstStrConfigResource.ControlAlgorithmConfigResourceEnum.class).stream().filter(
                stnConfigResourceEnum -> this.stringContainSpecifyStringArray(stnConfigResourceEnum.name(), specifyStringArray)).collect(Collectors.toList());

        // 2. 紀錄列舉
        filterResourceEnumList.forEach(filterResourceEnum -> result.put(filterResourceEnum.name(), filterResourceEnum.getConfigResource()));

        return result;
    }

    // 判斷字串包含指定字串
    private boolean stringContainSpecifyStringArray(String string, String[] specifyStringArray){
        for (String specifyString : specifyStringArray) {
            if (string.contains(specifyString)) {
                return true;
            }
        }

        return false;
    }
}
