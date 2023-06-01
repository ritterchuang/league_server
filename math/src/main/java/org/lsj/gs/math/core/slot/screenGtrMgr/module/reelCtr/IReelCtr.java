package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;

import java.util.List;

// 輪帶表計算者介面
public interface IReelCtr {
    //* 一般產畫面 *//
    ReelCtrResult calculateReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, FrameResult frameResult); // 依照高低表，隨機決定表，產出輪帶表結果

    ReelCtrResult calculateReelCtrResultBySpecifyReelId(int reelId, FrameResult frameResult); // 指定表，產出輪帶表結果

    List<List<Integer>> calculateScreenSymbol(FrameResult frameResult, ReelCtrResult reelCtrResult); // 計算畫面


    //* 消除相關 *//
    ReelCtrResult calculateCascadeReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, ScreenGtrResult beforeScreenGtrResult, EliminateCtrResult eliminateCtrResult); // 消除玩，新畫面
    ReelCtrResult calculateNoDampCascadeReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, ScreenGtrResult beforeScreenGtrResult, EliminateCtrResult eliminateCtrResult); // 消除玩，新畫面

}
