package com.example.salestaxes.goods;

public abstract class GoodDecorator implements Good {

    protected Good good;

    public GoodDecorator(Good good) {
        this.good = good;
    }

    public String getName() {
        return good.getName();
    }

    public double getTaxRate() {
        return good.getTaxRate();
    }

    public double getBasePrice() {
        return good.getBasePrice();
    }
}
