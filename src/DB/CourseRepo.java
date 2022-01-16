package DB;

import Domain.*;
import java.sql.*;
import javafx.collections.*;

/* This class connects with the database using the links in the class DBConnection to retrieve courses, 
   add new courses to the database, update courses and delete courses */
public class CourseRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();
    private String driverUrl = DBConnection.getDriverUrl();
    final ObservableList<Course> allCourses = FXCollections.observableArrayList();

    /* This method creates a course repository and calls the method to retrieve all courses from the database */
    public CourseRepo(){
        getAllCoursesFromDB();
    }

    /* This method adds a course to the database and then adds the course to the list allCourses so that the connection does not have to be called again to update the list 
       It returns true if the course was succesfully created and false if not */
    public boolean create(Course course, CourseModule module){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("INSERT INTO Course VALUES (?,?,?,?);");
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getIntroductionText());
            pstmt.setString(3, course.getSubject());
            pstmt.setString(4, course.getLevelIndication().name());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                allCourses.add(course);
                if (DBConnection.courseModuleRepo.updateModuleUsed(course, module)) course.addModule(module);
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return false;
    }

    /* This method returns the list with all courses in de database */
    public ObservableList<Course> getAllCourses(){
        return this.allCourses;
    }

    /* This method retrieves all courses from the database and adds them to a list to be used more efficiently */
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
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    /* This method changes a course in the database, if it has been changed succesfully the method returns true, if not the method returns false. */
    public boolean update(Course course, String previousName){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("UPDATE Course SET CourseName=?, Subject=?, IntroductionText=?, LevelIndication=? WHERE CourseName=?;");
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getSubject());
            pstmt.setString(3, course.getIntroductionText());
            pstmt.setString(4, course.getLevelIndication().name());
            pstmt.setString(5, previousName);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0){
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return false;
    }
    
    /* This method deletes a course from the database. If it was deleted succesfully, the method returns true, if not the method returns false. */
    public boolean delete(Course course){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("DELETE FROM Course WHERE CourseName=?;");
            pstmt.setString(1, course.getCourseName());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                allCourses.remove(course);
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return false;
    }
    
}
