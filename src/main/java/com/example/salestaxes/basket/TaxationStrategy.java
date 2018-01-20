package com.example.salestaxes.basket;

public interface TaxationStrategy {

    double getTaxes(double basePrice, double taxRate);
}
