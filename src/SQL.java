import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SQL {
    static String url ;
    static String username ;
    static String password ;
    static Connection connection ;
    public static void connect() throws SQLException
    {
        url = "jdbc:mysql://localhost:3306/oop-project-snapfood";
        username = "root";
        password = "W@2915djkq#";
        connection = DriverManager.getConnection(url, username, password);
        if (connection != null)
            System.out.println("Connected to the database!");
    }
}
