package com.example.salestaxes.goods;

public class ImportedGood extends GoodDecorator {

    private static final double TAX_RATE = 5.0;

    public ImportedGood(Good good) {
        super(good);
    }

    @Override
    public double getTaxRate() {
        return super.getTaxRate() + TAX_RATE;
    }
}
