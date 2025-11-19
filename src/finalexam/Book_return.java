package finalexam;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JOptionPane;

public class Book_return {
    private String librarian_number , borrowing_number , book_number , borrowing_date , borrowing_enddate ;
    private int borrowing_late ;
    private double  financial ;

    public Book_return() {
        this.librarian_number = "";
        this.borrowing_number = "";
        this.book_number = "";
        this.borrowing_date = "";
        this.borrowing_enddate = "";
        this.borrowing_late = 0 ;
        this.financial = 0.0 ;
    }

    public Book_return(String librarian_number, String borrowing_number, String book_number, String borrowing_date, String borrowing_enddate, int borrowing_late, double financial) {
        this.librarian_number = librarian_number;
        this.borrowing_number = borrowing_number;
        this.book_number = book_number;
        this.borrowing_date = borrowing_date;
        this.borrowing_enddate = borrowing_enddate;
        this.borrowing_late = borrowing_late;
        this.financial = financial;
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

    public void setBorrowing_late(int borrowing_late) {
        this.borrowing_late = borrowing_late;
    }

    public void setFinancial(double financial) {
        this.financial = financial;
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

    public int getBorrowing_late() {
        return borrowing_late;
    }

    public double getFinancial() {
        return financial;
    }

    @Override
    public String toString() {
        return "Book_return{" + "librarian_number=" + librarian_number + ", borrowing_number=" + borrowing_number + ", book_number=" + book_number + ", borrowing_date=" + borrowing_date + ", borrowing_enddate=" + borrowing_enddate + ", borrowing_late=" + borrowing_late + ", financial=" + financial + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.librarian_number);
        hash = 29 * hash + Objects.hashCode(this.borrowing_number);
        hash = 29 * hash + Objects.hashCode(this.book_number);
        hash = 29 * hash + Objects.hashCode(this.borrowing_date);
        hash = 29 * hash + Objects.hashCode(this.borrowing_enddate);
        hash = 29 * hash + this.borrowing_late;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.financial) ^ (Double.doubleToLongBits(this.financial) >>> 32));
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
        final Book_return other = (Book_return) obj;
        if (this.borrowing_late != other.borrowing_late) {
            return false;
        }
        if (Double.doubleToLongBits(this.financial) != Double.doubleToLongBits(other.financial)) {
            return false;
        }
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

    public static void returnBook(int n ,String u_id, String borr_id, String b_id) {
          CallableStatement  callpst ;
        Connection conn = Database.connDB();
        double x = 0.0;
        String ins = "" ;
        try {  
            if (n == 1) {
                ins = "begin ? := get_val_st(?,?,?); end;";
            } else if (n == 2) {
                ins = "begin ? := get_val_s(?,?,?); end;";
            }
            callpst = conn.prepareCall(ins);
            callpst.setDouble(1, x);
            callpst.setString(2, borr_id);
            callpst.setString(3, b_id);
            callpst.setString(4, u_id);
            callpst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted Don.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public static void checkint(String borr_id, String b_id, int n) {

        if ("".equals(borr_id)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  borrowed.");
        } else if ("".equals(b_id)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  book.");
        } else if (n == 3) {
            JOptionPane.showMessageDialog(null, "Plz,Choose satff or Student.");
        }
        returnBook(n, User.USER_ID, borr_id, b_id);
    }

    
}
