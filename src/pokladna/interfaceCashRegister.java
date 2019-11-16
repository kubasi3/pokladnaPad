package pokladna;

import java.util.ArrayList;

public interface interfaceCashRegister {
    String getName();

    String getCashRegisterID();

    float getCashNow();

    String buyItems(ArrayList<String> itemsID, float cash_);

    float getSumPrice(ArrayList<String> itemsID);

    ArrayList<Item> getItems();
}
