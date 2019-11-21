package pokladna;

import java.util.ArrayList;
import java.util.HashMap;

public interface interfaceCashRegister {
    String getName();

    String getCashRegisterID();

    float getCashNow();

    String buyItems(HashMap<String, Integer> shoppingList, float cash_);

    float getSumPrice(HashMap<String, Integer> shoppingList);

    ArrayList<Item> getItems();
}
