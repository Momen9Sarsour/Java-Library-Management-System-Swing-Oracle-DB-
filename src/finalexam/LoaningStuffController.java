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
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

public class LoaningStuffController implements Initializable {

    @FXML
    private ImageView LoaningStuffImageV;
    @FXML
    private Button BackLoaningStuffBtn;
    @FXML
    private TextField stid;
    @FXML
    private TextField bid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File LoaningStuffFile = new File("Images/loan.png");
        Image LoaningStuffImage = new Image(LoaningStuffFile.toURI().toString());
        LoaningStuffImageV.setImage(LoaningStuffImage);
        System.out.println(User.USER_ID);
    }

    public void BackButtonLoaningStuff(ActionEvent actionEvent) throws IOException { //Admin close

        Parent view3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
        Scene scene3 = new Scene(view3);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    public void loaningStaff(ActionEvent action) {
        
        Book_Loan borr = new Book_Loan();
        borr.setLibrarian_number(User.USER_ID);
        borr.setBorrowing_number(stid.getText());
        borr.setBook_number(bid.getText());
        Book_Loan.checkint( borr.getBorrowing_number(),  borr.getBook_number());
        Book_Loan.borrowBook(1, borr.getLibrarian_number(), borr.getBorrowing_number(),
                borr.getBook_number());

    }

}
