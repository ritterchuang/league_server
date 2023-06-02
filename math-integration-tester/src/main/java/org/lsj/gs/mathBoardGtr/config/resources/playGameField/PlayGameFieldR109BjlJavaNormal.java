package org.lsj.gs.mathBoardGtr.config.resources.playGameField;

import org.lsj.db.CompanyFieldObjBuilder;
import org.lsj.gs.FieldConfig;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendBaiRen;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeBaiRenConfigExtend.PlayTypeBaiRenConfigExtendNormal;
import org.lsj.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 109 新百家樂 正常模擬 遊戲房間設定
public class PlayGameFieldR109BjlJavaNormal extends AbstractPlayGameFieldR {
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
                                109,
                                "bjl_java",
                                "新百家樂",
                                3,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 12,
                                (short) 12,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(109101, new CompanyFieldObjBuilder()
                                            .setFieldId(109101)
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
                        , 109101
                )
        );
    }
}
