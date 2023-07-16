package lk.playTech.chatApp.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientFormController extends Thread{

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    private FileChooser fileChooser;
    private File filePath;
    String name;

    @FXML
    private GridPane gridPane;

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
    private AnchorPane root1;

    @FXML
    private AnchorPane root2;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtMessage;

    @FXML
    public void initialize() {
        name = LoginFormController.userName;
        root2.setVisible(false);
        lblName.setText("Hello " + name + " !");
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

        try {
            socket = new Socket("localhost", 3000);
            System.out.println("Socket connected to server");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            Task<Void> receiverTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        updateUIWithReceivedMessage(message);
                    }
                    return null;
                }
            };

            Thread receiverThread = new Thread(receiverTask);
            receiverThread.setDaemon(true);
            receiverThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUIWithReceivedMessage(String message) {
        Platform.runLater(() -> {
            TextFlow messageFlow = new TextFlow();
            Text receivedText = new Text(message);
            receivedText.setStyle("-fx-fill: black;");
            messageFlow.getChildren().add(receivedText);
            messageVbox.getChildren().add(messageFlow);
        });
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                // Process the received message (e.g., display it in the UI)
                processReceivedMessage(message);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private void processReceivedMessage(String message) {
        // Update the UI with the received message
        Platform.runLater(() -> {
            TextFlow messageFlow = new TextFlow();
            Text receivedText = new Text(message);
            receivedText.setStyle("-fx-fill: black;");
            messageFlow.getChildren().add(receivedText);
            messageVbox.getChildren().add(messageFlow);
        });
    }


    @FXML
    void imgEmojiOnMouseClicked(MouseEvent event) {
        root2.setVisible(true);
    }

    @FXML
    void root01OnMouseClicked(MouseEvent event) {
        if (event.getTarget() == imgEmoji) {
            root2.setVisible(true);
        } else if(event.getTarget() == gridPane){
            root2.setVisible(true);
        }else {
            root2.setVisible(false);
        }
    }

    @FXML
    void imgPhotoOnMouseClicked(MouseEvent mouseEvent) throws IOException {
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

    @FXML
    void emoji01(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F604));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji02(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F600));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji03(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F602));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji04(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F60D));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji05(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F618));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji06(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F44D));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji07(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F44E));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji08(MouseEvent event) {
        String emoji = new String(Character.toChars(0x2764));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji09(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F622));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji10(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F62D));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji11(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F614));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji12(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F634));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji13(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F60F));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji14(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F621));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji15(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F615));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji16(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F923));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji17(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F603));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji18(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F637));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji19(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F525));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji20(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F609));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji21(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F60E));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji22(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F92D));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji23(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F644));
        txtMessage.setText(txtMessage.getText() + emoji);
    }

    @FXML
    void emoji24(MouseEvent event) {
        String emoji = new String(Character.toChars(0x1F973));
        txtMessage.setText(txtMessage.getText() + emoji);
    }


    private void sendMessage() {
        String messageText = txtMessage.getText().trim();
        if (messageText.isEmpty()) {
            return;
        }

        String senderName = name;
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
//        messageVbox.getChildren().add(messageFlow);

        txtMessage.clear();

        writer.println(formattedMessage);

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
        String senderName = name;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));

        TextFlow messageFlow = new TextFlow();

        Text nameText = new Text(senderName + ": ");
        nameText.setStyle("-fx-font-weight: bold; -fx-fill: blue;");

        Text timestampText = new Text(timestamp);
        timestampText.setStyle("-fx-fill: gray;");

        ImageView photoImageView = new ImageView();
        photoImageView.setFitWidth(200);
        photoImageView.setPreserveRatio(true);
        photoImageView.setImage(new Image(photoFile.toURI().toString()));


        messageFlow.getChildren().addAll(nameText, new Text("\n"), photoImageView, new Text("\n"), timestampText);
        messageFlow.setStyle("-fx-background-color: lightgray; -fx-padding: 5px; -fx-background-radius: 5px;");
        messageVbox.getChildren().add(messageFlow);
    }

}
