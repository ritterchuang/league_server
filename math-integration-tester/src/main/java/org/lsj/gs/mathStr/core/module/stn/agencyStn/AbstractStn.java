package org.lsj.gs.mathStr.core.module.stn.agencyStn;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.module.stn.IStn;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;

// 抽象統計者
public abstract class AbstractStn implements IStn {
    private final AgencyStnConfig config; // 統計者設定

    public AbstractStn(AgencyStnConfig config) {
        this.config = config;
    }

    public AgencyStnConfig getConfig() {
        return config;
    }

    // 印出標題與統計資訊
    protected void printTitleStn(String title, int stnIndex, TemplateStn stn){
        this.printTitle(title, stnIndex);
        stn.print();
    }

    // 印出標題
    protected void printTitle(String title, int stnIndex){
        System.out.println("[" + title + " " + stnIndex + "]");
    }
}
