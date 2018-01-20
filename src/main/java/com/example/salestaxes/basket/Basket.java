package com.example.salestaxes.basket;

import com.example.salestaxes.goods.Good;

import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.Set;

public class Basket implements Printable {

    private final TaxationStrategy taxationStrategy;
    private final Set<BasketItem> items;
    private double salesTaxes;
    private double total;

    public Basket(TaxationStrategy taxationStrategy) {
        this.taxationStrategy = taxationStrategy;
        this.items = new LinkedHashSet<>();
    }

    public void add(int quantity, Good good) {
        BasketItem item = new BasketItem(quantity, good, taxationStrategy);
        salesTaxes += item.getTaxes();
        total += item.getPrice();
        items.add(item);
    }

    @Override
    public void print(DecimalFormat decimalFormat) {

        items.stream().forEach(item -> item.print(decimalFormat));

        System.out.printf("Sales Taxes: %s\n", decimalFormat.format(salesTaxes));
        System.out.printf("Total: %s\n", decimalFormat.format(total));
    }

    public Set<BasketItem> getItems() {
        return items;
    }

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public double getTotal() {
        return total;
    }
}
