package pokladna;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Item> creatItems() {
        ArrayList<Item> items = new ArrayList<>();
        //load data from "DB" :)
        Ticket item = new Ticket("Listek", 20, 30, "student");
        Ticket item1 = new Ticket("Listek", 60, 30, "dospeli");
        Ticket item2 = new Ticket("Listek", 150, 15, "rodinné");
        Ticket item3 = new Ticket("Listek", 30, 30, "senior");

        items.add(item);
        items.add(item1);
        items.add(item2);
        items.add(item3);

        Candys item4 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item5 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item6 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item4);
        items.add(item5);
        items.add(item6);
        return items;
    }

    public static CashRegister creatCashRegister() {
        return new CashRegister("POKLADNA-1", 2000,"Kč", creatItems());
    }
}
