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

        //Nakup
        ArrayList<String> buy = new ArrayList<>();
        buy.add(item.getId());
        buy.add(item.getId());
        buy.add(item.getId());
        buy.add(item1.getId());
        buy.add(item1.getId());

        //Pokladna
        cashRegister pokladna = new cashRegister("pakladna1", 2000, items);

        System.out.println(pokladna.toString());

        System.out.println("Vratit: " + pokladna.buyItems(buy,2000));

        System.out.println(pokladna.toString());

    }
}
