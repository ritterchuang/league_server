package com.lx.gs.math.core.fish.moduleConfigMgr;

import com.lx.enums.GameId;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import com.lx.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;
import com.lx.gs.math.core.fish.bulletMgr.entity.server.BulletMgrConfig;
import com.lx.gs.math.core.fish.detailCtr.enity.system.DetailCtrConfig;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.server.MathFishConfigHlrConfig;
import com.lx.gs.math.games.csby_java.enity.ConstCsbyJava;
import com.lx.gs.math.games.jcby_java.entity.ConstJcbyJava;
import com.lx.gs.math.games.rycs_java.enity.ConstRycsJava;
import com.lx.gs.math.games.slby_java.enity.ConstSlbyJava;
import com.lx.gs.math.games.szzb_java.enity.ConstSzzbJava;

import java.util.HashMap;
import java.util.Map;

// 魚機模組設定管理器
public class ModuleConfigMgrFish {
    private final TableFieldConfig config; // 牌桌設定
    private final int defaultPlay; // 預設遊戲類型

    public ModuleConfigMgrFish(TableFieldConfig config) {
        this.config = config;
        this.defaultPlay = 1;
    }

    // 建立子彈管理器設定
    public BulletMgrConfig createBulletMgrConfig(TableGameConfigFish tableGameConfig) {
        return new BulletMgrConfig(this.calculateBulletConfigMap(tableGameConfig), this.config.getBase());
    }

    // 計算子彈管理器設定
    private Map<Integer, BulletConfigBase> calculateBulletConfigMap(TableGameConfigFish tableGameConfig) {
        // 1. 取得玩法類型
        int play = this.config.getPlay();

        // 2. 回傳 子彈設定對應表
        if (tableGameConfig.getPlayToBulletConfigMap().containsKey(play)) {
            return tableGameConfig.getPlayToBulletConfigMap().get(play);
        }

        // 3. 防呆回傳  play_1 子彈設定對應表
        return tableGameConfig.getPlayToBulletConfigMap().get(this.defaultPlay);
    }

    // 計算詳細處理器設定
    public DetailCtrConfig createDetailCtrConfig() {
        switch (GameId.fromId(this.config.getGameId())){
            case JCBY_JAVA: return new DetailCtrConfig(ConstJcbyJava.BulletEnumJcbyJava.getBulletIndexStringMap(), ConstJcbyJava.TargetEnumJcbyJava.getTargetIndexStringMap());
            case CSBY_JAVA: return new DetailCtrConfig(ConstCsbyJava.BulletEnumCsbyJava.getBulletIndexStringMap(), ConstCsbyJava.TargetEnumCsbyJava.getTargetIndexStringMap());
            case SLBY_JAVA: return new DetailCtrConfig(ConstSlbyJava.BulletEnumSlbyJava.getBulletIndexStringMap(), ConstSlbyJava.TargetEnumSlbyJava.getTargetIndexStringMap());
            case RYCS_JAVA: return new DetailCtrConfig(ConstRycsJava.BulletEnumRycsJava.getBulletIndexStringMap(), ConstRycsJava.TargetEnumRycsJava.getTargetIndexStringMap());
            case SZZB_JAVA: return new DetailCtrConfig(ConstSzzbJava.BulletEnumSzzbJava.getBulletIndexStringMap(), ConstSzzbJava.TargetEnumSzzbJava.getTargetIndexStringMap());
            default: return new DetailCtrConfig(new HashMap<>(), new HashMap<>());
        }
    }

    // 建立數值設定處理器的設定
    public MathFishConfigHlrConfig createMathFishConfigHlrConfig(TableGameConfigFish tableGameConfig) {
        return new MathFishConfigHlrConfig(tableGameConfig.getRobotBeginMoneyLimitBase(), this.calculateBulletConfigMap(tableGameConfig), tableGameConfig.getHitCtrConfigMap(), this.config.getBase());
    }
}
