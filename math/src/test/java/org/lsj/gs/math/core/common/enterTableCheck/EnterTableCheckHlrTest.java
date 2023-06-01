package org.lsj.gs.math.core.common.enterTableCheck;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.user.User;
import org.lsj.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;

// 進入遊戲桌檢查處理器測試
@ExtendWith(MockitoExtension.class)
class EnterTableCheckHlrTest {
    EnterTableCheckHlr enterTableCheckHlr;

    @Mock TableFieldConfig tableFieldConfig;
    IPoolConfig poolConfig;
    @Mock User user;

    @BeforeEach
    void setUp() {
        // 1. 建構
        this.enterTableCheckHlr = new EnterTableCheckHlr();

        // 2. 重置替換物件
        reset(this.user);
    }

    // 給定玩家為空，當入桌檢查，則丟例外狀況
    @Test
    void given_user_null_when_enterTableCheck_then_exception() {
        assertThrows(TableException.class, () -> this.enterTableCheckHlr.enterTableCheck(
                StringUtil.getInstance().getEmptyString(),
                this.tableFieldConfig,
                0,
                this.poolConfig,
                null));
    }

    // 給定玩家為餘額不足，當入桌檢查，則丟例外狀況
    @Test
    void given_user_money_not_enough_when_enterTableCheck_then_exception() {
        // 1. given
        Mockito.when(this.user.getBalance()).thenReturn(1.0);
        Mockito.when(this.tableFieldConfig.getLimitMin()).thenReturn(2.0);

        // 2. when then
        assertThrows(TableException.class, () -> this.enterTableCheckHlr.enterTableCheck(
                StringUtil.getInstance().getEmptyString(),
                this.tableFieldConfig,
                0,
                this.poolConfig,
                this.user));
    }
}