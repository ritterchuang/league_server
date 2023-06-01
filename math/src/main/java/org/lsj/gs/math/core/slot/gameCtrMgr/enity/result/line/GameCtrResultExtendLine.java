package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResultExtend;
import org.lsj.utils.ListUtil;

import java.util.Collections;
import java.util.List;

// 畫面計算額外結果 線
public class GameCtrResultExtendLine extends GameCtrResultExtend {
    private final List<LineCtrWinResult> lineCtrWinResultList; // 線贏分結果

    public GameCtrResultExtendLine(ConstMathSlot.GameHitDirectionType gameHitDirectionType, List<LineCtrWinResult> lineCtrWinResultList) {
        super(gameHitDirectionType);
        this.lineCtrWinResultList = lineCtrWinResultList;
    }

    public GameCtrResultExtendLine(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        super(gameHitDirectionType);
        this.lineCtrWinResultList = Collections.emptyList();
    }

    // 計算所有得分位置聯集
    public List<List<Boolean>> calculateIntegralHitPosition() {
        // 1. 若無結果回傳空陣列
        if (this.lineCtrWinResultList.size() == 0) {
            return Collections.emptyList();
        }

        // 2. 複製第一個結果
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(this.lineCtrWinResultList.get(0).getScreenHitPosition());

        // 3. 遍歷所有結果取聯集
        for (int lineWinIndex = 1; lineWinIndex < this.lineCtrWinResultList.size(); lineWinIndex++) {
            // 3.1 取得當前得分結果位置
            List<List<Boolean>> currentHitPosition = this.lineCtrWinResultList.get(lineWinIndex).getScreenHitPosition();

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

    // 計算所有得分位置聯集
    public List<List<Boolean>> calculateIntegralDampHitPosition() {
        // 1. 若無結果回傳空陣列
        if (this.lineCtrWinResultList.size() == 0) {
            return Collections.emptyList();
        }

        // 2. 若無 Damp， 回傳一般中獎畫面聯集
        if (this.lineCtrWinResultList.get(0).getDampScreenHitPosition().isEmpty()) {
            return this.calculateIntegralHitPosition();
        }

        // 3. 複製第一個結果
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(this.lineCtrWinResultList.get(0).getDampScreenHitPosition());

        // 4. 遍歷所有結果取聯集
        for (int lineWinIndex = 1; lineWinIndex < this.lineCtrWinResultList.size(); lineWinIndex++) {
            // 4.1 取得當前得分結果位置
            List<List<Boolean>> currentHitPosition = this.lineCtrWinResultList.get(lineWinIndex).getDampScreenHitPosition();

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

    public List<LineCtrWinResult> getLineCtrWinResultList() {
        return lineCtrWinResultList;
    }
}
