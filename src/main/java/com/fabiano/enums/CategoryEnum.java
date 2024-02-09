package com.fabiano.enums;

public enum CategoryEnum {
    BACK_END("back-end"),
    FRONT_END("front-end");


    private String value;

    private CategoryEnum(String value){
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
