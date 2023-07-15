package lk.playTech.chatApp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.playTech.chatApp.controller.LoginFormController;

import java.io.File;
import java.io.IOException;
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

        imgEmoji.setOnMouseEntered(event -> {
            imgEmoji.setEffect(new javafx.scene.effect.DropShadow(javafx.scene.effect.BlurType.GAUSSIAN, Color.BLUEVIOLET, 10, 0.5, 0, 0));
        });
        imgEmoji.setOnMouseExited(event -> {
            imgEmoji.setEffect(null);
        });

        imgPhoto.setOnMouseEntered(event -> {
            imgPhoto.setEffect(new javafx.scene.effect.DropShadow(javafx.scene.effect.BlurType.GAUSSIAN, Color.BLUEVIOLET, 10, 0.5, 0, 0));
        });
        imgPhoto.setOnMouseExited(event -> {
            imgPhoto.setEffect(null);
        });

        imgSend.setOnMouseEntered(event -> {
            imgSend.setEffect(new javafx.scene.effect.DropShadow(javafx.scene.effect.BlurType.GAUSSIAN, Color.BLUEVIOLET, 10, 0.5, 0, 0));
        });
        imgSend.setOnMouseExited(event -> {
            imgSend.setEffect(null);
        });
    }

    @FXML
    void imgEmojiOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void imgPhotoOnMouseClicked(MouseEvent event) {
        selectAndSendPhoto();
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
        String messageText = txtMessage.getText().trim();
        if (messageText.isEmpty()) {
            return; // Return early if the message is empty
        }

        String senderName = LoginFormController.userName;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));

        String formattedMessage = String.format("%s:%n%n%s%n%n%s%n%n", senderName, messageText, timestamp);

        TextFlow messageFlow = new TextFlow();
        Text nameText = new Text(senderName + ":");
        nameText.setStyle("-fx-font-weight: bold; -fx-fill: blue;");
        Text contentText = new Text(messageText);
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


    private void selectAndSendPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"));

        Window window = root2.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            sendPhoto(selectedFile);
        }
    }

    private void sendPhoto(File photoFile) {
        String senderName = LoginFormController.userName;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));

        TextFlow messageFlow = new TextFlow();

        // Create a Text node for the sender's name
        Text nameText = new Text(senderName + ": ");
        nameText.setStyle("-fx-font-weight: bold; -fx-fill: blue;");

        // Create a Text node for the timestamp
        Text timestampText = new Text(timestamp);
        timestampText.setStyle("-fx-fill: gray;");

        // Create an ImageView to display the photo
        ImageView photoImageView = new ImageView();
        photoImageView.setFitWidth(200); // Set the desired width
        photoImageView.setPreserveRatio(true);
        photoImageView.setImage(new Image(photoFile.toURI().toString()));

        // Add the message components to the TextFlow
        messageFlow.getChildren().addAll(nameText, new Text("\n"), photoImageView, new Text("\n"), timestampText);
        messageFlow.setStyle("-fx-background-color: lightgray; -fx-padding: 5px; -fx-background-radius: 5px;");

        messageVbox.getChildren().add(messageFlow);
    }


}
