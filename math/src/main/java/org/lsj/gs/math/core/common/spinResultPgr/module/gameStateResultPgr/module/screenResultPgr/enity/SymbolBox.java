package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity;

// 客端標誌物件
public class SymbolBox {
    private final int id;   // 標誌輪帶表位置識別碼 positionId
    private final int res;  // 標誌識別碼 symbolId
    private final int g;    // 標誌組別識別碼 groupId
    private final int w;    // 標誌寬度 width
    private final int h;    // 標誌高度 height
    private final int x;    // 標誌 X 座標
    private final int y;    // 標誌 Y 座標

    public SymbolBox(int id, int res, int g, int w, int h, int x, int y) {
        this.id = id;
        this.res = res;
        this.g = g;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
    }

    // 預設 1*1 標誌
    public SymbolBox(int id, int res, int x) {
        this.id = id;
        this.res = res;
        this.g = id;
        this.w = 1;
        this.h = 1;
        this.x = x;
        this.y = id;
    }

    public SymbolBox() {
        this.id = 0;
        this.res = 0;
        this.g = 0;
        this.w = 0;
        this.h = 0;
        this.x = 0;
        this.y = 0;
    }

    public int getId() {
        return id;
    }

    public int getRes() {
        return res;
    }

    public int getG() {
        return g;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
