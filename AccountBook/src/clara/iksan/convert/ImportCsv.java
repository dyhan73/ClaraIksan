package clara.iksan.convert;

import clara.iksan.util.DbManager;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dyhan on 2017. 4. 29..
 */
public class ImportCsv {

    DbManager dbMgr = null;
    String dataRootPath = null; // "/Users/1001235/MyDev/ws_github/ClaraIksan/AccountBook/data/"

    public ImportCsv(String dataRootPath) {
        this.dbMgr = new DbManager();
        this.dataRootPath = dataRootPath;
    }

    public boolean importCategory() throws SQLException {
        File cvs = new File(dataRootPath + "csv/category.csv");
        FileReader fr = null;
        try {
            fr = new FileReader(cvs);
        } catch (FileNotFoundException e) {
            System.out.println("File not found : ");
        }
        BufferedReader br = new BufferedReader(fr);

        // skip header (first line)
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("IO Error");
        }

        dbMgr.open();
        try {
            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO category (rec_no, cat_no, cat_name) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importMember() {
        File cvs = new File(dataRootPath + "csv/member.csv");
        FileReader fr = null;
        try {
            fr = new FileReader(cvs);
        } catch (FileNotFoundException e) {
            System.out.println("File not found : ");
        }
        BufferedReader br = new BufferedReader(fr);

        // skip header (first line)
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("IO Error");
        }
/*
    `rec_no`   INTEGER         PRIMARY KEY    AUTOINCREMENT,
    `mbr_id`   INTEGER         NOT NULL,
    `cus_no`   VARCHAR(45)     NULL,
    `name`     VARCHAR(255)    NOT NULL,
    `company`  VARCHAR(255)    NULL,
    `addr`     VARCHAR(255)    NULL,
    `zip`      VARCHAR(45)     NULL,
    `kind`     INTEGER         NULL,
    `fdate`    DATE            NOT NULL,
    `mark`     INTEGER         NULL,
    `note`     VARCHAR(255)    NULL,
    `remark`   VARCHAR(255)    NULL,
    `etc_n1`   INTEGER         NULL,
    `etc_n2`   INTEGER         NULL,
    `etc_s1`   VARCHAR(45)     NULL,
    `etc_s2`   VARCHAR(45)     NULL
 */
        dbMgr.open();
        try {
            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO member (rec_no, mbr_id, cus_no, name, company, addr, zip, kind" +
                        ", fdate, mark, note, remark, etc_n1, etc_n2, etc_s1, etc_s2) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }
}
