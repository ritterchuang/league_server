package org.lsj.gs.mathStr.core.module.stn.agencyStn;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.config.entity.GameCenterMgrConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend.GameStnExtend;
import org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend.GameStnExtendFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 客製統計者
public class StnExtend extends AbstractStn {
    private final GameCenterMgrConfig validGameCenterMgrConfig; // 有效的遊戲中心管理器設定
    private final GameStnExtendFactory gameStnExtendFactory; // 客製遊戲統記者工廠
    private final Map<Integer, GameStnExtend> gameStnExtendMap; // 各客製統計對應表 <房間索引, 客製遊戲統計>

    public StnExtend(AgencyStnConfig config, GameCenterMgrConfig validGameCenterMgrConfig) {
        super(config);
        this.validGameCenterMgrConfig = validGameCenterMgrConfig;
        this.gameStnExtendFactory = new GameStnExtendFactory(config);
        this.gameStnExtendMap = this.createGameStnExtendMap();
    }

    // 建立各客製統計對應表
    private Map<Integer, GameStnExtend> createGameStnExtendMap(){
        Map<Integer, GameStnExtend> result = new HashMap<>();

        Arrays.stream(this.validGameCenterMgrConfig.getPlayGameFieldConfigArray()).forEach(playGameFieldConfig ->
                result.put(playGameFieldConfig.getTableFieldConfig().getFieldIndex(),
                        this.gameStnExtendFactory.createGameStnExtend(playGameFieldConfig.getTableFieldConfig().getGameId()))
        );

        return result;
    }

    // 更新統計物件
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        this.gameStnExtendMap.get(boardGtrResult.getStnResult().getFieldIndex()).update(boardGtrResult);
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        this.gameStnExtendMap.get(boardGtrReturnResult.getStnResult().getFieldIndex()).updateReturnResult(boardGtrReturnResult);
    }

    // 印出統計資訊
    @Override
    public void print() {
        this.gameStnExtendMap.forEach(this::printGameStnExtend);
    }

    private void printGameStnExtend(int fieldIndex, GameStnExtend gameStnExtend){
        super.printTitle("客製遊戲統計", fieldIndex);
        gameStnExtend.print();
    }
}
