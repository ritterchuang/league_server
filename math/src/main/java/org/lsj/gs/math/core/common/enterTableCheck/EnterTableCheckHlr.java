package org.lsj.gs.math.core.common.enterTableCheck;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.user.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Objects;

// 進入遊戲桌檢查處理器
public class EnterTableCheckHlr {
    private static final Logger LOG = LoggerFactory.getLogger(EnterTableCheckHlr.class); // 日誌器

    // 入桌檢查
    public void enterTableCheck(String tableLogTitle,
                                TableFieldConfig tableFieldConfig,
                                int tableId,
                                IPoolConfig poolConfig,
                                IUser user) throws TableException {
        // 1. 印出傳入資訊
        if (Objects.isNull(user)) {
            LOG.error("tableId:{}, poolConfig:{}, user is null", tableId, poolConfig);
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_USER_ERROR, Level.ERROR, "common user error");
        }else {
            LOG.info(tableLogTitle + " tableId:{}, poolConfig:{}, personControlLog:{}, thirdAccountId:{}, agent:{}, ip:{}", tableId, poolConfig, user.getPersonControlLog(), user.getThirdAccountId(), user.getBaseAgencyId(), user.getIp());
        }

        // 2. 入桌門檻檢查
        if(user.getBalance() < tableFieldConfig.getLimitMin()){
            LOG.error(tableLogTitle + " userBalance:{}, configLimitMin:{}, create table human money not enough error", user.getBalance(), tableFieldConfig.getLimitMin());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_MONEY_NOT_ENOUGH, Level.ERROR, "common money not enough");
        }
    }

    public void reset() {
    }
}
