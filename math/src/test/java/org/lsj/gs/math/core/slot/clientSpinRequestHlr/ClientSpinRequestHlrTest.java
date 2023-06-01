package org.lsj.gs.math.core.slot.clientSpinRequestHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendNoneNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.ClientSpinRequestHlrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigExtendNoneNormal;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfigExtendRatio;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 客端押注請求處理者測試
@ExtendWith(MockitoExtension.class)
class ClientSpinRequestHlrTest {
    ClientSpinRequestHlr clientSpinRequestHlr; // 客端押注請求處理者
    @Mock
    ClientSpinRequestHlrConfig mockConfig; // 設定檔
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包


    // 給定錯誤押注金額，回傳 押注金額錯誤
    @Test
    void test_given_nonExistCredit_when_checkClientSpinRequest_then_return_slotCreditNotExist() {
        // 1. given
        Mockito.when(this.mockConfig.getBetSpinConfigCluster()).thenReturn(new BetSpinConfigCluster(new ArrayList<>(){{add(1.0);}}, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new HashMap<>()));
        this.clientSpinRequestHlr = new ClientSpinRequestHlr(mockConfig, mockTableUtil);
        ClientSpinRequest clientSpinRequest = new ClientSpinRequest(0.5, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, ConstMathSlot.BetSpinType.NONE_NORMAL, new BetSpinTypeExtendNoneNormal());

        // 2. when
        ConstMathCommon.TableProtocolCode actualResult = this.clientSpinRequestHlr.checkClientSpinRequest(clientSpinRequest);
        ConstMathCommon.TableProtocolCode expectResult = ConstMathCommon.TableProtocolCode.SLOT_CREDIT_NOT_EXIST;

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定錯誤押注類型，回傳 押注類型錯誤
    @Test
    void test_given_nonExistBetType_when_checkClientSpinRequest_then_return_slotBetTypeNotExist() {
        // 1. given
        Mockito.when(this.mockConfig.getBetSpinConfigCluster()).thenReturn(new BetSpinConfigCluster(new ArrayList<>(){{add(1.0);}}, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new HashMap<>()));
        Mockito.when(this.mockConfig.getBetTypeList()).thenReturn(new ArrayList<>(){{add(ConstMathSlot.BetType.NONE);}});
        this.clientSpinRequestHlr = new ClientSpinRequestHlr(mockConfig, mockTableUtil);
        ClientSpinRequest clientSpinRequest = new ClientSpinRequest(1.0, ConstMathSlot.BetType.INVALID, ConstMathSlot.SpinType.NORMAL, ConstMathSlot.BetSpinType.NONE_NORMAL, new BetSpinTypeExtendNoneNormal());

        // 2. when
        ConstMathCommon.TableProtocolCode actualResult = this.clientSpinRequestHlr.checkClientSpinRequest(clientSpinRequest);
        ConstMathCommon.TableProtocolCode expectResult = ConstMathCommon.TableProtocolCode.SLOT_BET_TYPE_NOT_EXIST;

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定錯誤玩法類型，回傳 玩法類型錯誤
    @Test
    void test_given_nonExistSpinType_when_checkClientSpinRequest_then_return_slotSpinTypeNotExist() {
        // 1. given
        Mockito.when(this.mockConfig.getBetSpinConfigCluster()).thenReturn(new BetSpinConfigCluster(new ArrayList<>(){{add(1.0);}}, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new HashMap<>()));
        Mockito.when(this.mockConfig.getBetTypeList()).thenReturn(new ArrayList<>(){{add(ConstMathSlot.BetType.NONE);}});
        Mockito.when(this.mockConfig.getSpinTypeList()).thenReturn(new ArrayList<>(){{add(ConstMathSlot.SpinType.NORMAL);}});
        this.clientSpinRequestHlr = new ClientSpinRequestHlr(mockConfig, mockTableUtil);
        ClientSpinRequest clientSpinRequest = new ClientSpinRequest(1.0, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.INVALID, ConstMathSlot.BetSpinType.NONE_NORMAL, new BetSpinTypeExtendNoneNormal());

        // 2. when
        ConstMathCommon.TableProtocolCode actualResult = this.clientSpinRequestHlr.checkClientSpinRequest(clientSpinRequest);
        ConstMathCommon.TableProtocolCode expectResult = ConstMathCommon.TableProtocolCode.SLOT_SPIN_TYPE_NOT_EXIST;

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定錯誤押注玩法類型，回傳 押注玩法類型錯誤
    @Test
    void test_given_nonExistBetSpinType_when_checkClientSpinRequest_then_return_slotBetSpinTypeNotExist() {
        // 1. given
        Mockito.when(this.mockConfig.getBetSpinConfigCluster()).thenReturn(new BetSpinConfigCluster(new ArrayList<>(){{add(1.0);}}, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new HashMap<>(){
            {
                put(ConstMathSlot.BetType.NONE, new HashMap<>(){
                    {
                        put(ConstMathSlot.SpinType.NORMAL, new BetSpinConfig(
                                ConstMathSlot.BetSpinType.NONE_NORMAL,
                                new BetSpinConfigExtendNoneNormal(),
                                new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(1.0, 1.0))
                        ));
                    }
                });
            }
        }));
        Mockito.when(this.mockConfig.getBetTypeList()).thenReturn(new ArrayList<>(){{add(ConstMathSlot.BetType.NONE);}});
        Mockito.when(this.mockConfig.getSpinTypeList()).thenReturn(new ArrayList<>(){{add(ConstMathSlot.SpinType.NORMAL);}});
        this.clientSpinRequestHlr = new ClientSpinRequestHlr(mockConfig, mockTableUtil);
        ClientSpinRequest clientSpinRequest = new ClientSpinRequest(1.0, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, ConstMathSlot.BetSpinType.INVALID, new BetSpinTypeExtendNoneNormal());

        // 2. when
        ConstMathCommon.TableProtocolCode actualResult = this.clientSpinRequestHlr.checkClientSpinRequest(clientSpinRequest);
        ConstMathCommon.TableProtocolCode expectResult = ConstMathCommon.TableProtocolCode.SLOT_BET_SPIN_TYPE_NOT_EXIST;

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定正確押注，回傳 押注正確
    @Test
    void test_given_nonExistBetSpinType_when_checkClientSpinRequest_then_return_success() {
        // 1. given
        Mockito.when(this.mockConfig.getBetSpinConfigCluster()).thenReturn(new BetSpinConfigCluster(new ArrayList<>(){{add(1.0);}}, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new HashMap<>(){
            {
                put(ConstMathSlot.BetType.NONE, new HashMap<>(){
                    {
                        put(ConstMathSlot.SpinType.NORMAL, new BetSpinConfig(
                                ConstMathSlot.BetSpinType.NONE_NORMAL,
                                new BetSpinConfigExtendNoneNormal(),
                                new PaymentConfig(ConstMathSlot.PaymentType.RATIO, new PaymentConfigExtendRatio(1.0, 1.0))
                        ));
                    }
                });
            }
        }));
        Mockito.when(this.mockConfig.getBetTypeList()).thenReturn(new ArrayList<>(){{add(ConstMathSlot.BetType.NONE);}});
        Mockito.when(this.mockConfig.getSpinTypeList()).thenReturn(new ArrayList<>(){{add(ConstMathSlot.SpinType.NORMAL);}});
        this.clientSpinRequestHlr = new ClientSpinRequestHlr(mockConfig, mockTableUtil);
        ClientSpinRequest clientSpinRequest = new ClientSpinRequest(1.0, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, ConstMathSlot.BetSpinType.NONE_NORMAL, new BetSpinTypeExtendNoneNormal());

        // 2. when
        ConstMathCommon.TableProtocolCode actualResult = this.clientSpinRequestHlr.checkClientSpinRequest(clientSpinRequest);
        ConstMathCommon.TableProtocolCode expectResult = ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}