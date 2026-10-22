package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        if (LoginController.checkUsernameandPassword(
                txtUsername.getText(),
                txtPassword.getText())) {

            Stage stage = new Stage();

            try {
                stage.setScene(new Scene(
                        FXMLLoader.load(
                                getClass().getResource("/main_dashboard.fxml")
                        )
                ));

                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}