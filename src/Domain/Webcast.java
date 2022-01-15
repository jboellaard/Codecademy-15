package Domain;

public class Webcast extends ContentItem {
    private String nameSpeaker;
    private String nameOrganisation;
    private String url;

    public Webcast(int contentItemID, String publicationDate, String title, String description, Status status, String nameSpeaker, String nameOrganisation, String url){
        super(contentItemID, publicationDate, title, description, status);
        this.nameSpeaker = nameSpeaker;
        this.nameOrganisation = nameOrganisation;
        this.url = url;
    }

    
}
