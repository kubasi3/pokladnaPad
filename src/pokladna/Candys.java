package pokladna;

public class Candys extends Item {
    private String type;

    public Candys(String name, float price, int count, String type) {
        super(name, price, count);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "item{" +
                "name='" + this.getName() + '\'' +
                ", id='" + this.getId() + '\'' +
                "type='" + this.type + '\'' +
                ", price=" + this.getPrice() +
                ", count=" + this.getCount() +
                '}';
    }
}
