package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pokladna.CashRegister;
import pokladna.Data;
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
        setPrimaryStage(primaryStage); // **Set the Stage**
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 1075, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        CashRegister pokladna = Data.creatCashRegister();
        String fileName = "data";
        String fileType = ".txt";
        try {
            File temp;
            temp = File.createTempFile(fileName, fileType);
            if (!temp.exists()) {
                SerializationUtil.serializeObject(pokladna, fileName+fileType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        launch(args);
    }
}
