package Domain;

import DB.AddressRepo;

public class Address {

    private int addressID;
    private String zipCode;
    private int houseNo;
    private String suffix;
    private String street;
    private String city;
    private String country;

    public Address(String zipCode, int houseNo, String suffix, String street, String city, String country){
        //create new address in database?
        //cross check with database?
        this.zipCode = zipCode;
        this.houseNo = houseNo;
        this.suffix = suffix;
        this.street = street;
        this.city = city;
        this.country = country;
        AddressRepo repo = new AddressRepo();
        this.addressID = repo.findAddressID(this);
    }

    public int getAddressID(){
        return this.addressID;
    }

    public String getZipCode(){
        return this.zipCode;
    }

    public int getHouseNumber(){
        return this.houseNo;
    }

    public String getSuffix(){
        return this.suffix;
    }

    public String getStreet(){
        return this.street;
    }

    public String getCity(){
        return this.city;
    }

    public String getCountry(){
        return this.country;
    }

    public static String formatZipCode(String zipCode){
        if (zipCode == null){
            throw new NullPointerException("The input cannot be null");
        } else if (zipCode.length()<6) {
            throw new IllegalArgumentException("The input is too short");
        } else {
            if (zipCode.trim().matches("^[1-9][0-9]{3} *(?!sa|sd|ss|SA|SD|SS)[A-Za-z]{2}$")){
                return zipCode.trim().substring(0, 4) + " " + zipCode.trim().substring(4).trim().toUpperCase();
            } else {
                throw new IllegalArgumentException("The input is not a valid postalCode");
            }
        }
    }
    
    
}
