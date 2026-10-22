
package Controllers;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ReturnBookController {

    @FXML
    private ComboBox<BorrowingRecord> cmbBorrowedBook;

    @FXML
    private TextField txtReturnMemberId;

    @FXML
    private TextField txtReturnBookTitle;

    @FXML
    private TextField txtReturnDueDate;

    @FXML
    private DatePicker dpReturnDate;

    private final ObservableList<BorrowingRecord> activeBorrowings =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Display only books that have not been returned
        refreshBorrowedBooks();

        dpReturnDate.setValue(LocalDate.now());

        cmbBorrowedBook.setOnAction(event -> showSelectedBook());
    }

    private void refreshBorrowedBooks() {
        activeBorrowings.setAll(
                BorrowingData.records.filtered(
                        record -> "Borrowed".equals(record.getStatus())
                )
        );

        cmbBorrowedBook.setItems(activeBorrowings);
    }

    private void showSelectedBook() {
        BorrowingRecord record = cmbBorrowedBook.getValue();

        if (record != null) {
            txtReturnMemberId.setText(record.getMemberId());
            txtReturnBookTitle.setText(record.getBookName());
            txtReturnDueDate.setText(record.getDueDate().toString());
        } else {
            txtReturnMemberId.clear();
            txtReturnBookTitle.clear();
            txtReturnDueDate.clear();
        }
    }

    public void returnBookOnAction(ActionEvent event) {

        BorrowingRecord record = cmbBorrowedBook.getValue();
        LocalDate returnDate = dpReturnDate.getValue();

        if (record == null || returnDate == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Missing Information",
                    "Please select a borrowed book and return date.");
            return;
        }

        if (!"Borrowed".equals(record.getStatus())) {
            showAlert(Alert.AlertType.WARNING,
                    "Already Returned",
                    "This book has already been returned.");
            return;
        }

        if (returnDate.isBefore(record.getIssueDate())) {
            showAlert(Alert.AlertType.WARNING,
                    "Invalid Return Date",
                    "Return date cannot be before issue date.");
            return;
        }

        if (returnDate.isAfter(LocalDate.now())) {
            showAlert(Alert.AlertType.WARNING,
                    "Invalid Return Date",
                    "Return date cannot be in the future.");
            return;
        }

        // Save the return date
        record.setReturnDate(returnDate);

        // Update the borrowing status
        if (returnDate.isAfter(record.getDueDate())) {
            record.setStatus("Returned Late");
        } else {
            record.setStatus("Returned");
        }

        // Increase available book quantity
        record.getBook().setAvailableQuantity(
                record.getBook().getAvailableQuantity() + 1
        );

        showAlert(Alert.AlertType.INFORMATION,
                "Book Returned",
                "Book returned successfully!\n\n"
                        + "Book: " + record.getBookName()
                        + "\nMember: " + record.getMemberName()
                        + "\nReturn Date: " + returnDate
                        + "\nStatus: " + record.getStatus());

        // Remove only from this dropdown, not from borrowing history
        activeBorrowings.remove(record);

        cmbBorrowedBook.getSelectionModel().clearSelection();

        txtReturnMemberId.clear();
        txtReturnBookTitle.clear();
        txtReturnDueDate.clear();

        dpReturnDate.setValue(LocalDate.now());
    }

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
