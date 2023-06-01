package org.lsj.gs.mathBoardGtr.games.lhd_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigLhdJava;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.baiRen.CtsBetBaiRenJava;
import org.lsj.gs.math.games.lhd_java.TableLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;
import org.lsj.gs.math.games.lhd_java.module.state.StateGameBeginLhdJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendBaiRen;
import org.lsj.gs.mathBoardGtr.core.baiRen.AbstractBoardGtrBaiRen;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.gs.state.BaiRenGameState;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

// 抽象新龍虎鬥局產生器
public abstract class AbstractBoardGtrBaiRenLhdJava extends AbstractBoardGtrBaiRen {
    protected TableLhdJava table; // 牌桌
    protected final GameTypeConfigExtendBaiRen gameTypeConfigExtend; // 客製遊戲設定

    public AbstractBoardGtrBaiRenLhdJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        this.gameTypeConfigExtend = (GameTypeConfigExtendBaiRen) playGameFieldConfig.getGameTypeConfigExtend();

        // 2. 產生牌桌
        try {
            this.table = new TableLhdJava(
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigLhdJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
                    super.poolCtr.createPoolConfig(),
                    player.getUser(),
                    super.tableUtil);
        } catch (TableException e) {
            e.printStackTrace();
        }
    }

    // 真人下注
    protected void humanOnRecvBetMsg(){
        // 1. 決定押注次數
        int randomBetCount = super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(
                this.gameTypeConfigExtend.getPlayTypeBaiRenConfigExtend().getMaxBetCount(),
                ConstMathCommon.AccuracyType.A32768
        );

        // 2. 遍歷押注
        for(int betIndex = 0; betIndex < randomBetCount; betIndex++){
            // 3. 隨機押注區
            int randomAreaId = this.calculateRandomAreaId();

            // 4. 隨機選擇籌碼
            int randomBet = super.calculateRandomIntElement(this.table.getLogic().getChipsList().stream().filter(chips ->
                    this.table.getHumanGamePlayer().getBeginMoney() >= chips).collect(Collectors.toList()));

            // 5. 執行押注
            if(randomBet > 0) {
                this.onRecvBetMsg(randomAreaId, randomBet);
            }
        }
    }

    // 執行下注
    private void onRecvBetMsg(int randomAreaId, int randomBet){
        // 1. 檢查是否超過限額
        if((this.table.getLogic().getPlayerAreaBetArray(this.table.getLogic().getHumanChairIndex())[randomAreaId] + randomBet) > this.table.getLogic().getAreasTopLimitList().get(randomAreaId)){
            return;
        }

        // 2. 正常下注
        ((StateGameBeginLhdJava)this.table.getStateMgr().getStateMap().get(BaiRenGameState.GAME_BEGIN.getCode())).onRecvBetMsg(
                this.table.getLogic().getHumanChairIndex(),
                new CtsBetBaiRenJava(
                        randomAreaId,
                        randomBet
                ));

    }

    // 計算隨機下注區域
    private int calculateRandomAreaId(){
        // 1. 押龍則不能押虎
        if(Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()))
                && Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()).get(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()))){
            return super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                    Arrays.stream(ConstLhdJava.BetAreaIdLhdJava.values()).filter(betAreaEnum ->
                                            betAreaEnum.getCode() != ConstLhdJava.BetAreaIdLhdJava.INVALID.getCode()
                                                    && betAreaEnum.getCode() != ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()
                                    )
                            .mapToInt(ConstLhdJava.BetAreaIdLhdJava::getCode).boxed().collect(Collectors.toList()),
                    ConstMathCommon.AccuracyType.A32768
            );
        }


        // 2. 押虎則不能押龍
        if(Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()))
                && Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()).get(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()))){
            return super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                    Arrays.stream(ConstLhdJava.BetAreaIdLhdJava.values()).filter(betAreaEnum ->
                                    betAreaEnum.getCode() != ConstLhdJava.BetAreaIdLhdJava.INVALID.getCode()
                                            && betAreaEnum.getCode() != ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()
                            )
                            .mapToInt(ConstLhdJava.BetAreaIdLhdJava::getCode).boxed().collect(Collectors.toList()),
                    ConstMathCommon.AccuracyType.A32768
            );
        }

        // 3. 正常押
        return super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                Arrays.stream(ConstLhdJava.BetAreaIdLhdJava.values()).filter(betAreaEnum -> betAreaEnum.getCode() != ConstLhdJava.BetAreaIdLhdJava.INVALID.getCode())
                        .mapToInt(ConstLhdJava.BetAreaIdLhdJava::getCode).boxed().collect(Collectors.toList()),
                ConstMathCommon.AccuracyType.A32768
        );
    }

    @Override
    public abstract BoardGtrResult calculateOneBoardResult();


}
