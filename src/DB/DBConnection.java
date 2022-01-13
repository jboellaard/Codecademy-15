package DB;

// import java.sql.*;

public class DBConnection {
    // private String connectionUrl = "jdbc:sqlserver://localhost;databaseName=StudentTemp;integratedSecurity=true;";

    public DBConnection(){
    }


    public static String getConnectionUrl(){
        return "jdbc:sqlserver://localhost;databaseName=StudentTemp;integratedSecurity=true;";
    }

    public static String getDriverUrl(){
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }
    
}
