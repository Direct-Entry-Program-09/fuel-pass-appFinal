package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Navigation;

import java.io.IOException;
import java.net.URL;

public class SplashScreenController {
    public Label lblLoading;
    public Rectangle pgbContainer;
    public Rectangle pgbLoader;

    public void initialize(){
        Timeline t1=new Timeline();

        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(770), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoading.setText("Connecting with the database...!");
                pgbLoader.setWidth(pgbLoader.getWidth() + 25);
            }
        });
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoading.setText("Loading data...!");
                pgbLoader.setWidth(pgbLoader.getWidth() + 30);
            }
        });
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(1500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoading.setText("Setting up the UI ");
                pgbLoader.setWidth(pgbLoader.getWidth() + 25);
            }
        });
        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    pgbLoader.setWidth(pgbContainer.getWidth());
                    URL resource = this.getClass().getResource("/view/HomeForm.fxml");
                    Parent homeFormContainer = FXMLLoader.load(resource);
                    AnchorPane pneContainer=(AnchorPane)homeFormContainer.lookup("pneContainer");   // inside the lookup we can put a css container, pneContainer represent an
                    // AnchorPane
                    Navigation.init(pneContainer);
                    Scene scene=new Scene(homeFormContainer);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.setTitle("National Fuel Pass App");
                    stage.show();
                    stage.centerOnScreen();
                    lblLoading.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4);
        t1.playFromStart();
    }
}
