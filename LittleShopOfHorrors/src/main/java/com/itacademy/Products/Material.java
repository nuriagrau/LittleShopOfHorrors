package com.itacademy.Products;

public enum Material {

    WOOD("Wood"),
    PLASTIC("Plastic");

    public final String value;


    Material(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean findByValue(String value) {
        boolean found = false;
        for (Material material : values()) {
            if (material.getValue().equalsIgnoreCase(value)) {
                found = true;
            }
        }
        return found;
    }

}
