package com.lx.gs.math.games.mybjl_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lx.utils.JsonUtil;

// 路線圖訊息
public class StcChartMybjlJava extends AbstractStcMessageMybjlJava {
    @JsonIgnore public int[] chart; // 路線圖

    public StcChartMybjlJava(int[] chart) {
        super(StcChartMybjlJava.class.getCanonicalName());
        this.chart = chart;
        ((ObjectNode)super.data).putArray("chart").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(chart));
    }
}
