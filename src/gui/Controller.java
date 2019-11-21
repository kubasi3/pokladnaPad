package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pokladna.*;
import pokladna.TableRow;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private GridPane box;

    @FXML
    private TableView<RowModel> tableView;

    @FXML
    public TableColumn<RowModel, String> name;

    @FXML
    public TableColumn<RowModel, String> priceItem;

    @FXML
    public TableColumn<RowModel, String> countItems;

    @FXML
    public TableColumn<RowModel, String> sumPrice;

    @FXML
    private Label count;

    @FXML
    private Label returnCashLabel;

    @FXML
    private Label moneyInCashRegister;


    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<String, Integer> buy = new HashMap<>();
    private CashRegister cashRegister;

    private Stage window = Main.getPrimaryStage();

    private String fileName = "data";
    private String fileType = ".txt";

    private ObservableList<RowModel> rowModels = FXCollections.observableArrayList();

    @FXML
    private void buttonPay() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("PAY");
        dialog.setHeaderText("Pay: " + cashRegister.getSumPrice(buy) + "  " + cashRegister.getCurrency());
        dialog.setContentText("money: ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> {
            Float payed = null;
            try {
                payed = Float.parseFloat(s);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("This isn't number!!");
                alert.showAndWait();
            }
            if (payed != null) {
                String returnCash = cashRegister.buyItems(buy, payed);
                if (!returnCash.equals("Not enough money!")) {
                    returnCashLabel.setText("Return: " + returnCash + "  " + cashRegister.getCurrency());
                    buttonPrint();
                    moneyInCashRegister.setText("Money in Cash Register: " + cashRegister.getCashNow() +
                            "  " + cashRegister.getCurrency());
                    savePokladna();
                    buttonResetAfterPay();
                } else {
                    returnCashLabel.setText("Return: " + returnCash);
                }
            } else {
                returnCashLabel.setText("Return: error");
            }
        });
    }

    @FXML
    private void buttonPrint() {
        StringBuilder babisovka = new StringBuilder();
        for (TableRow i : cashRegister.getTableRows(buy)) {
            babisovka.append(i.getName());
            if (i.getCount() > 1) {
                babisovka.append("\n").append(i.getCount());
                babisovka.append("     X     ").append(i.getPrice());
                babisovka.append(" Kc     ").append(i.getSumPrice()).append("  ");
                babisovka.append(cashRegister.getCurrency()).append(" \n");
            } else {
                babisovka.append("\n" + "                        ");
                babisovka.append(i.getSumPrice()).append("  ");
                babisovka.append(cashRegister.getCurrency()).append("\n");
            }
        }
        System.out.println("------------Uctenka------------\n"
                + babisovka
                + "-------------------------------\nCelkova cena: "
                + cashRegister.getSumPrice(buy)
                + "  " + cashRegister.getCurrency()
        );
    }

    @FXML
    public void buttonReset() {
        buttonResetAfterPay();
        returnCashLabel.setText("Return: 0  " + cashRegister.getCurrency());

    }

    private void buttonResetAfterPay() {
        buy.clear();
        tableView.getItems().clear();
        moneyInCashRegister.setText("Money in Cash Register: " + cashRegister.getCashNow() +
                "  " + cashRegister.getCurrency());
        count.setText("Sum: 0  " + cashRegister.getCurrency());
        box.getChildren().clear();
        spawnButtons(items);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //load pokladna
        try {
            cashRegister = (CashRegister) SerializationUtil.deserializeObject(fileName + fileType);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        window.setOnCloseRequest(event -> {
            savePokladna();
            event.consume();
            System.exit(0);
        });
        window.setTitle(cashRegister.getName());
        items = cashRegister.getItems();
        spawnButtons(items);
        returnCashLabel.setText("Return: 0 " + cashRegister.getCurrency());
        count.setText("Sum: " + cashRegister.getSumPrice(buy) + " " + cashRegister.getCurrency());
        moneyInCashRegister.setText("Money in Cash Register: " + cashRegister.getCashNow() +
                "  " + cashRegister.getCurrency());
    }

    private void spawnButtons(ArrayList<Item> items) {
        List<Button> buttonlist = new ArrayList<>();
        for (Item x : items) {
            creatButton(x, buttonlist);
        }
        int x = 0, y = 0;
        for (Button b : buttonlist) {
            box.add(b, x++, y);
            if (x > 5) {
                y++;
                x = 0;
            }
            box.setMaxHeight(y * 100);
        }
    }

    private void creatButton(Item item, List<Button> buttonlist) {
        final Button button = new Button();
        if (item instanceof Ticket) {
            Ticket ticket = (Ticket) item;
            button.setText(ticket.getName() + "\n" + ticket.getType() + "\n" + ticket.getPrice() +
                    "  " + cashRegister.getCurrency() + "\nks: " + ticket.getCount());
            if (ticket.getCount() < 1) {
                button.setDisable(true);
            }
        } else {
            Candys candy = (Candys) item;
            button.setText(candy.getName() + "\n" + candy.getType() + "\n" + candy.getPrice() +
                    " " + cashRegister.getCurrency() + " \nks: " + candy.getCount());
            if (candy.getCount() < 1) {
                button.setDisable(true);
            }
        }
        button.setId(item.getId());
        button.setPrefSize(102, 100);
        button.setOnAction(e -> {
            if (buy.containsKey(button.getId())) {
                buy.put(button.getId(), buy.get(button.getId()) + 1);
            } else {
                buy.put(button.getId(), 1);
            }
            count.setText("Sum: " + cashRegister.getSumPrice(buy) + "  " + cashRegister.getCurrency());
            String text = button.getText();
            String sum = text.substring(text.length() - 2);
            String out = text.substring(0, text.length() - 2);
            sum = sum.replace(":", " ");
            int sumAfterClick = Integer.parseInt(sum.replace(" ", "")) - 1;
            if (sumAfterClick < 10) {
                out = out + " ";
            }
            button.setText(out + sumAfterClick);
            if (sumAfterClick < 1) {
                button.setDisable(true);
            }
            tableView.getItems().clear();
            name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            priceItem.setCellValueFactory(new PropertyValueFactory<>("PriceItem"));
            sumPrice.setCellValueFactory(new PropertyValueFactory<>("SumPrice"));
            countItems.setCellValueFactory(new PropertyValueFactory<>("Count"));
            //add your data to the table here.
            for (TableRow i : cashRegister.getTableRows(buy)) {
                String countItems = String.valueOf(i.getCount());
                String price = String.valueOf(i.getPrice());
                String sumPrice = String.valueOf(i.getSumPrice());
                rowModels.add(new RowModel(i.getName(), countItems, price, sumPrice));
            }
            tableView.setItems(rowModels);
            returnCashLabel.setText("Return: 0  " + cashRegister.getCurrency());
        });
        buttonlist.add(button);
    }

    private void savePokladna() {
        try {
            SerializationUtil.serializeObject(cashRegister, fileName + fileType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}