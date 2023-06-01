package org.lsj.gs.math.core.common.table.entity.exception;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.slf4j.event.Level;

// 牌桌例外狀況
public class TableException extends Exception{
    private final ConstMathCommon.TableProtocolCode tableProtocolCode; // 牌桌錯誤碼
    private final Level level; // 等級

    public TableException(ConstMathCommon.TableProtocolCode tableProtocolCode, Level level, String message, Exception exception) {
        super(message, exception);
        this.tableProtocolCode = tableProtocolCode;
        this.level = level;
    }

    public TableException(ConstMathCommon.TableProtocolCode tableProtocolCode, Level level, String message) {
        super(message);
        this.tableProtocolCode = tableProtocolCode;
        this.level = level;
    }

    public ConstMathCommon.TableProtocolCode getTableProtocolCode() {
        return tableProtocolCode;
    }

    public Level getLevel() {
        return level;
    }
}
