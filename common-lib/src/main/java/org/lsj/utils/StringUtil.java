package org.lsj.utils;

// 字串工具包
public class StringUtil {
    private static final StringUtil instance = new StringUtil();

    public static StringUtil getInstance() {
        return instance;
    }

    // 取得空字串
    public String getEmptyString(){
        return "";
    }

    // 取得逗號字串
    public String getCommaString(){
        return ",";
    }

    // 是否為null
    public boolean isNull(String str){
        return str == null;
    }

    // 是否為空字串
    public boolean isEmpty(String str){
        if(this.isNull(str)){
            return true;
        }

        return  str.equals(this.getEmptyString());
    }
}
