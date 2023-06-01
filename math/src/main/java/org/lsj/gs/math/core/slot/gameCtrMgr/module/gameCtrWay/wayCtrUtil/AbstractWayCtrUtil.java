package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayCtrUtil;

import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil.WayCtrCommonUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil.WayHitNumberUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil.WayHitScreenUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 抽象路計算 工具包
public abstract class AbstractWayCtrUtil {
    protected final SymbolConfig symbolConfig; // 標誌設定檔
    protected final PayTableConfig payTableConfig; // 賠率設定檔
    protected final WayCtrCommonUtil wayCtrCommonUtil; // 路共同計算工具包
    protected final WayHitNumberUtil wayHitNumberUtil; // 路連線數工具包
    protected final WayHitScreenUtil wayHitScreenUtil; // 路擊中畫面工具包

    public AbstractWayCtrUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        this.symbolConfig = symbolConfig;
        this.payTableConfig = payTableConfig;
        this.wayCtrCommonUtil = new WayCtrCommonUtil(symbolConfig, payTableConfig);
        this.wayHitNumberUtil = new WayHitNumberUtil(symbolConfig);
        this.wayHitScreenUtil = new WayHitScreenUtil(symbolConfig);
    }
}
