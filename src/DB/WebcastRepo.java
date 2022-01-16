package DB;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class WebcastRepo {

    public static Map<String, Integer> getTopThreeMostViewedWebcasts(){
        Map<String,Integer> top3Webcasts = new HashMap<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DBConnection.getDriverUrl());
            con = DriverManager.getConnection(DBConnection.getConnectionUrl());
            pstmt = con.prepareStatement(
            "SELECT Title, Count(*) AS Views FROM ProgressWebcast LEFT JOIN Webcast ON Webcast.ContentItemID=ProgressWebcast.ContentItemID GROUP BY Title;");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                top3Webcasts.put(rs.getString("Title"),rs.getInt("Views"));
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
