package org.lsj.gs.protocol;

import java.util.HashMap;
import java.util.Map;

public enum Command {

    UNKNOWN("unknown", "unknown"),

    /**
     * 由 client 先送出, 各類型遊戲通用
     */
    GET_FIELD_LIST("cts_getFieldList", "stc_fieldList"),
    ENTER_FIELD("cts_enterField", "stc_fieldSetting"),
    LEAVE_FIELD("cts_leaveField", "stc_userBalance"),
    LEAVE_GAME_CENTER("cts_leaveGameCenter", "stc_leaveGameCenter"),
    GET_BILL_LIST("cts_getBillList", "stc_billList"),
    KEEP_ALIVE("cts_keepAlive", "stc_keepAlive"),
    SET_RNG_DATA("cts_setRngData", "stc_setRngData"),
    DISCONNECT("", "stc_disconnect"),           // 此次錯誤 GS 會主動斷線

    /**
     * gs 主動送出, 各類型遊戲通用
     */
    DETECTED_ERROR("", "stc_detectedError"),

    /**
     * fish
     */
    HIT_TARGET("cts_hitTarget", "stc_hitResult"),

    /**
     * slot
     */
    SPIN_REQUEST("cts_spinRequest", "stc_spinResult"),
    LAST_PLAYED_PROGRESS_RESULT("cts_lastPlayedProgressResult", "none");

    private static final Map<String, Command> BY_CTS_COMMAND = new HashMap<>();

    static {
        for (Command e : values()) {
            BY_CTS_COMMAND.put(e.ctsCommand, e);
        }
    }

    public static Command getCommandByCtsCommand(String ctsCommand) {
        final Command command = BY_CTS_COMMAND.get(ctsCommand);
        return command == null ? UNKNOWN : command;
    }

    private final String ctsCommand;
    private final String stcCommand;

    Command(String ctsCommand, String stcCommand) {
        this.ctsCommand = ctsCommand;
        this.stcCommand = stcCommand;
    }

    public String getCtsCommand() {
        return ctsCommand;
    }

    public String getStcCommand() {
        return stcCommand;
    }
}
