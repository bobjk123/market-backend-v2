package com.tecnm.merida.market_backend_v2.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class PurchaseItem {

    @JsonAlias({"idProducto", "productId"})
    private int id;

    @JsonAlias({"cantidad"})
    private int quantity;

    private double total;

    @JsonAlias({"estado"})
    private boolean active;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
