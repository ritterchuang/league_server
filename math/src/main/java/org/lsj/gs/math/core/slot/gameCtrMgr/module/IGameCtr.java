package org.lsj.gs.math.core.slot.gameCtrMgr.module;

import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.List;
import java.util.Map;

// 畫面算分器介面
public interface IGameCtr {

    // 計算畫面分數
    GameCtrResult calculateGameCtrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult);

    // 計算有全屏倍數 畫面分數
    GameCtrResult calculateGameCtrResultWithScreenMultiplier(SpinRequest spinRequest,
                                                       ScreenGtrResult screenGtrResult,
                                                       int screenMultiplier);

    // 計算有多種倍數 畫面分數
    GameCtrResult calculateGameCtrResultWithMultiplier(SpinRequest spinRequest,
                                                       ScreenGtrResult screenGtrResult,
                                                       int screenMultiplier,
                                                       Map<Integer, Integer> symbolIdToMultiplierMap,
                                                       List<List<Integer>> multiplierMatrix);



}
