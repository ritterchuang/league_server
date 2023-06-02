package org.lsj.gs.math.core.common.tableConfigMgr;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.enums.MoneyType;
import org.lsj.enums.RoomType;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.moduleConfigMgr.ModuleConfigMgrBaiRen;
import org.lsj.gs.math.core.card.moduleConfigMgr.ModuleConfigMgrCard;
import org.lsj.gs.math.core.fish.moduleConfigMgr.ModuleConfigMgrFish;
import org.lsj.gs.math.core.slot.moduleConfigMgr.AbstractModuleConfigMgrSlot;
import org.lsj.gs.math.core.slot.moduleConfigMgr.ModuleConfigMgrSlotFactory;
import org.lsj.utils.JsonUtil;

import java.util.List;

// 遊戲桌設定管理器
public class TableConfigMgr {
    private final TableFieldConfig config; // 遊戲桌設定
    private final ModuleConfigMgrCore moduleConfigMgrCore; // 核心模組設定管理器
    private final ModuleConfigMgrCard moduleConfigMgrCard; // 棋牌模組設定管理器
    private final ModuleConfigMgrBaiRen moduleConfigMgrBaiRen; // 百人模組設定管理器
    private final ModuleConfigMgrFish moduleConfigMgrFish; // 魚機模組設定管理器
    private final AbstractModuleConfigMgrSlot moduleConfigMgrSlot; // 虎機模組設定管理器

    public TableConfigMgr(TableFieldConfig config){
        this.config = config;
        this.moduleConfigMgrCore = new ModuleConfigMgrCore(config);
        this.moduleConfigMgrCard = new ModuleConfigMgrCard(config);
        this.moduleConfigMgrBaiRen = new ModuleConfigMgrBaiRen(config);
        this.moduleConfigMgrFish = new ModuleConfigMgrFish(config);
        this.moduleConfigMgrSlot = new ModuleConfigMgrSlotFactory().create(config);
    }

    public TableFieldConfig getConfig() {
        return config;
    }

    // 組合設定
    public ObjectNode composedConfig(List<Integer> areasTopLimitList, List<Integer> chipsList){
        // 1. 轉換客制節點
        ObjectNode extendJsonNode = JsonUtil.getInstance().getObjectMapper().createObjectNode();
        extendJsonNode.set("areasTopLimit", this.transformList2ArrayNode(areasTopLimitList));
        extendJsonNode.set("chips", this.transformList2ArrayNode(chipsList));

        // 2. 組合設定
        ObjectNode configObjectNode = JsonUtil.getInstance().getObjectMapper().createObjectNode();
        configObjectNode.put("companyId", this.config.getFieldConfig().getCompanyId()); // 公司識別碼
        configObjectNode.put("master", 0); // 房主
        configObjectNode.put("roomid", 0); // 房間編號
        configObjectNode.put("gameid", this.config.getGameId()); // 遊戲識別碼
        configObjectNode.put("fid", this.config.getFieldIndex()); // 房間識別碼
        configObjectNode.put("fnameCN", this.config.getFieldNameCn()); // 房間名稱
        configObjectNode.put("maxPlayer", this.config.getMaxUser()); // 最大玩家數
        configObjectNode.put("moneyType", MoneyType.DOLLAR.getCode());  // 貨幣類型
        configObjectNode.put("roomType", RoomType.FIELD.getCode());    // 房間類型
        configObjectNode.put("minlimit", this.config.getLimitMin()); // 入桌下限
        configObjectNode.put("maxlimit", this.config.getLimitMax()); // 入桌上限(無用)
        configObjectNode.put("kicklimit", this.config.getLimitKick()); // 踢出限制(無用)
        configObjectNode.put("feeType", this.config.getCompanyFieldObj().getFeeType()); // 服務費類型
        configObjectNode.put("fee", this.config.getGameRate()); // 手續費率; feeType = 1: 固定服務費; feeType = 2: 百分比;
        configObjectNode.put("watermatch", false); // 流水匹配標誌
        configObjectNode.put("gameKind", this.config.getFieldConfig().getDefaultGameKind()); // 遊戲類型
        configObjectNode.put("gamePlay", this.config.getPlay()); // 玩法類型
        configObjectNode.set("rule", this.calculateRuleConfig(extendJsonNode)); // 玩法規則
        configObjectNode.set("areasTopLimit", extendJsonNode.get("areasTopLimit") == null ?
                JsonUtil.getInstance().getObjectMapper().createArrayNode() : extendJsonNode.get("areasTopLimit")); // 區域限紅; 兼容客端需複製一份;
        configObjectNode.set("chips", extendJsonNode.get("chips") == null ?
                JsonUtil.getInstance().getObjectMapper().createArrayNode() : extendJsonNode.get("chips")); // 下注硬幣; 兼容客端需複製一份;

        return configObjectNode;
    }

    // 計算玩法規則
    private ObjectNode calculateRuleConfig(ObjectNode extendJsonNode){
        ObjectNode result = JsonUtil.getInstance().getObjectMapper().createObjectNode();
        result.put("play", this.config.getCompanyFieldObj().getPlay()); // 玩法
        result.put("base", this.config.getBase()); // 底分
        result.put("user", this.config.getFieldConfig().getMaxUser()); // 最大玩家數
        result.set("round", JsonUtil.getInstance().getObjectMapper().createArrayNode()); // 包廂規則; 房主付費;(無用)
        result.set("AA", JsonUtil.getInstance().getObjectMapper().createArrayNode()); // 包廂規則; AA制;(無用)
        result.set("extend", extendJsonNode); // 客製規則
        return result;
    }

    public ModuleConfigMgrCore getModuleConfigMgrCore() {
        return moduleConfigMgrCore;
    }

    public ModuleConfigMgrCard getModuleConfigMgrCard() {
        return moduleConfigMgrCard;
    }

    public ModuleConfigMgrBaiRen getModuleConfigMgrBaiRen() {
        return moduleConfigMgrBaiRen;
    }

    public ModuleConfigMgrFish getModuleConfigMgrFish() {
        return moduleConfigMgrFish;
    }

    public AbstractModuleConfigMgrSlot getModuleConfigMgrSlot() { return moduleConfigMgrSlot; }

    // 轉換列表為陣列節點
    private ArrayNode transformList2ArrayNode(List<Integer> list){
        ArrayNode result = JsonUtil.getInstance().getObjectMapper().createArrayNode();

        for (Integer i : list) {
            result.add(i);
        }

        return result;
    }

    // 重設
    public void reset() {}
}
