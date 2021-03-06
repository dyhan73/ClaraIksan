package clara.iksan.convert;

import clara.iksan.manager.DbManager;

/**
 * Created by Daeyoung Han on 2017. 4. 29..
 */
public class CreateTables {

    DbManager dbMgr = null;

    public CreateTables() {
        dbMgr = new DbManager();
    }

    public boolean createBankAccount() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS bank_account;");
        String query = "CREATE TABLE bank_account\n" +
                "            (\n" +
                "    `bnk_id`      INTEGER         PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `balance`     INTEGER         NOT NULL,\n" +
                "    `last_date`   DATE            NOT NULL,\n" +
                "    `id_name`     VARCHAR(45)     NOT NULL,\n" +
                "    `act_no`      INTEGER         NULL,\n" +
                "    `open_date`   DATE            NULL,\n" +
                "    `what_id`     INTEGER         NULL,\n" +
                "    `bank_type`   VARCHAR(45)     NULL,\n" +
                "    `addr`        VARCHAR(255)    NULL,\n" +
                "    `tel`         VARCHAR(45)     NULL,\n" +
                "    `cat_name`    VARCHAR(45)     NULL,\n" +
                "    `sort`        VARCHAR(45)     NULL,\n" +
                "    `kind`        VARCHAR(45)     NULL,\n" +
                "    `damdang`     VARCHAR(255)    NULL,\n" +
                "    `prank`       INTEGER         NULL,\n" +
                "    `pay_method`  INTEGER         NULL,\n" +
                "    `pay_id_no`   INTEGER         NULL,\n" +
                "    `tax_method`  INTEGER         NULL,\n" +
                "    `norpt`       INTEGER         NULL,\n" +
                "    `add1`        VARCHAR(255)    NULL,\n" +
                "    `add2`        VARCHAR(255)    NULL,\n" +
                "    `zip`         VARCHAR(45)     NULL,\n" +
                "    `tel2`        VARCHAR(45)     NULL,\n" +
                "    `fax`         VARCHAR(45)     NULL,\n" +
                "    `item_use`    INTEGER         NULL\n" +
                ");";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createMember() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS member;");
        String query = "CREATE TABLE member\n" +
                "            (\n" +
                "    `mbr_id`   INTEGER         PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `cus_no`   VARCHAR(45)     NULL,\n" +
                "    `name`     VARCHAR(255)    NOT NULL,\n" +
                "    `company`  VARCHAR(255)    NULL,\n" +
                "    `addr`     VARCHAR(255)    NULL,\n" +
                "    `zip`      VARCHAR(45)     NULL,\n" +
                "    `kind`     INTEGER         NULL,\n" +
                "    `fdate`    DATE            NOT NULL,\n" +
                "    `mark`     INTEGER         NULL,\n" +
                "    `note`     VARCHAR(255)    NULL,\n" +
                "    `remark`   VARCHAR(255)    NULL,\n" +
                "    `etc_n1`   INTEGER         NULL,\n" +
                "    `etc_n2`   INTEGER         NULL,\n" +
                "    `etc_s1`   VARCHAR(45)     NULL,\n" +
                "    `etc_s2`   VARCHAR(45)     NULL\n" +
                ");";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createCategory() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS category;");
        String query = "CREATE TABLE category\n" +
                "            (\n" +
                "    `cat_no`    INTEGER        PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `cat_name`  VARCHAR(40)    NOT NULL\n" +
                ");";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createAccountGroup() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS account_group;");
        String query = "CREATE TABLE account_group\n" +
                "(\n" +
                "    `grp_no`    INTEGER        PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `cat_no`    INTEGER        NOT NULL,\n" +
                "    `grp_name`  VARCHAR(45)    NOT NULL,\n" +
                "    `short`     VARCHAR(45)    NULL,\n" +
                "    `easy`      INTEGER        NULL,\n" +
                "    `cl_use`    INTEGER        NULL,\n" +
                "    `tmp_str`   VARCHAR(45)    NULL,\n" +
                "    `tmp_num`   INTEGER        NULL\n" +
                ");\n";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createMemberDetail() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS member_detail;");
        String query = "CREATE TABLE member_detail\n" +
                "            (\n" +
                "    `detail_id`  INTEGER         PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `mbr_id`     INTEGER         NOT NULL,\n" +
                "    `info`       VARCHAR(255)    NOT NULL,\n" +
                "    `type`       VARCHAR(45)     NOT NULL\n" +
                ");";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createAccountBook() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS account_book;");
        String query = "CREATE TABLE account_book\n" +
                "            (\n" +
                "    `ord_no`      INTEGER         PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `bnk_id`      INTEGER         NOT NULL,\n" +
                "    `what_id`     INTEGER         NOT NULL,\n" +
                "    `entry_date`  DATE            NOT NULL,\n" +
                "    `io`          VARCHAR(45)     NOT NULL,\n" +
                "    `cat_no`      INTEGER         NOT NULL,\n" +
                "    `grp_no`      INTEGER         NOT NULL,\n" +
                "    `remark`      VARCHAR(255)    NOT NULL,\n" +
                "    `item_price`  INTEGER         NOT NULL,\n" +
                "    `price`       INTEGER         NOT NULL,\n" +
                "    `tax_rate`    INTEGER         NOT NULL,\n" +
                "    `tax_cost`    INTEGER         NOT NULL,\n" +
                "    `balance`     INTEGER         NOT NULL,\n" +
                "    `chk_no`      INTEGER         NOT NULL,\n" +
                "    `relation`    INTEGER         NOT NULL,\n" +
                "    `bnk_rid`     INTEGER         NOT NULL,\n" +
                "    `what_rid`    INTEGER         NOT NULL,\n" +
                "    `slip`        VARCHAR(45)     NULL,\n" +
                "    `note`        VARCHAR(255)     NULL,\n" +
                "    `mark`        VARCHAR(45)     NULL,\n" +
                "    `bank_no`     INTEGER         NULL,\n" +
                "    `tmno`        INTEGER         NULL,\n" +
                "    `pay_method`  INTEGER         NULL,\n" +
                "    `tax_mark`    VARCHAR(45)     NULL,\n" +
                "    `mbr_id`      INTEGER         NULL\n" +
                ");";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createDonationReceipt() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS donation_receipt;");
        String query = "CREATE TABLE donation_receipt\n" +
                "            (\n" +
                "    `rec_no`      INTEGER PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `mbr_id`      INTEGER NOT NULL,\n" +
                "    `issue_date`  DATE    NOT NULL,\n" +
                "    `detail_id`   INTEGER NOT NULL\n" +
                "            );";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createAccountDetail() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS account_detail;");
        String query = "CREATE TABLE account_detail\n" +
                "(\n" +
                "    `grp_no`  INTEGER        NOT NULL,\n" +
                "    `no`      INTEGER        NOT NULL, \n" +
                "    `detail`  VARCHAR(45)    NOT NULL,\n" +
                "    PRIMARY KEY (`grp_no`, `no`)\n" +
                ");";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createDonationBook() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS donation_book;");
        String query = "CREATE TABLE donation_book\n" +
                "            (\n" +
                "    `rec_no`      INTEGER         PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `mbr_id`      INTEGER         NOT NULL,\n" +
                "    `entry_date`        DATE            NOT NULL,\n" +
                "    `io`          INTEGER         NULL,\n" +
                "    `part_no`     INTEGER         NULL,\n" +
                "    `qty`         INTEGER         NULL,\n" +
                "    `unit_price`  INTEGER         NULL,\n" +
                "    `price`       INTEGER         NOT NULL,\n" +
                "    `item_name`   VARCHAR(255)    NULL,\n" +
                "    `ipkum`       INTEGER         NULL,\n" +
                "    `subject`     VARCHAR(255)    NOT NULL\n" +
                ");";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public boolean createReceiptMap() {
        boolean result = false;

        dbMgr.open();
        dbMgr.executeUpdate("DROP TABLE IF EXISTS receipt_map;");
        String query = "CREATE TABLE receipt_map\n" +
                "            (\n" +
                "    `rec_no`           INTEGER    PRIMARY KEY    AUTOINCREMENT,\n" +
                "    `receipt_id`       INTEGER    NOT NULL,\n" +
                "    `account_book_id`  INTEGER    NOT NULL\n" +
                "            );";
        result = dbMgr.executeUpdate(query);
        dbMgr.close();
        return result;
    }

    public void createAllTables() {
        createAccountBook();
        createBankAccount();
        createCategory();
        createAccountGroup();
        createDonationBook();
        createDonationReceipt();
        createMember();
        createMemberDetail();
        createReceiptMap();
        createAccountDetail();
    }

}
