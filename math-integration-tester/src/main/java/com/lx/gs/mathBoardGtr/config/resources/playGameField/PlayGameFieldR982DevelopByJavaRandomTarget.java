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

// 982 開發捕魚 正常模擬 遊戲房間設定
public class PlayGameFieldR982DevelopByJavaRandomTarget extends AbstractPlayGameFieldR {
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
                                }
                        )
                ),
                100,
                new TableFieldConfig(
                        new FieldConfig(
                                1005,
                                982,
                                "develop_by_java",
                                "開發捕鱼",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 1,
                                (short) 1,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(982101, new CompanyFieldObjBuilder()
                                            .setFieldId(982101)
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
                        , 982101
                )
        );
    }


}
