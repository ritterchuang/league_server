package org.lsj.gs.math.core.card.betCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

// 抽象押注計算器
public abstract class AbstractBetCtr extends AbstractModule {
    protected final AbstractBetCtrConfig config; // 設定檔
    protected Map<Integer, List<Integer>> canBetRateListMap; // 可下注倍數 <玩家座位索引, 玩家下注倍數列表>
    protected Map<Integer,Integer> betRateMap; // 下注倍數 <玩家座位索引, 玩家下注倍數>
    protected Map<Integer, Integer> timeOutBetRateMap; // 超時下注倍數 <玩家座位索引, 玩家下注倍數>

    public AbstractBetCtr(AbstractBetCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.reset();
    }

    //* 可下注倍數相關 *//

    // 取得玩家押注列表
    public List<Integer> getPlayerCanBetRateList(int chairIndex) { return this.canBetRateListMap.get(chairIndex); }


    //* 接受下注訊息相關 *//

    // 檢查是否重複下注
    public boolean isPlayerBet(int chairIndex) {
        return !Objects.isNull(this.betRateMap.get(chairIndex));
    }

    // 判斷倍數是否合法
    public boolean isValidBetRate(int chairIndex, int rate) {
        if (Objects.isNull(this.canBetRateListMap.get(chairIndex))) {
            return false;
        }

        return this.canBetRateListMap.get(chairIndex).stream().anyMatch(canBetRate -> canBetRate == rate);
    }

    // 更新下注倍數
    public void setPlayerBetRate(int chairIndex, int rate) {
        this.betRateMap.put(chairIndex, rate);
    }


    //* 下注結果相關 *//

    // 取得玩家押注
    public Map<Integer, Integer> getBetRateMap() {
        return this.betRateMap;
    }

    // 取得所有玩家下注倍數列表
    public int[] getBetResultArray() {
        return IntStream.range(0, this.config.getMaxUser())
                .map(chairId -> this.betRateMap.getOrDefault(chairId, -1))
                .toArray();
    }


    //* 超時下注相關 *//

    // 取得超時表演押注
    public Map<Integer, Integer> getTimeOutBetRateMap() {
        return this.timeOutBetRateMap;
    }

    // 重設
    @Override
    public void reset() {
        this.canBetRateListMap = new HashMap<>();
        this.betRateMap = new HashMap<>();
        this.timeOutBetRateMap = new HashMap<>();
    }
}
