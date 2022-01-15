package Domain;

public class Module extends ContentItem {
    private int version;
    private String nameContactPerson;
    private String emailAddressContactPerson;
    private int followNumber;
    
    public Module(int contentItemID, String publicationDate, String title, String description, Status status, int version, String nameContactPerson, String emailAddressContactPerson, int followNumber){
        super(contentItemID, publicationDate, title, description, status);
        this.version = version;
        this.nameContactPerson = nameContactPerson;
        this.emailAddressContactPerson = emailAddressContactPerson;
        this.followNumber = followNumber;
    }

}
