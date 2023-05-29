package org.lsj.math.core;

import com.lx.gs.fieldconfig.FieldConfig;
import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.*;
import com.lx.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import com.lx.gs.math.core.common.poolCtr.entity.PoolConfig;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.core.common.table.module.tableUtil.TableUtilSlot;
import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import com.lx.gs.pool.AgencyPool;
import com.lx.gs.pool.PersonControlConfig;
import com.lx.gs.user.IUser;
import com.lx.websocket.WebSocketUtil;

import java.util.concurrent.atomic.AtomicInteger;

// 牌桌工廠
public class TableFactory {
    // 創建 Message 卡牌牌桌
//    public ISeverTableMessageCard createISeverTableMessageCard(
//            Event<TableFinishedEvent> event,
//            AtomicInteger lastTableId,
//            FieldConfig fieldConfig,
//            int fid,
//            AgencyPool agencyPool,
//            PersonControlConfig personControlConfig,
//            IUser user,
//            ITableUtilCard tableUtil) throws TableException {
//        switch(GameId.fromId(fieldConfig.getGameId())){
//            case ZJH_JAVA: return new TableZjhJavaWpr(new TableZjhJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigZjhJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case QZNN_JAVA: return new TableQznnJavaWpr(new TableQznnJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigQznnJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case EBG_JAVA: return new TableEbgJavaWpr(new TableEbgJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigEbgJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case QZNN_KSZ_JAVA: return new TableQznnKszJavaWpr(new TableQznnKszJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigQznnKszJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case SG_JAVA: return new TableSgJavaWpr(new TableSgJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigSgJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case QZPJ_JAVA: return new TableQzpjJavaWpr(new TableQzpjJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigQzpjJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case TBNN_JAVA: return new TableTbnnJavaWpr(new TableTbnnJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigTbnnJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case QZNN_K4Z_JAVA: return new TableQznnK4zJavaWpr(new TableQznnK4zJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigQznnK4zJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            case LZNN_JAVA: return new TableLznnJavaWpr(new TableLznnJava(event, lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigLznnJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(agencyPool, personControlConfig), user, tableUtil));
//            default: throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_TABLE_CREATE_FAIL, Level.WARN, "common table create fail");
//        }
//    }

    // 創建Message百人牌桌
//    public ISeverTableMessageBaiRen createISeverTableMessageBaiRen(
//            AtomicInteger lastTableId,
//            FieldConfig fieldConfig,
//            int fid,
//            IUser user,
//            ITableUtilBaiRen tableUtil) throws TableException {
//        switch(GameId.fromId(fieldConfig.getGameId())){
//            case HHDZ_JAVA: return new TableHhdzJavaWpr(new TableHhdzJava(lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigHhdzJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(new AgencyPool(), new PersonControlConfig(Collections.emptyList(), Collections.emptyMap())), user, tableUtil));
//            case BJL_JAVA: return new TableBjlJavaWpr(new TableBjlJava(lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigBjlJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(new AgencyPool(), new PersonControlConfig(Collections.emptyList(), Collections.emptyMap())), user, tableUtil));
//            case BRNN_JAVA: return new TableBrnnJavaWpr(new TableBrnnJava(lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigBrnnJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(new AgencyPool(), new PersonControlConfig(Collections.emptyList(), Collections.emptyMap())), user, tableUtil));
//            case LHD_JAVA: return new TableLhdJavaWpr(new TableLhdJava(lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigLhdJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(new AgencyPool(), new PersonControlConfig(Collections.emptyList(), Collections.emptyMap())), user, tableUtil));
//            case MYBJL_JAVA: return new TableMybjlJavaWpr(new TableMybjlJava(lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigMybjlJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(new AgencyPool(), new PersonControlConfig(Collections.emptyList(), Collections.emptyMap())), user, tableUtil));
//            case CJNN_JAVA: return new TableCjnnJavaWpr(new TableCjnnJava(lastTableId.getAndIncrement(), new TableFieldConfig(fieldConfig, fid), (TableGameConfigCjnnJava) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user), new PoolConfig(new AgencyPool(), new PersonControlConfig(Collections.emptyList(), Collections.emptyMap())), user, tableUtil));
//            default: throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_TABLE_CREATE_FAIL, Level.WARN, "common table create fail");
//        }
//    }

    // 創建 Command 魚機牌桌
//    public ISeverTableCommandFish createISeverTableCommandFish(
//            AtomicInteger lastTableId,
//            FieldConfig fieldConfig,
//            int fid,
//            AgencyPool agencyPool,
//            PersonControlConfig personControlConfig,
//            IUser user) throws TableException{
//        return new TableCommandFishWpr(
//                new TableCommandFish(
//                        lastTableId.getAndIncrement(),
//                        new TableFieldConfig(fieldConfig, fid),
//                        (TableGameConfigFish) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user),
//                        new PoolConfig(agencyPool, personControlConfig),
//                        user,
//                        new TableUtilFish(
//                                WebSocketUtil.getInstance(),
//                                new MathRandomUtil(),
//                                new ControlAlgorithmUtil())));
//    }

    // 創建 Command 老虎機牌桌
    public ISeverTableCommandSlot createISeverTableCommandSlot(
            AtomicInteger lastTableId,
            FieldConfig fieldConfig,
            int fid,
            AgencyPool agencyPool,
            PersonControlConfig personControlConfig,
            IUser user) throws TableException{
        return new TableCommandSlotWpr(
                new TableCommandSlot(
                        lastTableId.getAndIncrement(),
                        new TableFieldConfig(fieldConfig, fid),
                        (TableGameConfigSlot) new TableGameConfigFactory().createTableGameConfig(fieldConfig.getGameId(), user),
                        new PoolConfig(agencyPool, personControlConfig),
                        user,
                        new TableUtilSlot(
                                WebSocketUtil.getInstance(),
                                new MathRandomUtil(),
                                new ControlAlgorithmUtil())));
    }
}
