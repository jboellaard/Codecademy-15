package Domain;

public class CourseModule extends ContentItem {
    private String title;
    private String description;
    private String version;
    private String nameContactPerson;
    private String emailAddressContactPerson;
    private int followNumber;
    
    public CourseModule(int contentItemID, String publicationDate, Status status, String title, String description, String version, String nameContactPerson, String emailAddressContactPerson, int followNumber){
        super(contentItemID, publicationDate, status);
        this.title = title;
        this.description = description;
        this.version = version;
        this.nameContactPerson = nameContactPerson;
        this.emailAddressContactPerson = emailAddressContactPerson;
        this.followNumber = followNumber;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public String getVersion(){
        return this.version;
    }

    public int getFollowNumber(){
        return this.followNumber;
    }

}
