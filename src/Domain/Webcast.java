package Domain;

public class Webcast extends ContentItem {
    private String nameSpeaker;
    private String nameOrganisation;
    private String url;
    private String title;
    private String description;

    public Webcast(int contentItemID, String publicationDate, String title, String description, Status status, String nameSpeaker, String nameOrganisation, String url){
        super(contentItemID, publicationDate, status);
        this.title=title;
        this.description=description;
        this.nameSpeaker = nameSpeaker;
        this.nameOrganisation = nameOrganisation;
        this.url = url;
    }

    public String getPublicationDate(){
        return this.publicationDate;
    }

    public Status getStatus(){
        return this.status;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public String getUrl(){
        return this.url;
    }

    public String getNameSpeaker(){
        return this.nameSpeaker;
    }

    public String getNameOrganisation(){
        return this.nameOrganisation;
    }


    
}
