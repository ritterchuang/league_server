package com.lx.gs.math.core.slot.gameCtrMgr.module;

import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.List;
import java.util.Map;

// 非法遊戲算分
public class GameCtrInvalid implements IGameCtr{

    @Override
    public GameCtrResult calculateGameCtrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        return new GameCtrResult();
    }

    @Override
    public GameCtrResult calculateGameCtrResultWithScreenMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier) {
        return new GameCtrResult();
    }

    @Override
    public GameCtrResult calculateGameCtrResultWithMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier, Map<Integer, Integer> symbolIdToMultiplierMap, List<List<Integer>> multiplierMatrix) {
        return new GameCtrResult();
    }
}
