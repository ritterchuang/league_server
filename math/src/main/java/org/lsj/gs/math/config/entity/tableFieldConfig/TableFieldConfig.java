package org.lsj.gs.math.config.entity.tableFieldConfig;

import org.lsj.db.CompanyFieldObj;
import org.lsj.gs.FieldConfig;

// 牌桌基礎配置
public class TableFieldConfig implements ITableFieldConfig {
    private final FieldConfig fieldConfig; // 房間設定
    private final int fieldIndex; // 房間索引

    public TableFieldConfig(FieldConfig fieldConfig, int fieldIndex) {
        this.fieldConfig = fieldConfig;
        this.fieldIndex = fieldIndex;
    }

    /* 遊戲相關 */
    @Override
    public int getGameId() { return this.fieldConfig.getGameId(); }

    @Override
    public String getGameName() { return this.fieldConfig.getGameName();}

    @Override
    public String getGameNameCN() { return this.fieldConfig.getGameNameCN(); }

    @Override
    public short getMinUser() { return this.fieldConfig.getMinUser(); }

    @Override
    public short getMaxUser() {
        return this.fieldConfig.getMaxUser();
    }



    /* 房間相關 */
    @Override
    public int getFieldIndex() { return fieldIndex; }

    @Override
    public String getFieldName() { return this.fieldConfig.getFieldConfigMap().get(fieldIndex).getFieldName(); }

    @Override
    public String getFieldNameCn(){ return this.fieldConfig.getFieldConfigMap().get(fieldIndex).getFieldNameCn(); }

    @Override
    public double getLimitMin() { return this.fieldConfig.getFieldConfigMap().get(this.fieldIndex).getLimitMin(); }

    @Override
    public double getLimitMax() { return this.fieldConfig.getFieldConfigMap().get(this.fieldIndex).getLimitMax(); }

    @Override
    public double getLimitKick() { return this.fieldConfig.getFieldConfigMap().get(this.fieldIndex).getLimitKick(); }

    @Override
    public double getBase() { return this.fieldConfig.getFieldConfigMap().get(fieldIndex).getBase(); }

    @Override
    public double getGameRate() { return this.fieldConfig.getFieldConfigMap().get(fieldIndex).getGameRate(); }

    @Override
    public short getPlay() { return this.fieldConfig.getFieldConfigMap().get(fieldIndex).getPlay(); }

    @Override
    public short getFeeType() { return this.fieldConfig.getFieldConfigMap().get(fieldIndex).getFeeType(); }

    @Override
    public CompanyFieldObj getCompanyFieldObj(){
        return this.fieldConfig.getFieldConfigMap().get(this.fieldIndex);
    }

    @Override
    public FieldConfig getFieldConfig() {
        return fieldConfig;
    }
}
