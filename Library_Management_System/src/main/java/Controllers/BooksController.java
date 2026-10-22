package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BooksController {

    public void addBookOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();

            stage.setScene(new Scene(
                    FXMLLoader.load(
                            getClass().getResource("/add_book.fxml")
                    )
            ));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}