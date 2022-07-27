package controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;

public class HomeFormController {
    public AnchorPane pneContainer;
    public ImageView pneLogin;
    public ImageView imgLogo;
    public AnchorPane imgContainer;

    public void  initialize() throws IOException {
        Platform.runLater(()->{
            try {
                Navigation.navigate(Routes.WELCOME);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void imgLogoOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        pneContainer.getChildren().clear();
        initialize();
    }

    public void pneLoginOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        pneContainer.getChildren().clear();
        Navigation.navigate(Routes.ADMIN_LOGIN);


    }
    public void pneLoginOnKeyRelease(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE){
            pneLoginOnMouseClicked(null);
        }
    }

}
