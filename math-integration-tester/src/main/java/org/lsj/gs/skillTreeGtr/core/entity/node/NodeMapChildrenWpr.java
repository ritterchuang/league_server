package org.lsj.gs.skillTreeGtr.core.entity.node;

// 技巧樹產生器 封裝器
public class NodeMapChildrenWpr<T> {
    private final double minHumanScore; // 最大玩家贏分
    private final double maxHumanScore; // 最大玩家贏分
    private final NodeMapChildren<T> node; // 技巧樹

    public NodeMapChildrenWpr() {
        this.minHumanScore = 0;
        this.maxHumanScore = 0;
        this.node = null;
    }

    public NodeMapChildrenWpr(double minHumanScore, double maxHumanScore, NodeMapChildren<T> node) {
        this.minHumanScore = minHumanScore;
        this.maxHumanScore = maxHumanScore;
        this.node = node;
    }

    public double getMinHumanScore() {
        return minHumanScore;
    }

    public double getMaxHumanScore() {
        return maxHumanScore;
    }

    public NodeMapChildren<T> getNode() {
        return node;
    }
}
