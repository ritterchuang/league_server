package org.lsj.gs.math.games.brnn_java.module.state;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenAward;
import org.lsj.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenSettlement;
import org.lsj.gs.math.games.brnn_java.TableBrnnJava;
import org.lsj.gs.math.games.brnn_java.entity.data.StcGameResultDataAreaScoreBrnnJava;
import org.lsj.gs.math.games.brnn_java.entity.data.StcGameResultDataMyScoreBrnnJava;
import org.lsj.gs.math.games.brnn_java.entity.data.StcGameResultDataRichScoreBrnnJava;
import org.lsj.gs.math.games.brnn_java.entity.detail.*;
import org.lsj.gs.math.games.brnn_java.entity.detail.*;
import org.lsj.gs.math.games.brnn_java.entity.message.StcGameResultBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.gameAdjust.GameAdjustBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.logic.LogicBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.robotLogic.RobotLogicBrnnJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 遊戲結束狀態
public class StateGameEndBrnnJava extends AbstractStateBrnnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndBrnnJava.class);
    public StateGameEndBrnnJava(TableBrnnJava table, LogicBrnnJava logic, RobotLogicBrnnJava aiLogic, GameAdjustBrnnJava gameAdjust, int stateId) {
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

    // 計算遊戲結果
    private StcGameResultBrnnJava calculateGameResultWithAfterMoney() {
        // 1. 得分區域
        int[] winAreas = new int[]{};

        // 2. 計算贏分的總分
        double winScore = super.logic.getAllWinScore();

        // 3. 計算真人玩家得分
        StcGameResultDataMyScoreBrnnJava myScore = this.calculateAfterMyScore();

        // 4. 計算榜單玩家得分陣列
        StcGameResultDataRichScoreBrnnJava[] richScores = this.calculateAfterRichScore();

        // 5. 計算幸運星玩家得分陣列
        StcGameResultDataRichScoreBrnnJava[] luckyStarScores = new StcGameResultDataRichScoreBrnnJava[]{};

        // 6. 計算所有區域牌
        int[][] cards = super.logic.getAllAreaCardNumber2dArray();

        // 7. 計算所有區域提牌
        int[][] lifts = super.logic.getAllAreaLiftCardNumber2dArray();;

        // 8. 計算牌型
        int[] types = super.logic.getAreaStackArray();

        // 9. 計算押注區域輸贏
        int[] result = super.logic.calculateAreaWinLossResult();

        // 10. 計算超時時間
        double time = super.table.getMathConfig().getEndTimeSec();

        return new StcGameResultBrnnJava(winAreas, winScore, myScore, richScores, luckyStarScores, cards, lifts, types, result, time);
    }

    // 計算榜單玩家得分陣列
    private StcGameResultDataRichScoreBrnnJava[] calculateAfterRichScore(){
        // 1. 取得榜單玩家
        List<GamePlayer> richPlayerList = super.table.getGamePlayerHlr().getGamePlayerSortByAfterMoneyTopLimitList(6);

        // 2. 取得玩家得分對應表
        Map<Integer, UidScore> uidScoreMap = super.logic.getUidScoreMap();

        // 3. 封裝
        List<StcGameResultDataRichScoreBrnnJava> richScoreList = new ArrayList<>();
        for (GamePlayer richPlayer : richPlayerList) {
            UidScore uidScore = uidScoreMap.get(richPlayer.getChairIndex());
            if(Objects.nonNull(uidScore)){
                richScoreList.add(new StcGameResultDataRichScoreBrnnJava(
                        richPlayer.getChairIndex(),
                        richPlayer.getUid(),
                        richPlayer.getAfterMoney(),
                        uidScore.getScore(),
                        this.calculateStcGameResultDataAreaScoreArray(richPlayer.getChairIndex())
                ));
            }
        }

        return richScoreList.toArray(StcGameResultDataRichScoreBrnnJava[]::new);
    }

    // 計算贏的區域得分
    private StcGameResultDataAreaScoreBrnnJava[] calculateStcGameResultDataAreaScoreArray(int chairIndex){
        List<StcGameResultDataAreaScoreBrnnJava> winAreaScoreList = new ArrayList<>();

        // 1. 取得玩家得分陣列
        double[] playerAreaScoreArray = super.logic.calculatePlayerAreaScoreArray(chairIndex);

        // 2.
        for (int areaIndex = 0; areaIndex < playerAreaScoreArray.length; areaIndex++) {
            if(playerAreaScoreArray[areaIndex] > 0){
                winAreaScoreList.add(new StcGameResultDataAreaScoreBrnnJava(
                        areaIndex,
                        playerAreaScoreArray[areaIndex]
                ));
            }
        }

        return winAreaScoreList.toArray(StcGameResultDataAreaScoreBrnnJava[]::new);
    }

    // 計算真人玩家得分
    private StcGameResultDataMyScoreBrnnJava calculateAfterMyScore() {
        if(!super.table.isHumanBet()){
            return new StcGameResultDataMyScoreBrnnJava(
                    MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                    0.0,
                    new StcGameResultDataAreaScoreBrnnJava[]{}
            );
        }

        return new StcGameResultDataMyScoreBrnnJava(
                MathUtil.moneyScale(super.logic.getHumanPlayerAfterMoney()),
                super.logic.getHumanUidScore().getScore(),
                this.calculateStcGameResultDataAreaScore()
        );
    }

    // 計算遊戲結果贏分區域資訊
    private StcGameResultDataAreaScoreBrnnJava[] calculateStcGameResultDataAreaScore(){
        // 1. 計算真人區域贏分陣列
        double[] areaScoreArray = super.logic.calculatePlayerAreaScoreArray(super.logic.getHumanChairIndex());

        // 2. 創建遊戲結果區域贏分列表
        List<StcGameResultDataAreaScoreBrnnJava> result = new ArrayList<>();

        // 3. 封裝真人贏的區域贏分
        for (int areaIndex = 0; areaIndex < areaScoreArray.length; areaIndex++) {
            if(areaScoreArray[areaIndex] > 0){
                result.add(new StcGameResultDataAreaScoreBrnnJava(areaIndex, areaScoreArray[areaIndex]));
            }
        }

        return result.toArray(StcGameResultDataAreaScoreBrnnJava[]::new);
    }


    //* 詳細日誌相關 *//

    // 紀錄詳細日誌
    private void addDetail(){
        // 1. 玩家無押注防呆
        if(Objects.isNull(super.logic.getPlayerAreaBetMap(super.logic.getHumanChairIndex()))){
            return;
        }

        // 2. 新增牛牛區域牌
        this.addNiuAreaCardDetail();

        // 4. 新增派獎
        this.addBaiRenAwardDetail();

        // 5. 新增結算
        this.addBaiRenSettlementDetail();
    }

    // 新增牛牛區域 詳細住單
    private void addNiuAreaCardDetail() {
        // 新增 天區
        super.logic.addDetail(
                new DetailTianCardBrnnJava(super.logic.getAllAreaCardNumber2dArray()[ConstMathNiu.BetAreaEnum.TIAN.getCode()],
                        super.logic.getAreaStackArray()[ConstMathNiu.BetAreaEnum.TIAN.getCode()]));

        // 新增 地區
        super.logic.addDetail(
                new DetailDiCardBrnnJava(super.logic.getAllAreaCardNumber2dArray()[ConstMathNiu.BetAreaEnum.DI.getCode()],
                        super.logic.getAreaStackArray()[ConstMathNiu.BetAreaEnum.DI.getCode()]));

        // 新增 玄區
        super.logic.addDetail(
                new DetailXuanCardBrnnJava(super.logic.getAllAreaCardNumber2dArray()[ConstMathNiu.BetAreaEnum.XUAN.getCode()],
                        super.logic.getAreaStackArray()[ConstMathNiu.BetAreaEnum.XUAN.getCode()]));

        // 新增 黃區
        super.logic.addDetail(
                new DetailHuangCardBrnnJava(super.logic.getAllAreaCardNumber2dArray()[ConstMathNiu.BetAreaEnum.HUANG.getCode()],
                        super.logic.getAreaStackArray()[ConstMathNiu.BetAreaEnum.HUANG.getCode()]));

        // 新增 莊區
        super.logic.addDetail(
                new DetailBankerCardBrnnJava(super.logic.getAllAreaCardNumber2dArray()[ConstMathNiu.BetAreaEnum.BANKER.getCode()],
                        super.logic.getAreaStackArray()[ConstMathNiu.BetAreaEnum.BANKER.getCode()]));

    }

    // 新增派獎 詳細住單
    private void addBaiRenAwardDetail() {
        Map<Integer, Double> playerAreaScoreMap = super.logic.calculatePlayerAreaScoreMap(super.logic.getHumanChairIndex());

        playerAreaScoreMap.forEach((key, value) -> super.logic.addDetailBaiRen(
                new DetailPlayBaiRenAward(
                        super.table.calculateSpendSec(),
                        key,
                        value
                )
        ));
    }

    // 新增結算 詳細住單
    private void addBaiRenSettlementDetail() {
        UidScore humanUidScore = super.logic.getHumanUidScore();

        super.logic.addDetailBaiRen(new DetailPlayBaiRenSettlement(
                super.table.calculateSpendSec(),
                (Objects.nonNull(humanUidScore)) ? super.logic.getHumanUidScore().getScore() : 0.0));
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
