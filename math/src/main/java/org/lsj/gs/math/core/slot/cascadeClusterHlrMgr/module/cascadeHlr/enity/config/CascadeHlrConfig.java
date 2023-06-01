package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr.enity.CascadeReadyHandHlrMgrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr.enity.config.CascadeSpecialFeatureHlrMgrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.config.EliminateCtrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtend;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrConfig;

// 消除處理者設定
public class CascadeHlrConfig {
    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final CascadeHlrConfigExtend cascadeHlrConfigExtend; // 消除處理者額外設定
    private final CascadeConfigExtend cascadeConfigExtend; // 消除額外設定
    private final ScreenGtrConfig screenGtrConfig; // 畫面生產器設定
    private final GameCtrConfig gameCtrConfig; // 遊戲算分者設定
    private final ProgressConfig cascadeProgressConfig; // 消除進度處理器設定
    private final CascadeSpecialFeatureHlrMgrConfig cascadeSpecialFeatureHlrMgrConfig; // 消除特殊事件處理者管理器設定
    private final CascadeReadyHandHlrMgrConfig cascadeReadyHandHlrMgrConfig; // 消除聽牌處理者管理器設定
    private final EliminateCtrConfig eliminateCtrConfig; // 消除位置計算者設定

    public CascadeHlrConfig(ConstMathSlot.CascadeType cascadeType, CascadeHlrConfigExtend cascadeHlrConfigExtend, CascadeConfigExtend cascadeConfigExtend, ScreenGtrConfig screenGtrConfig, GameCtrConfig gameCtrConfig, ProgressConfig cascadeProgressConfig, CascadeSpecialFeatureHlrMgrConfig cascadeSpecialFeatureHlrMgrConfig, CascadeReadyHandHlrMgrConfig cascadeReadyHandHlrMgrConfig, EliminateCtrConfig eliminateCtrConfig) {
        this.cascadeType = cascadeType;
        this.cascadeHlrConfigExtend = cascadeHlrConfigExtend;
        this.cascadeConfigExtend = cascadeConfigExtend;
        this.screenGtrConfig = screenGtrConfig;
        this.gameCtrConfig = gameCtrConfig;
        this.cascadeProgressConfig = cascadeProgressConfig;
        this.cascadeSpecialFeatureHlrMgrConfig = cascadeSpecialFeatureHlrMgrConfig;
        this.cascadeReadyHandHlrMgrConfig = cascadeReadyHandHlrMgrConfig;
        this.eliminateCtrConfig = eliminateCtrConfig;
    }

    public ConstMathSlot.CascadeType getCascadeType() {
        return cascadeType;
    }

    public CascadeHlrConfigExtend getCascadeHlrConfigExtend() {
        return cascadeHlrConfigExtend;
    }

    public CascadeConfigExtend getCascadeConfigExtend() {
        return cascadeConfigExtend;
    }

    public ScreenGtrConfig getScreenGtrConfig() {
        return screenGtrConfig;
    }

    public GameCtrConfig getGameCtrConfig() {
        return gameCtrConfig;
    }

    public ProgressConfig getCascadeProgressConfig() {
        return cascadeProgressConfig;
    }

    public CascadeSpecialFeatureHlrMgrConfig getCascadeSpecialFeatureHlrMgrConfig() {
        return cascadeSpecialFeatureHlrMgrConfig;
    }

    public CascadeReadyHandHlrMgrConfig getCascadeReadyHandHlrMgrConfig() {
        return cascadeReadyHandHlrMgrConfig;
    }

    public EliminateCtrConfig getEliminateCtrConfig() {
        return eliminateCtrConfig;
    }
}
