package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GameCtrResultExtendWayTest {

    // 給訂有得分結果，計算聯集位置
    @Test
    void test_given_winResult_when_calculateIntegralHitPosition_then_correct() {
        // 1. given
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;
        List<WayCtrWinResult> wayCtrWinResultList = new ArrayList<>(){
            {
                add(new WayCtrWinResult(0, 3, 3, 1, 1.0, new ArrayList<>(){
                    {
                        add(List.of(true, true, true));
                        add(List.of(false, true, false));
                        add(List.of(false, false, true));
                        add(List.of(false, false, false));
                        add(List.of(false, false, false));
                    }
                }, new ArrayList<>()));
                add(new WayCtrWinResult(0, 3, 3, 1, 1.0, new ArrayList<>(){
                    {
                        add(List.of(false, true, true));
                        add(List.of(true, false, false));
                        add(List.of(true, false, true));
                        add(List.of(false, true, false));
                        add(List.of(true, false, false));
                    }
                }, new ArrayList<>()));
            }
        };
        GameCtrResultExtendWay gameCtrResultExtendWay = new GameCtrResultExtendWay(gameHitDirectionType, wayCtrWinResultList);

        // 2. when
        List<List<Boolean>> actualResult = gameCtrResultExtendWay.calculateIntegralHitPosition();
        List<List<Boolean>> expectResult = new ArrayList<>(){
            {
                add(List.of(true, true, true));
                add(List.of(true, true, false));
                add(List.of(true, false, true));
                add(List.of(false, true, false));
                add(List.of(true, false, false));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給訂無得分結果，計算位置正確
    @Test
    void test_given_noWinResult_when_calculateIntegralHitPosition_then_correct() {
        // 1. given
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;
        List<WayCtrWinResult> wayCtrWinResultList = new ArrayList<>();
        GameCtrResultExtendWay gameCtrResultExtendWay = new GameCtrResultExtendWay(gameHitDirectionType, wayCtrWinResultList);

        // 2. when
        List<List<Boolean>> actualResult = gameCtrResultExtendWay.calculateIntegralHitPosition();
        List<List<Boolean>> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}