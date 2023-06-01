package org.lsj.utils;

public class GlobalVars {

    // 模板公司的companyId, 總公司companyId
    // 2019年11月22日后是用模板公司数据来复制创建新公司、代表所有公司进行游戏关闭/禁用等管理
    // 2020年6月6日后模板公司数据增加用途：用于初始化、与公司无关的游戏数据获取。
    public static final int TEMPLATE_COMPANY_ID = 0;

    public static final int IDLE_TIMEOUT_SECONDS = 240;  // 4 分鐘判斷為閒置過久, 約 4 分 20 秒可能會被其他網路服務判斷為閒置導致斷線

}
