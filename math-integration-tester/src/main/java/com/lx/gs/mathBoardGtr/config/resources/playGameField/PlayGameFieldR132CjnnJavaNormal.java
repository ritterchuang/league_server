package com.lx.gs.mathBoardGtr.config.resources.playGameField;

import com.lx.db.CompanyFieldObjBuilder;
import com.lx.gs.FieldConfig;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendBaiRen;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeBaiRenConfigExtend.PlayTypeBaiRenConfigExtendNormal;
import com.lx.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 132 新超級牛牛 正常模擬 遊戲房間設定
public class PlayGameFieldR132CjnnJavaNormal extends AbstractPlayGameFieldR {
    public PlayGameFieldConfig create(){
        return new PlayGameFieldConfig(
                ConstStr.GameType.BAIREN,
                new GameTypeConfigExtendBaiRen(
                        ConstStr.PlayTypeBaiRen.NORMAL,
                        new PlayTypeBaiRenConfigExtendNormal(6)
                ),
                100,
                new TableFieldConfig(
                        new FieldConfig(
                                1005,
                                132,
                                "cjnn_java",
                                "新超級牛牛",
                                3,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 12,
                                (short) 12,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(132101, new CompanyFieldObjBuilder()
                                            .setFieldId(132101)
                                            .setFieldName("xinshou")
                                            .setFieldNameCn("菜鸟房")
                                            .setLimitMin(0)
                                            .setLimitKick(0)
                                            .setBase(1.0)
                                            .setGameRate(5.0)
                                            .setFeeType((short) 2)
                                            .createCompanyFieldObj()
                                    );
                                }}
                        )
                        , 132101
                )
        );
    }
}
