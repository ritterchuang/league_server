package com.lx.gs.mathBoardGtr.core.fish;

import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import com.lx.gs.math.config.module.tableGameConfigGtr.TableGameConfigFactory;
import com.lx.gs.math.core.common.table.TableCommandFish;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.core.common.table.entity.message.fish.CtsGetHitResultData;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilFish;
import com.lx.gs.math.core.common.table.module.tableUtil.TableUtilFish;
import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import com.lx.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendFish;
import com.lx.gs.mathBoardGtr.core.AbstractBoardGtr;
import com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr.ClientBulletGtrFactory;
import com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr.IClientBulletGtr;
import com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientHitGtr.ClientHitGtr;
import com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientHitGtr.IClientHitGtr;
import com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientTargetGtr.ClientTargetGtr;
import com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientTargetGtr.IClientTargetGtr;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import com.lx.gs.mathStr.core.module.EmptyWebSocketUtil;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

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
