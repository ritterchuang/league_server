package org.lsj.gs.mathBoardGtr.config.resources.playGameField;

import org.lsj.db.CompanyFieldObjBuilder;
import org.lsj.gs.FieldConfig;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendCard;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeCardConfigExtend.PlayTypeCardConfigExtend;
import org.lsj.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 122 新搶莊牌九 正常模擬 遊戲房間設定
public class PlayGameFieldR122QzpjJavaNormal {
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
                                122,
                                "qzpj_java",
                                "新抢庄牌九",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 4,
                                (short) 4,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(122101, new CompanyFieldObjBuilder()
                                            .setFieldId(122101)
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
                        , 122101
                )
        );
    }


}
