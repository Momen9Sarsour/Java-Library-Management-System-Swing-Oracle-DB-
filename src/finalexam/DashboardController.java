package finalexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private ImageView AddStudentImageV;
    @FXML
    private Button AddStudentBtn;

    @FXML
    private ImageView AddStuffImageV;
    @FXML
    private Button AddStuffBtn;
    @FXML
    private ImageView AddBookImageV;
    @FXML
    private Button AddBookBtn;
    @FXML
    private ImageView SearchBookImageV;
    @FXML
    private Button SearchBookBtn;
    @FXML
    private ImageView StBorrowersImageV;
    @FXML
    private Button StBorrowersBtn;
    @FXML
    private ImageView StaffBorrowersImageV;
    @FXML
    private Button StaffBorrowersBtn;
    @FXML
    private ImageView LoaningStudentImageV;
    @FXML
    private Button LoaningStudentBtn;
    @FXML
    private ImageView LoaningStaffImageV;
    @FXML
    private Button LoaningStaffIBtn;
    @FXML
    private ImageView ReturnBookImageV;
    @FXML
    private Button ReturnBookBtn;


   public void addStudent(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddStudent.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();

    }


    public void addStaff(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddStaff.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();

    }
    public void AddBook(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddBook.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();

    }
    public void SearchBook(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SearchBook.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.setX(250);
         window.setY(50);
        window.show();

    }public void ReturnBook(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ReturnBook.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
          window.setX(250);
         window.setY(50);
        window.show();

    }
    public void LoaningStudent(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoaningStudent.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();

    }
    public void LoaningStuff(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoaningStuff.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();

    }
    public void StudentBorrowers(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentBorrowers.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();

    }
    public void StuffBorrowers(ActionEvent event) throws IOException {
        Parent view6=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StuffBorrowers.fxml")));
        Scene scene6=new Scene(view6);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();

    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File AddStudentFile = new File("Images/add-user.png");
        Image AddStudentImage = new Image(AddStudentFile.toURI().toString());
        AddStudentImageV.setImage(AddStudentImage);

        File  AddStaffFile = new File("Images/003-add-user.png");
        Image AddStuffImage = new Image(AddStaffFile.toURI().toString());
        AddStuffImageV.setImage(AddStuffImage);

        File  AddBookFile = new File("Images/001-book.png");
        Image AddBookImage = new Image(AddBookFile.toURI().toString());
        AddBookImageV.setImage(AddBookImage);

        File  SearchBookFile = new File("Images/001-search.png");
        Image SearchBookImage = new Image(SearchBookFile.toURI().toString());
        SearchBookImageV.setImage(SearchBookImage);

        File  StBorrowersFile = new File("Images/002-read.png");
        Image StBorrowersImage = new Image(StBorrowersFile.toURI().toString());
        StBorrowersImageV.setImage(StBorrowersImage);

        File  StaffBorrowesFile = new File("Images/003-teacher.png");
        Image StaffBorrowersImage = new Image(StaffBorrowesFile.toURI().toString());
        StaffBorrowersImageV.setImage(StaffBorrowersImage);

        File  LoaningStudentFile = new File("Images/003-student-1.png");
        Image LoaningStudentImage = new Image(LoaningStudentFile.toURI().toString());
        LoaningStudentImageV.setImage(LoaningStudentImage);

        File  LoaningStaffFile = new File("Images/001-teacher.png");
        Image LoaningStaffImage = new Image(LoaningStaffFile.toURI().toString());
        LoaningStaffImageV.setImage(LoaningStaffImage);

        File  ReturnBookFile = new File("Images/001-return.png");
        Image ReturnBookImage = new Image(ReturnBookFile.toURI().toString());
        ReturnBookImageV.setImage(ReturnBookImage);
    }
    }


