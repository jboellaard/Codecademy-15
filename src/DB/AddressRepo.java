package DB;

import java.sql.*;
// import java.util.*;

import Domain.*;
import javafx.collections.*;

public class AddressRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();
    final ObservableList<Address> allAddresses = FXCollections.observableArrayList();

    public AddressRepo(){
        getAllAddressesFromDB();
    }

    public void findAddressID(Address address){
        for (Address addressInDB : allAddresses){
            if (address.getSuffix()!=null){
                if (address.getZipCode().equals(addressInDB.getZipCode()) && address.getHouseNumber()==addressInDB.getHouseNumber() && address.getSuffix().equals(addressInDB.getSuffix())){
                    address.setAddressID(addressInDB.getAddressID());
                    address.setStreet(addressInDB.getStreet());
                    address.setCity(addressInDB.getCity());
                    address.setCountry(addressInDB.getCountry());
                    return;
                }
            } else {
                if (address.getZipCode().equals(addressInDB.getZipCode()) && address.getHouseNumber()==addressInDB.getHouseNumber() && addressInDB.getSuffix()==null){
                    address.setAddressID(addressInDB.getAddressID());
                    address.setStreet(addressInDB.getStreet());
                    address.setCity(addressInDB.getCity());
                    address.setCountry(addressInDB.getCountry());
                    return;
                }
            }
        }
        this.create(address);
        // Connection con = null;
        // Statement stmt = null;
        // ResultSet rs = null;

        // try {
        //     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //     con = DriverManager.getConnection(connectionUrl);
        //     PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Address");
        //     rs = pstmt.executeQuery();

        //     while (rs.next()) {
        //         String zipCode = rs.getString("Zipcode");
        //         int houseNo = rs.getInt("HouseNumber");
        //         String suffix = rs.getString("Suffix");
        //         if (address.getZipCode().equals(zipCode) && address.getHouseNumber()==houseNo && address.getSuffix().equals(suffix)){
        //             addressID = rs.getInt("AddressID");
        //             return addressID;
        //         }
        //     }
        // }
        // catch (Exception e) {
        //     e.printStackTrace();
        // }
        // finally {
        //     if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        //     if (con != null) try { con.close(); } catch(Exception e) {}
        // }
        // return addressID;
    }

    public void create(Address address){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Address VALUES (?,?,?,?,?,?);");
            pstmt.setString(1, address.getZipCode());
            pstmt.setInt(2, address.getHouseNumber());
            pstmt.setString(3, address.getSuffix());
            pstmt.setString(4, address.getStreet());
            pstmt.setString(5, address.getCity());
            pstmt.setString(6, address.getCountry());

            pstmt.executeUpdate();

            PreparedStatement getID = con.prepareStatement("SELECT AddressID FROM Address WHERE ZipCode=? AND HouseNumber=? AND Suffix is NULL;");
            if (address.getSuffix()!=null){
                getID = con.prepareStatement("SELECT AddressID FROM Address WHERE ZipCode=? AND HouseNumber=? AND Suffix=?;");
                getID.setString(3, address.getSuffix());
            }
            getID.setString(1, address.getZipCode());
            getID.setInt(2, address.getHouseNumber());

            rs = getID.executeQuery();
            while (rs.next()){
                address.setAddressID(rs.getInt("AddressID"));
            }
            
            allAddresses.add(address);

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    public ObservableList<Address> getAllAddresses(){
        return this.allAddresses;
    }

    public void getAllAddressesFromDB(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Address");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //
                Address addressInDB = new Address(rs.getInt("AddressID"),rs.getString("ZipCode"),rs.getInt("HouseNumber"),rs.getString("Suffix"),rs.getString("Street"),rs.getString("City"),rs.getString("Country"));
                allAddresses.add(addressInDB);
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    
}