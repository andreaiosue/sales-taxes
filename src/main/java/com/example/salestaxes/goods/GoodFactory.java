package com.example.salestaxes.goods;

public enum GoodFactory {
    GENERIC_GOOD(10.0),
    BOOK(0.0),
    FOOD(0.0),
    MEDICAL_PRODUCT(0.0);

    private final double taxRate;

    GoodFactory(double taxRate) {
        this.taxRate = taxRate;
    }

    public Good getGood(String name, double basePrice) {
        return new BasicGood(name, taxRate, basePrice);
    }
}
