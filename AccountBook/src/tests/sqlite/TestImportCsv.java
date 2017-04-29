package tests.sqlite;

import clara.iksan.convert.ImportCsv;

/**
 * Created by 1001235 on 2017. 4. 29..
 */
public class TestImportCsv {

    public static void main(String[] argv) {
        ImportCsv imp = new ImportCsv("/Users/1001235/MyDev/ws_github/ClaraIksan/AccountBook/data/");
        imp.importMember();
    }
}
