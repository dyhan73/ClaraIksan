package clara.iksan.util;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dyhan on 2017. 4. 29..
 */
public class DbManager {
    private Connection connection;
    private String dbFileName;
    private boolean isOpened = false;
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DbManager() {
        this.dbFileName = "/Users/1001235/MyDev/ws_github/ClaraIksan/AccountBook/data/database/clara_iksan.db";
    }

    public boolean open() {
        try {
            SQLiteConfig config = new SQLiteConfig();
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbFileName, config.toProperties());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        isOpened = true;
        return true;
    }

    public boolean close() {
        if (this.isOpened == false)
            return true;
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean executeUpdate(String query) {
        if (this.isOpened == false)
            return false;

        try {
            Statement stmt = this.connection.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
