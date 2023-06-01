package org.lsj.gs.math.games.lhd_java.module.state;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.table.entity.detail.DetailLhCard;
import org.lsj.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenAward;
import org.lsj.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenSettlement;
import org.lsj.gs.math.games.lhd_java.TableLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.data.StcGameResultDataAreaScoreLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.data.StcGameResultDataMyScoreLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.data.StcGameResultDataRichScoreLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.message.StcGameResultLhdJava;
import org.lsj.gs.math.games.lhd_java.module.gameAdjust.GameAdjustLhdJava;
import org.lsj.gs.math.games.lhd_java.module.logic.LogicLhdJava;
import org.lsj.gs.math.games.lhd_java.module.robotLogic.RobotLogicLhdJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 遊戲結束狀態
public class StateGameEndLhdJava extends AbstractStateLhdJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndLhdJava.class);
    public StateGameEndLhdJava(TableLhdJava table, LogicLhdJava logic, RobotLogicLhdJava aiLogic, GameAdjustLhdJava gameAdjust, int stateId) {
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

        // 6. 更新所有玩家 剩餘金額
        super.table.getGamePlayerHlr().updateAllPlayerAfterMoney(uidScoreMap);

        // 7. 傳送遊戲結果
        super.table.sendMessageToHumanPlayer(this.calculateGameResultWithAfterMoney());

        // 8. 傳送玩家餘額
        super.table.sendUpdateUser(super.logic.getHumanUid(), MathUtil.moneyScale(super.table.getHumanGamePlayer().getAfterMoney()));

        // 9. 更新路線圖
        super.table.updateChart(super.logic.getStackMap());

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

        // 2. 新增龍虎牌
        super.logic.addDetail(new DetailLhCard(
                new int[]{ super.logic.getAllAreaCardNumberArray()[0]},
                new int[]{ super.logic.getAllAreaCardNumberArray()[1]}));

        // 3. 新增派獎
        Map<Integer, Double> playerAreaScoreMap = super.logic.calculatePlayerAreaScoreMap(super.logic.getHumanChairIndex());
        for (Map.Entry<Integer, Double> playerAreaScoreEntry: playerAreaScoreMap.entrySet()) {
            super.logic.addDetailBaiRen(new DetailPlayBaiRenAward(
                    super.table.calculateSpendSec(),
                    playerAreaScoreEntry.getKey(),
                    playerAreaScoreEntry.getValue()
            ));
        }

        // 4. 新增結算
        UidScore humanUidScore = super.logic.getHumanUidScore();
        super.logic.addDetailBaiRen(new DetailPlayBaiRenSettlement(
                super.table.calculateSpendSec(),
                (Objects.nonNull(humanUidScore)) ? super.logic.getHumanUidScore().getScore() : 0.0));
    }


    // 計算遊戲結果
    private StcGameResultLhdJava calculateGameResultWithAfterMoney() {
        // 1. 計算得分區域
        int winAreaID = ConstLhdJava.BetAreaIdLhdJava.calculateWinBetAreaIdLhdJava(super.logic.getStackMap()).getCode();

        // 2. 計算贏分的總分
        double winScore = super.logic.getAllWinScore();

        // 3. 計算真人玩家得分
        StcGameResultDataMyScoreLhdJava myScore = this.calculateAfterMyScore();

        // 4. 計算榜單玩家得分陣列
        StcGameResultDataRichScoreLhdJava[] richScores = this.calculateAfterRichScore();

        // 5. 計算幸運星玩家得分陣列
        StcGameResultDataRichScoreLhdJava[] luckyStarScores = new StcGameResultDataRichScoreLhdJava[]{};

        // 6. 計算卡牌; [龍, 虎]
        int[] cards = super.logic.getAllAreaCardNumberArray();

        // 7. 計算比分; [龍, 虎]
        int[] compareScore = super.logic.getAllAreaCardPointArray();

        // 8. 計算超時時間
        double time = super.table.getMathConfig().getEndTimeSec();

        return new StcGameResultLhdJava(winAreaID, winScore, myScore, richScores, luckyStarScores, cards, compareScore, time);
    }

    // 計算榜單玩家得分陣列
    private StcGameResultDataRichScoreLhdJava[] calculateAfterRichScore(){
        // 1. 取得榜單玩家
        List<GamePlayer> richPlayerList = super.table.getGamePlayerHlr().getGamePlayerSortByAfterMoneyTopLimitList(6);

        // 2. 取得玩家得分對應表
        Map<Integer, UidScore> uidScoreMap = super.logic.getUidScoreMap();

        // 3. 封裝
        List<StcGameResultDataRichScoreLhdJava> richScoreList = new ArrayList<>();
        for (GamePlayer richPlayer : richPlayerList) {
            UidScore uidScore = uidScoreMap.get(richPlayer.getChairIndex());
            if(Objects.nonNull(uidScore)){
                richScoreList.add(new StcGameResultDataRichScoreLhdJava(
                        richPlayer.getChairIndex(),
                        richPlayer.getUid(),
                        richPlayer.getAfterMoney(),
                        uidScore.getScore(),
                        this.calculateStcGameResultDataAreaScoreLhdJavaArray(richPlayer.getChairIndex())
                ));
            }
        }

        return richScoreList.toArray(StcGameResultDataRichScoreLhdJava[]::new);
    }

    // 計算贏的區域得分
    private StcGameResultDataAreaScoreLhdJava[] calculateStcGameResultDataAreaScoreLhdJavaArray(int chairIndex){
        List<StcGameResultDataAreaScoreLhdJava> winAreaScoreList = new ArrayList<>();

        // 1. 取得玩家得分陣列
        double[] playerAreaScoreArray = super.logic.calculatePlayerAreaScoreArray(chairIndex);

        // 2. 包裝贏的區域贏分
        for (int areaIndex = 0; areaIndex < playerAreaScoreArray.length; areaIndex++) {
            if(playerAreaScoreArray[areaIndex] > 0){
                winAreaScoreList.add(new StcGameResultDataAreaScoreLhdJava(
                        areaIndex,
                        playerAreaScoreArray[areaIndex]
                ));
            }
        }

        return winAreaScoreList.toArray(StcGameResultDataAreaScoreLhdJava[]::new);
    }

    // 計算真人玩家得分
    private StcGameResultDataMyScoreLhdJava calculateAfterMyScore() {
        if(!super.table.isHumanBet()){
            return new StcGameResultDataMyScoreLhdJava(
                    MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                    0.0,
                    new StcGameResultDataAreaScoreLhdJava[]{}
            );
        }

        return new StcGameResultDataMyScoreLhdJava(
                MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                super.logic.getHumanUidScore().getScore(),
                this.calculateStcGameResultDataAreaScoreLhdJava()
        );
    }

    // 計算遊戲結果贏分區域資訊
    private StcGameResultDataAreaScoreLhdJava[] calculateStcGameResultDataAreaScoreLhdJava(){
        // 1. 計算真人區域贏分陣列
        double[] areaScoreArray = super.logic.calculatePlayerAreaScoreArray(super.logic.getHumanChairIndex());

        // 2. 創建遊戲結果區域贏分列表
        List<StcGameResultDataAreaScoreLhdJava> result = new ArrayList<>();

        // 3. 封裝真人贏的區域贏分
        for (int areaIndex = 0; areaIndex < areaScoreArray.length; areaIndex++) {
            if(areaScoreArray[areaIndex] > 0){
                result.add(new StcGameResultDataAreaScoreLhdJava(areaIndex, areaScoreArray[areaIndex]));
            }
        }

        return result.toArray(StcGameResultDataAreaScoreLhdJava[]::new);
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
