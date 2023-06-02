package org.lsj.gs.mathStr.core.entity;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.mathStr.config.entity.GamePlayerConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.user.IUser;

import java.util.Optional;

// 模擬用遊戲玩家
public class GamePlayerSimulation {
    private final GamePlayerConfig config; // 當前模擬器玩家設定
    private final IUser user; // 使用者資訊
    private int playedBoard; // 已遊戲局數

    public GamePlayerSimulation(GamePlayerConfig config, IUser user) {
        this.config = config;
        this.user = user;
        this.playedBoard = 0;
    }

    // 更新
    public void update(BoardGtrResult boardGtrResult){
        // 1. 更新已遊戲局數
        this.playedBoard += 1;

        // 2. 更新玩家餘額
        this.user.setBalance(this.user.getBalance() + boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore());
    }

    // 返還更新
    public void updateReturnResult(Optional<IGameBetLogResultFish> returnResult) {
        if (returnResult.isEmpty()) {
            return;
        }

        this.user.setBalance(this.user.getBalance() + returnResult.get().getGameBetLogObj().getScore());
    }

    // 判斷能否繼續執行遊戲
    public boolean canPlay(double limitMin){
        return ((this.user.getBalance() >= limitMin) &&
                (this.playedBoard < this.config.getMaxBoard()));
    }

    public IUser getUser() {
        return user;
    }
}
