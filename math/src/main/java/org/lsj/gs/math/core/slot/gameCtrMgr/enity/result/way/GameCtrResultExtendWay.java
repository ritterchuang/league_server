package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResultExtend;
import org.lsj.utils.ListUtil;

import java.util.Collections;
import java.util.List;

// 畫面計算額外結果 路
public class GameCtrResultExtendWay extends GameCtrResultExtend {
    private final List<WayCtrWinResult> wayCtrWinResultList; // 路贏分結果

    public GameCtrResultExtendWay(ConstMathSlot.GameHitDirectionType gameHitDirectionType, List<WayCtrWinResult> wayCtrWinResultList) {
        super(gameHitDirectionType);
        this.wayCtrWinResultList = wayCtrWinResultList;
    }

    public GameCtrResultExtendWay(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        super(gameHitDirectionType);
        this.wayCtrWinResultList = Collections.emptyList();
    }

    // 計算所有得分位置聯集
    public List<List<Boolean>> calculateIntegralHitPosition() {
        // 1. 若無結果回傳空陣列
        if (this.wayCtrWinResultList.size() == 0) {
            return Collections.emptyList();
        }

        // 2. 複製第一個結果
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(this.wayCtrWinResultList.get(0).getScreenHitPosition());

        // 3. 遍歷所有結果取聯集
        for (int wayWinIndex = 1; wayWinIndex < this.wayCtrWinResultList.size(); wayWinIndex++) {
            // 3.1 取得當前得分結果位置
            List<List<Boolean>> currentHitPosition = this.wayCtrWinResultList.get(wayWinIndex).getScreenHitPosition();

            // 3.2 取聯集
            for (int columnIndex = 0; columnIndex < currentHitPosition.size(); columnIndex++) {
                for (int rowIndex = 0; rowIndex < currentHitPosition.get(columnIndex).size(); rowIndex++) {
                    if (currentHitPosition.get(columnIndex).get(rowIndex) == true) {
                        result.get(columnIndex).set(rowIndex, true);
                    }
                }
            }
        }

        // 4. 回傳
        return result;
    }

    // 計算破框所有得分位置聯集
    public List<List<Boolean>> calculateIntegralDampHitPosition() {
        // 1. 若無結果回傳空陣列
        if (this.wayCtrWinResultList.size() == 0) {
            return Collections.emptyList();
        }

        // 2. 若無 Damp， 回傳一般中獎畫面聯集
        if (this.wayCtrWinResultList.get(0).getDampScreenHitPosition().isEmpty()) {
            return this.calculateIntegralHitPosition();
        }

        // 3. 複製第一個結果
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(this.wayCtrWinResultList.get(0).getDampScreenHitPosition());

        // 4. 遍歷所有結果取聯集
        for (int wayWinIndex = 1; wayWinIndex < this.wayCtrWinResultList.size(); wayWinIndex++) {
            // 4.1 取得當前得分結果位置
            List<List<Boolean>> currentHitPosition = this.wayCtrWinResultList.get(wayWinIndex).getDampScreenHitPosition();

            // 4.2 取聯集
            for (int columnIndex = 0; columnIndex < currentHitPosition.size(); columnIndex++) {
                for (int rowIndex = 0; rowIndex < currentHitPosition.get(columnIndex).size(); rowIndex++) {
                    if (currentHitPosition.get(columnIndex).get(rowIndex) == true) {
                        result.get(columnIndex).set(rowIndex, true);
                    }
                }
            }
        }

        // 5. 回傳
        return result;
    }

    public List<WayCtrWinResult> getWayCtrWinResultList() {
        return wayCtrWinResultList;
    }
}
