package lk.playTech.chatApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.playTech.chatApp.server.Server;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        Server.main(new String[]{});
    }
}
