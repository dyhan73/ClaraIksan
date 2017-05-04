package tests.sqlite;

/**
 * Created by Daeyoung Han on 2017. 4. 28..
 */
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

public class DataReader {
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

    public DataReader(String databaseFileName) {
        this.dbFileName = databaseFileName;
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

    public boolean readMeta(String loginid, String loginpw) throws SQLException {
        if (this.isOpened == false)
            return false;
        boolean result = false;
        String query = "SELECT * FROM userstbl WHERE id=? AND pw=?;";
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.setString(1, loginid);
        prep.setString(2, loginpw);
        ResultSet row = prep.executeQuery();
        if (row.next()) {
            row.getString(1);
            result = true;
        }
        row.close();
        prep.close();
        return result;
    }
    public boolean readMeta2(String loginid, String loginpw) throws SQLException {
        if (this.isOpened == false)
            return false;
        if (readMeta(loginid, loginpw))
            return false;
        String query = "INSERT INTO userstbl (id, pw, score) VALUES (?, ?, 0);";
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.setString(1, loginid);
        prep.setString(2, loginpw);
        prep.executeUpdate();
        return true;
    }
    public void readMeta3(String username) throws SQLException { //Add score
        if (this.isOpened == false)
            return;
        String query = "UPDATE userstbl SET score = score + 10 WHERE id = ?";
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.setString(1, username);
        prep.executeUpdate();
        prep.close();
    }

    public List<String> readMeta4() throws SQLException { //Get score
        if (this.isOpened == false)
            return null;
        List<String> ls = new ArrayList<>();
        String query = "SELECT id FROM userstbl ORDER BY score DESC";
        PreparedStatement prep = this.connection.prepareStatement(query);
        ResultSet row = prep.executeQuery();
        while (row.next()){
            System.out.println("ranking:"+row.getString("id"));
            ls.add(row.getString("id"));
        }
        row.close();
        prep.close();
        return ls;
    }
}