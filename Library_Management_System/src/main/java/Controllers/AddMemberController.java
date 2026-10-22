
package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddMemberController {

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextArea txtAddress;

    public void registerMemberOnAction(ActionEvent event) {

        String memberId = txtMemberId.getText().trim();
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();

        // Check empty fields
        if (memberId.isEmpty() || fullName.isEmpty()
                || email.isEmpty() || phone.isEmpty()
                || address.isEmpty()) {

            showAlert(Alert.AlertType.WARNING,
                    "Missing Information",
                    "Please fill all fields.");
            return;
        }

        // Check duplicate Member ID
        for (Member member : MemberData.members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                showAlert(Alert.AlertType.WARNING,
                        "Duplicate Member ID",
                        "This Member ID already exists. Please use another ID.");
                return;
            }
        }

        // Create new member
        Member newMember = new Member(
                memberId,
                fullName,
                email,
                phone,
                address
        );

        // Save member to shared list
        MemberData.members.add(newMember);

        showAlert(Alert.AlertType.INFORMATION,
                "Member Registered",
                "Member registered successfully!");

        // Clear fields
        txtMemberId.clear();
        txtFullName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
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
