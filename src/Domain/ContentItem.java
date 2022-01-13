package Domain;

public abstract class ContentItem {
    private int contentItemID;
    private String publicationDate;
    private String title;
    private String description;
    private Status status; 

    public int getContentItemID(){
        return this.contentItemID;
    }

    public void setContentItemID(int contentItemID){
        this.contentItemID = contentItemID;
    }

    public String getPublicationDate(){
        return this.publicationDate;
    }

    public void setPublicationDate(String publicationDate){
        this.publicationDate = publicationDate;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return this.description;
    } 

    public void setDescription(String description){
        this.description = description;
    }

    public Status getStatus(){
        return this.status;
    }
    
    public void setStatus(Status status){
        this.status = status;
    }

    
}
