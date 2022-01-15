package DB;

import Domain.*;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* This class retrieves and creates enrollments for selected students */
public class EnrollmentRepo {
    private static String connectionUrl = DBConnection.getConnectionUrl();
    private static String driverUrl = DBConnection.getDriverUrl();

    /* This method retrieves all enrollments from the database connected to the given student */
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
                for (Course course : DBConnection.courseRepo.allCourses){
                    if (rs.getString("CourseName").equals(course.getCourseName())){
                        allStudentEnrollments.add(new Enrollment(student, course, rs.getString("SignUpDate")));
                        break;
                    }
                }
            }
            return allStudentEnrollments;
        }
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

    /* This method adds an enrollment to the database. If it was succesfully added, the method returns true, if not the method returns false. */
    public static boolean create(Student student, Course course, String date){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("INSERT INTO Enrollment VALUES (?,?,?);");
            pstmt.setString(1, student.getEmailAddress());
            pstmt.setString(2, course.getCourseName());
            pstmt.setString(3, date);

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
    
}
