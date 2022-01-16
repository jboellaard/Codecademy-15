package DB;

import Domain.*;
import javafx.collections.*;

import java.sql.*;

public class WebcastRepo {

    public static ObservableList<ProgressWebcast> getWebcastsViewed(Student student){
        final ObservableList<ProgressWebcast> allStudentWebcasts = FXCollections.observableArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DBConnection.getDriverUrl());
            con = DriverManager.getConnection(DBConnection.getConnectionUrl());
            pstmt = con.prepareStatement("SELECT * FROM ProgressWebcast LEFT JOIN Webcast ON ProgressWebcast.ContentItemID=Webcast.ContentItemID LEFT JOIN ContentItem ON ContentItem.ContentItemID=Webcast.ContentItemID WHERE StudentEmail=?; ");
            pstmt.setString(1, student.getEmailAddress());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Webcast webcast = new Webcast(rs.getInt("ContentItemID"),rs.getString("PublicationDate"),rs.getString("Title"),rs.getString("Description"),Status.valueOf(rs.getString("Status")), rs.getString("NameSpeaker"),rs.getString("NameOrganisation"),rs.getString("Url"));
                allStudentWebcasts.add(new ProgressWebcast(webcast, rs.getInt("progress")));
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
        return allStudentWebcasts;
    }

    public static String[][] getTopThreeMostViewedWebcasts(){
        String[][] top3Webcasts = new String[3][2];
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DBConnection.getDriverUrl());
            con = DriverManager.getConnection(DBConnection.getConnectionUrl());
            pstmt = con.prepareStatement(
            "SELECT TOP 3 Title, Count(*) AS Views FROM ProgressWebcast LEFT JOIN Webcast ON Webcast.ContentItemID=ProgressWebcast.ContentItemID GROUP BY Title ORDER BY Views DESC;");
            rs = pstmt.executeQuery();

            int i = 0;
            while (rs.next()) {
                if (i<3) {
                    top3Webcasts[i][0] = rs.getString("Title");
                    top3Webcasts[i][1] = String.valueOf(rs.getInt("Views"));
                }
                i++;
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
        return top3Webcasts;
    }
    
}
