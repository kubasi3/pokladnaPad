package pokladna;

public abstract class Item implements interfaceItem {
    private String name;
    private String id;
    private float price;
    private int count;

    public void newItems(int count) {
        this.count = count;
    }

    public void changePrice(float price) {
        this.price = price;
    }

    public float boughtItem(String id) {
        this.count--;
        return this.getPrice();
    }

    public Item(String name, float price, int count) {
        this.name = name;
        ItemId id_ = new ItemId();
        this.id = id_.getRandomUUIDString();
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void decreaseCount(int count_) {
        this.count -= count_;
    }

    public void decreaseCount() {
        this.count--;
    }
}
