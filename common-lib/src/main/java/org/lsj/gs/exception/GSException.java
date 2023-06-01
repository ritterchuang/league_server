package org.lsj.gs.exception;

import org.lsj.websocket.ProtocolCode;
import org.slf4j.event.Level;

public class GSException extends Exception {

    private final ProtocolCode protocolCode;
    private final Level level;

    public GSException(ProtocolCode protocolCode, Level level, String message, Exception ex) {
        super(message, ex);
        this.protocolCode = protocolCode;
        this.level = level;
    }

    public GSException(ProtocolCode protocolCode, Level level, String message) {
        super(message);
        this.protocolCode = protocolCode;
        this.level = level;
    }

    public ProtocolCode getProtocolCode() {
        return protocolCode;
    }

    public Level getLevel() {
        return level;
    }
}
