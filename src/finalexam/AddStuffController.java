package finalexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class AddStuffController implements Initializable {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;
    
    @FXML
    private Button BackBtnStuff;
    @FXML
    private TextField id;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField phone;
    @FXML
    private TextField department;
    @FXML
    private TableView tableStaff;
    @FXML
    private TableColumn idStaff;
    @FXML
    private TableColumn fnameStaff;
    @FXML
    private TableColumn lnameStaff;
    @FXML
    private TableColumn phoneStaff;
    @FXML
    private TableColumn departmentStaff;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn  = Database.connDB();
        showStaffsData();
    }
    
    public void BackButtonAction(ActionEvent actionEvent) throws IOException { //Admin close

        Parent view3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
        Scene scene3 = new Scene(view3);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }
    
    public void addStaff(ActionEvent action) {

        Staff staff = new Staff();
        staff.setIdnumberStaff(id.getText());
        staff.setFirstname(fname.getText());
        staff.setLastname(lname.getText());
        staff.setPhonenumber(phone.getText());
        staff.setDepartment(department.getText());
        Staff.check(staff.getIdnumberStaff(), staff.getFirstname(),
                staff.getLastname(), staff.getPhonenumber(), staff.getDepartment());
            showStaffsData();
            id.setText("");
            fname.setText("");
            lname.setText("");
            phone.setText("");
            department.setText("");
        } 
    
        public void showStaffsData() {
        ObservableList<Staff> data = FXCollections.observableArrayList();
        String sele = "select * from staffs";
        try {
            pst = conn.prepareStatement(sele);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs);
                Staff staff = new Staff();
                staff.setIdnumberStaff(rs.getString("st_id"));
                staff.setFirstname(rs.getString("st_first_name"));
                staff.setLastname(rs.getString("st_last_name"));
                staff.setPhonenumber(rs.getString("st_phone_number"));
                staff.setDepartment(rs.getString("st_department"));
                data.add(staff);
            }
            idStaff.setCellValueFactory(new PropertyValueFactory<Student, String>("idnumberStaff"));
            fnameStaff.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
            lnameStaff.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
            phoneStaff.setCellValueFactory(new PropertyValueFactory<Student, String>("phonenumber"));
            departmentStaff.setCellValueFactory(new PropertyValueFactory<Student, String>("department"));
            tableStaff.setItems(data);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}