package com.itacademy.Products.Flowers;

public class SqlFlower extends Flower{

    int sqlId;

    public SqlFlower(int flowerShopId, String name, double price, int stock, String colour, int sqlId) {
        super(flowerShopId,name, price, stock, colour);
        this.sqlId = sqlId;
    }

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }
}
