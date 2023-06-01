package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig;

public class MegaSymbolBox {
    private final int id; // 標誌識別碼
    private final int x; // 起始 X 座標
    private final int y; // 起始 Y 座標
    private final int w; // 標誌寬度
    private final int h; // 標誌高度

    public MegaSymbolBox(int id, int x, int y, int w, int h) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
