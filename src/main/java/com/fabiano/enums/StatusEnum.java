package com.fabiano.enums;

public enum StatusEnum {

    ATIVO("ativo"),
    INATIVO("inativo");

    private String value;

    private StatusEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
