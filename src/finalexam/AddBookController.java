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
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddBookController implements Initializable {

    Connection conn = null;

    @FXML
    private ImageView AddBookImageV;

    @FXML
    private Button BackAddBookBtn;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField AuthorTextField;
    @FXML
    private TextField PublisherTextField;
    @FXML
    private TextField IDTextField;
    @FXML
    private DatePicker YearOFPublicTextField1;
    @FXML
    private TextField NumOFCopTextfield;

    @FXML
    private TextField EditioneTextfield;

    @FXML
    private TextField FinancailTextField;

    @FXML
    private Button AddBookBotton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = Database.connDB();
        System.out.println(conn);
        File AddBookFile = new File("Images/001-storytelling.png");
        Image AddBookImage = new Image(AddBookFile.toURI().toString());
        AddBookImageV.setImage(AddBookImage);
    }

    public void backDash(ActionEvent actionEvent) throws IOException { //Admin close

        Parent view3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
        Scene scene3 = new Scene(view3);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    public void addBook(ActionEvent actionEvent) {
        Book book = new Book();
        System.out.println(IDTextField.getText());
        System.out.println(YearOFPublicTextField1.getValue().toString());
        book.setIdnumberBook(IDTextField.getText());
        book.setTitle(TitleTextField.getText());
        book.setPublisher(PublisherTextField.getText());
        book.setAuthor(AuthorTextField.getText());
        book.setYear_ofpublication(YearOFPublicTextField1.getValue().toString());
        book.setNumber_copies(Integer.parseInt(NumOFCopTextfield.getText()));
        book.setFinancialvalue(Double.parseDouble(FinancailTextField.getText()));
        Book.addBook(book.getIdnumberBook(), book.getTitle(), book.getPublisher(),
                book.getAuthor(), book.getYear_ofpublication(),
                book.getNumber_copies(), book.getFinancialvalue());

    }

}
