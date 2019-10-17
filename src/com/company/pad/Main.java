package com.company.pad;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    //Zbozi
        ArrayList<item> items = new ArrayList<item>();
        ticket item = new ticket("Listek", 20, 30, "student");
        ticket item1 = new ticket("Listek", 60, 30, "dospeli");
        ticket item2 = new ticket("Listek", 150, 15, "rodinné");
        ticket item3 = new ticket("Listek", 30, 30, "senior");

        items.add(item);
        items.add(item1);
        items.add(item2);
        items.add(item3);

        candys item4 = new candys("Mars", 30, 30, "coko_tyčinka");
        candys item5 = new candys("Bebe-cosi", 10, 30, "oplatek");
        candys item6 = new candys("Bohemia_bramburky", 150, 15, "brambůrky");

        items.add(item4);
        items.add(item5);
        items.add(item6);

        //Nakup
        ArrayList<String> buy = new ArrayList<>();
        buy.add(item.getId());
        buy.add(item.getId());
        buy.add(item.getId());
        buy.add(item1.getId());
        buy.add(item1.getId());
        buy.add(item4.getId());
        buy.add(item4.getId());
        buy.add(item5.getId());
        buy.add(item5.getId());
        buy.add(item6.getId());

        //Pokladna
        cashRegister pokladna = new cashRegister("pakladna1", 2000, items);

        System.out.println(pokladna.toString());

        System.out.println("Zaplatit: " + pokladna.getSumPrice(buy));

        System.out.println("Vratit: " + pokladna.buyItems(buy,2000));

        System.out.println(pokladna.toString());

    }
}
