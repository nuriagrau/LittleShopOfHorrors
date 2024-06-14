package com.itacademy.Products;

import java.util.HashMap;
import java.util.Map;

public enum ProductType {
    TREE("tree", "height(cm)"),
    FLOWER("flower", "colour"),
    DECORATION("decoration", "material(Wood, Plastic)");

    public final String value;
    public final String typeParameter;

    private static final Map<String, String> BY_VALUE = new HashMap<>();
    private static final Map<String, String> BY_TYPE_PARAMETER = new HashMap<>();

    static {
        for (ProductType pt: values()) {
            BY_VALUE.put(pt.value, pt.value);
            BY_TYPE_PARAMETER.put(pt.value, pt.typeParameter);
        }
    }
    private ProductType(String value, String typeParameter) {
        this.value = value;
        this.typeParameter = typeParameter;
    }

    public String getValue() {
        return value;
    }

    public static String valueOfValue(String value) {
        return  BY_VALUE.get(value);
    }

    public static String valueOfTypeParameter(String value) {
        return BY_TYPE_PARAMETER.get(value);
    }

    public static boolean findByValue(String value) {
        boolean found = false;
        for (ProductType pt : values()) {
            if (pt.getValue().equalsIgnoreCase(value)) {
                found = true;
            }
        }
        return found;
    }
}
