package finalexam;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class Book_Loan {

    private String librarian_number, borrowing_number, book_number, borrowing_date, borrowing_enddate;

    public Book_Loan() {
        this.librarian_number = "";
        this.borrowing_number = "";
        this.book_number = "";
        this.borrowing_date = "";
        this.borrowing_enddate = "";
    }

    public Book_Loan(String librarian_number, String borrowing_number, String book_number, String borrowing_date, String borrowing_enddate) {
        this.librarian_number = librarian_number;
        this.borrowing_number = borrowing_number;
        this.book_number = book_number;
        this.borrowing_date = borrowing_date;
        this.borrowing_enddate = borrowing_enddate;
    }

    public void setLibrarian_number(String librarian_number) {
        this.librarian_number = librarian_number;
    }

    public void setBorrowing_number(String borrowing_number) {
        this.borrowing_number = borrowing_number;
    }

    public void setBook_number(String book_number) {
        this.book_number = book_number;
    }

    public void setBorrowing_date(String borrowing_date) {
        this.borrowing_date = borrowing_date;
    }

    public void setBorrowing_enddate(String borrowing_enddate) {
        this.borrowing_enddate = borrowing_enddate;
    }

    public String getLibrarian_number() {
        return librarian_number;
    }

    public String getBorrowing_number() {
        return borrowing_number;
    }

    public String getBook_number() {
        return book_number;
    }

    public String getBorrowing_date() {
        return borrowing_date;
    }

    public String getBorrowing_enddate() {
        return borrowing_enddate;
    }

    @Override
    public String toString() {
        return "Book_Loan{" + "librarian_number=" + librarian_number + ", borrowing_number=" + borrowing_number + ", book_number=" + book_number + ", borrowing_date=" + borrowing_date + ", borrowing_enddate=" + borrowing_enddate +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.librarian_number);
        hash = 97 * hash + Objects.hashCode(this.borrowing_number);
        hash = 97 * hash + Objects.hashCode(this.book_number);
        hash = 97 * hash + Objects.hashCode(this.borrowing_date);
        hash = 97 * hash + Objects.hashCode(this.borrowing_enddate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book_Loan other = (Book_Loan) obj;
        if (!Objects.equals(this.librarian_number, other.librarian_number)) {
            return false;
        }
        if (!Objects.equals(this.borrowing_number, other.borrowing_number)) {
            return false;
        }
        if (!Objects.equals(this.book_number, other.book_number)) {
            return false;
        }
        if (!Objects.equals(this.borrowing_date, other.borrowing_date)) {
            return false;
        }
        if (!Objects.equals(this.borrowing_enddate, other.borrowing_enddate)) {
            return false;
        }
        return true;
    }

    public static void borrowBook(int n, String u_id, String borr_id, String b_id) {
        PreparedStatement pst, pst1;
        CallableStatement callpst;
        int x = 0;
        Connection conn = Database.connDB();
        ResultSet rs ;
        try {
            String check = "select * from books where b_id = ?";
            callpst = conn.prepareCall(check);
            callpst.setString(1, b_id);
            rs = callpst.executeQuery();
            while (rs.next()) {                
            x = rs.getInt("b_number_of_copies");
            System.out.println(x);
            System.out.println(rs);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if(x > 0){
        String ins = "" ;
        String upd ;
        try {  
            if (n == 1) {
                ins = " insert into BORROW_STAFF (u_id,b_id,st_id) values (?,?,?) ";
            } else if (n == 2) {
                ins = " insert into BORROW_STDUDENT (u_id,b_id,s_id) values (?,?,?) ";
            }
            pst = conn.prepareStatement(ins);
            pst.setString(1, u_id);
            pst.setString(2, b_id);
            pst.setString(3, borr_id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted Don.");
            System.out.println(x);
            upd = "UPDATE books    SET  b_number_of_copies = ( b_number_of_copies - 1 )    WHERE      b_id =  ? ";
            pst1 = conn.prepareStatement(upd);
            pst1.setString(1, b_id);
            pst1.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
        else
            JOptionPane.showMessageDialog(null,"There are no copies of this book available.");
    }
    
    public static void checkint ( String borr_id, String b_id) {
        ResultSet rs = null;
        if("".equals(borr_id))
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  borrowed.");
        else if("".equals(b_id))
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  book.");
 }

}



        /*else{
            PreparedStatement pst;
            Connection conn = Database.connDB();
            try {
                String id = "select * from user_id";
                pst = conn.prepareStatement(id);
                rs = pst.executeQuery();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }*/
