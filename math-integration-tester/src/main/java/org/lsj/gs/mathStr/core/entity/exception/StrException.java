package org.lsj.gs.mathStr.core.entity.exception;

import org.lsj.gs.mathStr.core.entity.ConstStr;
import org.slf4j.event.Level;

// 模擬器例外狀況
public class StrException extends Exception{
    private final ConstStr.StrErrorCode strErrorCode; // 模擬器錯誤碼
    private final Level level; // 等級

    public StrException(ConstStr.StrErrorCode strErrorCode, Level level, String message, Exception exception) {
        super(message, exception);
        this.strErrorCode = strErrorCode;
        this.level = level;
    }

    public StrException(ConstStr.StrErrorCode strErrorCode, Level level, String message) {
        super(message);
        this.strErrorCode = strErrorCode;
        this.level = level;
    }

    public ConstStr.StrErrorCode getStrErrorCode() {
        return strErrorCode;
    }

    public Level getLevel() {
        return level;
    }
}
