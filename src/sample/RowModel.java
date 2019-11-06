package sample;

import javafx.beans.property.SimpleStringProperty;

public class RowModel {

    private SimpleStringProperty name;
    private SimpleStringProperty count;
    private SimpleStringProperty priceItem;
    private SimpleStringProperty sumPrice;

    RowModel(String name, String count, String priceItem, String sumPrice) {
        this.name = new SimpleStringProperty(name);
        this.count = new SimpleStringProperty(count);
        this.sumPrice = new SimpleStringProperty(sumPrice);
        this.priceItem = new SimpleStringProperty(priceItem);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getPriceItem() {
        return priceItem.get();
    }

    public void setPriceItem(String priceItem) {
        this.priceItem = new SimpleStringProperty(priceItem);
    }

    public String getCount() {
        return count.get();
    }

    public void setCount(String count) {
        this.count = new SimpleStringProperty(count);
    }

    public String getSumPrice() {
        return sumPrice.get();
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = new SimpleStringProperty(sumPrice);
    }
}
