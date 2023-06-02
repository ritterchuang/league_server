package org.lsj.gs.skillTreeGtr.games.modelSkill.entity;

// 技巧模型定義
public class ConstModelSkill {

    // 位置定義
    public enum TurnModelSkill{
        INVALID(-1),
        HUMAN(0),
        ROBOT(1);

        private final int code;
        TurnModelSkill(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static TurnModelSkill fromCode(int code) {
            final TurnModelSkill[] allEnumInstance = values();
            for (TurnModelSkill enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 動作定義
    public enum ActionModelSkill{
        INVALID(-1),
        FOLD(0),
        COMPARE(1),
        RAISE(2);

        private final int code;
        ActionModelSkill(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static ActionModelSkill fromCode(int code) {
            final ActionModelSkill[] allEnumInstance = values();
            for (ActionModelSkill enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }
}
