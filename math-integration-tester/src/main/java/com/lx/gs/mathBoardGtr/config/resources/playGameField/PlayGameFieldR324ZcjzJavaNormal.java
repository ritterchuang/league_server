package com.lx.gs.mathBoardGtr.config.resources.playGameField;

import com.lx.db.CompanyFieldObjBuilder;
import com.lx.gs.FieldConfig;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendSlot;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeSlotConfigExtend.PlayTypeSlotConfigExtendNormal;
import com.lx.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 324 招財金豬 正常模擬 遊戲房間設定
public class PlayGameFieldR324ZcjzJavaNormal extends AbstractPlayGameFieldR {
    public PlayGameFieldConfig create(){
        return new PlayGameFieldConfig(
                ConstStr.GameType.SLOT,
                new GameTypeConfigExtendSlot(
                        ConstStr.PlayTypeSlot.NORMAL,
                        new PlayTypeSlotConfigExtendNormal(ConstStr.PlayCostType.FIX_COST)
                ),
                100,
                new TableFieldConfig(
                        new FieldConfig(
                                1005,
                                324,
                                "zcjz_java",
                                "招财金猪",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 1,
                                (short) 1,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(324101, new CompanyFieldObjBuilder()
                                            .setFieldId(324101)
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
                        , 324101
                )
        );
    }


}
