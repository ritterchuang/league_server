package org.lsj.gs.user;

// 使用者建構器
public class UserBdr extends AbstractUserBdr {
    public User createUser(){
        return new User(
                super.uid,
                super.phone,
                super.ip,
                super.robot,
                super.alipay,
                super.nickName,
                super.payAccount,
                super.account,
                super.headImgUrl,
                super.interfaceType,
                super.thirdAccount,
                super.regTime,
                super.directAgent,
                super.platform,
                super.sex,
                super.thirdAccountId,
                super.vipState,
                super.vipLevel,
                super.realName,
                super.companyId,
                super.baseAgencyId,
                super.promoteState,
                super.isTest,
                super.table,
                super.gameid,
                super.accountType,
                super.boxid,
                super.gsid,
                super.roomCode,
                super.role,
                super.chair,
                super.state,
                super.changeTime,
                super.loginVersion,
                super.fid,
                super.personControlLog,
                super.afterBybNeedUpdateMoney,
                super.balance,
                super.session);
    }
}
