package Domain;

public class Address {

    private String address;
    private int addressID;
    private String zipCode;
    private int houseNumber;
    private String suffix;
    private String city;
    private String country;

    public Address(String address){
        this.addressID = 1;
    }

    public int getAddressID(){
        return this.addressID;
    }

    public static boolean isValidAddress(){
        return true;
    }
    
    
}
