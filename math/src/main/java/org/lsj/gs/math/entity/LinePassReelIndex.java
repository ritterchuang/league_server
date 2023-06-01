package org.lsj.gs.math.entity;

import java.util.List;

// 中獎資訊
public class LinePassReelIndex {
    private List<Integer> symb_index; // [5]，每線經過的SYMB，預設值-1
    private int line_index; // 第幾條線
    private int line_symb; // 中獎SYMBOL
    private long line_win; // 線獎贏分

    public LinePassReelIndex(List<Integer> symb_index, int line_index, int line_symb, long line_win) {
        this.symb_index = symb_index;
        this.line_index = line_index;
        this.line_symb = line_symb;
        this.line_win = line_win;
    }

    public List<Integer> getSymb_index() {
        return symb_index;
    }

    public int getLine_index() {
        return line_index;
    }

    public int getLine_symb() {
        return line_symb;
    }

    public long getLine_win() {
        return line_win;
    }
}
