package controller;

import db.InMemoryDB;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtNiclogin;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (!RegisterFormController.isValidNIC(txtNiclogin.getText()) ||
                InMemoryDB.findUser(txtNiclogin.getText()==null)){
            new Alert(Alert.AlertType.ERROR,"Please Enter a Valid Nic to Login").showAndWait();
            txtNiclogin.requestFocus();
        }else {
            Navigation.navigate(Routes.DASHBOARD);
        }
    }

    public void lblRegisterOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.REGISTRATION);
    }
}
