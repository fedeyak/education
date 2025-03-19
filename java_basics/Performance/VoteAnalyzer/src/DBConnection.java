import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "fedor";
    private static String dbPass = "testtest";

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id integer primary key auto_increment, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        insertQuery.delete(insertQuery.length()-1, insertQuery.length());
        String sql = "INSERT INTO voter_count(name, birthDate) " +
                "VALUES" + insertQuery.toString();
        DBConnection.getConnection().createStatement().execute(sql);
        insertQuery.delete(0, insertQuery.length());
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay.replace('.', '-');
        insertQuery.append("('" + name + "', '" + birthDay + "'),");
        if (insertQuery.length() > 10_000_000) {
            executeMultiInsert();
        }
    }
}
