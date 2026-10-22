
package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class BorrowingHistoryController {

    @FXML
    private TableView<BorrowingRecord> tblBorrowingHistory;

    @FXML
    private TableColumn<BorrowingRecord, String> colMemberId;

    @FXML
    private TableColumn<BorrowingRecord, String> colBookTitle;

    @FXML
    private TableColumn<BorrowingRecord, Object> colIssueDate;

    @FXML
    private TableColumn<BorrowingRecord, Object> colDueDate;

    @FXML
    private TableColumn<BorrowingRecord, Object> colReturnDate;

    @FXML
    private TableColumn<BorrowingRecord, String> colStatus;


    @FXML
    public void initialize() {

        colMemberId.setCellValueFactory(
                new PropertyValueFactory<>("memberId")
        );

        colBookTitle.setCellValueFactory(
                new PropertyValueFactory<>("bookName")
        );

        colIssueDate.setCellValueFactory(
                new PropertyValueFactory<>("issueDate")
        );

        colDueDate.setCellValueFactory(
                new PropertyValueFactory<>("dueDate")
        );

        colReturnDate.setCellValueFactory(
                new PropertyValueFactory<>("returnDate")
        );

        colStatus.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        // Display all borrowing records, including returned books
        tblBorrowingHistory.setItems(BorrowingData.records);
    }


    @FXML
    public void backToDashboardOnAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/main_dashboard.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException | NullPointerException e) {

            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText("Unable to open Dashboard");
            alert.setContentText(
                    "Please check that main_dashboard.fxml exists "
                            + "inside src/main/resources."
            );
            alert.showAndWait();
        }
    }
}
