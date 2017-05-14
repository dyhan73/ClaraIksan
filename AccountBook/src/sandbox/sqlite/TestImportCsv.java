package sandbox.sqlite;

import clara.iksan.convert.ImportCsv;

import java.io.*;

/**
 * Created by Daeyoung Han on 2017. 4. 29..
 */
public class TestImportCsv {

    public static void main(String[] argv) {
//        ImportCsv imp = new ImportCsv("/Users/1001235/MyDev/ws_github/ClaraIksan/AccountBook/data/");
//        imp.importAll();
//        imp.importMember();
//        imp.importAccountBook();
//        imp.importBankAccount();
//        imp.importCategory();
//        imp.importAccountGroup();
//        imp.importDonationBook();
//        imp.importMemberDetail();
//        imp.importSubClass();

        TestImportCsv tic = new TestImportCsv();
        tic.skipFirstField();
    }

    public void skipFirstField() {
        File file = new File("/Users/1001235/Documents/ClaraIksan/data/csv/account_book.csv");

        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found : ");
        }
        BufferedReader br = new BufferedReader(fr);

        // skip header (first line)
        String line = null;
        try {
            for (int i=0; i<10; i++) {
                line = br.readLine();
                System.out.println(line);
                System.out.println(line.replaceFirst("^.*?,", ""));
            }
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }
}
