package Domain;

public class Date {

    private String date;

    public Date(String string){
        this.date = string;
    }

    public boolean isValidDate(){
        return true;
    }

    @Override
    public String toString(){
        return this.date;
    }
    
}
