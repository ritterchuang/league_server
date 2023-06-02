package org.lsj.gs.math.games.lhd_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 路線圖訊息
public class StcChartLhdJava extends AbstractStcMessageLhdJava {
    @JsonIgnore public int[] chart; // 路線圖

    public StcChartLhdJava(int[] chart) {
        super(StcChartLhdJava.class.getCanonicalName());
        this.chart = chart;
        ((ObjectNode)super.data).putArray("chart").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(chart));
    }
}
