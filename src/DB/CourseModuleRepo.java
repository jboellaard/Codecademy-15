package DB;

import Domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/* This class retrieves the modules from the database and keeps track of which modules have been used and which haven't. */
public class CourseModuleRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();
    private String driverUrl = DBConnection.getDriverUrl();
    final ObservableList<CourseModule> unusedModules = FXCollections.observableArrayList();

    public CourseModuleRepo(){
        getModulesFromDB();
    }

    /* This method retrieves all modules from the database and adds them to their respective courses.
       If a module is not connected to a course yet it is added to the list of unused modules */
    public void getModulesFromDB(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement getID = null;
        ResultSet rsID = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("SELECT * FROM Module;");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                getID = con.prepareStatement("SELECT * FROM ContentItem WHERE ContentItemID=?;");
                getID.setInt(1,rs.getInt("ContentItemID"));
                rsID = getID.executeQuery();
                rsID.next();
                CourseModule module = new CourseModule(rs.getInt("ContentItemID"), rsID.getString("PublicationDate"), Status.valueOf(rsID.getString("Status")), rs.getString("Title"),
                rs.getString("Description"), rs.getString("Version"), rs.getString("NameContactPerson"), rs.getString("EmailContactPerson"), rs.getInt("FollowNumber"));
                if (!(rs.getString("CourseName")==null)){
                    for (Course course : DBConnection.courseRepo.allCourses){
                        if (rs.getString("CourseName").equals(course.getCourseName())){
                            course.addModule(module);
                            break;
                        }
                    }   
                } else {
                    unusedModules.add(module);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (rsID != null) try { rs.close(); } catch(Exception e) {}
            if (getID != null) try { pstmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public ObservableList<CourseModule> getUnusedModules(){
        return this.unusedModules;
    }
    
    public ObservableList<ProgressModule> getModulesAndProgressFromDB(Course course){
        ObservableList<ProgressModule> progress = FXCollections.observableArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("SELECT * FROM Module LEFT JOIN ProgressModule ON ProgressModule.ContentItemID=Module.ContentItemID WHERE CourseName=?;");
            pstmt.setString(1, course.getCourseName());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProgressModule progressModule;
                if (rs.getInt("Progress")==0){
                    progressModule = new ProgressModule(rs.getString("Title"),rs.getString("Version"),rs.getString("Description"),rs.getInt("FollowNumber"),0);
                } else {
                    progressModule = new ProgressModule(rs.getString("Title"),rs.getString("Version"),rs.getString("Description"),rs.getInt("FollowNumber"),rs.getInt("Progress"));
                }
                progress.add(progressModule);
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
        return progress;
    }

    public boolean updateModuleUsed(Course course, CourseModule module){
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("UPDATE Module SET CourseName=? WHERE ContentItemID=?;");
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, module.getContentItemID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0){
                DBConnection.courseModuleRepo.unusedModules.remove(module);
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

    public int getAverageProgress(CourseModule module){
        int avgProgress = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("SELECT AVG(Progress) AS Average FROM ProgressModule WHERE ContentItemID=?;");
            pstmt.setInt(1, module.getContentItemID());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                avgProgress=rs.getInt("Average");
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
        return avgProgress;


    }

}
