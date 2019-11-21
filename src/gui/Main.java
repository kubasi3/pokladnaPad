package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pokladna.CashRegister;
import pokladna.DataLoader;
import pokladna.SerializationUtil;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fileName = "data";
        String fileType = ".txt";
        try {
            if (!(new File(fileName + fileType).exists())) {
                CashRegister pokladna = DataLoader.creatCashRegister();
                SerializationUtil.serializeObject(pokladna, fileName + fileType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        setPrimaryStage(primaryStage); // **Set the Stage**
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setScene(new Scene(root, 1085, 600));
        primaryStage.show();
    }


    public static void main(Stage args) {
        launch(String.valueOf(args));
    }
}
