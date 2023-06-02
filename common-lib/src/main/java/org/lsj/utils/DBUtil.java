package org.lsj.utils;

import org.lsj.db.enums.SQLParamType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class DBUtil {

    private DBUtil() {
    }

    /**
     * mysql datetime 只支援到秒, 將毫秒歸 0
     *
     * @param ts 時間
     * @return 將毫秒歸 0 後的值
     */
    public static long convertMillisecondToZero(long ts) {
        return ts - (ts % 1000);
    }

    public static void setParamsToPrepareStatement(List<Map<Object, SQLParamType>> paramList, PreparedStatement pstmt) throws SQLException {
        int indexCounter = 1;
        for (Map<Object, SQLParamType> paramValueToTypeMap : paramList) {
            setPrepareStatement(paramValueToTypeMap.entrySet().iterator().next(), pstmt, indexCounter++);
        }
    }

    private static void setPrepareStatement(Map.Entry<Object, SQLParamType> param, PreparedStatement pstmt, int index) throws SQLException {
        Object paramValue = param.getKey();
        SQLParamType paramType = param.getValue();

        switch(paramType){
            case INT:
                pstmt.setInt(index, (Integer) paramValue);
                break;
            case STRING:
                pstmt.setString(index, (String) paramValue);
                break;
            case BOOLEAN:
                pstmt.setBoolean(index, (Boolean) paramValue);
                break;
            case TIMESTAMP:
                pstmt.setTimestamp(index, new Timestamp((Long) paramValue));
                break;
        }
    }

}
