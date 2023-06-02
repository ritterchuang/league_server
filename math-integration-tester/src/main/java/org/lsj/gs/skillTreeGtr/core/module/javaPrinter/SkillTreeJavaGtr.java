package org.lsj.gs.skillTreeGtr.core.module.javaPrinter;

import org.lsj.gs.skillTreeGtr.core.entity.node.NodeMapChildrenWpr;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.StringUtil;
import io.vertx.ext.web.handler.sockjs.impl.StringEscapeUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;

// 技巧樹 JAVA檔打印器
public class SkillTreeJavaGtr<T> {
    private final String OUTPUT_PATH = "./lx-math-integration-tester/src/main/java/com/lx/gs/skillTreeGtr/output/SkillTree.java"; //檔案輸出路徑

    // 寫入標題
    public void writeTitle(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.OUTPUT_PATH, false));

            bw.write("package org.lsj.gs.skillTreeGtr.output;");
            bw.newLine();
            bw.newLine();
            bw.write("public class SkillTree {");
            bw.newLine();
            bw.write("\tpublic String[] create(){");
            bw.newLine();
            bw.write("\t\treturn new String[]{");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 寫入技巧樹
    public void writeTree(boolean lastTreeFlag, NodeMapChildrenWpr<T> skillTree){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.OUTPUT_PATH, true));

            String skillTreeString = JsonUtil.getInstance().writeValueAsStringWithoutException(skillTree);

            bw.newLine();
            bw.write("\t\t\t\"" + StringEscapeUtils.escapeJava(skillTreeString) + "\"");

            if (lastTreeFlag) {
                bw.write(StringUtil.getInstance().getEmptyString());
            } else {
                bw.write(StringUtil.getInstance().getCommaString());
            }

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 寫入結尾
    public void writeEnd(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.OUTPUT_PATH, true));

            bw.newLine();
            bw.write("\t\t};");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.write("}");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
