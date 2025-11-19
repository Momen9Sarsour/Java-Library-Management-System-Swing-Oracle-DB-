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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class ReturnBookController implements Initializable {

    @FXML
    private TextField idBorr;
    @FXML
    private TextField idBo;
    @FXML
    private TextField idsearch;
    @FXML
    private RadioButton rStaff;
    @FXML
    private RadioButton rStudent;
    @FXML
    private Button BackReturnBookBtn;
    @FXML
    private ToggleGroup sORst;
    @FXML
    private ImageView SearchReturnBookIcon;
    @FXML
    private TableView tableReturn;
    @FXML 
            TableColumn idBook;
    @FXML 
            TableColumn idUser;
    @FXML 
            TableColumn idBorrower;
    @FXML 
            TableColumn lateDays;
    @FXML 
            TableColumn returnData;
    @FXML 
            TableColumn fineFinancial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File ReturnBookFile = new File("Images/002-search.png");
        Image SearchReturnBook = new Image(ReturnBookFile.toURI().toString());
        SearchReturnBookIcon.setImage(SearchReturnBook);
        showBooks();
    }

    public void BackButtonSearchAction(ActionEvent actionEvent) throws IOException { //Admin close

        Parent view3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
        Scene scene3 = new Scene(view3);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    public void returnBook(ActionEvent action) {
        //   sORst = new ToggleGroup();
        int n ;
        this.rStaff.setToggleGroup(sORst);
        this.rStudent.setToggleGroup(sORst);
        if (this.sORst.getSelectedToggle().equals(this.rStaff)) {
            n = 1;
        } else if (this.sORst.getSelectedToggle().equals(this.rStudent)) {
            n = 2;
        } else {
            n = 3;
        }
        Book_return borr = new Book_return();
        borr.setLibrarian_number(User.USER_ID);
        borr.setBorrowing_number(idBorr.getText());
        borr.setBook_number(idBo.getText());

        Book_return.checkint(borr.getBorrowing_number(), borr.getBook_number(), n);
        showBooks();
    }

    public void showBooks() {
        ObservableList<Book_return> data = FXCollections.observableArrayList();
        String sele = "select * from s_returned_books union all select * from st_returned_books";
        try {
            Connection conn = Database.connDB();
            PreparedStatement pst = conn.prepareStatement(sele);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs);
                Book_return book = new Book_return();
                book.setLibrarian_number(rs.getString("u_id"));
                book.setBook_number(rs.getString("b_id"));
                book.setBorrowing_number(rs.getString("s_id"));
                book.setFinancial(rs.getDouble("financil"));
                book.setBorrowing_late(rs.getInt("late_days"));
                book.setBorrowing_date(rs.getString("return_date"));
                data.add(book);
            }
            idBook.setCellValueFactory(new PropertyValueFactory<Book_return, String>("librarian_number"));
            idUser.setCellValueFactory(new PropertyValueFactory<Book_return, String>("book_number"));
            idBorrower.setCellValueFactory(new PropertyValueFactory<Book_return, String>("borrowing_number"));
            lateDays.setCellValueFactory(new PropertyValueFactory<Book_return, Integer>("borrowing_late"));
            returnData.setCellValueFactory(new PropertyValueFactory<Book_return, String>("borrowing_date"));
            fineFinancial.setCellValueFactory(new PropertyValueFactory<Book_return, Double>("financial"));
            tableReturn.setItems(data);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void search(ActionEvent action) {
        
        if(idsearch.getText().equals(""))
            showBooks();
        
        ObservableList<Book_return> data = FXCollections.observableArrayList();
        String sele = "    select * from s_returned_books where s_id = ? union all select * from st_returned_books where st_id = ?";
        try {
            Connection conn = Database.connDB();
            PreparedStatement pst = conn.prepareStatement(sele);
            pst.setString(1, idsearch.getText());
            pst.setString(2, idsearch.getText());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs);
                Book_return book = new Book_return();
                book.setLibrarian_number(rs.getString("u_id"));
                book.setBook_number(rs.getString("b_id"));
                book.setBorrowing_number(rs.getString("s_id"));
                book.setFinancial(rs.getDouble("financil"));
                book.setBorrowing_late(rs.getInt("late_days"));
                book.setBorrowing_date(rs.getString("return_date"));
                data.add(book);
            }
            idBook.setCellValueFactory(new PropertyValueFactory<Book_return, String>("librarian_number"));
            idUser.setCellValueFactory(new PropertyValueFactory<Book_return, String>("book_number"));
            idBorrower.setCellValueFactory(new PropertyValueFactory<Book_return, String>("borrowing_number"));
            lateDays.setCellValueFactory(new PropertyValueFactory<Book_return, Integer>("borrowing_late"));
            returnData.setCellValueFactory(new PropertyValueFactory<Book_return, String>("borrowing_date"));
            fineFinancial.setCellValueFactory(new PropertyValueFactory<Book_return, Double>("financial"));
            tableReturn.setItems(data);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}
