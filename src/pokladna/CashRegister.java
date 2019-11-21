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

    public String buyItems(HashMap<String, Integer> shoppingList, float cash_) {
        for (Item i : this.items) {
            if (shoppingList.containsKey(i.getId())) {
                if ((i.getPrice() * shoppingList.get(i.getId())) <= cash_) {
                    this.cash += i.getPrice() * shoppingList.get(i.getId());
                    cash_ -= i.getPrice() * shoppingList.get(i.getId());
                    i.decreaseCount(shoppingList.get(i.getId()));
                } else {
                    return "Not enough money!";
                }
            }
        }
        return String.valueOf(cash_);
    }

    public ArrayList<TableRow> getTableRows(HashMap<String, Integer> shoppingList) {
        ArrayList<TableRow> rows = new ArrayList<>();
        for (Item i : this.items) {
            if (shoppingList.containsKey(i.getId())) {
                int count = shoppingList.get(i.getId());
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


    public float getSumPrice(HashMap<String, Integer> shoppingList) {
        float price = 0;
        for (Item i : this.items) {
            if (shoppingList.containsKey(i.getId())) {
                price += i.getPrice() * shoppingList.get(i.getId());
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
