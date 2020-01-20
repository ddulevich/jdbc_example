import java.sql.*;

public class UsersJdbcDemo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/production_eshop?serverTimezone=UTC";

    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM user";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("\nUsers:");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("login");
            String specialty = resultSet.getString("email");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("login: " + name);
            System.out.println("email: " + specialty);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

}
