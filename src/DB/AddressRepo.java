package DB;

import Domain.*;
import java.sql.*;
import javafx.collections.*;

/* This class connects with the database using the links in the class DBConnection to retrieve addresses, 
   compare a new address to existing ones and create a new address if it has not been used before. */
public class AddressRepo {
    private String connectionUrl = DBConnection.getConnectionUrl();
    private String driverUrl = DBConnection.getDriverUrl();
    final ObservableList<Address> allAddresses = FXCollections.observableArrayList();

    /* This method creates and address repository and calls the method to retrieve addresses from the database */
    public AddressRepo(){
        getAllAddressesFromDB();
    }

    /* This method checks whether the list of addresses contains given address based on zipcode, housenumber and suffix. 
       if an address does not exist yet, it calls the method create */
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
    }

    /* This method adds an address to the database and then adds the address to the list allAddresses so that the connection does not have to be called again to update the list. 
       It returns true if the address was succesfully created and false if not */
    public boolean create(Address address){
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement getID = null;
        ResultSet rs = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("INSERT INTO Address VALUES (?,?,?,?,?,?);");
            pstmt.setString(1, address.getZipCode());
            pstmt.setInt(2, address.getHouseNumber());
            pstmt.setString(3, address.getSuffix());
            pstmt.setString(4, address.getStreet());
            pstmt.setString(5, address.getCity());
            pstmt.setString(6, address.getCountry());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0){
                getID = con.prepareStatement("SELECT AddressID FROM Address WHERE ZipCode=? AND HouseNumber=? AND Suffix is NULL;");
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

                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
            if (getID != null) try { getID.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return false;
    }

    /* This method returns the list with all addresses in de database */
    public ObservableList<Address> getAllAddresses(){
        return this.allAddresses;
    }

    /* This method retrieves all addresses from the database and adds them to a list to be used more efficiently */
    public void getAllAddressesFromDB(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverUrl);
            con = DriverManager.getConnection(connectionUrl);
            pstmt = con.prepareStatement("SELECT * FROM Address");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Address addressInDB = new Address(rs.getInt("AddressID"),rs.getString("ZipCode"),rs.getInt("HouseNumber"),rs.getString("Suffix"),rs.getString("Street"),rs.getString("City"),rs.getString("Country"));
                allAddresses.add(addressInDB);
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

}
