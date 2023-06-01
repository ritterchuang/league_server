package org.lsj.gs.math.entity;

import java.util.List;

// Ng spin 輸入資料
public class CmdIn_NgSpin {
    private int chance; // 目前設定機率(無效)
    private int chance_level; // 目前設定機率起伏值(無效).
    private List<Integer> strip; // [5]自然機率：Strip[0]~Strip[4]:-1; Debug：Strip[0]~Strip[4]:轉輪表輪子編號(無效)
    private int lines; // 押線數
    private int line_bet; // 單線押分
    private boolean debug_hit_jpg; // Debug強制中JPG,需要畫面有WD圖騰才會觸發(無效)

    public CmdIn_NgSpin(int chance, int chance_level, List<Integer> strip, int lines, int line_bet, boolean debug_hit_jpg) {
        this.chance = chance;
        this.chance_level = chance_level;
        this.strip = strip;
        this.lines = lines;
        this.line_bet = line_bet;
        this.debug_hit_jpg = debug_hit_jpg;
    }

    public int getChance() {
        return chance;
    }

    public int getChance_level() {
        return chance_level;
    }

    public List<Integer> getStrip() {
        return strip;
    }

    public int getLines() {
        return lines;
    }

    public int getLine_bet() {
        return line_bet;
    }

    public boolean isDebug_hit_jpg() {
        return debug_hit_jpg;
    }
}
