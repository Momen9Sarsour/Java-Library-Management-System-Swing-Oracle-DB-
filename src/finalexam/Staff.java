package finalexam;

import java.sql.*;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JOptionPane;

public class Staff {

    private String idnumberStaff, firstname, lastname, phonenumber, department;

    public Staff() {
        this.idnumberStaff = "";
        this.firstname = "";
        this.lastname = "";
        this.phonenumber = "";
        this.department = "";
    }

    public Staff(String idnumberStaff, String firstname, String lastname, String phonenumber, String department) {
        this.idnumberStaff = idnumberStaff;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.department = department;
    }

    public void setIdnumberStaff(String idnumberStaff) {
        this.idnumberStaff = idnumberStaff;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIdnumberStaff() {
        return idnumberStaff;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Staff{" + "idnumberStaff=" + idnumberStaff + ", firstname=" + firstname + ", lastname=" + lastname + ", phonenumber=" + phonenumber + ", department=" + department + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idnumberStaff);
        hash = 79 * hash + Objects.hashCode(this.firstname);
        hash = 79 * hash + Objects.hashCode(this.lastname);
        hash = 79 * hash + Objects.hashCode(this.phonenumber);
        hash = 79 * hash + Objects.hashCode(this.department);
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
        final Staff other = (Staff) obj;
        if (!Objects.equals(this.idnumberStaff, other.idnumberStaff)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.phonenumber, other.phonenumber)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        return true;
    }

    public static void addStaff(String id, String fname, String lname, String phone, String department) {

       
        PreparedStatement pst ;
        Connection conn = Database.connDB() ;

        String ins = "insert into staffs (st_id,st_first_name,st_last_name,st_phone_number,st_department) values (?,?,?,?,?) ";
        try {
            pst = conn.prepareStatement(ins);
            pst.setString(1, id);
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setString(4, phone);
            pst.setString(5, department);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted Don.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }

    public static void check(String id, String fname, String lname, String phone, String department) {
        if ("".equals(id)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  staff.");
        } else if ("".equals(fname)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter first name of  staff.");
        } else if ("".equals(lname)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter last name of  staff.");
        } else if ("".equals(phone)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter phone number of  staff.");
        } else if ("".equals(department)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter department of  staff.");
        }
        addStaff(id, fname, lname, phone, department);
    }

}
