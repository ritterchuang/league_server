package org.lsj.gs.math.config.entity.fieldConfig;

// 臨時房間設定
public class FieldConfigTemp {
    public int fid; // 房間ID
    public String fname; // 房間名
    public String fnameCN; // 房間中文名
    public double base; // 底注
    public int min; // 入桌下限
    public int max; // 入桌上限
    public int play; // 玩法
    public int[] areasTopLimit; // 單點限額列表
    public int[] chips; // 下注籌法列表
    public int rate; // 手續費率
    public int state; // 房間狀態
    public int playerNum; // 當前遊戲人數
    public int gameStage; // 當前遊戲階段
    public long stageLeftTime; // 當前遊戲階段剩餘時間
    public int[] chart; // 百人類走勢圖
    public int[] betTopLimit; // 單點限紅
    public FieldConfigExtend extend; // 額外設定檔
}
