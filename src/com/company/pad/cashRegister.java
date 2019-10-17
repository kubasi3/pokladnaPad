package com.company.pad;

import java.util.ArrayList;
import java.util.HashMap;

public class cashRegister {
    private String name;
    private String cashRegisterID;
    private float cash;
    private ArrayList<item> items;

    public cashRegister(String name, float cash, ArrayList<item> items) {
        this.name = name;
        itemId id_ = new itemId();
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

    public float buyItems(ArrayList<String> itemsID, float cash_){
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for(String x:itemsID){
            if(!hm.containsKey(x)){
                hm.put(x,1);
            }else{
                hm.put(x, hm.get(x) +1);
            }
        }
        for (item i:this.items) {
            if (itemsID.contains(i.getId())){
                if((i.getPrice()* hm.get(i.getId()))<=cash_) {
                    this.cash += i.getPrice()* hm.get(i.getId());
                    cash_-=i.getPrice()* hm.get(i.getId());
                    i.decreaseCount(hm.get(i.getId()));
                }else {
                    System.err.println("malo peněz");
                }
            }
        }
        return cash_;
    }

    public float getSumPrice(ArrayList<String> itemsID){
        float price = 0;
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for(String x:itemsID){
            if(!hm.containsKey(x)){
                hm.put(x,1);
            }else{
                hm.put(x, hm.get(x) +1);
            }
        }
        for (item i:this.items) {
            if (itemsID.contains(i.getId())){
                price += i.getPrice()* hm.get(i.getId());
            }
        }
        return price;
    }

    public String getItems(){
        String out = "{";
        for (item i:this.items) {
            out = out + i.toString()+", " ;
        }
        out = out.substring(0, out.length() - 2) + '}';
        return out;
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
