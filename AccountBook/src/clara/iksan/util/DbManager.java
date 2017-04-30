package clara.iksan.util;

import clara.iksan.model.BankStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.SQLiteConfig;

import java.sql.*;

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

    public ResultSet executeQuery(String query) {
        if (this.isOpened == false)
            return null;

        ResultSet rs = null;

        try {
            Statement stmt = this.connection.createStatement();
            System.out.println(query);
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ObservableList<BankStatement> searchBankStatement(String searchKeyword) {
        if (this.isOpened == false)
            return null;

        ObservableList<BankStatement> data = FXCollections.observableArrayList();

        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = null;

            rs = stmt.executeQuery("" +
                    "SELECT DATE(ab.entry_date + 2378858) AS entry_date, ba.id_name, ca.cat_name, cl.cla_name, ab.price, ab.remark, ab.note\n" +
                    "FROM account_book ab\n" +
                    "  JOIN bank_account ba ON ab.bnk_id = ba.bnk_id\n" +
                    "  JOIN category ca ON ab.cat_no = ca.cat_no\n" +
                    "  JOIN class cl ON ab.cls_no = cl.cls_no\n" +
                    "WHERE ab.bnk_id != 3 AND ab.remark LIKE '%" + searchKeyword + "%'\n" +
                    "ORDER BY entry_date;");

            while (rs.next()) {
                BankStatement bankStatement = new BankStatement(rs.getString("entry_date"),
                        rs.getString("id_name"),
                        rs.getString("cat_name"),
                        rs.getString("cla_name"),
                        rs.getInt("price"),
                        rs.getString("remark"),
                        rs.getString("note"));

                data.add(bankStatement);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

}
