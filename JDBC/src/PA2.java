/**
* Xinyi He
* A13561164
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PA2 {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:pa22.db");
            System.out.println("Opened database successfully.");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE if EXISTS Connected;");
            stmt.executeUpdate("CREATE TABLE Connected (Airline, Origin, Destination CHAR(32), Stops INT)");

            // T
            stmt.executeUpdate("DROP TABLE if EXISTS T;" +
                    "CREATE TABLE T AS SELECT DISTINCT *, 0 as Stops FROM Flight;");

            // Delta
            stmt.executeUpdate("DROP TABLE if EXISTS Delta;" +
                    "CREATE TABLE Delta AS SELECT * FROM T;");

            // T_old
            stmt.executeUpdate("DROP TABLE if EXISTS T_old;" +
                    "CREATE TABLE T_old AS SELECT * FROM T;");

            ResultSet delta = stmt.executeQuery("SELECT * FROM Delta");
            int i = 0;

            while (delta.next()) {
                i++;

                //T old
                stmt.executeUpdate("DELETE FROM T_old; " +
                        "INSERT INTO T_old SELECT * FROM T;");

                stmt.executeUpdate("INSERT INTO T SELECT DISTINCT " +
                        "x.Airline, x.Origin, y.Destination, " + i + " AS Stops " +
                        "FROM Flight x, Delta y WHERE x.Airline = y.Airline AND x.Destination = y.Origin " +
                        "AND x.Origin <> y.Destination AND " +
                        "NOT EXISTS (SELECT * FROM T WHERE Airline = x.Airline AND Origin = x.Origin AND Destination " +
                        "= y.Destination);");

                stmt.executeUpdate("DELETE FROM Delta; " +
                        "INSERT INTO Delta SELECT * FROM T EXCEPT SELECT * FROM T_old;");
                delta = stmt.executeQuery("SELECT * FROM Delta");
            }

            stmt.executeUpdate("INSERT INTO Connected SELECT DISTINCT * FROM T");
            stmt.executeUpdate("DROP TABLE IF EXISTS T;" +
                    "DROP TABLE IF EXISTS Delta;" +
                    "DROP TABLE IF EXISTS T_old;");
            // Close the Statement objects.
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException("There was a runtime problem!", e);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(
                        "Cannot close the connection!", e);
            }
        }
    }

}
