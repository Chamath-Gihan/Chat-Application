package lk.playTech.chatApp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lk.playTech.chatApp.controller.LoginFormController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientFormController {

    @FXML
    private ImageView imgEmoji;

    @FXML
    private ImageView imgPhoto;

    @FXML
    private ImageView imgSend;

    @FXML
    private Label lblName;

    @FXML
    private VBox messageVbox;

    @FXML
    private AnchorPane root2;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtMessage;

    @FXML
    public void initialize() {
        lblName.setText("Hello " + LoginFormController.userName + " !");
        scrollPane.vvalueProperty().bind(messageVbox.heightProperty());
    }

    @FXML
    void imgEmojiOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void imgPhotoOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void imgSendOnMouseClicked(MouseEvent event) {
        sendMessage();
    }

    @FXML
    void txtMessegeOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String senderName = LoginFormController.userName;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));

        String formattedMessage = String.format("%s:%n%n%s%n%n%s%n%n", senderName, txtMessage.getText(), timestamp);

        TextFlow messageFlow = new TextFlow();
        Text nameText = new Text(senderName + ":");
        nameText.setStyle("-fx-font-weight: bold; -fx-fill: blue;");
        Text contentText = new Text(txtMessage.getText());
        contentText.setStyle("-fx-fill: black;");
        Text timestampText = new Text(timestamp);
        timestampText.setStyle("-fx-fill: gray;");

        VBox nameBox = new VBox();
        nameBox.getChildren().add(nameText);

        VBox contentBox = new VBox();
        contentBox.getChildren().add(contentText);

        VBox timestampBox = new VBox();
        timestampBox.getChildren().add(timestampText);

        VBox messageBox = new VBox(10);
        messageBox.getChildren().addAll(nameBox, contentBox, timestampBox);
        messageBox.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-background-radius: 5px;");

        messageBox.setOnMouseEntered(event -> {
            messageBox.setEffect(new javafx.scene.effect.DropShadow(javafx.scene.effect.BlurType.GAUSSIAN, Color.BLUEVIOLET, 10, 0.5, 0, 0));
        });
        messageBox.setOnMouseExited(event -> {
            messageBox.setEffect(null);
        });

        messageFlow.getChildren().add(messageBox);

        if (!messageVbox.getChildren().isEmpty()) {
            messageVbox.getChildren().add(new Text("\n")); // Add new line
        }
        messageVbox.getChildren().add(messageFlow);

        txtMessage.clear();
    }



}
