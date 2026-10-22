
package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class MembersController {

    @FXML
    private TableView<Member> tblMembers;

    @FXML
    private TextField txtSearchMember;

    @FXML
    private TableColumn<Member, String> colMemberId;

    @FXML
    private TableColumn<Member, String> colFullName;

    @FXML
    private TableColumn<Member, String> colEmail;

    @FXML
    private TableColumn<Member, String> colPhone;

    @FXML
    private TableColumn<Member, String> colAddress;

    private final ObservableList<Member> members = MemberData.members;

    @FXML
    public void initialize() {

        colMemberId.setCellValueFactory(
                new PropertyValueFactory<>("memberId"));

        colFullName.setCellValueFactory(
                new PropertyValueFactory<>("fullName"));

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        colAddress.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        tblMembers.setItems(members);
    }

    public void searchMemberOnAction(ActionEvent event) {

        String keyword = txtSearchMember.getText()
                .trim().toLowerCase();

        ObservableList<Member> results =
                FXCollections.observableArrayList();

        for (Member member : members) {

            if (member.getMemberId().toLowerCase().contains(keyword)
                    || member.getFullName().toLowerCase().contains(keyword)
                    || member.getEmail().toLowerCase().contains(keyword)
                    || member.getPhone().toLowerCase().contains(keyword)) {

                results.add(member);
            }
        }

        tblMembers.setItems(results);
    }

    public void editMemberOnAction(ActionEvent event) {

        Member selectedMember =
                tblMembers.getSelectionModel().getSelectedItem();

        if (selectedMember == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Member Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a member to edit.");
            alert.showAndWait();

            return;
        }

        TextInputDialog dialog =
                new TextInputDialog(selectedMember.getFullName());

        dialog.setTitle("Edit Member");
        dialog.setHeaderText("Edit Member Name");
        dialog.setContentText("Full Name:");

        dialog.showAndWait().ifPresent(newName -> {

            if (newName.trim().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Name");
                alert.setHeaderText(null);
                alert.setContentText("Member name cannot be empty.");
                alert.showAndWait();

            } else {

                selectedMember.setFullName(newName.trim());
                tblMembers.refresh();
            }
        });
    }

    public void deleteMemberOnAction(ActionEvent event) {

        Member selectedMember =
                tblMembers.getSelectionModel().getSelectedItem();

        if (selectedMember == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Member Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a member to delete.");
            alert.showAndWait();

            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);
        confirm.setContentText(
                "Are you sure you want to delete "
                        + selectedMember.getFullName() + "?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL)
                == ButtonType.OK) {

            members.remove(selectedMember);

            searchMemberOnAction(event);


        }
    }
    public void addMemberOnAction(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader =
                    new javafx.fxml.FXMLLoader(
                            getClass().getResource("/add_member.fxml"));

            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage =
                    (javafx.stage.Stage) tblMembers.getScene()
                            .getWindow();

            stage.setScene(new javafx.scene.Scene(root));
            stage.show();

        } catch (java.io.IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Could not open Add Member page. Check add_member.fxml.");
            alert.showAndWait();
        }
    }
}
