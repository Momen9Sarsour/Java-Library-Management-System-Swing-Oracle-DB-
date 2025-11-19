package finalexam;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class LoginController implements Initializable{
    
    
    @FXML
    private Button LogupButton;
    @FXML
    private Label MessageInvalid;
    @FXML
    private ImageView brandingImageV;
    @FXML
    private ImageView loginImageV;
    @FXML
    private TextField usernameTextfiled;
    @FXML
    private TextField PasswordTextfiled;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File  brandingFile = new File("Images/857.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageV.setImage(brandingImage);

        File  LoginFile = new File("Images/Login4.png");
        Image LoginImage = new Image(LoginFile.toURI().toString());
        loginImageV.setImage(LoginImage);
        usernameTextfiled.setText("admin");
         PasswordTextfiled.setText("admin");
         
                         
         PreparedStatement pst2 ;
        Connection conn = Database.connDB();
        ResultSet rss = null;
        try {
            String check = "select B_NUMBER_OF_COPIES from books where b_id = '29522';";
            pst2 = conn.prepareStatement(check);
           // pst2.setString(1, "29522");
            rss = pst2.executeQuery();
            System.out.println(rss);
            System.out.println(rss);
            System.out.println(rss);
            System.out.println(rss);

        } catch (Exception e) {
            
        }
            System.out.println(rss);
            System.out.println(rss);

    }
//    public void registerButtonOnAction(){}
    
    public void login(ActionEvent actionEvent)throws IOException {
//        if (!usernameTextfiled.getText().isEmpty()&& !PasswordTextfiled.getText().isEmpty()) {
////            validationLogin();
//                Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard2.fxml")));
//                Scene scene6=new Scene(view6);
//                Stage window =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//                window.setScene(scene6);
//                window.show();
//
//
//        }else
//            MessageInvalid.setText("Place , Enter username and password");
                if (User.login(usernameTextfiled.getText(), PasswordTextfiled.getText())) {
                    User.USER_ID = usernameTextfiled.getText();
                FXMLLoader fxmlLoader = new FXMLLoader(FinalExam.class.getResource("Dashboard2.fxml"));
                Scene scene = new Scene(fxmlLoader.load()/*, 520, 400*/);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setTitle("Login Data!");
                window.setScene(scene);
                window.show();
            } else /* ("".equals(usernameTextfiled.getText()) || "".equals(PasswordTextfiled.getText()))*/ {
                PasswordTextfiled.setText("");
                JOptionPane.showMessageDialog(null, "Username or Password is not correct!!");
            }

                
    }


    public void logup(ActionEvent action){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FinalExam.class.getResource("Admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch (Exception s){

        }


    }


}