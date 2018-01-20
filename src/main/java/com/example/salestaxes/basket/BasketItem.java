package com.example.salestaxes.basket;

import com.example.salestaxes.goods.Good;

import java.text.DecimalFormat;
import java.util.Objects;

public class BasketItem implements Printable {

    private final int quantity;
    private final String description;
    private final double price;
    private final double taxes;

    public BasketItem(int quantity, Good good, TaxationStrategy taxationStrategy) {
        this.quantity = quantity;
        this.description = good.getName();

        double basePrice = this.quantity * good.getBasePrice();

        this.taxes = taxationStrategy.getTaxes(basePrice, good.getTaxRate());
        this.price = basePrice + this.taxes;
    }

    @Override
    public void print(DecimalFormat decimalFormat) {
        System.out.printf("%d %s: %s\n", quantity, description, decimalFormat.format(price));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem item = (BasketItem) o;
        return Double.compare(item.price, price) == 0 &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price);
    }

    public double getPrice() {
        return price;
    }

    public double getTaxes() {
        return taxes;
    }
}
