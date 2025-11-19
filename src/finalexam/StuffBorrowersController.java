package finalexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class StuffBorrowersController implements Initializable {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;
    @FXML
    private TextField idStaff;
    @FXML
    private TableView tableStaff;
    @FXML
    private TableColumn idSt;
    @FXML
    private TableColumn idB;
    @FXML
    private TableColumn idU;
    @FXML
    private TableColumn loanDate;
    @FXML
    private TableColumn returnDate;

    @FXML
    private ImageView SearchIconImageV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File StuffBorrowersFile = new File("Images/002-search.png");
        Image SearchIconImage = new Image(StuffBorrowersFile.toURI().toString());
        SearchIconImageV.setImage(SearchIconImage);
        showStaffBorrowers();

    }

    public void BackButtonStuffBorrsAction(ActionEvent actionEvent) throws IOException { //Admin close

        Parent view3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
        Scene scene3 = new Scene(view3);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    public void search(ActionEvent action) {
        PreparedStatement pst;
        ResultSet rs = null;
        Connection conn;
        ObservableList<Book_Loan> data = FXCollections.observableArrayList();

        String sel = "select * from BORROW_STAFF where st_id = ?";
        try {
            conn = Database.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, idStaff.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                Book_Loan borr = new Book_Loan();
                borr.setBorrowing_number(rs.getString("st_id"));
                borr.setBook_number(rs.getString("b_id"));
                borr.setLibrarian_number(rs.getString("u_id"));
                borr.setBorrowing_date(rs.getString("BRRROWING_DATE"));
                borr.setBorrowing_enddate(rs.getString("BRRROWING_END_DATE"));
                data.add(borr);
            }
            idSt.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("borrowing_number"));
            idB.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("book_number"));
            idU.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("librarian_number"));
            loanDate.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("borrowing_date"));
            returnDate.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("borrowing_enddate"));
            tableStaff.setItems(data);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void showStaffBorrowers() {
        PreparedStatement pst;
        ResultSet rs = null;
        Connection conn;
        ObservableList<Book_Loan> data = FXCollections.observableArrayList();

        //  String sel = "select s_id ,b_id,u_id,to_cahr(BRRROWING_DATE),to_cahr(BRRROWING_END_DATE) from BORROW_STDUDENT ";
        String sel = "select * from BORROW_STAFF ";

        try {
            conn = Database.connDB();
            pst = conn.prepareStatement(sel);
            // pst.setString(1, idStudent.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                Book_Loan borr = new Book_Loan();
                borr.setBorrowing_number(rs.getString("st_id"));
                borr.setBook_number(rs.getString("b_id"));
                borr.setLibrarian_number(rs.getString("u_id"));
                borr.setBorrowing_date(rs.getString("BRRROWING_DATE"));
                borr.setBorrowing_enddate(rs.getString("BRRROWING_END_DATE"));
                data.add(borr);
            }
            System.out.println(data);
            idSt.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("borrowing_number"));
            idB.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("book_number"));
            idU.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("librarian_number"));
            loanDate.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("borrowing_date"));
            returnDate.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("borrowing_enddate"));
            tableStaff.setItems(data);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    
}
