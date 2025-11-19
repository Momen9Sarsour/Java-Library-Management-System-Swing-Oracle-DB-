package finalexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class SearchBookController implements Initializable {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;

    @FXML
    private ImageView SearchIconImageV;
    @FXML
    private TextField idBook;
    @FXML
    private Button BackSearchBtn;
    @FXML
    private TableView tableBook;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn title;
    @FXML
    private TableColumn author;
    @FXML
    private TableColumn publisher;
    @FXML
    private TableColumn numberCopies;
    @FXML
    private TableColumn finacial;
    @FXML
    private TableColumn yearPob;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File SearchIconFile = new File("Images/002-search.png");
        Image SearchIconImage = new Image(SearchIconFile.toURI().toString());
        SearchIconImageV.setImage(SearchIconImage);
        showBooks();
    }

    public void BackButtonSearchAction(ActionEvent actionEvent) throws IOException { //Admin close

        Parent view3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
        Scene scene3 = new Scene(view3);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    public void showBooks() {
        ObservableList<Book> data = FXCollections.observableArrayList();
        String sele = "select * from books";
        try {
            conn = Database.connDB();
            pst = conn.prepareStatement(sele);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs);
                Book book = new Book();
                book.setIdnumberBook(rs.getString("b_id"));
                book.setTitle(rs.getString("b_title"));
                book.setAuthor(rs.getString("b_author"));
                book.setPublisher(rs.getString("b_publisher"));
                book.setNumber_copies(rs.getInt("b_number_of_copies"));
                book.setFinancialvalue(rs.getDouble("b_financil_value"));
                book.setYear_ofpublication(rs.getString("b_year_of_publication"));
                data.add(book);
            }
            id.setCellValueFactory(new PropertyValueFactory<Book, String>("idnumberBook"));
            title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
            author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
            publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
            numberCopies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("number_copies"));
            finacial.setCellValueFactory(new PropertyValueFactory<Book, Double>("financialvalue"));
            yearPob.setCellValueFactory(new PropertyValueFactory<Book, String>("year_ofpublication"));
            tableBook.setItems(data);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void SearchBook(ActionEvent action) {
        ResultSet rs = Book.search(idBook.getText());
        ObservableList<Book> data = FXCollections.observableArrayList();
        Book book = new Book();
        try {
            while (rs.next()) {
                book.setIdnumberBook(rs.getString("b_id"));
                book.setTitle(rs.getString("b_title"));
                book.setAuthor(rs.getString("b_author"));
                book.setPublisher(rs.getString("b_publisher"));
                book.setNumber_copies(rs.getInt("b_number_of_copies"));
                book.setFinancialvalue(rs.getDouble("b_financil_value"));
                book.setYear_ofpublication(rs.getString("b_year_of_publication"));
                data.add(book);
            }
            id.setCellValueFactory(new PropertyValueFactory<Book, String>("idnumberBook"));
            title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
            author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
            publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
            numberCopies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("number_copies"));
            finacial.setCellValueFactory(new PropertyValueFactory<Book, Double>("financialvalue"));
            yearPob.setCellValueFactory(new PropertyValueFactory<Book, String>("year_ofpublication"));
            tableBook.setItems(data);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}
