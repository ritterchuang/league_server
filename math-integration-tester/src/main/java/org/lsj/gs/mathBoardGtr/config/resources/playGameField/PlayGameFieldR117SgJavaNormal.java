package org.lsj.gs.mathBoardGtr.config.resources.playGameField;

import org.lsj.db.CompanyFieldObjBuilder;
import org.lsj.gs.FieldConfig;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendCard;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeCardConfigExtend.PlayTypeCardConfigExtend;
import org.lsj.gs.mathStr.core.entity.ConstStr;

import java.util.HashMap;

// 117 新三公 正常模擬 遊戲房間設定
public class PlayGameFieldR117SgJavaNormal extends AbstractPlayGameFieldR {
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
                                117,
                                "sg_java",
                                "新三公",
                                1,
                                (short) 1,
                                (short) 0,
                                (short) 10,
                                (short) 3,
                                (short) 5,
                                "{}",
                                "{}",
                                new HashMap<>(){{
                                    put(117101, new CompanyFieldObjBuilder()
                                            .setFieldId(117101)
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
                        , 117101
                )
        );
    }


}
