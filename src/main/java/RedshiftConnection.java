import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class RedshiftConnection {
    private static final String dbURL = "jdbc:redshift://host:port/db";
    private static final String MasterUsername = "user";
    private static final String MasterUserPassword = "password";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //Dynamically load driver at runtime.
            Class.forName("com.amazon.redshift.jdbc.Driver");

            //Open a connection and define properties.
            System.out.println("Connecting to database...");
            Properties props = new Properties();

            //Uncomment the following line if using a keystore.
            //props.setProperty("ssl", "true");
            props.setProperty("user", MasterUsername);
            props.setProperty("password", MasterUserPassword);
            conn = DriverManager.getConnection(dbURL, props);

            //Try a simple query.
            System.out.println("Listing system tables...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT table_schema, table_name " +
                    "FROM information_schema.tables " +
                    "WHERE table_schema = 'science';";
            ResultSet rs = stmt.executeQuery(sql);

            //Get the data from the result set.
            while (rs.next()) {
                //Retrieve two columns.
                String schema = rs.getString("table_schema");
                String name = rs.getString("table_name");

                //Display values.
                System.out.print("Schema: " + schema + ", Table: " + name + "\n");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            //For convenience, handle all errors here.
            ex.printStackTrace();
        } finally {
            //Finally block to close resources.
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ex) {} // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Finished connectivity test.");
    }
}
