package DB;

import java.sql.*;

import Domain.*;
import GUI.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();
    //add list of students so it can be used faster than making a connection every time?
    final ObservableList<Student> allStudents = FXCollections.observableArrayList();

    public StudentRepo(){
        getAllStudentsFromDB();
    }

    public void create(Student student){
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?);");
            pstmt.setString(1, student.getEmailAddress());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDateOfBirth());
            pstmt.setString(4, student.getGender().name());
            pstmt.setInt(5, student.getAddressID());

            pstmt.executeUpdate();
            allStudents.add(student);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public ObservableList<Student> getAllStudents(){
        return this.allStudents;
    }

    public void getAllStudentsFromDB(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                for (Address address : GUI.addressRepo.getAllAddresses()){
                    if (rs.getInt("AddressID") == address.getAddressID()){
                        allStudents.add(new Student(rs.getString("Name"),rs.getString("EmailAddress"),rs.getString("DateOfBirth"),Gender.valueOf(rs.getString("Gender")),address));
                        break;
                    }
                }
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public void update(Student student, String previousEmail){
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("UPDATE Student SET EmailAddress=?, Name=?, DateOfBirth=?, Gender=?, AddressID=? WHERE EmailAddress=?;");
            pstmt.setString(1, student.getEmailAddress());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDateOfBirth());
            pstmt.setString(4, student.getGender().name());
            pstmt.setInt(5, student.getAddressID());
            pstmt.setString(6, previousEmail);

            pstmt.executeUpdate();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
    
    public void delete(){

    }
}
