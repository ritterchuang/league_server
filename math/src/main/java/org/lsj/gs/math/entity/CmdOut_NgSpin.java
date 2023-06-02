package org.lsj.gs.math.entity;

import java.util.List;

public class CmdOut_NgSpin {
    private List<Integer> reels; // [15]每局產出之轉輪結果.
    //  0  1  2  3  4
    //  5  6  7  8  9
    // 10 11 12 13 14

    private List<LinePassReelIndex> line_pass_symb;  // 每線經過的SYMB，不亮的SYMB傳-1，有中獎才傳
    private int round_win; // 總贏分 (不含 jp win)
//    private int win_type; // 中獎狀態 (參照enum EWinType) TODO 未知先不做
//    private List<Integer> prize_list; // [EPrizeList_COUNT]大牌表 TODO 未知先不做
    private int wheel_acc_value; // 轉輪當前收集數量
    private int wheel_acc_max; // 轉輪收集觸頂值
//    private List<Long> jp_value; // [4]JP分數 (最大押分) TODO 未知先不做
//    private List<Integer> reels_select; // [15]，二選一不中獎之轉輪結果 TODO 未知先不做
//    private CounterInfo counter_info; //轉數表 TODO 未知先不做


    public CmdOut_NgSpin(List<Integer> reels, List<LinePassReelIndex> line_pass_symb, int round_win, int wheel_acc_value, int wheel_acc_max) {
        this.reels = reels;
        this.line_pass_symb = line_pass_symb;
        this.round_win = round_win;
        this.wheel_acc_value = wheel_acc_value;
        this.wheel_acc_max = wheel_acc_max;
    }

    public List<Integer> getReels() {
        return reels;
    }

    public List<LinePassReelIndex> getLine_pass_symb() {
        return line_pass_symb;
    }

    public int getRound_win() {
        return round_win;
    }

    public int getWheel_acc_value() {
        return wheel_acc_value;
    }

    public int getWheel_acc_max() {
        return wheel_acc_max;
    }
}
