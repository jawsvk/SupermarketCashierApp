package Example;

import java.math.BigDecimal;
import java.util.*;

class App {
    private Map<String, Item> itemList;
    private List<Item> bill;

    public App() {
        itemList = new HashMap<>();
        bill = new ArrayList<>();
        itemList.put("01001", new Item("01001", "Lemon", 1.30));
        itemList.put("01002", new Item("01002", "Apple", 1.60));
        itemList.put("01003", new Item("01003", "Mango", 0.99));
    }

    List<Item> getBill() {
        return bill;
    }

    void addItemToBill(String barcode) {
        Item item = itemList.get(barcode);
        bill.add(item);
    }

    void removeItem(String s) {
        bill.remove(itemList.get(s));
    }

    String getSummary() {
        StringBuilder summary = new StringBuilder();
        BigDecimal itemTotal;
        BigDecimal total = BigDecimal.ZERO;

        final Item[] distinctItems = bill.stream().distinct().toArray(Item[]::new);
        int itemCount;
        for (Item item : distinctItems) {
            itemCount = getItemCount(item.barcode);
            itemTotal = BigDecimal.valueOf(itemCount*item.price).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            total = total.add(itemTotal);
            summary.append(String.format("%s x %s @ %.2f = %s\n",itemCount,item.name,item.price, itemTotal.toString()));

        }
        summary.append("Total : ").append(total.setScale(2,BigDecimal.ROUND_HALF_EVEN));
        return summary.toString();

    }

    private int getItemCount(String barcode) {
        int count = 0;
        for (Item item : bill) {
            if (barcode.equals(item.barcode)) count++;
        }
        return count;
    }
}
