import com.example.salestaxes.basket.BasicTaxation;
import com.example.salestaxes.basket.Basket;
import com.example.salestaxes.basket.BasketItem;
import com.example.salestaxes.basket.TaxationStrategy;
import com.example.salestaxes.goods.Good;
import com.example.salestaxes.goods.GoodFactory;
import com.example.salestaxes.goods.ImportedGood;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    private static TaxationStrategy TAXATION_STRATEGY;
    private static DecimalFormat DECIMAL_FORMAT;

    private Basket basket;

    @BeforeClass
    public static void setUpClass() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(',');
        TAXATION_STRATEGY = new BasicTaxation();
        DECIMAL_FORMAT = new DecimalFormat("0.00", decimalFormatSymbols);
    }

    @Before
    public void setUp() {
        basket = new Basket(TAXATION_STRATEGY);
    }

    @After
    public void tearDown() {
        basket.print(DECIMAL_FORMAT);
        System.out.println();
    }

    @Test
    public void basket01() {

//        Input 1:
//        1 book at 12.49
//        1 music CD at 14.99
//        1 chocolate bar at 0.85

//        Output 1:
//        1 book : 12.49
//        1 music CD: 16.49
//        1 chocolate bar: 0.85
//        Sales Taxes: 1.50
//        Total: 29.83

//        GIVEN
        Good goodOne = GoodFactory.BOOK.getGood("book", 12.49);
        Good goodTwo = GoodFactory.GENERIC_GOOD.getGood("music CD", 14.99);
        Good goodThree = GoodFactory.FOOD.getGood("chocolate bar", 0.85);

//        WHEN
        basket.add(1, goodOne);
        basket.add(1, goodTwo);
        basket.add(1, goodThree);

//        THEN
        BasketItem[] items = basket.getItems().stream().toArray(BasketItem[]::new);
        assertEquals("12.49", DECIMAL_FORMAT.format(items[0].getPrice()));
        assertEquals("16.49", DECIMAL_FORMAT.format(items[1].getPrice()));
        assertEquals("0.85", DECIMAL_FORMAT.format(items[2].getPrice()));
        assertEquals("1.50", DECIMAL_FORMAT.format(basket.getSalesTaxes()));
        assertEquals("29.83", DECIMAL_FORMAT.format(basket.getTotal()));
    }

    @Test
    public void basket02() {

//        Input 2:
//        1 imported box of chocolates at 10.00
//        1 imported bottle of perfume at 47.50

//        Output 2:
//        1 imported box of chocolates: 10.50
//        1 imported bottle of perfume: 54.65
//        Sales Taxes: 7.65
//        Total: 65.15

//        GIVEN
        Good goodOne = new ImportedGood(GoodFactory.FOOD.getGood("imported box of chocolates", 10.00));
        Good goodTwo = new ImportedGood(GoodFactory.GENERIC_GOOD.getGood("imported bottle of perfume", 47.50));

//        WHEN
        basket.add(1, goodOne);
        basket.add(1, goodTwo);

//        THEN
        BasketItem[] items = basket.getItems().stream().toArray(BasketItem[]::new);
        assertEquals("10.50", DECIMAL_FORMAT.format(items[0].getPrice()));
        assertEquals("54.65", DECIMAL_FORMAT.format(items[1].getPrice()));
        assertEquals("7.65", DECIMAL_FORMAT.format(basket.getSalesTaxes()));
        assertEquals("65.15", DECIMAL_FORMAT.format(basket.getTotal()));
    }

    @Test
    public void basket03() {

//        Input 3:
//        1 imported bottle of perfume at 27.99
//        1 bottle of perfume at 18.99
//        1 packet of headache pills at 9.75
//        1 box of imported chocolates at 11.25

//        Output 3:
//        1 imported bottle of perfume: 32.19
//        1 bottle of perfume: 20.89
//        1 packet of headache pills: 9.75
//        1 imported box of chocolates: 11.85
//        Sales Taxes: 6.70
//        Total: 74.68

//        GIVEN
        Good goodOne = new ImportedGood(GoodFactory.GENERIC_GOOD.getGood("imported bottle of perfume", 27.99));
        Good goodTwo = GoodFactory.GENERIC_GOOD.getGood("bottle of perfume", 18.99);
        Good goodThree = GoodFactory.MEDICAL_PRODUCT.getGood("packet of headache pills", 9.75);
        Good goodFour = new ImportedGood(GoodFactory.FOOD.getGood("imported box of chocolates", 11.25));

//        WHEN
        basket.add(1, goodOne);
        basket.add(1, goodTwo);
        basket.add(1, goodThree);
        basket.add(1, goodFour);

//        THEN
        BasketItem[] items = basket.getItems().stream().toArray(BasketItem[]::new);
        assertEquals("32.19", DECIMAL_FORMAT.format(items[0].getPrice()));
        assertEquals("20.89", DECIMAL_FORMAT.format(items[1].getPrice()));
        assertEquals("9.75", DECIMAL_FORMAT.format(items[2].getPrice()));
        assertEquals("11.85", DECIMAL_FORMAT.format(items[3].getPrice()));
        assertEquals("6.70", DECIMAL_FORMAT.format(basket.getSalesTaxes()));
        assertEquals("74.68", DECIMAL_FORMAT.format(basket.getTotal()));
    }

}
