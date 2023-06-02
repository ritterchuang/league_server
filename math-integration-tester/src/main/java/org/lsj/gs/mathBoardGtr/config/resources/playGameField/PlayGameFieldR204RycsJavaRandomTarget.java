package org.lsj.gs.mathBoardGtr.config.resources.playGameField;

import org.lsj.db.CompanyFieldObjBuilder;
import org.lsj.gs.FieldConfig;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendFish;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.PlayTypeFishConfigExtend;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.TargetInfo;
import org.lsj.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 204 人魚傳說 正常模擬 遊戲房間設定
public class PlayGameFieldR204RycsJavaRandomTarget extends AbstractPlayGameFieldR {
    public PlayGameFieldConfig create(){
        return new PlayGameFieldConfig(
                ConstStr.GameType.FISH,
                new GameTypeConfigExtendFish(
                        ConstStr.PlayTypeFish.RANDOM_TARGET,
                        new PlayTypeFishConfigExtend(
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
                                        new TargetInfo(26,1),
                                        new TargetInfo(27,1),
                                        new TargetInfo(28,1)
                                }
                        )
                ),
                100,
                new TableFieldConfig(
                        new FieldConfig(
                                1005,
                                204,
                                "rycs_java",
                                "人鱼传说",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 1,
                                (short) 1,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(204101, new CompanyFieldObjBuilder()
                                            .setFieldId(204101)
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
                        , 204101
                )
        );
    }


}
