package org.lsj.gs.skillTreeGtr.games.modelSkill;

import org.lsj.gs.skillTreeGtr.core.AbstractSkillTreeGtr;
import org.lsj.gs.skillTreeGtr.core.entity.node.NodeMapChildren;
import org.lsj.gs.skillTreeGtr.core.entity.node.NodeMapChildrenWpr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.actionExecutor.ActionExecutor;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.actionGtr.ActionGtr;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.actionSelector.ActionSelector;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.rootGtr.RootGtrModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.treeParser.TreeParser;
import org.lsj.gs.skillTreeGtr.games.modelSkill.module.turnCtr.TurnCtr;

import java.util.ArrayList;

// 技巧樹產生器
public class SkillTreeGtrModelSkill extends AbstractSkillTreeGtr {
    private final RootGtrModelSkill rootGtr; // 根產生器
    private final TurnCtr turnCtr; // 執行權計算器
    private final ActionGtr actionGtr; // 動作產生器
    private final ActionSelector actionSelector; // 動作選擇器
    private final ActionExecutor actionExecutor; // 動作執行器

    public SkillTreeGtrModelSkill() {
        super();
        this.rootGtr = new RootGtrModelSkill();
        this.turnCtr = new TurnCtr();
        this.actionGtr = new ActionGtr();
        this.actionSelector = new ActionSelector();
        this.actionExecutor = new ActionExecutor();
    }

    // 產出技巧樹檔
    public void generate(int maxTreeCount) {
        // 1. 寫入標題
        super.skillTreeJavaGtr.writeTitle();

        for (int treeIndex = 0; treeIndex < maxTreeCount; treeIndex++) {
            // 2. 產出技巧樹
            NodeMapChildren<TablePhaseModelSkill> skillTree = this.generate(this.rootGtr.generateRoot());
            new TreeParser<TablePhaseModelSkill>().travelPrint(skillTree);

            // 3. 計算贏分範圍
            ArrayList<Integer> scoreList =  new TreeParser<TablePhaseModelSkill>().travelIntScoreList(skillTree);
            System.out.println(scoreList.toString());

            // 4. 寫入技巧樹
            super.skillTreeJavaGtr.writeTree(
                    (treeIndex == maxTreeCount-1),
                    new NodeMapChildrenWpr<>(
                            scoreList.get(0), scoreList.get(1), skillTree
                    ));
            System.out.println();
        }

        // 5. 寫入結尾
        super.skillTreeJavaGtr.writeEnd();
    }

    private NodeMapChildren<TablePhaseModelSkill> generate(TablePhaseModelSkill tablePhase){
        // 1. 建立根
        NodeMapChildren<TablePhaseModelSkill> result = new NodeMapChildren<>(tablePhase);

        // 2. 判斷是否結束
        if(tablePhase.isGameEndFlag()){
            return result;
        }

        // 3. 計算子點
        TablePhaseModelSkill[] children = this.calculateChildren(tablePhase);

        // 4. 增加子點
        for (TablePhaseModelSkill child: children) {
            result.addChild(child.getKey(), this.generate(child));
        }

        return result;
    }

    private TablePhaseModelSkill[] calculateChildren(TablePhaseModelSkill root){
        // 1. 計算執行權
        int turn = this.turnCtr.calculateTurn(root);

        // 2. 計算執行次數
        int turnCount = root.getTurnCount() + 1;

        // 3. 計算動作陣列
        int[] actionArray = this.actionGtr.calculateActionArray(root, turnCount);

        // 4. 計算選擇動作
        int[] selectedActionArray = this.actionSelector.select(root, actionArray);

        // 5. 執行動作
        return this.actionExecutor.execute(root, turn, turnCount, actionArray, selectedActionArray);
    }
}
