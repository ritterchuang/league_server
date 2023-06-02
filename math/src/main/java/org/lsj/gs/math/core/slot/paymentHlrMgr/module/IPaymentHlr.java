package org.lsj.gs.math.core.slot.paymentHlrMgr.module;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.CreditBetBox;

import java.util.List;

// 收費處理者
public interface IPaymentHlr {

    List<CreditBetBox> calculateCreditBoxList(); // 計算押注成本資訊

    double calculateCreditBase(double credit); // 計算算分基準

    double calculatePlayerCost(double credit); // 計算成本

    double calculatePlayerBet(double credit); // 計算玩家押注
}
