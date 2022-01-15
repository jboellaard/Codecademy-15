package DB;

// import java.sql.*;

public class DBConnection {
    // private String connectionUrl = "jdbc:sqlserver://localhost;databaseName=CodecademyData;integratedSecurity=true;";

    public DBConnection(){
    }


    public static String getConnectionUrl(){
        return "jdbc:sqlserver://localhost;databaseName=CodecademyData;integratedSecurity=true;";
    }

    public static String getDriverUrl(){
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }
    
}
