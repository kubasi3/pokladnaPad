package pokladna;

public class TableRow {
    private String name;
    private float price;
    private float sumPrice;
    private int count;


    TableRow(String name, float price, float sumPrice, int count) {
        this.name = name;
        this.price = price;
        this.sumPrice = sumPrice;
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public float getSumPrice() {
        return sumPrice;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
