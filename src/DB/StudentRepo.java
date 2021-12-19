package DB;

import java.sql.*;
// import java.util.*;
import Domain.*;

public class StudentRepo {

    public StudentRepo(){
        
    }

    public void create(Student student){
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=StudentTemp;integratedSecurity=true;";
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Student VALUES ('"+ student.getEmail() +"','"+student.getName() + "','"+student.getDOB() + "','" + student.getGender() + "'," +student.getAddressID()+ ");";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            
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
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=StudentTemp;integratedSecurity=true;";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM Student";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

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
