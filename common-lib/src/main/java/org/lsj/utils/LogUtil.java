package org.lsj.utils;

import javax.websocket.Session;

public class LogUtil {

    private LogUtil() {
    }

    public static String getLogPrefix(Session session, int uid) {
        String uidStr = uid == 0 ? "NULL" : String.valueOf(uid);

        return "[" + subSessionId(session) + "][" + uidStr + "]";
    }

    public static String getLogPrefix(Session session, int uid, String roundId) {
        String uidStr = (uid == 0) ? "NULL" : String.valueOf(uid);
        String roundIdStr = (StringUtil.getInstance().isNull(roundId)) ? "NULL": roundId;

        return "[" + subSessionId(session) + "][" + uidStr + "][" + roundIdStr + "]";
    }

    public static String getLogPrefixForLB(Session gameClientSession, Session gameServerSession, int uid) {
        String uidStr = uid == 0 ? "NULL" : String.valueOf(uid);

        return "[" + subSessionId(gameClientSession) + "][" + subSessionId(gameServerSession) + "][" + uidStr + "]";
    }

    private static String subSessionId(Session session) {
        String subSessionId;
        if (session == null) {
            subSessionId = "NULL";
        } else {
            subSessionId = session.getId().length() < 8 ? session.getId() : session.getId().substring(0, 8);
        }
        return subSessionId;
    }

}
