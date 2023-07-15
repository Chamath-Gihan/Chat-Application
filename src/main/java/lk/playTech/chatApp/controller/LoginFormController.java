package lk.playTech.chatApp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public static String userName;

    @FXML
    private TextField txtName;

    @FXML
    void txtNameOnKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            userName = txtName.getText();
            txtName.clear();
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(LoginFormController.class.getResource("/view/client_form.fxml"))));
            stage.close();
            stage.centerOnScreen();
            stage.show();
        }
    }
}
