package clara.iksan.convert;

import clara.iksan.manager.DbManager;

import java.io.*;

/**
 * Created by Daeyoung Han on 2017. 4. 29..
 */
public class ImportCsv {

    DbManager dbMgr = null;
    String dataPath = null;

    public ImportCsv(String dataPath) {
        this.dbMgr = new DbManager();
        this.dataPath = dataPath;
    }

    public boolean importCategory() {
        File cvs = new File(dataPath + "/csv/category.csv");
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
            dbMgr.executeUpdate("DELETE FROM category; ");

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
        File cvs = new File(dataPath + "/csv/member.csv");
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
            dbMgr.executeUpdate("DELETE FROM member; ");

            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO member (rec_no, mbr_id, cus_no, name, company, addr, zip, kind" +
                        ", fdate, mark, note, remark, etc_n1, etc_n2, etc_s1, etc_s2) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }

            dbMgr.executeUpdate("UPDATE member SET fdate = fdate + 2378858;");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importBankAccount() {
        File cvs = new File(dataPath + "/csv/bank_account.csv");
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
            dbMgr.executeUpdate("DELETE FROM bank_account;");

            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO bank_account (rec_no, bnk_id, balance, last_date, id_name, act_no, open_date" +
                        ", what_id, bank_type, addr, tel, cat_name, sort, kind, damdang, prank, pay_method" +
                        ", pay_id_no, tax_method, norpt, add1, add2, zip, tel2, fax, item_use) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }

            dbMgr.executeUpdate("UPDATE bank_account SET last_date = last_date + 2378858;");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importClass() {
        File cvs = new File(dataPath + "/csv/class.csv");
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
            dbMgr.executeUpdate("DELETE FROM class;");

            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO class (rec_no, cls_no, cat_no, cla_name, short, easy" +
                        ", cl_use, tmp_str, tmp_num) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importMemberDetail() {
        File cvs = new File(dataPath + "/csv/member_detail.csv");
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
            dbMgr.executeUpdate("DELETE FROM member_detail;");

            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO member_detail (detail_id, mbr_id, info, type) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importAccountBook() {
        File cvs = new File(dataPath + "/csv/account_book.csv");
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
            dbMgr.executeUpdate("DELETE FROM account_book;");

            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO account_book (rec_no, bnk_id, what_id, ord_no, entry_date, io, cat_no, cls_no" +
                        ", remark, item_price, price, tax_rate, tax_cost, balance, chk_no, relation, bnk_rid" +
                        ", what_rid, slip, note, mark, bank_no, tmno, pay_method, tax_mark) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }

            dbMgr.executeUpdate("UPDATE account_book SET entry_date = entry_date + 2378858;");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importSubClass() {
        File cvs = new File(dataPath + "/csv/subclass.csv");
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
            dbMgr.executeUpdate("DELETE FROM subclass;");

            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO subclass (rec_no, cls_no, no, detail) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importDonationBook() {
        File cvs = new File(dataPath + "/csv/donation_book.csv");
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
            dbMgr.executeUpdate("DELETE FROM donation_book;");

            while ((line = br.readLine()) != null) {
                String query = "INSERT INTO donation_book (rec_no, mbr_id, entry_date, io, part_no, qty" +
                        ", unit_price, price, item_name, ipkum, subject) VALUES (" + line + ");";
                dbMgr.executeUpdate(query);
            }

            dbMgr.executeUpdate("UPDATE donation_book SET entry_date = entry_date + 2378858;");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbMgr.close();
        }

        return true;
    }

    public boolean importAll() {
        importMember();
        importAccountBook();
        importBankAccount();
        importCategory();
        importClass();
        importDonationBook();
        importMemberDetail();
        importSubClass();

        System.out.println("Completed : All CSV files are imported to DB");

        return true;
    }
}
