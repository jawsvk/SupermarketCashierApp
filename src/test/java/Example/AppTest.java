package Example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void addLemonToBill_ReturnPriceOfLemon() {
        //given
        String barcode = "01001";
        App app = new App();

        //when
        app.addItemToBill(barcode);
        List<Item> result = app.getBill();

        //then
        double expected = 1.30;
        assertEquals(expected,result.get(0).price, 2);
    }

    @Test
    public void addAppleToBill_ReturnPriceOfApple() {
        //given
        String barcode = "01002";
        App app = new App();

        //when
        app.addItemToBill(barcode);
        List<Item> result = app.getBill();

        //then
        double expected = 1.60;
        assertEquals(expected,result.get(0).price, 2);
    }

    @Test
    public void addAppleAndLemonToBill_ReturnCountOfTwo() {
        //given
        String[] barcodes =new String[]{"01002","01001"};
        App app = new App();

        //when
        for(String s: barcodes){
            app.addItemToBill(s);
        }
        List<Item> result = app.getBill();

        //then
        int expected = 2;
        assertEquals(expected,result.size());
    }

    @Test
    public void removeAppleFromBill_ReturnCountOfOne() {
        //given
        App app = new App();
        String[] barcodeList =new String[]{"01002","01001"};

        for(String s: barcodeList){
            app.addItemToBill(s);
        }

        //when
        app.removeItem("01002");
        List<Item> result = app.getBill();

        //then
        int expected = 1;
        assertEquals(expected,result.size());
    }

    @Test
    public void addThreeLemonsThreeApplesThreeMangoes_ReturnSummaryAndTotal() {
        //given
        App app = new App();
        String[] barcodes =new String[]{"01001","01002","01003","01001","01002","01003","01001","01002","01003"};

        for(String s: barcodes){
            app.addItemToBill(s);
        }

        //when
        String result = app.getSummary();

        //then
        String expected = "3 x Lemon @ 1.30 = 3.90\n" +
        "3 x Apple @ 1.60 = 4.80\n" +
        "3 x Mango @ 0.99 = 2.97\n" +
        "Total : 11.67";

        assertEquals(expected,result);
    }
}
