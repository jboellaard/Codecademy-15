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
                        allStudentEnrollments.add(new Enrollment(rs.getInt("EnrollmentID"),student, course, rs.getString("SignUpDate")));
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

    /* This method returns the percentage of enrollments that has a certificate by gender */
    public static int certificatesPerGender(Gender gender){
        int percentage = 0;
        Connection con = null;
        PreparedStatement byGender = null;
        ResultSet rsByGender = null;
        PreparedStatement total = null;
        ResultSet rsTotal = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            byGender = con.prepareStatement("SELECT COUNT(*) AS Certificates FROM Enrollment LEFT JOIN Student ON Enrollment.StudentEmail=Student.EmailAddress WHERE Student.Gender=? AND Enrollment.CertificateID IS NOT NULL");
            byGender.setString(1,String.valueOf(gender));
            rsByGender = byGender.executeQuery();

            while (rsByGender.next()) {
                total = con.prepareStatement("SELECT COUNT(*) AS Total FROM Enrollment LEFT JOIN Student ON Enrollment.StudentEmail=Student.EmailAddress WHERE Student.Gender=? ");
                total.setString(1,String.valueOf(gender));
                rsTotal = total.executeQuery();

                while (rsTotal.next()){
                    percentage = (int) (100*(1.0 * rsByGender.getInt("Certificates")) / (1.0 * rsTotal.getInt("Total")));
                    return percentage;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rsByGender != null) try { rsByGender.close(); } catch(Exception e) {}
            if (byGender != null) try { byGender.close(); } catch(Exception e) {}
            if (rsTotal != null) try { rsTotal.close(); } catch(Exception e) {}
            if (total != null) try { total.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return percentage;
    }
    
}
