package finalexam;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class AdminController implements Initializable {

    @FXML
    private Button AdminloginButton;
    @FXML
    private Button AdminClose;
    @FXML
    private ImageView AdmenImageV;

    @FXML
    private TextField UsernameAdmenTextFiled;
    @FXML
    private TextField PasswordAdmenTextFiled;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File AdminFile = new File("Images/adminLogin.png");
        Image AdminImage = new Image(AdminFile.toURI().toString());
        AdmenImageV.setImage(AdminImage);
        UsernameAdmenTextFiled.setText("admin");
        PasswordAdmenTextFiled.setText("admin2022");
    }
    public void login(ActionEvent actionEvent){
     String user = UsernameAdmenTextFiled.getText();
        String pass = PasswordAdmenTextFiled.getText();
        try {
            if(("admin".equals(user)&&"admin2022".equals(pass))||("asd".equals(user)&&"123".equals(pass)))
            {
            FXMLLoader fxmlLoader = new FXMLLoader(FinalExam.class.getResource("Register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 400);
            Stage RegisterStage = new Stage();
            RegisterStage.setTitle("Register Data!");
            RegisterStage.setScene(scene);
            RegisterStage.setResizable(false);
            RegisterStage.show();
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Username or Password is not correct!!");
            }
        }catch (Exception s){

        }


    }

    public void close(ActionEvent actionEvent){ //Admin close
        Stage stage = (Stage)AdminClose.getScene().getWindow();
        stage.close();
    }
}

