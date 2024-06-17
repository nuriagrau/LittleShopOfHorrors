package com.itacademy.Products.Decorations;

public class SqlDecoration extends Decoration {

    int sqlId;

    public SqlDecoration(int flowerShopId, String name, double price, int stock, String material, int sqlId) {
        super(flowerShopId, name, price, stock, material);
        this.sqlId = sqlId;
    }

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }
}
