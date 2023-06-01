package org.lsj.gs.math.core.common.state.stateFish;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.logic.ILogicFish;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.common.state.IState;
import org.lsj.gs.math.core.common.table.TableCommandFish;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.fish.CtsGetHitResultData;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.fish.StcGetHitResultData;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.protocol.Command;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.MathUtil;
import org.lsj.websocket.ProtocolCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

// 抽象魚機遊戲開始狀態
public abstract class AbstractStateGameBeginFish extends AbstractStateFish implements IState {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractStateGameBeginFish.class);

    public AbstractStateGameBeginFish(TableCommandFish tableCommandFish, ILogicFish logicFish, IGameAdjust gameAdjust, int stateId) {
        super(tableCommandFish, logicFish, gameAdjust, stateId);
    }

    //* 計算器相關 *//
    // 取得打擊結果
    public IGameBetLogResultFish getHitResult(CtsGetHitResultData ctsGetHitResultData) throws TableException {
        // 1. 重置局資訊
        super.table.reset();

        // 2. 更新玩家餘額
        super.table.updatePlayerBeginMoneyBeforeGame();

        // 3. 檢查子彈正確性
        ConstMathCommon.TableProtocolCode bulletCheckProtocolCode = this.checkClientBullet(ctsGetHitResultData);
        if (bulletCheckProtocolCode.equals(ConstMathCommon.TableProtocolCode.FISH_BULLET_STATE_ERROR)) {
            return super.logic.calculateErrorBulletUsageGameBetLogResult();
        } else {
            this.handleCheckProtocol(bulletCheckProtocolCode);
        }

        // 4. 檢查打擊正確性
        this.handleCheckProtocol(super.logic.checkClientHit(ctsGetHitResultData.getBullet(), ctsGetHitResultData.getTarget(), ctsGetHitResultData.getHit()));

        // 5. 判斷玩家出始金額是否足夠
        if (!super.table.checkHumanBeginMoneyEnough(super.logic.getClientBulletCost(ctsGetHitResultData.getBullet()))) {
            this.handleCheckProtocol(ConstMathCommon.TableProtocolCode.COMMON_MONEY_NOT_ENOUGH);
        }

        // 6. 紀錄當前資訊
        super.logic.setCurrentBullet(super.logic.changeClientBullet2Bullet(ctsGetHitResultData.getBullet()));
        super.logic.setCurrentTarget(ctsGetHitResultData.getTarget());
        super.logic.setCurrentHit(ctsGetHitResultData.getHit());

        // 7. 計算個人控
        super.table.calculatePersonControlAllResult();
        LOG.debug(super.table.getTableLogTitle() + " adjustInfo:{}, personAdjustType:{}, personControlLogString:{}",
                super.table.getPoolCtr().getAdjustInfoString(),
                super.table.getPoolCtr().getPersonAdjustType(),
                super.table.getPoolCtr().getPersonControlLogString());

        // 8. 開啟調控執行打擊
        super.gameAdjust.startGameAdjust();

        // 9. 取得調控結果
        HitResult hitResult = super.logic.getHitResult();

        // 10.更新獎勵子彈結果
        super.logic.updateAwardBullet(hitResult, super.logic.getCurrentBullet());

        // 11. 更新局號
        super.table.updateRoundId();

        // 12. 新增注單詳細記錄
        this.addDetail(super.logic.getCurrentBullet(), ctsGetHitResultData.getTarget(), hitResult);

        // 13. 更新 玩家剩餘金額
        super.table.updateHumanAfterMoney(MathUtil.subtract(hitResult.getTotalWin(), super.logic.getCurrentBullet().getCost()));

        // 14. 計算客端打擊結果
        ClientHitResult clientHitResult = super.logic.packageClientHitResult(hitResult);

        // 15. 計算客端資訊
        JsonNode stcGetHitResultNode = this.calculateStcHitResultNode(
                ctsGetHitResultData.getFishSeq(),
                super.logic.getHumanPlayerAfterMoney(),
                clientHitResult);

        // 16. 傳送客端資訊
        LOG.debug(super.table.getTableLogTitle() + " ctsHitRequest:{}, stcHitResult:{}",
                JsonUtil.getInstance().writeValueAsStringWithoutException(ctsGetHitResultData),
                stcGetHitResultNode.toString());
        super.table.sendMessageToHumanPlayer(
                ProtocolCode.SUCCESS,
                Command.HIT_TARGET.getStcCommand(),
                stcGetHitResultNode);

        // 17. 計算注單資訊
        IGameBetLogResultFish gameBetLogResultFish = super.logic.getGameBetLogResultFish(super.logic.getHumanUidScore());

        // 18. 回傳
        return gameBetLogResultFish;
    }

    // 牌桌協議編碼處理
    private void handleCheckProtocol(ConstMathCommon.TableProtocolCode checkProtocolCode) throws TableException {
        switch (checkProtocolCode) {
            case COMMON_SUCCESS:
                return;
            case FISH_BULLET_INDEX_NOT_EXIST:
                LOG.error(super.table.getTableLogTitle() + " {}", "bullet index not exist");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "bullet index not exist");
            case FISH_BULLET_AMOUNT_NOT_ENOUGH:
                LOG.error(super.table.getTableLogTitle() + " {}", "bullet amount not enough");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "bullet amount not enough");
            case FISH_BULLET_COST_ERROR:
                LOG.error(super.table.getTableLogTitle() + " {}", "bullet cost error");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "bullet cost error");
            case FISH_BULLET_NOT_CONSISTENCY:
                LOG.error(super.table.getTableLogTitle() + " {}", "bullet information not consistency");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "bullet information not consistency");
            case FISH_BULLET_NOT_COMPLETE:
                LOG.error(super.table.getTableLogTitle() + " {}", "bullet information not complete");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "bullet information not complete");
            case FISH_HIT_NOT_EXIST:
                LOG.error(super.table.getTableLogTitle() + " {}", "hitCtr or hitConfig not exist");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "hitCtr or hitConfig not exist");
            case FISH_HIT_NOT_CONSISTENCY:
                LOG.error(super.table.getTableLogTitle() + " {}", "hit information not consistency");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "hit information not consistency");
            case FISH_HIT_NOT_COMPLETE:
                LOG.error(super.table.getTableLogTitle() + " {}", "hit information not complete");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "hit information not complete");
            case COMMON_MONEY_NOT_ENOUGH:
                LOG.warn(super.table.getTableLogTitle() + " {}", "money not enough");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FA_GAME_MONEY_NOT_ENOUGH, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.WARN, "common money not enough");
            default:
                LOG.error(super.table.getTableLogTitle() + " {}", "unexpect tableProtocol");
                super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.HIT_TARGET.getStcCommand());
                throw new TableException(checkProtocolCode, Level.ERROR, "unexpect tableProtocol");
        }
    }

    // 檢查子彈
    private ConstMathCommon.TableProtocolCode checkClientBullet(CtsGetHitResultData ctsGetHitResultData) {
        // 1. 檢查子彈資訊
        ConstMathCommon.TableProtocolCode protocolCode = super.logic.checkClientBullet(ctsGetHitResultData.getBullet());

        // 2. 子彈狀態錯誤處理
        if (protocolCode.equals(ConstMathCommon.TableProtocolCode.FISH_BULLET_STATE_ERROR)) {
            // 2.1 計算客端封包資訊
            JsonNode stcGetHitResultNode = this.calculateStcHitResultNode(ctsGetHitResultData.getFishSeq(),
                    super.table.getGamePlayerHlr().getHumanGamePlayer().getBeginMoney(),
                    new ClientHitResult());

            // 2.2 傳送客端封包資訊
            // [玩家使用機關炮打機關炮，最後幾發可能尚未收到服務端回傳，又傳送一般子彈，導致子彈數量狀態錯誤。]
            LOG.debug(super.table.getTableLogTitle() + " ctsHitRequest:{}, stcHitResult:{}",
                    JsonUtil.getInstance().writeValueAsStringWithoutException(ctsGetHitResultData),
                    stcGetHitResultNode.toString());
            super.table.sendMessageToHumanPlayer(
                    ProtocolCode.SUCCESS,
                    Command.HIT_TARGET.getStcCommand(),
                    stcGetHitResultNode
            );
        }

        // 3. 回傳牌桌協議編碼
        return protocolCode;
    }

    // 新增注單詳細記錄
    private void addDetail(Bullet bullet, ClientTarget target, HitResult hitResult) {
        // 1. 添加目標詳細記錄
        super.logic.addDetailFish(bullet, target, hitResult);

        // 2. 添加特殊事件詳細記錄
        super.logic.addDetailSpecialFeature(bullet, hitResult);

        // 3. 添加子彈打擊詳細記錄
        super.logic.addDetailBulletHit(bullet);

        // 4. 添加獎勵子彈詳細記錄
        super.logic.addDetailAwardBullet(bullet, hitResult);
    }

    // 計算客端封包資訊
    private JsonNode calculateStcHitResultNode(int fishSeq, double humanBeginMoney, ClientHitResult clientHitResult) {
        return JsonUtil.getInstance().getObjectMapper().convertValue(
                new StcGetHitResultData(
                        fishSeq,
                        new GamePlayerResult(humanBeginMoney),
                        clientHitResult),
                JsonNode.class);
    }
}
