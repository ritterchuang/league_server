package org.lsj.gs.math.games.qznn_java.module.state;

import org.lsj.gs.math.games.qznn_java.TableQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.CtsSelectQznnJava;
import org.lsj.gs.math.games.qznn_java.module.gameAdjust.GameAdjustQznnJava;
import org.lsj.gs.math.games.qznn_java.module.logic.LogicQznnJava;
import org.lsj.gs.math.games.qznn_java.module.robotLogic.RobotLogicQznnJava;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

// 狀態測試
@ExtendWith(MockitoExtension.class)
class StateSelectQznnJavaTest {
    private StateSelectQznnJava stateSelectQznnJava;

    @Mock
    TableQznnJava mockTable; // 牌桌

    @Mock
    LogicQznnJava mockLogic; // 邏輯

    @Mock
    RobotLogicQznnJava mockRobotLogic; // 機器人邏輯

    @Mock
    GameAdjustQznnJava mockGameAdjust; // 調控

    @BeforeEach
    void setUp() {
        // 1. 建立狀態物件
        this.stateSelectQznnJava = new StateSelectQznnJava(
                this.mockTable,
                this.mockLogic,
                this.mockRobotLogic,
                this.mockGameAdjust,
                ConstQznnJava.StateEnumQznnJava.SELECT.getCode()
        );

        // 2. Mock
        Mockito.when(this.mockTable.getTableLogTitle()).thenReturn("[TEST]");
        Mockito.when(this.mockTable.getTableTimer()).thenReturn(new EmptyTableTimer());
    }


    @Test
    void test_given_onTimeout_when_onMsgSelect_then_finishSelectOneTime(){
        // 1. given
        StateSelectQznnJava spyStateSelectQznnJava = spy(this.stateSelectQznnJava);

        // 2. when
        spyStateSelectQznnJava.onTimeout();
        spyStateSelectQznnJava.onMessageSelect(
                0,
                new CtsSelectQznnJava(new int[]{},1)
        );

        // 3. then
        verify(spyStateSelectQznnJava, times(1)).finishSelect();
    }
}

