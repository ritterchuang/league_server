package com.lx.gs.math.games.mybjl_java.module.state;

import com.lx.gs.math.core.baiRen.ConstMathBjl;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import com.lx.gs.math.core.common.table.entity.detail.DetailBjlCard;
import com.lx.gs.math.core.common.table.entity.detail.DetailBjlScoreChips;
import com.lx.gs.math.core.common.table.entity.detail.DetailWinAreasBjl;
import com.lx.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenAward;
import com.lx.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenReturn;
import com.lx.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenSettlement;
import com.lx.gs.math.games.mybjl_java.TableMybjlJava;
import com.lx.gs.math.games.mybjl_java.entity.data.StcGameResultDataAreaScoreMybjlJava;
import com.lx.gs.math.games.mybjl_java.entity.data.StcGameResultDataMyScoreMybjlJava;
import com.lx.gs.math.games.mybjl_java.entity.data.StcGameResultDataRichScoreMybjlJava;
import com.lx.gs.math.games.mybjl_java.entity.message.StcGameResultMybjlJava;
import com.lx.gs.math.games.mybjl_java.module.gameAdjust.GameAdjustMybjlJava;
import com.lx.gs.math.games.mybjl_java.module.logic.LogicMybjlJava;
import com.lx.gs.math.games.mybjl_java.module.robotLogic.RobotLogicMybjlJava;
import com.lx.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 遊戲結束狀態
public class StateGameEndMybjlJava extends AbstractStateMybjlJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndMybjlJava.class);
    public StateGameEndMybjlJava(TableMybjlJava table, LogicMybjlJava logic, RobotLogicMybjlJava aiLogic, GameAdjustMybjlJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 計算個人控
        super.table.calculatePersonControlAllResult();
        LOG.info(super.table.getTableLogTitle() + " adjustInfo:{}, personAdjustType:{}, personControlLogString:{}",
                super.table.getPoolCtr().getAdjustInfoString(),
                super.table.getPoolCtr().getPersonAdjustType(),
                super.table.getPoolCtr().getPersonControlLogString());

        // 2. 開啟調控發牌
        this.gameAdjust.startGameAdjust(super.table.isHumanBet());

        // 3. 設定牌型
        super.logic.setStackMap();

        // 4. 計算所有玩家輸贏分
        Map<Integer, UidScore> uidScoreMap = this.logic.calculateUidScoreMap();

        // 5. 設定玩家輸贏分
        super.logic.setUidScoreMap(uidScoreMap);

        // 6. 更新所有玩家起始金額
        super.table.getGamePlayerHlr().updateAllPlayerAfterMoney(uidScoreMap);

        // 7. 傳送遊戲結果
        super.table.sendMessageToHumanPlayer(this.calculateGameResultWithAfterMoney());

        // 8. 傳送玩家餘額
        super.table.sendUpdateUser(super.logic.getHumanUid(), MathUtil.moneyScale(super.table.getHumanGamePlayer().getAfterMoney()));

        // 9. 更新路線圖
        super.table.updateChart(super.logic.calculateWinAreaArray());

        // 10. 傳送路線圖
        super.table.sendChart();

        // 11. 紀錄詳細日誌
        this.addDetail();

        // 12. 替換的機器人
        super.table.getGamePlayerHlr().replaceRobot();
    }

    // 紀錄詳細日誌
    private void addDetail(){
        // 1. 玩家無押注防呆
        if(Objects.isNull(super.logic.getPlayerAreaBetMap(super.logic.getHumanChairIndex()))){
            return;
        }

        // 2. 新增百家牌
        super.logic.addDetail(new DetailBjlCard(
                super.logic.getPlayCardArray(),
                super.logic.getBankCardArray()
        ));

        // 3. 新增派獎區域
        super.logic.addDetail(new DetailWinAreasBjl(
                super.logic.calculateWinAreaArray(),
                super.logic.calculateReturnAreaArray()
        ));

        // 4. 新增派獎
        Map<Integer, Double> playerAreaScoreMap = super.logic.calculatePlayerAreaScoreMap(super.logic.getHumanChairIndex());
        for (Map.Entry<Integer, Double> playerAreaScoreEntry: playerAreaScoreMap.entrySet()) {
            super.logic.addDetailBaiRen(new DetailPlayBaiRenAward(
                    super.table.calculateSpendSec(),
                    playerAreaScoreEntry.getKey(),
                    playerAreaScoreEntry.getValue()
            ));
        }

        // 5. 新增返還
        super.logic.addDetailBaiRen(new DetailPlayBaiRenReturn(
                super.table.calculateSpendSec(),
                super.logic.calculateReturnValue()
        ));

        // 6. 新增所有下注資訊
        super.logic.addDetail(new DetailBjlScoreChips(
                super.logic.getBetTypeChips(ConstMathBjl.BetAreaTypeEnum.BANK),
                super.logic.getBetTypeChips(ConstMathBjl.BetAreaTypeEnum.PLAY),
                super.logic.getBetTypeChips(ConstMathBjl.BetAreaTypeEnum.TIE),
                super.logic.getHumanUidScore().getScore()
        ));

        // 7. 新增結算
        UidScore humanUidScore = super.logic.getHumanUidScore();
        super.logic.addDetailBaiRen(new DetailPlayBaiRenSettlement(
                super.table.calculateSpendSec(),
                (Objects.nonNull(humanUidScore)) ? super.logic.getHumanUidScore().getScore() : 0.0));
    }


    // 計算遊戲結果
    private StcGameResultMybjlJava calculateGameResultWithAfterMoney() {
        // 1. 計算得分區域
        int[] winAreas = super.logic.calculateWinAreaArray();

        // 2. 計算返還區域
        int[] returnAreas = super.logic.calculateReturnAreaArray();

        // 3. 計算贏分的總分
        double winScore = super.logic.getAllWinScore();

        // 4. 計算真人玩家得分
        StcGameResultDataMyScoreMybjlJava myScore = this.calculateAfterMyScore();

        // 5. 計算榜單玩家得分陣列
        StcGameResultDataRichScoreMybjlJava[] richScores = this.calculateAfterRichScore();

        // 6. 計算幸運星玩家得分陣列
        StcGameResultDataRichScoreMybjlJava[] luckyStarScores = new StcGameResultDataRichScoreMybjlJava[]{};

        // 7. 取得閒牌
        int[] playCards = super.logic.getPlayCardArray();

        // 8. 取得庄牌
        int[] bankCards = super.logic.getBankCardArray();

        // 9. 計算依序點數
        int[][] compareScore = super.logic.calculateAccumulateAreaPoint2DArray();

        // 10. 計算超時時間
        double time = super.table.getMathConfig().getEndTimeSec();

        return new StcGameResultMybjlJava(winAreas, returnAreas, winScore, myScore, richScores, luckyStarScores, playCards, bankCards, compareScore, time);
    }

    // 計算榜單玩家得分陣列
    private StcGameResultDataRichScoreMybjlJava[] calculateAfterRichScore(){
        // 1. 取得榜單玩家
        List<GamePlayer> richPlayerList = super.table.getGamePlayerHlr().getGamePlayerSortByAfterMoneyTopLimitList(6);

        // 2. 取得玩家得分對應表
        Map<Integer, UidScore> uidScoreMap = super.logic.getUidScoreMap();

        // 3. 封裝
        List<StcGameResultDataRichScoreMybjlJava> richScoreList = new ArrayList<>();
        for (GamePlayer richPlayer : richPlayerList) {
            UidScore uidScore = uidScoreMap.get(richPlayer.getChairIndex());
            if(Objects.nonNull(uidScore)){
                richScoreList.add(new StcGameResultDataRichScoreMybjlJava(
                        richPlayer.getChairIndex(),
                        richPlayer.getUid(),
                        richPlayer.getAfterMoney(),
                        uidScore.getScore(),
                        this.calculateStcGameResultDataAreaScoreArray(richPlayer.getChairIndex())
                ));
            }
        }

        return richScoreList.toArray(StcGameResultDataRichScoreMybjlJava[]::new);
    }

    // 計算贏的區域得分
    private StcGameResultDataAreaScoreMybjlJava[] calculateStcGameResultDataAreaScoreArray(int chairIndex){
        List<StcGameResultDataAreaScoreMybjlJava> winAreaScoreList = new ArrayList<>();

        // 1. 取得玩家得分陣列
        double[] playerAreaScoreArray = super.logic.calculatePlayerAreaScoreArray(chairIndex);

        // 2.
        for (int areaIndex = 0; areaIndex < playerAreaScoreArray.length; areaIndex++) {
            if(playerAreaScoreArray[areaIndex] > 0){
                winAreaScoreList.add(new StcGameResultDataAreaScoreMybjlJava(
                        areaIndex,
                        playerAreaScoreArray[areaIndex]
                ));
            }
        }

        return winAreaScoreList.toArray(StcGameResultDataAreaScoreMybjlJava[]::new);
    }

    // 計算真人玩家得分
    private StcGameResultDataMyScoreMybjlJava calculateAfterMyScore() {
        if(!super.table.isHumanBet()){
            return new StcGameResultDataMyScoreMybjlJava(
                    MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                    0.0,
                    new StcGameResultDataAreaScoreMybjlJava[]{}
            );
        }

        return new StcGameResultDataMyScoreMybjlJava(
                MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                super.logic.getHumanUidScore().getScore(),
                this.calculateStcGameResultDataAreaScore()
        );
    }

    // 計算遊戲結果贏分區域資訊
    private StcGameResultDataAreaScoreMybjlJava[] calculateStcGameResultDataAreaScore(){
        // 1. 計算真人區域贏分陣列
        double[] areaScoreArray = super.logic.calculatePlayerAreaScoreArray(super.logic.getHumanChairIndex());

        // 2. 創建遊戲結果區域贏分列表
        List<StcGameResultDataAreaScoreMybjlJava> result = new ArrayList<>();

        // 3. 封裝真人贏的區域贏分
        for (int areaIndex = 0; areaIndex < areaScoreArray.length; areaIndex++) {
            if(areaScoreArray[areaIndex] > 0){
                result.add(new StcGameResultDataAreaScoreMybjlJava(areaIndex, areaScoreArray[areaIndex]));
            }
        }

        return result.toArray(StcGameResultDataAreaScoreMybjlJava[]::new);
    }

    @Override
    public void onLeave() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 清空牌桌資訊
        super.table.reset();
    }

    @Override
    public void onTimeout() {
        super.iLeave();
    }
}
