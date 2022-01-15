package DB;

import Domain.*;
import GUI.*;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* This class connects with the database using the links in the class DBConnection to retrieve students, 
   add new student to the database, update students and delete students */
public class StudentRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();
    private String driverUrl = DBConnection.getDriverUrl();
    final ObservableList<Student> allStudents = FXCollections.observableArrayList();

    /* This method creates a student repository and calls the method to retrieve all students from the database */
    public StudentRepo(){
        getAllStudentsFromDB();
    }

    /* This method adds a student to the database and then adds the student to the list allStudent so that the connection does not have to be called again to update the list. 
       It returns true if the student was succesfully created and false if not */
    public boolean create(Student student){
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

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0){
                allStudents.add(student);
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

    /* This method returns the list with all students in de database */
    public ObservableList<Student> getAllStudents(){
        return this.allStudents;
    }

    /* This method retrieves all students from the database and adds them to a list to be used more efficiently */
    public void getAllStudentsFromDB(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("SELECT * FROM Student");
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
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    /* This method changes a student in the database, if it has been changed succesfully the method returns true, if not the method returns false. */
    public boolean update(Student student, Student previousValues){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("UPDATE Student SET EmailAddress=?, Name=?, DateOfBirth=?, Gender=?, AddressID=? WHERE EmailAddress=?;");
            pstmt.setString(1, student.getEmailAddress());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDateOfBirth());
            pstmt.setString(4, student.getGender().name());
            pstmt.setInt(5, student.getAddressID());
            pstmt.setString(6, previousValues.getEmailAddress());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0){
                for (int i =0; i<allStudents.size(); i++){
                    if (allStudents.get(i).equals(previousValues)) allStudents.set(i,student);
                }
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
    
    /* This method deletes a student from the database. If it was deleted succesfully, the method returns true, if not the method returns false. */
    public boolean delete(Student student){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("DELETE FROM Student WHERE EmailAddress=?;");
            pstmt.setString(1, student.getEmailAddress());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                allStudents.remove(student);
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
