import java.sql.*;

public class UsersJdbcDemo {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/eshop?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        registerDriver();

        executeQuery();

        executeUpdate();

//        preparedStatement();
    }

    private static void preparedStatement() {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)){

            String sql = "Select * from user where id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("login: " + name);
                System.out.println("email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeQuery() {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {

                System.out.println("Executing statement...");

                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {

                    System.out.println("\nUsers:");


                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeUpdate() {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {

            System.out.println("Executing statement...");

            int i = statement.executeUpdate("update user set email = 'a1@gmail.com' where id = 1");

            System.out.println("Количсество измененных записей: " + i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
