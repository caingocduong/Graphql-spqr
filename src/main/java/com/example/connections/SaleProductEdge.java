package com.example.connections;

import com.example.models.SaleProduct;

public class SaleProductEdge {
    private SaleProduct node;
    private String cursor;

    public SaleProductEdge() {}

    public SaleProductEdge(SaleProduct node, String cursor) {
        this.node = node;
        this.cursor = cursor;
    }

    public SaleProduct getNode() {

        return node;
    }

    public void setNode(SaleProduct node) {
        this.node = node;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
