package clara.iksan.manager;

import clara.iksan.model.AccountGroup;
import clara.iksan.model.BankStatement;
import clara.iksan.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.SQLiteConfig;

import java.sql.*;

/**
 * Created by Daeyoung Han on 2017. 4. 29..
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
        PrefManager prefManager = new PrefManager();
        this.dbFileName = prefManager.getDbFile();
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
            //System.out.println(query);
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
            //System.out.println(query);
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

            String query = "SELECT DATE(ab.entry_date) AS entry_date, ba.id_name, ca.cat_name, cl.grp_name, ab.price, ab.remark, ab.note\n" +
                    "FROM account_book ab\n" +
                    "  JOIN bank_account ba ON ab.bnk_id = ba.bnk_id\n" +
                    "  JOIN category ca ON ab.cat_no = ca.cat_no\n" +
                    "  JOIN account_group cl ON ab.grp_no = cl.grp_no\n" +
                    "WHERE ab.bnk_id != 3 AND ab.remark LIKE '%" + searchKeyword + "%'\n" +
                    "ORDER BY entry_date;";

            System.out.println(query);

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                BankStatement bankStatement = new BankStatement(rs.getString("entry_date"),
                        rs.getString("id_name"),
                        rs.getString("cat_name"),
                        rs.getString("grp_name"),
                        rs.getInt("price"),
                        rs.getString("remark"),
                        rs.getString("note"));

                data.add(bankStatement);
                System.out.println(bankStatement.getEntryDate() + ", " + bankStatement.getRemark());
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ObservableList<Category> getCategory() {
        if (this.isOpened == false)
            return null;

        ObservableList<Category> data = FXCollections.observableArrayList();

        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = null;

            String query = "SELECT cat_no, cat_name FROM category ORDER BY cat_no;";

            System.out.println(query);

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Category category = new Category(rs.getInt("cat_no"),
                        rs.getString("cat_name"));

                data.add(category);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ObservableList<AccountGroup> getAccountGroup() {
        if (this.isOpened == false)
            return null;

        ObservableList<AccountGroup> data = FXCollections.observableArrayList();

        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = null;

            String query = "SELECT grp.grp_no, grp.grp_name, grp.short, grp.cat_no, cat.cat_name, grp.easy, grp.cl_use\n" +
                    "FROM account_group grp\n" +
                    "JOIN category cat ON grp.cat_no = cat.cat_no\n" +
                    "ORDER BY grp.grp_no;";

            System.out.println(query);

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                AccountGroup accountGroup = new AccountGroup(rs.getInt("grp_no"),
                        rs.getString("grp_name"),
                        rs.getString("short"),
                        rs.getInt("cat_no"),
                        rs.getString("cat_name"),
                        rs.getInt("easy"),
                        rs.getInt("cl_use"));
                data.add(accountGroup);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
