package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;

import java.util.List;

// 消除進度者介面
public interface ICascadeProgressHlr {

    ProgressHlrResult calculateProgressHlrResult(int cascadeIndex, List<CascadeHlrResult> preCascadeHlrResultList, EliminateCtrResult currentEliminateCtrResult);
}
