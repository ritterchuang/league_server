package org.lsj.gs.mathStr.core.module.stn.agencyStn;


import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.utils.StringUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

// 原始資料統計者
public class StnRawData extends AbstractStn {
    private static final String OUTPUT_PATH = "./lx-math-integration-tester/src/main/java/com/lx/gs/mathStr/output/rawData.csv"; //檔案輸出路徑
    private int currentRecordCount = 0;

    public StnRawData(AgencyStnConfig config) {
        super(config);
    }

    // 更新統計資訊
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        if(!super.getConfig().isOutputRawData()){
            return;
        }

        try {
            // 1. 輸出標題
            if(this.currentRecordCount == 0){
                this.addTitle();
            }

            // 2. 新增紀錄
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH, true));
            bw.newLine();
            bw.write("0" + "," +
                    boardGtrResult.getGameBetLogResult().getGameBetLogObj().getUid() + "," +
                    boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore() + "," +
                    boardGtrResult.getGameBetLogResult().getGameBetLogObj().getFee() + "," +
                    boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet() + "," +
                    ((!Objects.isNull(boardGtrResult.getGameResultExtend())) ? boardGtrResult.getGameResultExtend() : StringUtil.getInstance().getEmptyString()));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. 增加紀錄數
        this.currentRecordCount++;
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        if(!super.getConfig().isOutputRawData()){
            return;
        }

        if (boardGtrReturnResult.getReturnResult().isEmpty()) {
            return;
        }

        try {
            // 1. 輸出標題
            if(this.currentRecordCount == 0){
                this.addTitle();
            }

            // 2. 新增紀錄
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH, true));
            bw.newLine();
            bw.write("0" + "," +
                    boardGtrReturnResult.getReturnResult().get().getGameBetLogObj().getUid() + "," +
                    boardGtrReturnResult.getReturnResult().get().getGameBetLogObj().getScore() + "," +
                    boardGtrReturnResult.getReturnResult().get().getGameBetLogObj().getFee() + "," +
                    boardGtrReturnResult.getReturnResult().get().getGameBetLogObj().getValidBet() + "," +
                    "");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. 增加紀錄數
        this.currentRecordCount++;
    }

    // 印出統計資訊
    @Override
    public void print() {}

    private void addTitle(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH));
            bw.write("chair,uid,score,fee,validBets,customResult");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
