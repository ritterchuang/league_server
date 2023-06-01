package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Collections;
import java.util.List;

// 畫面計算結果非法
public class GameCtrResultExtendInvalid extends GameCtrResultExtend{

    public GameCtrResultExtendInvalid() {
        super(ConstMathSlot.GameHitDirectionType.INVALID);
    }

    @Override
    public List<List<Boolean>> calculateIntegralHitPosition() {
        return Collections.emptyList();
    }

    @Override
    public List<List<Boolean>> calculateIntegralDampHitPosition() {
        return Collections.emptyList();
    }
}
