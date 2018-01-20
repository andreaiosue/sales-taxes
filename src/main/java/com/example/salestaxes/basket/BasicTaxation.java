package com.example.salestaxes.basket;

public class BasicTaxation implements TaxationStrategy {

    private static final double TAXES_ROUNDING = 20.0;

    @Override
    public double getTaxes(double basePrice, double taxRate) {
        double taxes = basePrice * taxRate / 100.0;
        return Math.ceil(taxes * TAXES_ROUNDING) / TAXES_ROUNDING;
    }
}
