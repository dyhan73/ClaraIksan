package tests.sqlite;

import clara.iksan.convert.ImportCsv;

/**
 * Created by Daeyoung Han on 2017. 4. 29..
 */
public class TestImportCsv {

    public static void main(String[] argv) {
        ImportCsv imp = new ImportCsv("/Users/1001235/MyDev/ws_github/ClaraIksan/AccountBook/data/");
//        imp.importAll();
//        imp.importMember();
        imp.importAccountBook();
//        imp.importBankAccount();
//        imp.importCategory();
//        imp.importClass();
//        imp.importDonationBook();
//        imp.importMemberDetail();
//        imp.importSubClass();
    }
}
