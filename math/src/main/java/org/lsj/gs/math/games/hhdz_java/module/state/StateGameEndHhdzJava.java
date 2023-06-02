package org.lsj.gs.math.games.hhdz_java.module.state;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.table.entity.detail.DetailJhCard;
import org.lsj.gs.math.core.common.table.entity.detail.DetailWinAreas;
import org.lsj.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenAward;
import org.lsj.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenSettlement;
import org.lsj.gs.math.games.hhdz_java.TableHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.data.StcGameResultDataAreaScoreHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.data.StcGameResultDataMyScoreHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.data.StcGameResultDataRichScoreHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.message.StcGameResultHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.gameAdjust.GameAdjustHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.logic.LogicHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.robotLogic.RobotLogicHhdzJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 遊戲結束狀態
public class StateGameEndHhdzJava extends AbstractStateHhdzJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndHhdzJava.class);
    public StateGameEndHhdzJava(TableHhdzJava table, LogicHhdzJava logic, RobotLogicHhdzJava aiLogic, GameAdjustHhdzJava gameAdjust, int stateId) {
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

        // 2. 新增紅黑牌
        super.logic.addDetail(new DetailJhCard(
                super.logic.getBlackCardArray(),
                super.logic.getRedCardArray(),
                super.logic.getAreaStackArray()[ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()],
                super.logic.getAreaStackArray()[ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()]
        ));

        // 3. 新增派獎區域
        super.logic.addDetail(new DetailWinAreas(
                super.logic.calculateWinAreaArray()
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

        // 5. 新增結算
        UidScore humanUidScore = super.logic.getHumanUidScore();
        super.logic.addDetailBaiRen(new DetailPlayBaiRenSettlement(
                super.table.calculateSpendSec(),
                (Objects.nonNull(humanUidScore)) ? super.logic.getHumanUidScore().getScore() : 0.0));
    }


    // 計算遊戲結果
    private StcGameResultHhdzJava calculateGameResultWithAfterMoney() {
        // 1. 計算得分區域
        int[] winAreas = super.logic.calculateWinAreaArray();

        // 2. 計算贏分的總分
        double winScore = super.logic.getAllWinScore();

        // 3. 計算真人玩家得分
        StcGameResultDataMyScoreHhdzJava myScore = this.calculateAfterMyScore();

        // 4. 計算榜單玩家得分陣列
        StcGameResultDataRichScoreHhdzJava[] richScores = this.calculateAfterRichScore();

        // 5. 計算幸運星玩家得分陣列
        StcGameResultDataRichScoreHhdzJava[] luckyStarScores = new StcGameResultDataRichScoreHhdzJava[]{};

        // 6. 計算紅牌
        int[] redCards = super.logic.getAllAreaCardNumber2dArray()[ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()];

        // 7. 計算黑牌
        int[] blackCards = super.logic.getAllAreaCardNumber2dArray()[ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()];

        // 8. 計算牌型
        int[] types = super.logic.getAreaStackArray();

        // 9. 計算超時時間
        double time = super.table.getMathConfig().getEndTimeSec();

        return new StcGameResultHhdzJava(winAreas, winScore, myScore, richScores, luckyStarScores, redCards, blackCards, types, time);
    }

    // 計算榜單玩家得分陣列
    private StcGameResultDataRichScoreHhdzJava[] calculateAfterRichScore(){
        // 1. 取得榜單玩家
        List<GamePlayer> richPlayerList = super.table.getGamePlayerHlr().getGamePlayerSortByAfterMoneyTopLimitList(6);

        // 2. 取得玩家得分對應表
        Map<Integer, UidScore> uidScoreMap = super.logic.getUidScoreMap();

        // 3. 封裝
        List<StcGameResultDataRichScoreHhdzJava> richScoreList = new ArrayList<>();
        for (GamePlayer richPlayer : richPlayerList) {
            UidScore uidScore = uidScoreMap.get(richPlayer.getChairIndex());
            if(Objects.nonNull(uidScore)){
                richScoreList.add(new StcGameResultDataRichScoreHhdzJava(
                        richPlayer.getChairIndex(),
                        richPlayer.getUid(),
                        richPlayer.getAfterMoney(),
                        uidScore.getScore(),
                        this.calculateStcGameResultDataAreaScoreArray(richPlayer.getChairIndex())
                ));
            }
        }

        return richScoreList.toArray(StcGameResultDataRichScoreHhdzJava[]::new);
    }

    // 計算贏的區域得分
    private StcGameResultDataAreaScoreHhdzJava[] calculateStcGameResultDataAreaScoreArray(int chairIndex){
        List<StcGameResultDataAreaScoreHhdzJava> winAreaScoreList = new ArrayList<>();

        // 1. 取得玩家得分陣列
        double[] playerAreaScoreArray = super.logic.calculatePlayerAreaScoreArray(chairIndex);

        // 2.
        for (int areaIndex = 0; areaIndex < playerAreaScoreArray.length; areaIndex++) {
            if(playerAreaScoreArray[areaIndex] > 0){
                winAreaScoreList.add(new StcGameResultDataAreaScoreHhdzJava(
                        areaIndex,
                        playerAreaScoreArray[areaIndex]
                ));
            }
        }

        return winAreaScoreList.toArray(StcGameResultDataAreaScoreHhdzJava[]::new);
    }

    // 計算真人玩家得分
    private StcGameResultDataMyScoreHhdzJava calculateAfterMyScore() {
        if(!super.table.isHumanBet()){
            return new StcGameResultDataMyScoreHhdzJava(
                    MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                    0.0,
                    new StcGameResultDataAreaScoreHhdzJava[]{}
            );
        }

        return new StcGameResultDataMyScoreHhdzJava(
                MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                super.logic.getHumanUidScore().getScore(),
                this.calculateStcGameResultDataAreaScore()
        );
    }

    // 計算遊戲結果贏分區域資訊
    private StcGameResultDataAreaScoreHhdzJava[] calculateStcGameResultDataAreaScore(){
        // 1. 計算真人區域贏分陣列
        double[] areaScoreArray = super.logic.calculatePlayerAreaScoreArray(super.logic.getHumanChairIndex());

        // 2. 創建遊戲結果區域贏分列表
        List<StcGameResultDataAreaScoreHhdzJava> result = new ArrayList<>();

        // 3. 封裝真人贏的區域贏分
        for (int areaIndex = 0; areaIndex < areaScoreArray.length; areaIndex++) {
            if(areaScoreArray[areaIndex] > 0){
                result.add(new StcGameResultDataAreaScoreHhdzJava(areaIndex, areaScoreArray[areaIndex]));
            }
        }

        return result.toArray(StcGameResultDataAreaScoreHhdzJava[]::new);
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
