package finalexam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JOptionPane;

public class Book {

    private String idnumberBook, title, publisher, author, year_ofpublication;
    private int number_copies;
    private double financialvalue;
    private boolean status;

    public Book() {
        this.title = "";
        this.publisher = "";
        this.author = "";
        this.year_ofpublication = "";
        this.number_copies = 0;
        this.financialvalue = 0.0;
        this.status = false;

    }

    public Book(String id, String title, String publisher, String author, String year_ofpublication, int number_copies, double financialvalue) {
        this.idnumberBook = id;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.year_ofpublication = year_ofpublication;
        this.number_copies = number_copies + this.number_copies;
        this.financialvalue = financialvalue;
    }

    public void setIdnumberBook(String idnumberBook) {
        this.idnumberBook = idnumberBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear_ofpublication(String year_ofpublication) {
        this.year_ofpublication = year_ofpublication;
    }

    public void setNumber_copies(int number_copies) {
        this.number_copies = number_copies;
    }

    public void setFinancialvalue(double financialvalue) {
        this.financialvalue = financialvalue;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdnumberBook() {
        return idnumberBook;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear_ofpublication() {
        return year_ofpublication;
    }

    public int getNumber_copies() {
        return number_copies;
    }

    public double getFinancialvalue() {
        return financialvalue;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Book{" + "idnumberBook=" + idnumberBook + ", title=" + title + ", publisher=" + publisher + ", author=" + author + ", year_ofpublication=" + year_ofpublication + ", number_copies=" + number_copies + ", financialvalue=" + financialvalue + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idnumberBook);
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.publisher);
        hash = 67 * hash + Objects.hashCode(this.author);
        hash = 67 * hash + Objects.hashCode(this.year_ofpublication);
        hash = 67 * hash + this.number_copies;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.financialvalue) ^ (Double.doubleToLongBits(this.financialvalue) >>> 32));
        hash = 67 * hash + (this.status ? 1 : 0);
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
        final Book other = (Book) obj;
        if (this.number_copies != other.number_copies) {
            return false;
        }
        if (Double.doubleToLongBits(this.financialvalue) != Double.doubleToLongBits(other.financialvalue)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idnumberBook, other.idnumberBook)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.year_ofpublication, other.year_ofpublication)) {
            return false;
        }
        return true;
    }

    public static void addBook(String id, String title, String publisher, String author, String year_ofpublication, int number_copies, double financialvalue) {
        PreparedStatement pst;
        //ResultSet rs = null;
        Connection conn;

        String ins = "insert into books (b_id,b_title,b_publisher,b_author,b_year_of_publication,b_number_of_copies,b_financil_value) values (?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?) ";
        try {
            conn = Database.connDB();
            pst = conn.prepareStatement(ins);
            pst.setString(1, id);
            pst.setString(2, title);
            pst.setString(3, publisher);
            pst.setString(4, author);
            pst.setString(5, year_ofpublication);
            pst.setInt(6, number_copies);
            pst.setDouble(7, financialvalue);
           /* rs =*/ pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted Don.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public static void check(String id, String title, String publisher, String author, String year_ofpublication/*, int number_copies, double financialvalue*/) {

        if ("".equals(id)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  book.");
        } else if ("".equals(title)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter title of  book.");
        } else if ("".equals(publisher)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter publisher of  book.");
        } else if ("".equals(author)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter author of  book.");
        } else if ("".equals(year_ofpublication)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter year of publication of  book.");
        }

    }

    public static ResultSet search(String id) {
        PreparedStatement pst;
        ResultSet rs = null;
        Connection conn;

        String sel = "select * from books where b_id = ?";
        try {
            conn = Database.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
}
