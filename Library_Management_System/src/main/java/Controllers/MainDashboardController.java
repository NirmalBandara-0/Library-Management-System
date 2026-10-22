
package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainDashboardController {

    @FXML private Label lblTotalBooks;
    @FXML private Label lblTotalMembers;
    @FXML private Label lblCurrentlyBorrowed;
    @FXML private Label lblOverdueBooks;

    @FXML
    public void initialize() {
        updateDashboard();
    }

    private void updateDashboard() {

        lblTotalBooks.setText(
                String.valueOf(BookData.books.size())
        );

        lblTotalMembers.setText(
                String.valueOf(MemberData.members.size())
        );

        long borrowedCount = BorrowingData.records.stream()
                .filter(record -> "Borrowed".equals(record.getStatus()))
                .count();

        lblCurrentlyBorrowed.setText(
                String.valueOf(borrowedCount)
        );

        long overdueCount = BorrowingData.records.stream()
                .filter(record ->
                        "Borrowed".equals(record.getStatus())
                                && record.getDueDate() != null
                                && record.getDueDate().isBefore(LocalDate.now())
                )
                .count();

        lblOverdueBooks.setText(
                String.valueOf(overdueCount)
        );
    }

    public void booksOnAction(ActionEvent event) {
        openPage(event, "/books.fxml");
    }

    public void membersOnAction(ActionEvent event) {
        openPage(event, "/members.fxml");
    }

    public void borrowedBooksOnAction(ActionEvent event) {
        openPage(event, "/issue_book.fxml");
    }

    public void returnBookOnAction(ActionEvent event) {
        openPage(event, "/return_book.fxml");
    }

    public void borrowingHistoryOnAction(ActionEvent event) {
        openPage(event, "/borrowing_history.fxml");
    }

    public void logoutOnAction(ActionEvent event) {
        openPage(event, "/login_page.fxml");
    }

    private void openPage(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlFile)
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
