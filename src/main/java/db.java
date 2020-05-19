import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class db {
    // Url for DB connection
    private String url = "jdbc:mysql://79.98.129.3/bagdiken_se318?useLegacyDatetimeCode=false&serverTimezone=UTC";

    public Connection connect() throws SQLException {
        // Connect and retırn the connection
        return DriverManager.getConnection(url,"bagdiken_se318","XEZ#)^My3BHm");
    }
}
