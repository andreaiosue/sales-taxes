package com.example.salestaxes.goods;

public class BasicGood implements Good {

    private final String name;
    private final double taxRate;
    private final double basePrice;

    public BasicGood(String name, double taxRate, double basePrice) {
        this.name = name;
        this.taxRate = taxRate;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getBasePrice() {
        return basePrice;
    }

}
