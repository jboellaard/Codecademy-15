package DB;

import java.sql.*;
import java.util.*;

import Domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();

    public StudentRepo(){
        
    }

    public void create(Student student){
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Student VALUES ('?','?','?','?',?);");
            pstmt.setString(1, student.getEmailAddress());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDateOfBirth());
            pstmt.setString(4, student.getGender().name());
            pstmt.setInt(5, student.getAddressID());

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

    public ObservableList<Student> getAllStudents(){
        final ObservableList<Student> allStudents = FXCollections.observableArrayList();
        // List<Student> allStudents = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                allStudents.add(new Student(rs.getString("Name"),rs.getString("EmailAddress"),rs.getString("DateOfBirth"),Gender.valueOf(rs.getString("Gender")),rs.getInt("AddressID")));
            }
            return allStudents;
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
        return null;
    }

    public void update(Student student){
        
    }
    
    public void delete(){

    }
}
