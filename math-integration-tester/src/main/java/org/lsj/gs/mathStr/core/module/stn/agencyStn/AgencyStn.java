package org.lsj.gs.mathStr.core.module.stn.agencyStn;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.config.entity.GameCenterMgrConfig;
import org.lsj.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResult;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap.StnTemplateMapField;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap.StnTemplateMapGame;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap.StnTemplateMapPlayer;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.stnTemplateMap.StnTemplateMapPoolType;

// 代理統計者
public class AgencyStn {
    private final AgencyStnConfig config; // 統計者設定
    private final AgencyStnUtr agencyStnUtr; // 代理統計更新者
    private final StnTotal stnTotal; // 總體統計
    private final StnTemplateMapGame stnGame; // 遊戲統計
    private final StnTemplateMapField stnField; // 房間統計
    private final StnTemplateMapPlayer stnPlayer; // 玩家統計
    private final StnTemplateMapPoolType stnPoolType; // 水池類型統計
    private final StnRawData stnRawData; // 原始資料統計
    private final StnExtend stnExtend; // 客製統計

    public AgencyStn(AgencyStnConfig config, GameCenterMgrConfig validGameCenterMgrConfig) {
        this.config = config;
        this.agencyStnUtr = new AgencyStnUtr();
        this.stnTotal = new StnTotal(config);
        this.stnGame = new StnTemplateMapGame(config);
        this.stnField = new StnTemplateMapField(config);
        this.stnPlayer = new StnTemplateMapPlayer(config);
        this.stnPoolType = new StnTemplateMapPoolType(config);
        this.stnRawData = new StnRawData(config);
        this.stnExtend = new StnExtend(config, validGameCenterMgrConfig);
    }

    // 更新統計陣列資訊
    public void update(PlayStrResult playStrResult) {
        this.agencyStnUtr.updateStn(this.stnTotal, playStrResult);
        this.agencyStnUtr.updateStn(this.stnGame, playStrResult);
        this.agencyStnUtr.updateStn(this.stnField, playStrResult);
        this.agencyStnUtr.updateStn(this.stnPlayer, playStrResult);
        this.agencyStnUtr.updateStn(this.stnPoolType, playStrResult);
        this.agencyStnUtr.updateStn(this.stnRawData, playStrResult);
        this.agencyStnUtr.updateStn(this.stnExtend, playStrResult);
    }

    // 印出統計資訊
    public void print() {
        this.stnTotal.print();
        this.stnGame.print();
        this.stnField.print();
        this.stnPlayer.print();
        this.stnPoolType.print();
        this.stnRawData.print();
        this.stnExtend.print();
    }

    // 取得當前公司利潤率
    public double getCurrentCompanyProfitRate(){
        return this.stnTotal.getCurrentCompanyProfitRate();
    }
}
