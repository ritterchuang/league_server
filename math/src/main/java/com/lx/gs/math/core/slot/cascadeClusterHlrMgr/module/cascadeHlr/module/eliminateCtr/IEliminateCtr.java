package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr;

import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

// 消除方式計算者介面
public interface IEliminateCtr {

    // 計算消除計算者結果
    EliminateCtrResult calculateEliminationCtrResult(
            GameCtrResult gameCtrResult,
            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster,
            ScreenGtrResult screenGtrResult);

}
