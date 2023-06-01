package com.lx.gs.mathBoardGtr.config.resources.playGameField;

import com.lx.db.CompanyFieldObjBuilder;
import com.lx.gs.FieldConfig;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendCard;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeCardConfigExtend.PlayTypeCardConfigExtend;
import com.lx.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 114 新搶莊二八槓 正常模擬 遊戲房間設定
public class PlayGameFieldR114EbgJavaNormal {
    public PlayGameFieldConfig create(){
        return new PlayGameFieldConfig(
                ConstStr.GameType.CARD,
                new GameTypeConfigExtendCard(
                        ConstStr.PlayTypeCard.NORMAL,
                        new PlayTypeCardConfigExtend()
                ),
                100,
                new TableFieldConfig(
                        new FieldConfig(
                                1005,
                                114,
                                "ebg_java",
                                "新抢庄二八槓",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 4,
                                (short) 4,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(114101, new CompanyFieldObjBuilder()
                                            .setFieldId(114101)
                                            .setFieldName("xinshou")
                                            .setFieldNameCn("菜鸟房")
                                            .setLimitMin(1.0)
                                            .setLimitKick(1.0)
                                            .setBase(1.0)
                                            .setGameRate(5.0)
                                            .setFeeType((short) 2)
                                            .createCompanyFieldObj()
                                    );
                                }}
                        )
                        , 114101
                )
        );
    }


}
