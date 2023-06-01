package com.lx.gs.mathBoardGtr.config.resources.playGameField;

import com.lx.db.CompanyFieldObjBuilder;
import com.lx.gs.FieldConfig;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendCard;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeCardConfigExtend.PlayTypeCardConfigExtend;
import com.lx.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 115 新看三張搶莊牛牛 正常模擬 遊戲房間設定
public class PlayGameFieldR115QznnKszJavaNormal extends AbstractPlayGameFieldR {
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
                                115,
                                "qznn_ksz_java",
                                "新看三张抢庄牛牛",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 3,
                                (short) 4,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(115101, new CompanyFieldObjBuilder()
                                            .setFieldId(115101)
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
                        , 115101
                )
        );
    }


}
