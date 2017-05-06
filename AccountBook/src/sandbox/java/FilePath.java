package sandbox.java;

import java.io.File;

/**
 * Java File and Path test
 * Created by Daeyoung Han on 2017-05-05.
 */
public class FilePath {
    public static void main(String[] args) {
        FilePath fp = new FilePath();

        String docPath = fp.getDocumentsPath();

        System.out.println(docPath);

        System.out.println(fp.makeDirectory(docPath + "/aaa/bbb/ccc"));



    }

    public String getDocumentsPath() {
        String docPath = System.getProperty("user.home") + "/Documents/";
        File file = new File(docPath);
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.getAbsolutePath());
        return docPath;
    }

    public boolean makeDirectory(String path) {
        File file = new File(path);
        return file.mkdirs();

    }
}
