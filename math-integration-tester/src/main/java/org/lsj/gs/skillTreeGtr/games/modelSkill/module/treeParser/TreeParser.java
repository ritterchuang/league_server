package org.lsj.gs.skillTreeGtr.games.modelSkill.module.treeParser;

import org.lsj.gs.skillTreeGtr.core.entity.node.NodeMapChildren;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.ConstModelSkill;
import org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase.TablePhaseModelSkill;

import java.util.ArrayList;

// 技巧樹解析器
public class TreeParser<T> {
    // 遍歷打印
    public void travelPrint(NodeMapChildren<T> node){
        System.out.println(node.getRoot().toString());

        if(!node.isLeaf()){
            node.getChildren().forEach((key, childNode) -> this.travelPrint(childNode));
        }
    }

    // 遍歷得分陣列 [最低得分, 最高得分]
    public ArrayList<Integer> travelIntScoreList(NodeMapChildren<T> node){
        return this.travelIntScoreList(node, new ArrayList<>(){});
    }

    // 遍歷得分陣列 [最低得分, 最高得分]
    private ArrayList<Integer> travelIntScoreList(NodeMapChildren<T> node, ArrayList<Integer> scoreList){
        if(!node.isLeaf()){
            node.getChildren().forEach((key, childNode) -> this.travelIntScoreList(childNode, scoreList));
        }

        if(node.isLeaf()){
            int humanScore = ((TablePhaseModelSkill)node.getRoot()).getScoreArray()[ConstModelSkill.TurnModelSkill.HUMAN.getCode()];

            if(scoreList.size() == 0){
                scoreList.add(humanScore);
                scoreList.add(humanScore);
            }

            scoreList.set(0, Math.min(scoreList.get(0), humanScore));
            scoreList.set(1, Math.max(scoreList.get(1), humanScore));
        }

        return scoreList;
    }
}
