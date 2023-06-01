package com.lx.gs.math.core.slot.animationCtr;

import com.lx.gs.math.core.slot.animationCtr.enity.result.AnimationResult;

// 動畫表演計算者
public interface IAnimationCtr {

    AnimationResult calculateAnimationResult(double playerBet, double totalWin); // 計算動畫結果
}
