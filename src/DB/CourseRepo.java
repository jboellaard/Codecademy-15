package DB;

import java.sql.*;

import Domain.*;
import GUI.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();
    private String driverUrl = DBConnection.getDriverUrl();
    final ObservableList<Course> allCourses = FXCollections.observableArrayList();

    public CourseRepo(){
        getAllCoursesFromDB();
    }

    public void create(Course course){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?);");
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
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public ObservableList<Course> getAllCourses(){
        return this.allCourses;
    }

    public void getAllCoursesFromDB(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("SELECT * FROM Course");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                allCourses.add(new Course(rs.getString("CourseName"),rs.getString("Subject"),rs.getString("IntroductionText"),LevelIndication.valueOf(rs.getString("LevelIndication"))));
                //add modules and add recommendedcourses
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public void update(Course course, String previousName){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("UPDATE Course SET CourseName=?, Subject=?, IntroductionText=?, LevelIndication=? WHERE CourseName=?;");
            pstmt.setString(1, course.getName());
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
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
    
    public void delete(Student student){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("DELETE FROM Student WHERE EmailAddress=?;");
            pstmt.setString(1, student.getEmailAddress());

            pstmt.executeUpdate();
            allStudents.remove(student);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }


    
}
