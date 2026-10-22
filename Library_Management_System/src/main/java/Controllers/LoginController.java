package Controllers;

import javafx.scene.control.Alert;

public class LoginController {
    public static boolean checkUsernameandPassword(String Username, String Password) {
        if (Username.equals("Nirmal") && Password.equals("12345")){
            return true;
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username or Password! Please try again.");
            alert.showAndWait();
        }
        return false;
    }
}
