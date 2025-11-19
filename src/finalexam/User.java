package finalexam;

import java.sql.*;
import java.util.Objects;
import javax.swing.JOptionPane;

public class User {
    public static String USER_ID = "" ;
    private String idnumberUser, firstname, lastname, password, phonenumber;

    public User() {
        this.idnumberUser = "";
        this.firstname = "";
        this.lastname = "";
        this.password = "";
        this.phonenumber = "";
    }

    public User(String idnumberUser, String firstname, String lastname, String password, String phonenumber) {
        this.idnumberUser = idnumberUser;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public String getIdnumberUser() {
        return idnumberUser;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setIdnumberUser(String idnumberUser) {
        this.idnumberUser = idnumberUser;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "User{" + "idnumberUser=" + idnumberUser + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", phonenumber=" + phonenumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idnumberUser);
        hash = 83 * hash + Objects.hashCode(this.firstname);
        hash = 83 * hash + Objects.hashCode(this.lastname);
        hash = 83 * hash + Objects.hashCode(this.password);
        hash = 83 * hash + Objects.hashCode(this.phonenumber);
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
        final User other = (User) obj;
        if (!Objects.equals(this.idnumberUser, other.idnumberUser)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.phonenumber, other.phonenumber)) {
            return false;
        }
        return true;
    }

    public static boolean login(String user_name, String password) {
        PreparedStatement pst ;
        ResultSet rs ;
        Connection conn = Database.connDB();
        boolean found = false;
        String log = "select * from users where u_id = ? and u_password = ?";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, user_name);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                found = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return found;
    }
    
    public static void addUser(String id, String fname, String lname, String password ,String phone) {
        PreparedStatement pst ;
        Connection conn = Database.connDB() ;

        String ins = "insert into users (u_id,u_first_name,u_last_name,u_password,u_phone_number) values (?,?,?,?,?) ";
        try {
            pst = conn.prepareStatement(ins);
            pst.setString(1, id);
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setString(4, password);
            pst.setString(5, phone);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted Don.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }

    public static void check(String id, String fname, String lname,String pass1,String pass2 ,String phone) {
        if ("".equals(id)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  user.");
        } else if ("".equals(fname)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter first name of  user.");
        } else if ("".equals(lname)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter last name of  user.");
        } else if ("".equals(pass1)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter password of  user.");
        } else if ("".equals(pass2)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter confirm password of  user.");
        } else if ("".equals(phone)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter phone number of  user.");
        }

        /*
        String ins = "insert into students (s_id,s_first_name,s_last_name,s_phone_number,s_major) values (?,?,?,?,?) ";
        try {
            
            pst.setString(1, id);
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setString(4, phone);
            pst.setString(5, major);
            pst = conn.prepareStatement(ins);
            rs = pst.executeQuery();
            
            JOptionPane.showMessageDialog(null, "Inserted Don.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         */
    }

}
