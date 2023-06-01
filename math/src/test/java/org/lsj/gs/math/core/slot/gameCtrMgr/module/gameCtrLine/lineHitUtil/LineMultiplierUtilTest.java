package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 線 倍數工具包 測試
@ExtendWith(MockitoExtension.class)
class LineMultiplierUtilTest {
    LineMultiplierUtil lineMultiplierUtil; // 線倍數工具包

    // 給定空的倍數資訊，當計算額外倍數，預期得1
    @Test
    void test_givenEmptyMultiplierInformation_when_calculateMultiplier_then_return_1() {
        // 1. given
        List<List<Integer>> linePositionIndexList = new ArrayList<>(){
            {
                add(List.of(0,0,0,0,0));
                add(List.of(1,1,1,1,1));
                add(List.of(2,2,2,2,2));
            }
        };
        this.lineMultiplierUtil = new LineMultiplierUtil(linePositionIndexList);
        int lineId = 0;
        int hitNumber = 3;
        List<Integer> hitScreenPerLine = List.of(3,4,5,-1,-1);
        int screenMultiplier = 0;
        Map<Integer, Integer> symbolIdToMultiplierMap = new HashMap<>();
        List<List<Integer>> multiplierMatrix = new ArrayList<>();

        // 2. when
        int actualResult = this.lineMultiplierUtil.calculateMultiplier(lineId, hitNumber, hitScreenPerLine, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);
        int expectResult = 1;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定連線數4，畫面倍數5，單1標誌倍數2，壘乘倍數(2,3)，當計算額外倍數，預期得60
    @Test
    void test_given_screenMultiplier5_singleMultiplier2_matrixMultiplier6_hitNumber4_when_calculateMultiplier_then_return_60() {
        // 1. given
        List<List<Integer>> linePositionIndexList = new ArrayList<>(){
            {
                add(List.of(0,0,0,0,0));
                add(List.of(1,1,1,1,1));
                add(List.of(2,2,2,2,2));
            }
        };
        this.lineMultiplierUtil = new LineMultiplierUtil(linePositionIndexList);
        int lineId = 0;
        int hitNumber = 4;
        List<Integer> hitScreenPerLine = List.of(3,4,3,4,-1);
        int screenMultiplier = 5;
        Map<Integer, Integer> symbolIdToMultiplierMap = new HashMap<>(){
            {
                put(3, 2);
            }
        };
        List<List<Integer>> multiplierMatrix = new ArrayList<>(){
            {
                add(List.of(0,0,0));
                add(List.of(2,0,2));
                add(List.of(0,0,0));
                add(List.of(3,0,2));
                add(List.of(3,0,3));
            }
        };

        // 2. when
        int actualResult = this.lineMultiplierUtil.calculateMultiplier(lineId, hitNumber, hitScreenPerLine, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);
        int expectResult = 60;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

}