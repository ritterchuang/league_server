package org.lsj.gs.mathBoardGtr.config.resources.playGameField;

import org.lsj.db.CompanyFieldObjBuilder;
import org.lsj.gs.FieldConfig;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendSlot;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeSlotConfigExtend.PlayTypeSlotConfigExtendNormal;
import org.lsj.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 312 帝国榮耀 正常模擬 遊戲房間設定
public class PlayGameFieldR312DgryJavaNormal extends AbstractPlayGameFieldR {
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
                                312,
                                "dgry_java",
                                "帝国荣耀",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 1,
                                (short) 1,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(312101, new CompanyFieldObjBuilder()
                                            .setFieldId(312101)
                                            .setFieldName("xinshou")
                                            .setFieldNameCn("菜鸟房")
                                            .setLimitMin(0.0)
                                            .setLimitKick(0.0)
                                            .setBase(0.01)
                                            .setGameRate(0.0)
                                            .setFeeType((short) 0)
                                            .createCompanyFieldObj()
                                    );
                                }}
                        )
                        , 312101
                )
        );
    }


}
