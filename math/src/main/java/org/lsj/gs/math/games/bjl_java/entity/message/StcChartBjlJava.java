package org.lsj.gs.math.games.bjl_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 路線圖訊息
public class StcChartBjlJava extends AbstractStcMessageBjlJava {
    @JsonIgnore public int[] chart; // 路線圖

    public StcChartBjlJava(int[] chart) {
        super(StcChartBjlJava.class.getCanonicalName());
        this.chart = chart;
        ((ObjectNode)super.data).putArray("chart").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(chart));
    }
}
