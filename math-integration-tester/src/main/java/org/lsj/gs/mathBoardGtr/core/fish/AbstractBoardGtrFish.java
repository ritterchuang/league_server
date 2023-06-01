package org.lsj.gs.mathBoardGtr.core.fish;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import org.lsj.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import org.lsj.gs.math.core.common.table.TableCommandFish;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.fish.CtsGetHitResultData;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilFish;
import org.lsj.gs.math.core.common.table.module.tableUtil.TableUtilFish;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendFish;
import org.lsj.gs.mathBoardGtr.core.AbstractBoardGtr;
import org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr.ClientBulletGtrFactory;
import org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr.IClientBulletGtr;
import org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientHitGtr.ClientHitGtr;
import org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientHitGtr.IClientHitGtr;
import org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientTargetGtr.ClientTargetGtr;
import org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientTargetGtr.IClientTargetGtr;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.EmptyWebSocketUtil;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

// 魚機抽象局產生器工廠
public abstract class AbstractBoardGtrFish extends AbstractBoardGtr implements IBoardGtrFish {
    protected TableCommandFish table; // 牌桌
    protected final ITableUtilFish tableUtil; // 牌桌工具包
    protected final IClientBulletGtr clientBulletGtr; // 子彈生產者
    protected final IClientTargetGtr clientTargetGtr; // 目標生產者
    protected final IClientHitGtr clientHitGtr; // 打擊生產者
    protected final MathFishConfig mathFishConfig; // 數值設定

    public AbstractBoardGtrFish(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig){
        // 1. 初始化牌局產生器父類別
        super(player, poolCtr, playGameFieldConfig.getTableFieldConfig(), controlAlgorithmConfig);

        // 2. 初始化牌桌工具包
        this.tableUtil = new TableUtilFish(
                new EmptyWebSocketUtil(),
                new MathRandomUtil(),
                new ControlAlgorithmUtil(
                        controlAlgorithmConfig.getControlAlgorithmUtil().isControlFlag(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlPoolControlType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlDealType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlShuffleType(),
                        controlAlgorithmConfig.getControlAlgorithmUtil().getControlPersonAdjustType()));

        // 3. 產生牌桌
        try {
            this.table = new TableCommandFish(
                    lastTableId.getAndIncrement(),
                    playGameFieldConfig.getTableFieldConfig(),
                    (TableGameConfigFish) new TableGameConfigFactory().createTableGameConfig(playGameFieldConfig.getTableFieldConfig().getGameId(), player.getUser()),
                    super.poolCtr.createPoolConfig(),
                    player.getUser(),
                    this.tableUtil);
        } catch (TableException e) {
            e.printStackTrace();
        }

        // 4. 取得設定
        GameTypeConfigExtendFish gameTypeConfigExtendFish = (GameTypeConfigExtendFish) playGameFieldConfig.getGameTypeConfigExtend();

        // 5. 產生子彈生產者
        this.clientBulletGtr = new ClientBulletGtrFactory().createClientBulletGtr(gameTypeConfigExtendFish, this.tableUtil);

        // 6. 產生目標生產者
        this.clientTargetGtr = new ClientTargetGtr(this.tableUtil, gameTypeConfigExtendFish);

        // 7. 產生打擊生產者
        this.clientHitGtr = new ClientHitGtr();

        // 8. 數值設定
        this.mathFishConfig = this.table.getMathFishConfig();
    }

    // 取得返還資訊
    @Override
    public BoardGtrReturnResult getReturnResult(){
        return new BoardGtrReturnResult(super.calculateStatisticResult(), this.table.calculateFishReturnResult());
    }

    // 更新返還資訊
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult){
        // 1. 更新玩家餘額
        this.player.updateReturnResult(boardGtrReturnResult.getReturnResult());

        // 2. 更新水池
        this.poolCtr.updateReturnResult(boardGtrReturnResult.getReturnResult());
    }

    // 計算打擊資訊
    @Override
    public CtsGetHitResultData createRandomHitCtsGetHitResultData(MathFishConfig mathFishConfig) {
        // 1. 建構子彈資訊
        ClientBullet clientBullet = this.clientBulletGtr.generateClientBullet(mathFishConfig);

        // 2. 建構目標資訊
        ClientTarget clientTarget = this.clientTargetGtr.generateWeightedClientTarget();

        // 3. 建構打擊資訊
        ClientHit clientHit = this.clientHitGtr.calculateClientHit(mathFishConfig, clientBullet.getBulletIndex(), clientTarget.getTargetIndex());

        // 4. 封裝回傳
        return new CtsGetHitResultData(0, clientBullet, clientTarget, clientHit);
    }
}
