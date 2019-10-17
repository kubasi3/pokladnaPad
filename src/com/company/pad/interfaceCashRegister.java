package com.company.pad;

import java.util.ArrayList;

public interface interfaceCashRegister {
    public String getName();
    public String getCashRegisterID();
    public float getCashNow();
    public float buyItems(ArrayList<String> itemsID, float cash_);
    public float getSumPrice(ArrayList<String> itemsID);
    public String getItems();
}
