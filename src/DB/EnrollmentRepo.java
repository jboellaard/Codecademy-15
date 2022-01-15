package DB;

import java.sql.*;

import Domain.*;
import GUI.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EnrollmentRepo {
    private static String connectionUrl = DBConnection.getConnectionUrl();
    private static String driverUrl = DBConnection.getDriverUrl();

    public static ObservableList<Enrollment> getEnrollmentsFromDB(Student student){
        final ObservableList<Enrollment> allStudentEnrollments = FXCollections.observableArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("SELECT * FROM Enrollment WHERE StudentEmail=?;");
            pstmt.setString(1, student.getEmailAddress());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                for (Course course : GUI.courseRepo.allCourses){
                    if (rs.getString("CourseName").equals(course.getCourseName())){
                        allStudentEnrollments.add(new Enrollment(student, course, rs.getString("SignUpDate")));
                        break;
                    }
                }
            }
            return allStudentEnrollments;
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
        return null;
    }
    
}
