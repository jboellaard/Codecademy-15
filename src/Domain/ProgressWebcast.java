package Domain;

public class ProgressWebcast {
    private Webcast webcast;
    private int progress;

    public ProgressWebcast(Webcast webcast, int progress){
        this.webcast=webcast;
        this.progress=progress;
    }

    public String getTitle(){
        return this.webcast.getTitle();
    }

    public String getDescription(){
        return this.webcast.getDescription();
    }

    public String getUrl(){
        return this.webcast.getUrl();
    }

    public String getNameSpeaker(){
        return this.webcast.getNameSpeaker();
    }

    public String getNameOrganisation(){
        return this.webcast.getNameOrganisation();
    }

    public int getProgress(){
        return this.progress;
    }


    
    
}
