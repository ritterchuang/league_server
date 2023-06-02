package org.lsj.gs.mathBoardGtr.config.resources.playGameField;

import org.lsj.db.CompanyFieldObjBuilder;
import org.lsj.gs.FieldConfig;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendBaiRen;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeBaiRenConfigExtend.PlayTypeBaiRenConfigExtendNormal;
import org.lsj.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 105 新紅黑大戰 正常模擬 遊戲房間設定
public class PlayGameFieldR105HhdzJavaNormal extends AbstractPlayGameFieldR {
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
                                105,
                                "hhdz_java",
                                "新红黑大战",
                                3,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 12,
                                (short) 12,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(105101, new CompanyFieldObjBuilder()
                                            .setFieldId(105101)
                                            .setFieldName("xinshou")
                                            .setFieldNameCn("菜鸟房")
                                            .setLimitMin(0)
                                            .setLimitKick(0)
                                            .setBase(1.0)
                                            .setGameRate(5.0)
                                            .setFeeType((short) 0)
                                            .createCompanyFieldObj()
                                    );
                                }}
                        )
                        , 105101
                )
        );
    }
}
