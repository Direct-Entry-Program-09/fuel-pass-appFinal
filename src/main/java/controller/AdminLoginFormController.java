package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AdminLoginFormController {
    private static int attempts=3;
    public PasswordField txtPassword;

    private static final String ADMIN_PASSWORD ="dep9@ADMIN";

    public AnchorPane pneAdminLoginForm;

    public void intialize() { Platform.runLater(txtPassword::requestFocus);}

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        if (!txtPassword.getText().equals(ADMIN_PASSWORD)){
            if (attempts==0){
                new Alert(Alert.AlertType.ERROR, "Sorry, You have reached maximum number of attempts").showAndWait();
                Platform.exit();
                return;
            }
           URL resource = this.getClass().getResource("/audio/sci-fi-voiceclip-753-sound-effect-12634278.mp3");
////            //Media media = new Media(resource.toString());
            Media media = new Media(resource.toString());
////
////            //MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
////
            mediaPlayer.play();


            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Admin Password. You have " + attempts + " more attempts to try again.");
            attempts--;

            InputStream resourceAsStream = this.getClass().getResourceAsStream("/images/lock.png");
            Image image = new Image(resourceAsStream);
            //Image image = new Image(resourceAsStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(48);
            imageView.setFitHeight(48);
            alert.setGraphic(imageView);

            alert.setHeaderText("Invalid Login Credential");
            alert.setTitle("Access Denied");

            alert.showAndWait();
            mediaPlayer.dispose();
            txtPassword.requestFocus();
            return;
        }
        Navigation.navigate(Routes.CONTROL_CENTER);
    }
}
