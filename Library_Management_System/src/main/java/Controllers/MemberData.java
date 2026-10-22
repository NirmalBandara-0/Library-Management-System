
package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemberData {

    public static final ObservableList<Member> members =
            FXCollections.observableArrayList();

    static {
        members.add(new Member(
                "M001", "Nimal",
                "nimal@gmail.com", "0771234567", "Kandy"));

        members.add(new Member(
                "M002", "Kamal",
                "kamal@gmail.com", "0711234567", "Colombo"));

        members.add(new Member(
                "M003", "Amara",
                "amara@gmail.com", "0751234567", "Galle"));
    }
}
