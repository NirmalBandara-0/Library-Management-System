
package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtPublishedYear;

    @FXML
    private TextField txtQuantity;

    public void saveBookOnAction(ActionEvent event) {

        String bookId = txtBookId.getText().trim();
        String bookName = txtBookName.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = txtCategory.getText().trim();
        String year = txtPublishedYear.getText().trim();
        String quantityText = txtQuantity.getText().trim();

        // Check empty fields
        if (bookId.isEmpty() || bookName.isEmpty()
                || author.isEmpty() || category.isEmpty()
                || year.isEmpty() || quantityText.isEmpty()) {

            showAlert(Alert.AlertType.WARNING,
                    "Missing Information",
                    "Please fill all fields.");
            return;
        }

        // Check duplicate Book ID
        for (Book book : BookData.books) {

            if (book.getBookId().equalsIgnoreCase(bookId)) {

                showAlert(Alert.AlertType.WARNING,
                        "Duplicate Book ID",
                        "This Book ID already exists.");
                return;
            }
        }

        // Validate quantity
        int quantity;

        try {
            quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                showAlert(Alert.AlertType.WARNING,
                        "Invalid Quantity",
                        "Quantity must be greater than zero.");
                return;
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING,
                    "Invalid Quantity",
                    "Please enter a whole number for Quantity.");
            return;
        }

        // Create new book
        Book newBook = new Book(
                bookId,
                bookName,
                author,
                category,
                year,
                quantity
        );

        // Save book to shared list
        BookData.books.add(newBook);

        showAlert(Alert.AlertType.INFORMATION,
                "Book Added",
                "Book added successfully!\n\n"
                        + "Book ID: " + bookId
                        + "\nBook Name: " + bookName);

        clearFields();
    }

    public void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtBookId.clear();
        txtBookName.clear();
        txtAuthor.clear();
        txtCategory.clear();
        txtPublishedYear.clear();
        txtQuantity.clear();
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
