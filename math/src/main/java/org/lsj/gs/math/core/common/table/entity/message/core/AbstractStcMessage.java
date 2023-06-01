package org.lsj.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.utils.JsonUtil;

import java.util.Map;

// 抽象服務端傳客戶端訊息
public abstract class AbstractStcMessage extends AbstractMessage{
    public String main; // 事件類型
    public String sub; // 訊息定義
    public JsonNode data; // 資料

    public AbstractStcMessage(Map<String, IMessageEnum> messageObjectEnumMap, String subClassName){
        this.main = messageObjectEnumMap.get(subClassName).getMain();
        this.sub = messageObjectEnumMap.get(subClassName).getSub();
        this.data = JsonUtil.getInstance().getObjectMapper().createObjectNode();
    }
}
