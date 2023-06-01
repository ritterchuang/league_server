package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.AwardBulletGtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.RobotAwardBulletGtrResult;

import java.util.ArrayList;
import java.util.List;

// 獎勵子彈生產者None
public class AwardBulletGtrNone extends AbstractAwardBulletGtr implements IAwardBulletGtr {
    private final ConstMathFish.AwardBulletType awardBulletType; // 獎勵子彈類型
    private final AwardBulletGtrConfigExtendNone config; // 獎勵子彈額外設定

    public AwardBulletGtrNone(ConstMathFish.AwardBulletType awardBulletType, AwardBulletGtrConfigExtend awardBulletGtrConfigExtend, ITableUtil tableUtil) {
        super(tableUtil);
        this.awardBulletType = awardBulletType;
        this.config = (AwardBulletGtrConfigExtendNone) awardBulletGtrConfigExtend;
    }

    //* 計算者相關 *//
    // 生產子彈
    @Override
    public AwardBulletGtrResult calculateAwardBulletGtrResult(int killCount, Bullet currentBullet, BulletMgr bulletMgr) {
        return new AwardBulletGtrResult(ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone());
    }

    // 計算預期總倍數
    @Override
    public double calculateAwardBulletOdds(Bullet currentBullet, BulletMgr bulletMgr) {
        return 0.0;
    }

    /* 獎勵子彈資訊相關 */
    // 取得獎勵子彈類型
    @Override
    public ConstMathFish.AwardBulletType getAwardBulletType() {
        return awardBulletType;
    }

    //* 機器人相關 *//
    // 計算機器人獎勵子彈結果列表
    @Override
    public List<RobotAwardBulletGtrResult> calculateRobotAwardBulletGtrResult(BulletMgr bulletMgr) {
        return new ArrayList<>() {
            {
                add(new RobotAwardBulletGtrResult(new AwardBulletGtrResult(ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone()), 0.0, 1.0));
            }
        };
    }

    @Override
    public AwardBulletGtrResult calculateNoKillResult() {
        return new AwardBulletGtrResult(ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone());
    }
}
