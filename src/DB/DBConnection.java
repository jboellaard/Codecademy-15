package DB;

/* This class saves the links needed for the database connection */
public class DBConnection {
    public static AddressRepo addressRepo = new AddressRepo();
    public static StudentRepo studentRepo = new StudentRepo();
    public static CourseRepo courseRepo = new CourseRepo();
    public static CourseModuleRepo courseModuleRepo = new CourseModuleRepo();

    public DBConnection(){
        
    }

    /* This method returns the connection url */
    public static String getConnectionUrl(){
        return "jdbc:sqlserver://localhost;databaseName=CodecademyData;integratedSecurity=true;";
    }

    /* This method returns the driver url */
    public static String getDriverUrl(){
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }
    
}
