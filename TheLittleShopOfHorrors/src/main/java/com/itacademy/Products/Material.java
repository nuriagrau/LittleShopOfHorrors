package com.itacademy.Products;

public enum Material {


    WOOD("Wood"),
    PLASTIC("Plastic"),
    ;

    private String name;


    Material(String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean findByName(String name) {
        boolean found = false;
        for (Material material : values()) {
            if (material.getName().equalsIgnoreCase(name)) {
                found = true;
            }
        }
        return found;
    }
}
