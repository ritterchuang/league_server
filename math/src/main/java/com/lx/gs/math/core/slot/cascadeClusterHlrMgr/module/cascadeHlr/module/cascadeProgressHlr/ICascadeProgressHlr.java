package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr;

import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;

import java.util.List;

// 消除進度者介面
public interface ICascadeProgressHlr {

    ProgressHlrResult calculateProgressHlrResult(int cascadeIndex, List<CascadeHlrResult> preCascadeHlrResultList, EliminateCtrResult currentEliminateCtrResult);
}
