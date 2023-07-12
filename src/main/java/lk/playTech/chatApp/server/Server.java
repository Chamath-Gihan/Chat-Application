package lk.playTech.chatApp.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Server extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
        primaryStage.setTitle("Server");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
