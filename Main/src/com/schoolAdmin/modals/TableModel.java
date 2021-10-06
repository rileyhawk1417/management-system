package com.schoolAdmin.modals;

// import javafx.beans.property.String;

// public class TableModel {
//    String idColumn; 
//    String itemName; 
//    String description;
//    String unitsConsumed; 
//    String unitsRemaining; 
//    String restock;
//    String genderVal; 

//     public TableModel(String idColumn, String itemName, String description, String unitsConsumed, String unitsRemaining, String restock, String genderVal){
//         this.idColumn = idColumn;
//         this.itemName = itemName;
//         this.description = description;
//         this.unitsConsumed = unitsConsumed;
//         this.unitsRemaining = unitsRemaining;
//         this.restock = restock;
//         this.genderVal = genderVal;
//     }
// //TODO: Type Mismatch from string to String
//     public String getId(){
//         return idColumn;
//     }

//     public void setId(String idColumn){
//         this.idColumn = idColumn;
//     }

//     public String getName(){
//         return itemName;
//     }

//     public void setName(String itemName){
//         this.itemName = itemName;
//     }
    
//     public String getDetail(){
//         return description;
//     }

//     public void setDetails(String description){
//         this.description = description;
//     }

//     public String getUnits_consumed(){
//         return unitsConsumed;
//     }

//     public void setUnits_consumed(String unitsConsumed){
//         this.unitsConsumed = unitsConsumed;
//     }

//     public String getUnits_remaining(){
//         return unitsRemaining;
//     }

//     public void setUnits_remaining(String unitsRemaining){
//         this.unitsRemaining = unitsRemaining;
//     }
//     public String getRestock(){
//         return restock;
//     }

//     public void setRestock(String restock){
//         this.restock = restock;
//     }
//     public String getGender(){
//         return genderVal;
//     }

//     public void setGender(String genderVal){
//         this.genderVal = genderVal;
//     }

// }


import javafx.beans.property.*;

public class TableModel {
    
    public String idCol = new String(); 
    public String item_name = new String(); 
    public String desc = new String();
    public String units_used = new String(); 
    public String units_left = new String(); 
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

    public String getRestock() {
        return this.restock;
    }

    public void setRestock(String restock) {
        this.restock = restock;
    }

    public TableModel(String idCol, String item_name, String desc, String units_used, String units_left, String restock) {
        this.idCol = idCol;
        this.item_name = item_name;
        this.desc = desc;
        this.units_used = units_used;
        this.units_left = units_left;
        this.restock = restock;

    }

//TODO: Type Mismatch from string to String

    // public TableModel(String idCol, String item_name, String desc, String units_used, String units_left, String restock, String genderVal){
    //     this.idCol = new String(idCol);
    //     this.item_name = new String(item_name);
    //     this.desc = new String(desc);
    //     this.units_used= new String(units_used);
    //     this.units_left = new String(units_left);
    //     this.restock = new String(restock);
    //     this.genderVal = new String(genderVal);
    // }

    // public final StringProperty idColProperty(){
    //     return idCol;
    // }

    // public String getId(){
    //     return idCol.get();
    // }

    // public final void setId(String value){
    //     idCol.set(value);
    // }


    // public String getName(){
    //     return item_name.get();
    // }
    
    // public final void setName(String value){
    //     item_name.set(value);
    // }

    // public final StringProperty item_nameProperty(){
    //     return item_name;
    // }

    // public String getDesc(){
    //     return desc.get();
    // }
    
    // public final void setDesc(String value){
    //     desc.set(value);
    // }

    // public final StringProperty descProperty(){
    //     return desc;
    // }

    // public String getUnits_used(){
    //     return units_used.get();
    // }
    
    // public final void setUnits_used(String value){
    //     units_used.set(value);
    // }
    
    // public final StringProperty units_usedProperty(){
    //     return units_used;
    // }


    // public String getUnits_left(){
    //     return units_left.get();
    // }
    
    // public final void setUnits_left(String value){
    //     units_left.set(value);
    // }
    
    // public final StringProperty units_leftProperty(){
    //     return units_left;
    // }


    // public String getRestock(){
    //     return restock.get();
    // }
    
    // public final void setRestock(String value){
    //     restock.set(value);
    // }
    
    // public final StringProperty restockProperty(){
    //     return restock;
    // }


    // public String getGenderVal(){
    //     return genderVal.get();
    // }
    
    // public final void setGenderVal(String value){
    //     genderVal.set(value);
    // }
    
    // public final StringProperty genderValProperty(){
    //     return genderVal;
    // }


}

