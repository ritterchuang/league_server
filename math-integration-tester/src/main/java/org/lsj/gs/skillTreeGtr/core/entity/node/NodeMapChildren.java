package org.lsj.gs.skillTreeGtr.core.entity.node;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class NodeMapChildren<T> {
    private int level; // 層級
    private final T root; // 自身
    private T parent; // 父
    private final Map<String, NodeMapChildren<T>> children; // 子

    // 解析用建構子
    public NodeMapChildren() {
        this.level = 0;
        this.root = null;
        this.parent = null;
        this.children = new HashMap<>();
    }

    public NodeMapChildren(T root) {
        this.level = 0;
        this.root = root;
        children = new HashMap<>();
    }

    public NodeMapChildren<T> addChild(String childrenKey, NodeMapChildren<T> childNode) {
        childNode.level = this.level + 1;
        childNode.parent = this.root;
        this.children.put(childrenKey, childNode);
        return childNode;
    }

    public T getRoot() {
        return root;
    }

    public boolean isRoot() {
        return parent == null;
    }

    @JsonIgnore
    public boolean isLeaf() {
        return children.size() == 0;
    }

    public int getLevel() {
        return this.level;
    }

    public Map<String, NodeMapChildren<T>> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return root != null ? root.toString() : "null";
    }
}

