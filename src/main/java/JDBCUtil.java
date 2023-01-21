import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

    public static final String  DB_URL = "jdbc:mysql://localhost:3306/testttttt";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    private static Connection connection;
    private static Statement statement;

    private JDBCUtil() {

    }

    static {

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Statement getStatement() {
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return statement;
    }

}