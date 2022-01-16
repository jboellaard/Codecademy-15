package Domain;
/*Ads data to the ProgressModule class */ 
public class ProgressModule {
    private String title;
    private String version;
    private String description;
    private int followNumber;
    private int progress;

    public ProgressModule(String title, String version, String description, int followNumber, int progress){
        this.title = title;
        this.version = version;
        this.description = description;
        this.followNumber = followNumber;
        this.progress = progress;
    }
    /*Returns requested data */ 
    public String getTitle(){
        return this.title;
    }

    public String getVersion(){
        return this.version;
    }

    public String getDescription(){
        return this.description;
    }

    public int getFollowNumber(){
        return this.followNumber;
    }

    public int getProgress(){
        return this.progress;
    }

    
    
    
}
