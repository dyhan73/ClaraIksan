package tests.java;

import org.apache.commons.lang.SystemUtils;

import java.nio.file.Paths;
import java.util.prefs.Preferences;

/**
 * Created by Daeyoung Han on 2017. 5. 4..
 */
public class SystemVariables {
    public static void main(String[] args) {
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));

        System.out.println(SystemUtils.IS_OS_WINDOWS);
        System.out.println(SystemUtils.IS_OS_MAC);
        System.out.println(SystemUtils.USER_HOME);

        System.out.println(SystemUtils.JAVA_CLASS_PATH);
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(SystemUtils.JAVA_CLASS_VERSION);

        System.out.println(Paths.get("").toAbsolutePath().toString());

        System.out.println("Preferences");
        Preferences prefs = Preferences.userNodeForPackage(SystemVariables.class);
        prefs.put("currentPath", Paths.get("").toAbsolutePath().toString());

        System.out.println(prefs.get("currentPath", null));


    }

    private boolean testFile() {
        return true;
    }
}
