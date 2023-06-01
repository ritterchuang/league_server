package com.lx.gs.mathBoardGtr.config.resources.playGameField;

import com.lx.db.CompanyFieldObjBuilder;
import com.lx.gs.FieldConfig;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendFish;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.PlayTypeFishConfigExtendRandomTarget;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.TargetInfo;
import com.lx.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 203 神龍補魚 正常模擬 遊戲房間設定
public class PlayGameFieldR203SlbyJavaRandomTarget extends AbstractPlayGameFieldR {
    public PlayGameFieldConfig create(){
        return new PlayGameFieldConfig(
                ConstStr.GameType.FISH,
                new GameTypeConfigExtendFish(
                        ConstStr.PlayTypeFish.RANDOM_TARGET,
                        new PlayTypeFishConfigExtendRandomTarget(
                                ConstStr.PlayCostType.FIX_COST,
                                new TargetInfo[]{
                                        new TargetInfo(1,1),
                                        new TargetInfo(2,1),
                                        new TargetInfo(3,1),
                                        new TargetInfo(4,1),
                                        new TargetInfo(5,1),
                                        new TargetInfo(6,1),
                                        new TargetInfo(7,1),
                                        new TargetInfo(8,1),
                                        new TargetInfo(9,1),
                                        new TargetInfo(10,1),
                                        new TargetInfo(11,1),
                                        new TargetInfo(12,1),
                                        new TargetInfo(13,1),
                                        new TargetInfo(14,1),
                                        new TargetInfo(15,1),
                                        new TargetInfo(16,1),
                                        new TargetInfo(17,1),
                                        new TargetInfo(18,1),
                                        new TargetInfo(19,1),
                                        new TargetInfo(20,1),
                                        new TargetInfo(21,1),
                                        new TargetInfo(22,1),
                                        new TargetInfo(23,1),
                                        new TargetInfo(24,1),
                                        new TargetInfo(25,1),
                                        new TargetInfo(26,1)
                                }
                        )
                ),
                100,
                new TableFieldConfig(
                        new FieldConfig(
                                1005,
                                203,
                                "slby_java",
                                "神龍捕鱼",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 1,
                                (short) 1,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(203101, new CompanyFieldObjBuilder()
                                            .setFieldId(203101)
                                            .setFieldName("xinshou")
                                            .setFieldNameCn("菜鸟房")
                                            .setLimitMin(0.0)
                                            .setLimitKick(0.0)
                                            .setBase(0.1)
                                            .setGameRate(0.0)
                                            .setFeeType((short) 0)
                                            .createCompanyFieldObj()
                                    );
                                }}
                        )
                        , 203101
                )
        );
    }


}
