package Domain;

public class nameOfStaffMember {

    private String nameOfStaffMember;

    public nameOfStaffMember(String nameOfStaffMember){
        this.nameOfStaffMember=nameOfStaffMember;
    }

    public boolean isValidMember(){
        return true;
    }

    @Override
    public String toString(){
        return this.nameOfStaffMember;
    }   
}
