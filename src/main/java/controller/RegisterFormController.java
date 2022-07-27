package controller;

import db.InMemoryDB;
import db.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;

public class RegisterFormController {
    public TextField txtId;
    public Button btnRegister;
    public TextField txtLastName;
    public TextField txtFirstName;
    public TextField txtAddress;
    public Label lblLogin;
    public AnchorPane pneRegisterForm;
    public Label lblNicStatus;

    private void setDisableCmp(boolean disable){
        txtFirstName.setDisable(disable);
        txtLastName.setDisable(disable);
        txtAddress.setDisable(disable);
        btnRegister.setDisable(disable);
    }

    public void initialize(){
        Platform.runLater(txtId::requestFocus);
        txtId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldText, String currentText) {
                setDisableCmp(true);
                if (currentText.isBlank()){
                    lblNicStatus.setText("Please enter a valid NIC to proceed");
                    lblNicStatus.setTextFill(Color.BLACK);
                    return;
                }else {
                    if(isValidNIC(currentText)) {
                        lblNicStatus.setText("Valid Text");
                        lblNicStatus.setTextFill(Color.GREEN);
                        setDisableCmp(false);
                    }else {lblNicStatus.setText("Invalid Text");
                    lblNicStatus.setTextFill(Color.RED);}
                }
            }
        });
    }

//    public void btnRegisterOnAction(ActionEvent actionEvent) {
//        User user=new User(txtId.getText(),txtFirstName.getText(),txtLastName.getText(),txtAddress.getText());
//        InMemoryDB.registerUser(user);
//    }

    public void lblLoginOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/LoginForm.fxml");
        AnchorPane loginForm= FXMLLoader.load(resource);
        AnchorPane pnecontainer=(AnchorPane) pneRegisterForm.getParent();
        pnecontainer.getChildren().clear();
        pnecontainer.getChildren().add(loginForm);
        AnchorPane.setRightAnchor(loginForm,0.0);
        AnchorPane.setLeftAnchor(loginForm,0.0);
        AnchorPane.setTopAnchor(loginForm,0.0);
        AnchorPane.setBottomAnchor(loginForm,0.0);

    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        String firstName=txtFirstName.getText();
        if (firstName.isBlank()){
            new Alert(Alert.AlertType.ERROR, "Sorry you haven't entered the first name !").showAndWait();
            txtFirstName.requestFocus();
            return;
        } else if (!isName(firstName)) {
            new Alert(Alert.AlertType.CONFIRMATION, "First name is invalid. Please enter a valid first name").showAndWait();
            txtFirstName.requestFocus();
            return;
        } else if (!isName(txtLastName.getText())) {
            new Alert(Alert.AlertType.ERROR,"Sorry you haven't entered the first name !").showAndWait();
            txtLastName.requestFocus();
            return;

        } else {
                User user=new User(txtId,txtFirstName,txtLastName,txtAddress);
                boolean result= InMemoryDB.registerUser(user);
                new Alert(Alert.AlertType.CONFIRMATION,"Registration Successful !").showAndWait();
            Navigation.navigate(Routes.LOGIN);
//                URL resource2 = this.getClass().getResource("/view/LoginForm.fxml");
//                AnchorPane controlCenter = FXMLLoader.load(resource2);
//                AnchorPane pneContainer = (AnchorPane) pneRegisterForm.getParent();
//                pneContainer.getChildren().clear();
//                pneContainer.getChildren().add(controlCenter);
//                AnchorPane.setLeftAnchor(controlCenter, 0.0);
//                AnchorPane.setRightAnchor(controlCenter, 0.0);
//                AnchorPane.setTopAnchor(controlCenter, 0.0);
//                AnchorPane.setBottomAnchor(controlCenter, 0.0);
            if (result){
                new Alert(Alert.AlertType.ERROR, "Registration is successfull you will be redirected to the login form");
                lblLoginOnMouseClicked(null);
            }else {
                new Alert(Alert.AlertType.ERROR,"You are already registered, check the NIC number");
            }

            }



    }
    public boolean isName(String input){
        char[] chars = input.toCharArray();
        for (char aChars: chars){
            if (!Character.isLetter(aChars) && aChars!=' ') return false;  // space is kept because if someone entered the first name as Rashmi Nishani with a space in betwwn
        }
        return true;
    }
    public static boolean isValidNIC(String input){
        if (input.length()!=10) return false;
        if(!(input.endsWith("v")||input.endsWith("V"))) return false;
        if (!input.substring(8,9).matches("\\d")) return false;
        return true;
    }
}
