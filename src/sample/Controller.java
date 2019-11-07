package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pokladna.*;
import pokladna.TableRow;

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

    private ObservableList<RowModel> rowModels = FXCollections.observableArrayList();

    @FXML
    private void buttonPay(ActionEvent event) {
        Dialog<RowModel> pay = new Dialog<>();
        pay.setTitle("Pay");
        pay.setWidth(350);
        pay.setHeight(250);
        createPay(pay);
        final Optional<RowModel> vysledek = pay.showAndWait();

    }

    @FXML
    private void buttonPrint(ActionEvent event) {
        String babisovka = "";
        for (TableRow i : pokladna.getTableRows(buy)) {
            String countItems = String.valueOf(i.getCount());
            babisovka += i.getName();
            if (i.getCount() > 1) {
                babisovka += "\n"
                        + String.valueOf(i.getCount())
                        + "     X     "
                        + String.valueOf(i.getPrice())
                        + " Kc     "
                        + String.valueOf(i.getSumPrice())
                        + " Kc \n";
            } else {
                babisovka += "\n"
                        + "                        "
                        + String.valueOf(i.getSumPrice())
                        + " Kc\n";
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

        //load pokladna
        pokladna = new CashRegister("POKLADNA-1", 2000, items);
        Stage window = Main.getPrimaryStage();
        window.setTitle(pokladna.getName());

        //load products
        spawnButtons(items);

    }

    private void spawnButtons(ArrayList<Item> items){
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

    private void creatButton(Item item){
        final Button button = new Button();
        if (item instanceof Ticket) {
            Ticket ticket = (Ticket) item;
            button.setText(ticket.getName() + "\n" + ticket.getType());
        }else{
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
        vypocti.setOnAction(e -> {
            vratit.setText(String.valueOf(Float.parseFloat(zaplaceno.getText()) - pokladna.getSumPrice(buy)));
        });

        grid.add(zaplacenoLabel, 0, 0);
        grid.add(zaplaceno, 1, 0);
        grid.add(vratitLabel, 0, 1);
        grid.add(vratit, 1, 1);
        grid.add(vypocti, 0, 2);

        dialog.getDialogPane().setContent(grid);
    }

}