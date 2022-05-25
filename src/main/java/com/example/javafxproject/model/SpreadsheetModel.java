package com.example.javafxproject.model;


import javafx.beans.property.SimpleBooleanProperty;

public class SpreadsheetModel {
    private String AttributeName;
    private String TypeAttribute;
//    private TypeAttr TypeAttribute;
    private Boolean primaryKey;
    private Boolean uniqueAttribute;
    private String defaultValue;

//    public SpreadsheetModel(String AttributeName,TypeAttr TypeAttribute, Boolean primaryKey, Boolean uniqueAttribute, String defaultValue){
    public SpreadsheetModel(String AttributeName,String TypeAttribute, Boolean primaryKey, Boolean uniqueAttribute, String defaultValue){
        this.AttributeName = AttributeName;
        this.TypeAttribute = TypeAttribute;
//        this.TypeAttribute = TypeAttribute;
        this.primaryKey = primaryKey;
        this.uniqueAttribute = uniqueAttribute;
        this.defaultValue = defaultValue;
    }

    public void setTypeAttribute(String typeAttribute) {
//    public void setTypeAttribute(TypeAttr typeAttribute) {
        TypeAttribute = typeAttribute;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setAttributeName(String attributeName) {
        AttributeName = attributeName;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setUniqueAttribute(Boolean uniqueAttribute) {
        this.uniqueAttribute = uniqueAttribute;
    }

    public String getTypeAttribute() {
        return TypeAttribute;
    }

    public String getAttributeName() {
        return AttributeName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean getPrimaryKey(){
        return primaryKey;
    }

    public boolean getUniqueAttribute(){
        return uniqueAttribute;
    }
}
