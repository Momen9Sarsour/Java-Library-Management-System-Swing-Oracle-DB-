package finalexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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

public class AddStudentController implements Initializable {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;

    @FXML
    private Button BackBtnStudent;
    @FXML
    private Button addNewStudent;
    @FXML
    private TextField id;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField phone;
    @FXML
    private TextField major;
    @FXML
    private TableView tableStudent;
    @FXML
    private TableColumn idStudent;
    @FXML
    private TableColumn fnameStudent;
    @FXML
    private TableColumn lnameStudent;
    @FXML
    private TableColumn phoneStudent;
    @FXML
    private TableColumn majorStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = Database.connDB();
        showStudentsData();
    }

    public void BackButtonStudentAction(ActionEvent actionEvent) throws IOException { //Admin close
        Parent view3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
        Scene scene3 = new Scene(view3);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    public void AddStudentInformation(ActionEvent action) {
        String ins = "insert into students (s_id,s_first_name,s_last_name,s_phone_number,s_major) values (?,?,?,?,?) ";
        Student student = new Student();
        student.setIdnumberStudent(id.getText());
        student.setFirstname(fname.getText());
        student.setLastname(lname.getText());
        student.setPhonenumber(phone.getText());
        student.setMajor(major.getText());
        Student.check(student.getIdnumberStudent(), student.getFirstname(),
                student.getLastname(), student.getPhonenumber(), student.getMajor());
        showStudentsData();
        JOptionPane.showMessageDialog(null, "Inserted Don.");

    }

    public void showStudentsData() {
        ObservableList<Student> data = FXCollections.observableArrayList();
        String sel = "select * from students";
        try {
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs);
                Student student = new Student();
                student.setIdnumberStudent(rs.getString("s_id"));
                student.setFirstname(rs.getString("s_first_name"));
                student.setLastname(rs.getString("s_last_name"));
                student.setPhonenumber(rs.getString("s_phone_number"));
                student.setMajor(rs.getString("s_major"));
                data.add(student);
            }
            idStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("idnumberStudent"));
            fnameStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
            lnameStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
            phoneStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("phonenumber"));
            majorStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
            tableStudent.setItems(data);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
