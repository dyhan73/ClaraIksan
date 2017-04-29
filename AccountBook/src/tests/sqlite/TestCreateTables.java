package tests.sqlite;

import clara.iksan.convert.CreateTables;

/**
 * Created by dyhan on 2017. 4. 29..
 */
public class TestCreateTables {
    public static void main(String[] argv) {
        CreateTables ct = new CreateTables();
        ct.createAllTables();
    }
}
