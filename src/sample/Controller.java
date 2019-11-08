package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pokladna.*;
import pokladna.TableRow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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

    private List<Button> buttonlist = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> buy = new ArrayList<>();
    private CashRegister pokladna;

    private String fileName = "data";
    private String fileType = ".txt";

    private ObservableList<RowModel> rowModels = FXCollections.observableArrayList();

    @FXML
    private void buttonPay() {
        Dialog<RowModel> pay = new Dialog<>();
        pay.setTitle("Pay");
        pay.setWidth(350);
        pay.setHeight(250);
        createPay(pay);
        final Optional<RowModel> vysledek = pay.showAndWait();
        savePokladna();
    }

    @FXML
    private void buttonPrint() {
        StringBuilder babisovka = new StringBuilder();
        for (TableRow i : pokladna.getTableRows(buy)) {
            babisovka.append(i.getName());
            if (i.getCount() > 1) {
                babisovka.append("\n").append(i.getCount());
                babisovka.append("     X     ").append(i.getPrice());
                babisovka.append(" Kc     ").append(i.getSumPrice()).append(" Kc \n");
            } else {
                babisovka.append("\n" + "                        ");
                babisovka.append(i.getSumPrice()).append(" Kc\n");
            }
        }
        System.out.println("------------Uctenka------------\n"
                + babisovka
                + "-------------------------------\nCelkova cena: "
                + pokladna.getSumPrice(buy)
                + " Kc"
        );
    }

    @FXML
    public void buttonReset() {
        buy.clear();
        tableView.getItems().clear();
        count.setText("Sum: " + pokladna.getSumPrice(buy));
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //load pokladna
        try {
            pokladna = (CashRegister) SerializationUtil.deserializeObject(fileName + fileType);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        Stage window = Main.getPrimaryStage();
        window.setOnCloseRequest(event -> {
            savePokladna();
            event.consume();
        });
        window.setTitle(pokladna.getName());
        items = pokladna.getItems();
        spawnButtons(items);
    }

    private void spawnButtons(ArrayList<Item> items) {
        for (Item x : items) {
            creatButton(x);
        }
        int x = 0, y = 0;
        for (Button b : buttonlist) {
            box.add(b, x++, y);
            if (x > 5) {
                y++;
                x = 0;
                if (y > 4) {
                    System.err.println("ITEMS OVERFLOW!(Y)");
                }
            }
        }
    }

    private void creatButton(Item item) {
        final Button button = new Button();
        if (item instanceof Ticket) {
            Ticket ticket = (Ticket) item;
            button.setText(ticket.getName() + "\n" + ticket.getType());
        } else {
            Candys candy = (Candys) item;
            button.setText(candy.getName() + "\n" + candy.getType());
        }
        button.setId(item.getId());
        button.setPrefSize(100, 100);
        button.setOnAction(e -> {
            buy.add(button.getId());
            count.setText("Sum: " + pokladna.getSumPrice(buy));

            tableView.getItems().clear();
            name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            priceItem.setCellValueFactory(new PropertyValueFactory<>("PriceItem"));
            sumPrice.setCellValueFactory(new PropertyValueFactory<>("SumPrice"));
            countItems.setCellValueFactory(new PropertyValueFactory<>("Count"));
            //add your data to the table here.
            for (TableRow i : pokladna.getTableRows(buy)) {
                String countItems = String.valueOf(i.getCount());
                String price = String.valueOf(i.getPrice());
                String sumPrice = String.valueOf(i.getSumPrice());
                rowModels.add(new RowModel(i.getName(), countItems, price, sumPrice));
            }
            tableView.setItems(rowModels);
        });
        buttonlist.add(button);
    }

    private void createPay(Dialog<RowModel> dialog) {
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().setAll(okButton);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField zaplaceno = new TextField();
        zaplaceno.setText("0");
        Label zaplacenoLabel = new Label("Zaplaceno");
        Label vratit = new Label("");
        Label vratitLabel = new Label("Vratit:");

        Button vypocti = new Button("Vypocti");
        vypocti.setText("Vypocti");
        vypocti.setOnAction(e -> vratit.setText(String.valueOf(Float.parseFloat(zaplaceno.getText()) - pokladna.getSumPrice(buy))));

        grid.add(zaplacenoLabel, 0, 0);
        grid.add(zaplaceno, 1, 0);
        grid.add(vratitLabel, 0, 1);
        grid.add(vratit, 1, 1);
        grid.add(vypocti, 0, 2);

        dialog.getDialogPane().setContent(grid);
    }

    private void savePokladna() {
        try {
            SerializationUtil.serializeObject(pokladna, fileName + fileType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}