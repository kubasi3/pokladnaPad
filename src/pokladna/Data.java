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


        Candys item7 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item8 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item9 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item7);
        items.add(item8);
        items.add(item9);

        Candys item41 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item51= new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item61 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item41);
        items.add(item51);
        items.add(item61);

        Candys item42 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item52 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item62 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item42);
        items.add(item52);
        items.add(item62);

        Candys item43 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item53 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item63 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item43);
        items.add(item53);
        items.add(item63);

        Candys item44 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item54 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item64 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item44);
        items.add(item54);
        items.add(item64);

        Candys item45 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item55 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item65 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item45);
        items.add(item55);
        items.add(item65);

        Candys item46 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item56 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item66 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item46);
        items.add(item56);
        items.add(item66);

        Candys item47 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item57 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item67 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item47);
        items.add(item57);
        items.add(item67);

        Candys item48 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item58 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item68 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item48);
        items.add(item58);
        items.add(item68);

        Candys item49 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item59 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item69 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item49);
        items.add(item59);
        items.add(item69);
        Candys item40 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item50 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item60 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item40);
        items.add(item50);
        items.add(item60);


        Candys item401 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item501 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item601 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item401);
        items.add(item501);
        items.add(item601);

        Candys item402 = new Candys("Mars", 30, 30, "coko_tyčinka");
        Candys item502 = new Candys("Bebe-cosi", 10, 30, "oplatek");
        Candys item602 = new Candys("Bohemia", 150, 15, "brambůrky");

        items.add(item402);
        items.add(item502);
        items.add(item602);


        System.out.println(items.size());
        return items;
    }

    public static CashRegister creatCashRegister() {
        return new CashRegister("POKLADNA-1", 2000,"Kč", creatItems());
    }
}
