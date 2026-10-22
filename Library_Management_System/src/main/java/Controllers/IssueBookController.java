
package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class IssueBookController {

    @FXML
    private ComboBox<Member> cmbMember;

    @FXML
    private ComboBox<Book> cmbBook;

    @FXML
    private DatePicker dpIssueDate;

    @FXML
    private DatePicker dpDueDate;

    @FXML
    public void initialize() {
        cmbMember.setItems(MemberData.members);
        cmbBook.setItems(BookData.books);

        dpIssueDate.setValue(LocalDate.now());
    }

    public void issueBookOnAction(ActionEvent event) {

        Member selectedMember = cmbMember.getValue();
        Book selectedBook = cmbBook.getValue();

        LocalDate issueDate = dpIssueDate.getValue();
        LocalDate dueDate = dpDueDate.getValue();

        if (selectedMember == null || selectedBook == null
                || issueDate == null || dueDate == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Missing Information",
                    "Please select a member, book and both dates.");
            return;
        }

        if (dueDate.isBefore(issueDate)) {
            showAlert(Alert.AlertType.WARNING,
                    "Invalid Due Date",
                    "Due date cannot be before issue date.");
            return;
        }

        if (selectedBook.getAvailableQuantity() <= 0) {
            showAlert(Alert.AlertType.WARNING,
                    "Book Not Available",
                    "There are no available copies of this book.");
            return;
        }

        BorrowingRecord record = new BorrowingRecord(
                selectedMember,
                selectedBook,
                issueDate,
                dueDate
        );

        BorrowingData.records.add(record);

        selectedBook.setAvailableQuantity(
                selectedBook.getAvailableQuantity() - 1
        );

        showAlert(Alert.AlertType.INFORMATION,
                "Book Issued",
                "Book issued successfully to "
                        + selectedMember.getFullName() + "!");

        cmbMember.getSelectionModel().clearSelection();
        cmbBook.getSelectionModel().clearSelection();

        dpIssueDate.setValue(LocalDate.now());
        dpDueDate.setValue(null);
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
