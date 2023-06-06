package org.lsj.gs.math.core.common.cmdOutResultPgr.entity.cmdOutNgSpin;

// 一般遊戲結果
public class CmdOutNgSpinLucky777 extends AbstractCmdOutNgSpin{
    private final int[] reel_symb; // [9]，每局產出之轉輪結果
                                //  0  1  2
                                //  3  4  5
                                //  6  7  8
    private final double round_win; // 每局總贏分 TODO 客端原本想要int
    private final int[] prize_list; // [12]，大牌表 TODO 不清楚是甚麼
    private final int win_type; // 每局中獎狀態 2: 沒中特殊遊戲 4: 中免費遊戲 8: 中紅利遊戲

    public CmdOutNgSpinLucky777(int[] reel_symb, double round_win, int[] prize_list, int win_type) {
        this.reel_symb = reel_symb;
        this.round_win = round_win;
        this.prize_list = prize_list;
        this.win_type = win_type;
    }

    public int[] getReel_symb() {
        return reel_symb;
    }

    public double getRound_win() {
        return round_win;
    }

    public int[] getPrize_list() {
        return prize_list;
    }

    public int getWin_type() {
        return win_type;
    }
}
