package DB;

import java.sql.*;
// import java.util.*;
import Domain.*;

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
            pstmt.setString(1, student.getEmail());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDOB());
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

    public String getAllStudents(){
        String all = "";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String email = rs.getString("EmailAddress");
                String name = rs.getString("Name");
                String DOB = rs.getString("DateOfBirth");

                all += "\n" + email + " - " + name + " - " + DOB;
            }
            return all;

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
        return "";
    }

    public void update(Student student){
        
    }
    
    public void delete(){

    }
}
