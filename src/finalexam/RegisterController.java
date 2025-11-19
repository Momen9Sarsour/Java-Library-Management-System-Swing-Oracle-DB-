package finalexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class RegisterController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ImageView RegisterImageV;
    @FXML
    private TextField firstNameTxtField;
    @FXML
    private TextField lastNameTxtField;
    @FXML
    private TextField idNumTxtField;
    @FXML
    private TextField phoneTxtField;
    @FXML
    private TextField PasswordRegstTxtField;
    @FXML
    private TextField ConfPasswordRegstTxtField1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File AdminFile = new File("Images/RegisterBrand.jpg");
        Image RegisterImage= new Image(AdminFile.toURI().toString());
        RegisterImageV.setImage(RegisterImage);
    }

    public void register(ActionEvent actionEvent) {
        User user = new User();
        user.setIdnumberUser(idNumTxtField.getText());
        user.setFirstname(firstNameTxtField.getText());
        user.setLastname(lastNameTxtField.getText());
        user.setPassword(PasswordRegstTxtField.getText());
        user.setPhonenumber(phoneTxtField.getText());
        User.check(user.getIdnumberUser(), user.getFirstname(),
                user.getLastname(), user.getPassword(),
                ConfPasswordRegstTxtField1.getText(),
                user.getPhonenumber());
        if (user.getPassword().equals(ConfPasswordRegstTxtField1.getText())) {
            try {
                User.addUser(user.getIdnumberUser(), user.getFirstname(),
                        user.getLastname(), user.getPassword(),
                        user.getPhonenumber());
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Passwords are not compatible!");
        }
    }
    
    public void close(ActionEvent actionEvent){
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }

}
