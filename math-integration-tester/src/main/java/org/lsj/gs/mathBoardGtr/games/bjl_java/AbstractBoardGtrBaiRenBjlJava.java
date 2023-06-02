package org.lsj.gs.mathBoardGtr.games.bjl_java;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigBjlJava;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.baiRen.CtsBetBaiRenJava;
import org.lsj.gs.math.games.bjl_java.TableBjlJava;
import org.lsj.gs.math.games.bjl_java.module.state.StateGameBeginBjlJava;
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

// 抽象新百家樂戰局產生器
public abstract class AbstractBoardGtrBaiRenBjlJava extends AbstractBoardGtrBaiRen {
    protected TableBjlJava table; // 牌桌
    protected final GameTypeConfigExtendBaiRen gameTypeConfigExtend; // 客製遊戲設定

    public AbstractBoardGtrBaiRenBjlJava(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        // 1. 初始化
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        this.gameTypeConfigExtend = (GameTypeConfigExtendBaiRen) playGameFieldConfig.getGameTypeConfigExtend();

        // 2. 產生牌桌
        try {
            this.table = new TableBjlJava(
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigBjlJava) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
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
        ((StateGameBeginBjlJava)this.table.getStateMgr().getStateMap().get(BaiRenGameState.GAME_BEGIN.getCode())).onRecvBetMsg(
                this.table.getLogic().getHumanChairIndex(),
                new CtsBetBaiRenJava(
                        randomAreaId,
                        randomBet
                ));

    }

    // 計算隨機下注區域
    private int calculateRandomAreaId(){
        // 1. 押庄則不能押閒
        if(Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()))
                && Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()).get(ConstMathBjl.BetAreaEnum.BANK.getCode()))){
            return super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                    Arrays.stream(ConstMathBjl.BetAreaEnum.values()).filter(betAreaEnum ->
                                    betAreaEnum.getCode() != ConstMathBjl.BetAreaEnum.INVALID.getCode()
                                            && betAreaEnum.getCode() != ConstMathBjl.BetAreaEnum.PLAY.getCode()
                            )
                            .mapToInt(ConstMathBjl.BetAreaEnum::getCode).boxed().collect(Collectors.toList()),
                    ConstMathCommon.AccuracyType.A32768
            );
        }

        // 2. 押閒則不能押庄
        if(Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()))
                && Objects.nonNull(this.table.getLogic().getPlayerAreaBetMap(this.table.getLogic().getHumanChairIndex()).get(ConstMathBjl.BetAreaEnum.PLAY.getCode()))){
            return super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                    Arrays.stream(ConstMathBjl.BetAreaEnum.values()).filter(betAreaEnum ->
                                    betAreaEnum.getCode() != ConstMathBjl.BetAreaEnum.INVALID.getCode()
                                            && betAreaEnum.getCode() != ConstMathBjl.BetAreaEnum.BANK.getCode()
                            )
                            .mapToInt(ConstMathBjl.BetAreaEnum::getCode).boxed().collect(Collectors.toList()),
                    ConstMathCommon.AccuracyType.A32768
            );
        }

        // 3. 正常押
        return super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                Arrays.stream(ConstMathBjl.BetAreaEnum.values()).filter(betAreaEnum -> betAreaEnum.getCode() != ConstMathBjl.BetAreaEnum.INVALID.getCode())
                        .mapToInt(ConstMathBjl.BetAreaEnum::getCode).boxed().collect(Collectors.toList()),
                ConstMathCommon.AccuracyType.A32768
        );
    }

    @Override
    public abstract BoardGtrResult calculateOneBoardResult();


}
