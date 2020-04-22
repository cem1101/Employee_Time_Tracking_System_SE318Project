import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String url = "jdbc:mysql://localhost/se318?useLegacyDatetimeCode=false&serverTimezone=UTC";

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url,"root","");
    }


}
