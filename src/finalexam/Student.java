package finalexam;

import java.sql.*;
import java.util.Objects;
import javax.swing.JOptionPane;

public class Student {

    private String idnumberStudent, firstname, lastname, phonenumber, major;

    public Student() {
        this.idnumberStudent = "";
        this.firstname = "";
        this.lastname = "";
        this.phonenumber = "";
        this.major = "";
    }

    public Student(String idnumberStudent, String firstname, String lastname, String phonenumber, String major) {
        this.idnumberStudent = idnumberStudent;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.major = major;
    }

    public void setIdnumberStudent(String idnumberStudent) {
        this.idnumberStudent = idnumberStudent;
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

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdnumberStudent() {
        return idnumberStudent;
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

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return "Student{" + "idnumberStudent=" + idnumberStudent + ", firstname=" + firstname + ", lastname=" + lastname + ", phonenumber=" + phonenumber + ", major=" + major + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idnumberStudent);
        hash = 41 * hash + Objects.hashCode(this.firstname);
        hash = 41 * hash + Objects.hashCode(this.lastname);
        hash = 41 * hash + Objects.hashCode(this.phonenumber);
        hash = 41 * hash + Objects.hashCode(this.major);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.idnumberStudent, other.idnumberStudent)) {
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
        if (!Objects.equals(this.major, other.major)) {
            return false;
        }
        return true;
    }

    public static void addStudent(String id, String fname, String lname, String phone, String department) {

        PreparedStatement pst;
        Connection conn = Database.connDB();

        String ins = "insert into students (s_id,s_first_name,s_last_name,s_phone_number,s_major) values (?,?,?,?,?) ";
        try {
            pst = conn.prepareStatement(ins);
            pst.setString(1, id);
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setString(4, phone);
            pst.setString(5, department);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public static void check(String id, String fname, String lname, String phone, String major) {
        if ("".equals(id)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter id number of  student.");
        } else if ("".equals(fname)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter first name of  student.");
        } else if ("".equals(lname)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter last name of  student.");
        } else if ("".equals(phone)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter phone number of  student.");
        } else if ("".equals(major)) {
            JOptionPane.showMessageDialog(null, "Plz,Enter major of  student.");
        }
        addStudent(id, fname, lname, phone, major);
    }

}
