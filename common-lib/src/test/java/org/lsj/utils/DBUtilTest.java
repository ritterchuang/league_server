package org.lsj.utils;

import org.lsj.db.enums.SQLParamType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBUtilTest {

    PreparedStatement mockPSTMT;

    List<Map<Object, SQLParamType>> sqlParamList;

    @Test
    void GivenTs_WhenConvertMillisecondToZero_ThenReturnMillisecondsIs0() {
        long ts = System.currentTimeMillis();

        long actual = DBUtil.convertMillisecondToZero(ts);

        String tsString = String.valueOf(ts);
        assertEquals(tsString.substring(0, tsString.length() - 3) + "000", String.valueOf(actual));
    }

    @Test
    void GivenTsIs0_WhenConvertMillisecondToZero_ThenReturn0() {
        long ts = 0;

        long actual = DBUtil.convertMillisecondToZero(ts);

        assertEquals(ts, actual);
    }

    @Test
    void GivenSQLParamList_WhenSetParamsToPrepareStatement_ThenSetPrepareStatementBySqlType() throws SQLException {
        prepareArgsForSetParamsToPrepareStatement();

        DBUtil.setParamsToPrepareStatement(this.sqlParamList, this.mockPSTMT);

        Mockito.verify(this.mockPSTMT, Mockito.times(1)).setInt(1, 1);
        Mockito.verify(this.mockPSTMT, Mockito.times(1)).setString(2, "str");
        Mockito.verify(this.mockPSTMT, Mockito.times(1)).setBoolean(3, true);
        Mockito.verify(this.mockPSTMT, Mockito.times(1)).setTimestamp(4, new Timestamp(1679629571));
    }

    private void prepareArgsForSetParamsToPrepareStatement() throws SQLException {
        this.mockPSTMT = Mockito.mock(PreparedStatement.class);
        this.sqlParamList = new ArrayList<>();

        sqlParamList.add(Collections.singletonMap(1, SQLParamType.INT));
        sqlParamList.add(Collections.singletonMap("str", SQLParamType.STRING));
        sqlParamList.add(Collections.singletonMap(true, SQLParamType.BOOLEAN));
        sqlParamList.add(Collections.singletonMap((long) 1679629571, SQLParamType.TIMESTAMP));

        Assertions.assertEquals(sqlParamList.size(), SQLParamType.values().length);
    }

}
