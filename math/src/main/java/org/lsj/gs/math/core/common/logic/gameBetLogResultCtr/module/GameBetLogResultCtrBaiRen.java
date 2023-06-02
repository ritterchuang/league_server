package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.db.GameBetLogObj;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.GameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.entity.detail.DetailRealPlayList;
import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;
import java.util.List;

// 下注記錄計算器百人
public class GameBetLogResultCtrBaiRen extends AbstractGameBetLogResultCtr implements IGameBetLogResultCtrBaiRen {
    private final List<IDetailPlayBaiRen> detailPlayBaiRenList; // 百人遊戲詳細資訊列表

    public GameBetLogResultCtrBaiRen(long beginTime, int tableId, TableFieldConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(beginTime, tableId, config, gamePlayerHlr, poolCtr, tableUtil);
        this.detailPlayBaiRenList = new ArrayList<>();
    }

    // 計算下注紀錄結果
    public IGameBetLogResultBaiRen calculateGameBetLogResult(String roundId, UidScore uidScore) {
        // 1. 計算下注記錄物件
        GameBetLogObj gameBetLogObj = super.calculateGameBetLogObj(roundId, uidScore);

        // 2. 計算百人詳細記錄
        List<Object> detailBaiRenList = this.calculateDetailBaiRenList();

        // 3. 封裝
        return new GameBetLogResultBaiRen(gameBetLogObj, detailBaiRenList, super.poolCtr.getPersonControlLogString(), super.tableUtil.getMainRandomUtil().getRandomNumberUsedList());
    }

    // 新增百人遊戲詳細記錄
    @Override
    public void addDetailPlayBaiRen(IDetailPlayBaiRen detailPlayBaiRen){
        this.detailPlayBaiRenList.add(detailPlayBaiRen);
    }

    // 計算遊戲詳細列表
    private List<Object> calculateDetailBaiRenList() {
        List<Object> result = new ArrayList<>(super.detail);

        // 1. 空值防呆
        if(detailPlayBaiRenList.isEmpty()){
            return result;
        }

        // 2. 新增百人遊戲詳細資訊
        result.add(new DetailRealPlayList(
                super.gamePlayerHlr.getHumanUid(),
                super.gamePlayerHlr.getHumanChairIndex(),
                new ArrayList<>(this.detailPlayBaiRenList)
        ));

        return result;
    }

    // 重設
    @Override
    public void reset(){
        super.reset();
        this.detailPlayBaiRenList.clear();
    }
}
