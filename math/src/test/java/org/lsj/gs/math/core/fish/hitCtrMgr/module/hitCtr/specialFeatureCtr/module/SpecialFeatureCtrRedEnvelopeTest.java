package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr.IKillCountCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.SpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 特殊事件紅包計算者測試
@ExtendWith(MockitoExtension.class)
class SpecialFeatureCtrRedEnvelopeTest {
    SpecialFeatureCtrRedEnvelope specialFeatureCtrRedEnvelope; // 特殊事件紅包計算者
    @Mock ITableUtil mockTableUtil;  // 牌桌工具包
    @Mock IRandomUtilMain mockRandomUtilMain; // 隨機工具包
    @Mock IKillCountCtr mockKillCountCtr; // 擊殺次數計算者
    @Mock SpecialFeatureCtrConfigExtendRedEnvelope mockConfig; // 特殊事件設定

    // 包裝結果正確
    @Test
    void test_given_noTriggerSpecialFeature_when_calculateSpecialFeatureResult_then_return_correct() {
        // 1. given
        this.mockTool();
        this.specialFeatureCtrRedEnvelope = new SpecialFeatureCtrRedEnvelope(ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, mockConfig, mockTableUtil);
        Mockito.when(mockKillCountCtr.calculateKillCount(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt())).thenReturn(0);
        SpecialFeatureResult expectResult = new SpecialFeatureResult(false,0.0, ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureResultExtendRedEnvelope());

        // 2. when
        SpecialFeatureResult specialFeatureResult = this.specialFeatureCtrRedEnvelope.calculateSpecialFeatureResult(0.2, 10, mockKillCountCtr);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(specialFeatureResult);
        assertEquals(expectString, actualString);
    }

    // 觸發特殊事件，結果正確
    @Test
    void test_given_triggerSpecialFeature_when_calculateSpecialFeatureResult_then_return_correct() {
        // 1. given
        this.mockTool();
        this.specialFeatureCtrRedEnvelope = new SpecialFeatureCtrRedEnvelope(ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, mockConfig, mockTableUtil);
        Mockito.when(mockKillCountCtr.calculateKillCount(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt())).thenReturn(1);
        SpecialFeatureResult expectResult = new SpecialFeatureResult(true,30.0, ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE,
                new SpecialFeatureResultExtendRedEnvelope(true, 1, 30.0, 30.0, 1, new int[]{3, 1, 1}, new double[]{30.0, 10.0, 10.0}));

        // 2. when
        SpecialFeatureResult specialFeatureResult = this.specialFeatureCtrRedEnvelope.calculateSpecialFeatureResult(0.2, 10, mockKillCountCtr);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(specialFeatureResult);
        assertEquals(expectString, actualString);
    }

    // 計算機器人結果正確
    @Test
    void test_given_correctConfig_when_calculateRobotSpecialFeatureResultList_then_returnCorrectResult() {
        // 1. given
        Mockito.when(mockConfig.getRtpUseRatio()).thenReturn(0.2);
        Mockito.when(mockConfig.getAwardCountAndWeightArray()).thenReturn(new int[][]{{1},{1}});
        Mockito.when(mockConfig.getAwardCountToAwardOddsListMap()).thenReturn(new HashMap<>(){{
          put(1, new ArrayList<>(){{
                add(new int[]{3});
                add(new int[]{6});
                add(new int[]{9});
          }});
        }});
        Mockito.when(mockConfig.getAwardCountToAwardWeightArrayMap()).thenReturn(new HashMap<>() {{
            put(1, new int[]{1, 1, 1});
        }});
        this.specialFeatureCtrRedEnvelope = new SpecialFeatureCtrRedEnvelope(ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, mockConfig, mockTableUtil);

        // 2. when
        List<RobotSpecialFeatureResult> robotSpecialFeatureResultList = this.specialFeatureCtrRedEnvelope.calculateRobotSpecialFeatureResultList(1);

        // 3. then
        assertEquals(4, robotSpecialFeatureResultList.size());
    }

    private void mockTool() {
        // 1. mock 工具包
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(mockRandomUtilMain);
        Mockito.when(mockRandomUtilMain.getArrayIndexByWeightWithAccuracy(Mockito.anyList(), Mockito.any())).thenReturn(0);
        Mockito.when(mockRandomUtilMain.getRandomElement(Mockito.anyList(), Mockito.any())).thenReturn(1);

        // 2. mock 設定
        Mockito.when(mockConfig.getRtpUseRatio()).thenReturn(0.2);
        Mockito.when(mockConfig.getShowCount()).thenReturn(3);
        Mockito.when(mockConfig.getAwardOddsArray()).thenReturn(new int[]{1});
        Mockito.when(mockConfig.getAwardCountToAwardWeightArrayMap()).thenReturn(new HashMap<>() {{
            put(1, new int[]{1, 1, 1});
        }});
        Mockito.when(mockConfig.getAwardCountAndWeightArray()).thenReturn(new int[][]{{1},{1}});
        Mockito.when(mockConfig.getAwardCountToAwardOddsListMap()).thenReturn(new HashMap<>(){{
            put(1, new ArrayList<>(){{
                add(new int[]{3});
                add(new int[]{6});
                add(new int[]{9});
            }});
        }});
    }
}