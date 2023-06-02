package org.lsj.gs.math.core.slot.animationCtr;

import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResult;

// 動畫表演計算者
public interface IAnimationCtr {

    AnimationResult calculateAnimationResult(double playerBet, double totalWin); // 計算動畫結果
}
