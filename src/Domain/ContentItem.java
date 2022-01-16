package Domain;

/*Ads data to contentitem class */
public abstract class ContentItem {
    protected int contentItemID;
    protected String publicationDate;
    protected Status status; 

    public ContentItem(int contentItemID, String publicationDate, Status status){
        this.contentItemID = contentItemID;
        this.publicationDate = publicationDate;
        this.status = status;
    }
    /*Returns requested data*/
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

    public Status getStatus(){
        return this.status;
    }
    
    public void setStatus(Status status){
        this.status = status;
    }

    
}
