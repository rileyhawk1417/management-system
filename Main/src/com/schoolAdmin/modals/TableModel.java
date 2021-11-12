package com.schoolAdmin.modals;

public class TableModel {

    public String idCol = new String();
    public String item_name = new String();
    public String desc = new String();
    public String units_used = new String();
    public String units_left = new String();
    public String unit_price = new String();
    public String restock = new String();


    public String getIdCol() {
        return this.idCol;
    }

    public void setIdCol(String idCol) {
        this.idCol = idCol;
    }

    public String getItem_name() {
        return this.item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUnits_used() {
        return this.units_used;
    }

    public void setUnits_used(String units_used) {
        this.units_used = units_used;
    }

    public String getUnits_left() {
        return this.units_left;
    }

    public void setUnits_left(String units_left) {
        this.units_left = units_left;
    }

    public String getUnit_price(){
        return this.unit_price;
    }

    public void setUnit_price(String unit_price){
        this.unit_price = unit_price;
    }

    public String getRestock() {
        return this.restock;
    }

    public void setRestock(String restock) {
        this.restock = restock;
    }

    public TableModel(String idCol, String item_name, String desc, String units_used, String units_left, String restock, String unit_price) {
        this.idCol = idCol;
        this.item_name = item_name;
        this.desc = desc;
        this.units_used = units_used;
        this.units_left = units_left;
        this.unit_price = unit_price;
        this.restock = restock;
    }
}
