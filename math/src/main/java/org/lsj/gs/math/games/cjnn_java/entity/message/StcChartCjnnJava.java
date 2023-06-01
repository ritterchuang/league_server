package org.lsj.gs.math.games.cjnn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 路線圖訊息 TODO 二維陣列
public class StcChartCjnnJava extends AbstractStcMessageCjnnJava {
    @JsonIgnore public int[][] chart; // 路線圖

    public StcChartCjnnJava(int[][] chart) {
        super(StcChartCjnnJava.class.getCanonicalName());
        this.chart = chart;
        ((ObjectNode)super.data).putArray("chart").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(chart));
    }
}
