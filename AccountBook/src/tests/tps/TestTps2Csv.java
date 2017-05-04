package tests.tps;

import clara.iksan.convert.*;

/**
 * Created by Daeyoung Han on 2017. 4. 29..
 */
public class TestTps2Csv {
    public static void main(String[] argv) {
        Tps2Csv conv = new Tps2Csv();
        conv.convertAll("/Users/1001235/MyDev/ws_github/ClaraIksan/AccountBook/data/");
    }
}
