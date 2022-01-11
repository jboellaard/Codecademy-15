package DB;

import java.sql.*;
// import java.util.*;

import Domain.*;

public class AddressRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();

    public AddressRepo(){

    }

    public int findAddressID(Address address){
        int addressID = 0;
        boolean found = false;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Address");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String zipCode = rs.getString("Zipcode");
                int houseNo = rs.getInt("HouseNumber");
                String suffix = rs.getString("Suffix");
                if (address.getZipCode().equals(zipCode) && address.getHouseNumber()==houseNo && address.getSuffix().equals(suffix)){
                    addressID = rs.getInt("AddressID");
                    found = true;
                }
            }
            if (!found){
                //create address and get newly made addressid
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return addressID;
    }

    public void create(){

    }

    
}
