package com.itacademy.Products.Trees;

public class SqlTree extends Tree{

    int sqlId;

    public SqlTree(int flowerShopId, String name, double price, int stock, int heightCm, int sqlId) {
        super(flowerShopId, name, price, stock, heightCm);
        this.sqlId = sqlId;
    }

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }
}
