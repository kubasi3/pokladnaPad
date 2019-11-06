package pokladna;

import java.util.ArrayList;

public interface interfaceCashRegister {
    String getName();

    String getCashRegisterID();

    float getCashNow();

    float buyItems(ArrayList<String> itemsID, float cash_);

    float getSumPrice(ArrayList<String> itemsID);

    String getItems();
}
