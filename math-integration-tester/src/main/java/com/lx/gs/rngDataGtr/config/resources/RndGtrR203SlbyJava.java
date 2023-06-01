package com.lx.gs.rngDataGtr.config.resources;

import com.lx.gs.mathBoardGtr.config.entity.ConstBoardGtr;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.ConstPlayGameField;
import com.lx.gs.mathBoardGtr.config.module.BoardGtrConfigFactory;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEFishSpecifyHitKillFlag;
import com.lx.gs.rngDataGtr.core.entity.ConstRngDataGtr;

import java.util.ArrayList;

// 亂數產生器設定 神龙捕魚
public class RndGtrR203SlbyJava extends AbstractRndGtrR {
    public RngDataGtrConfig create(){
        return new RngDataGtrConfig(
                new RngDataScenarioHlrConfig(1000000),
                new BoardGtrConfigFactory().create(ConstPlayGameField.PlayGameFieldResource.R203_SLBY_JAVA_RANDOM_TARGET, ConstBoardGtr.BoardGtrControlAlgorithmType.NATURE),
                new RngDataTesterConfig(
                        new ArrayList<>(){{
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 1, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 2, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 3, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 4, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 5, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 6, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 7, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 8, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 9, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 10, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 11, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 12, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 13, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 14, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 15, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 16, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 17, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 18, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 19, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 20, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 21, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 22, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 23, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 24, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 25, true)));
                            add(new TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum.E_FISH_SPECIFY_HIT_KILL_FLAG, new TestCaseConfigEFishSpecifyHitKillFlag(1, 26, true)));
                        }}
                )
        );
    }
}
