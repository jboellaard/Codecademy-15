package DB;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class WebcastRepo {

    public static String[][] getTopThreeMostViewedWebcasts(){
        String[][] top3Webcasts = new String[3][2];
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DBConnection.getDriverUrl());
            con = DriverManager.getConnection(DBConnection.getConnectionUrl());
            pstmt = con.prepareStatement(
            "SELECT Title, Count(*) AS Views FROM ProgressWebcast LEFT JOIN Webcast ON Webcast.ContentItemID=ProgressWebcast.ContentItemID GROUP BY Title;");
            rs = pstmt.executeQuery();

            int i = 0;
            while (rs.next()) {
                if (i<3) {
                    top3Webcasts[i][0] = rs.getString("Title");
                    top3Webcasts[i][1] = String.valueOf(rs.getInt("Views"));
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
        return top3Webcasts;
    }
    
}
