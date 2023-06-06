package org.lsj.gs.math.core.common.table;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturn;
import org.lsj.gs.math.entity.CmdOut_NgSpin;
import org.lsj.gs.math.entity.LinePassReelIndex;
import org.lsj.gs.user.IUser;

import java.util.ArrayList;
import java.util.Optional;

// Command 老虎機 遊戲桌封裝器
public class TableCommandSlotWpr implements ISeverTableCommandSlot {
    private final TableCommandSlot table; // 遊戲桌

    public TableCommandSlotWpr(TableCommandSlot table) {
        this.table = table;
    }

    @Override
    public void startBeforeGameEnd() {
        this.table.startBeforeGameEnd();
    }

    @Override
    public MathSlotConfig getMathSlotConfig() {
        return this.table.getMathSlotConfig();
    }

    @Override
    public IPoolConfig getPoolConfig() {
        return this.table.getPoolConfig();
    }

    @Override
    public void updatePoolConfig(IPoolConfig poolConfig) {
        this.table.updatePoolConfig(poolConfig);
    }

    @Override
    public IGameBetLogResultSlot getSpinResult(String ctsSpinRequestString) throws TableException {
        return this.table.getSpinResult(ctsSpinRequestString);
    }

    // TODO 應急用
    @Override
    public CmdOut_NgSpin getSpinResult2(String ctsSpinRequestString){
        // TODO 先寫假資料
        CmdOut_NgSpin cmdOut_ngSpin = new CmdOut_NgSpin(
                new ArrayList<>(){{
                    add(1);add(1);add(1);add(1);add(1);
                    add(2);add(2);add(2);add(2);add(2);
                    add(3);add(3);add(3);add(3);add(3);
                }},
                new ArrayList<>(){{
                    add(new LinePassReelIndex(
                            new ArrayList<>(){{add(2);add(2);add(2);add(2);add(2);}},
                            2,
                            2,
                            20
                    ));
                }},
                20,
                3,
                5
        );

        return cmdOut_ngSpin;
    }

    @Override
    public void sendCmdOutResultToHumanPlayer(){
        this.table.sendCmdOutResultToHumanPlayer();
    }

    @Override
    public void sendSpinResultToHumanPlayer(){
        this.table.sendSpinResultToHumanPlayer();
    }

    @Override
    public double getSpinCost(String ctsSpinRequestString) throws TableException {
        return this.table.getSpinCost(ctsSpinRequestString);
    }

    @Override
    public void updateLastPlayedProgressResult(String ctsLastPlayedProgressResult) throws TableException {
        this.table.updateLastPlayedProgressResult(ctsLastPlayedProgressResult);
    }

    @Override
    public void updateUser(IUser user) {
        this.table.updateUser(user);
    }

    @Override
    public Optional<SlotCutReturn> getSlotCutReturn() {
        return this.table.getSlotCutReturn();
    }

    @Override
    public void setRngData(String rngDataString) {
        this.table.setRngData(rngDataString);
    }
}
