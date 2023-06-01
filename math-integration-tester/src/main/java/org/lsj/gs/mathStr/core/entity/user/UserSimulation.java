package org.lsj.gs.mathStr.core.entity.user;

import org.lsj.gs.user.AbstractUser;

import javax.websocket.Session;

// 模擬器用使用者
public class UserSimulation extends AbstractUser {
    public UserSimulation(int uid, String phone, String ip, int robot, String alipay, String nickName, String payAccount, String account, String headImgUrl, int interfaceType, String thirdAccount, int regTime, String directAgent, int platform, int sex, int thirdAccountId, int vipState, int vipLevel, String realName, int companyId, int baseAgencyId, int promoteState, int isTest, int table, int gameid, int accountType, int boxid, String gsid, int roomCode, int role, int chair, int state, int changeTime, String loginVersion, int fid, String personControlLog, boolean afterBybNeedUpdateMoney, double balance, Session session) {
        super(uid,
              phone,
              ip,
              robot,
              alipay,
              nickName,
              payAccount,
              account,
              headImgUrl,
              interfaceType,
              thirdAccount,
              regTime,
              directAgent,
              platform,
              sex,
              thirdAccountId,
              vipState,
              vipLevel,
              realName,
              companyId,
              baseAgencyId,
              promoteState,
              isTest,
              table,
              gameid,
              accountType,
              boxid,
              gsid,
              roomCode,
              role,
              chair,
              state,
              changeTime,
              loginVersion,
              fid,
              personControlLog,
              afterBybNeedUpdateMoney,
              balance,
              session);
    }
}
