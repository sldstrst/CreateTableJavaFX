package com.example.javafxproject.model;


public enum TypeAttr {
    ENTRYT("e", ""),
    INT("i", "integer"),
    STRING("s", "character varying(40)"),
    BOOLEAN("b", "boolean"),
    DOUBLE("d", "double precision"),
    CHAR("c", "char");



    private String code;
    private String text;

    TypeAttr(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getCode(String typeText) {
        TypeAttr atr = getByText(typeText);

        return code;
    }

    public String getText() {
        return text;
    }

    public String getText(String typeCode) {
        for (TypeAttr t : TypeAttr.values()) {
            if (t.code.equals(typeCode)) {
                return t.text;
            }
        }
        return null;
    }

    public static TypeAttr getByCode (String typeCode){
        for (TypeAttr t : TypeAttr.values()) {
            if (t.code.equals(typeCode)) {
                return t;
            }
        }
        return null;
    }

    public static TypeAttr getByText (String typeText){
        for (TypeAttr c : TypeAttr.values()) {
            if (c.code.equals(typeText)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
