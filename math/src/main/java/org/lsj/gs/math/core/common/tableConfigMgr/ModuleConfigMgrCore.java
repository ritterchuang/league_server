package org.lsj.gs.math.core.common.tableConfigMgr;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayerHlrConfig;

// 核心模組設定管理器
public class ModuleConfigMgrCore {
    private final TableFieldConfig config; // 牌桌設定

    public ModuleConfigMgrCore(TableFieldConfig config) {
        this.config = config;
    }

    // 建立遊戲玩家處理器設定
    public GamePlayerHlrConfig createGamePlayerHlrConfig() {
        return new GamePlayerHlrConfig(this.config.getMinUser(),this.config.getMaxUser(), config.getBase(), config.getLimitMin());
    }
}
