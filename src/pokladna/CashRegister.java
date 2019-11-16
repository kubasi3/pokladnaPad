package pokladna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CashRegister implements interfaceCashRegister, Serializable {
    private String name;
    private String cashRegisterID;
    private float cash;
    private ArrayList<Item> items;

    public CashRegister(String name, float cash, ArrayList<Item> items) {
        this.name = name;
        ItemId id_ = new ItemId();
        this.cashRegisterID = id_.getRandomUUIDString();
        this.cash = cash;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getCashRegisterID() {
        return cashRegisterID;
    }

    public float getCashNow() {
        return cash;
    }

    public String buyItems(ArrayList<String> itemsID, float cash_) {
        HashMap<String, Integer> hm = new HashMap<>();
        for (String x : itemsID) {
            if (!hm.containsKey(x)) {
                hm.put(x, 1);
            } else {
                hm.put(x, hm.get(x) + 1);
            }
        }
        for (Item i : this.items) {
            if (itemsID.contains(i.getId())) {
                if ((i.getPrice() * hm.get(i.getId())) <= cash_) {
                    this.cash += i.getPrice() * hm.get(i.getId());
                    cash_ -= i.getPrice() * hm.get(i.getId());
                    i.decreaseCount(hm.get(i.getId()));
                } else {
                    return "Not enough money!";
                }
            }
        }
        return String.valueOf(cash_);
    }

    public ArrayList<TableRow> getTableRows(ArrayList<String> itemsID) {
        HashMap<Item, Integer> hm = new HashMap<>();
        for (String x : itemsID) {
            for (Item i : this.items) {
                if (i.getId().equals(x)) {
                    if (!hm.containsKey(i)) {
                        hm.put(i, 1);
                    } else {
                        hm.put(i, hm.get(i) + 1);
                    }
                }
            }
        }
        ArrayList<TableRow> rows = new ArrayList<>();
        for (Item i : this.items) {
            if (itemsID.contains(i.getId())) {
                int count = hm.get(i);
                if (i instanceof Ticket) {
                    Ticket ticket = (Ticket) i;
                    rows.add(new TableRow(ticket.getName() + "-" + ticket.getType(), ticket.getPrice(), ticket.getPrice() * count, count));
                } else {
                    Candys candy = (Candys) i;
                    rows.add(new TableRow(candy.getName() + "-" + candy.getType(), candy.getPrice(), candy.getPrice() * count, count));
                }
            }
        }
        return rows;
    }


    public float getSumPrice(ArrayList<String> itemsID) {
        float price = 0;
        HashMap<String, Integer> hm = new HashMap<>();
        for (String x : itemsID) {
            if (!hm.containsKey(x)) {
                hm.put(x, 1);
            } else {
                hm.put(x, hm.get(x) + 1);
            }
        }
        for (Item i : this.items) {
            if (itemsID.contains(i.getId())) {
                price += i.getPrice() * hm.get(i.getId());
            }
        }
        return price;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    @Override
    public String toString() {
        return "cashRegister{" +
                "name='" + name + '\'' +
                ", cashRegisterID='" + cashRegisterID + '\'' +
                ", cash=" + cash +
                ", items=" + this.getItems() +
                '}';
    }
}
