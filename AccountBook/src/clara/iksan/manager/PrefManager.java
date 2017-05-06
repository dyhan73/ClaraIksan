package clara.iksan.manager;

import clara.iksan.MainApp;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.prefs.Preferences;

/**
 * Created by Daeyoung Han on 2017. 5. 4..
 */
public class PrefManager {
    private Preferences prefs;
    private String tpsPath;
    private String dataPath;
    private String lastUpdate;

    public PrefManager() {
        // check preferences
        prefs = Preferences.userNodeForPackage(MainApp.class);
        tpsPath = prefs.get("tpsPath", "C:/m7/data");
        dataPath = prefs.get("dataPath", System.getProperty("user.home") + "/Documents/ClaraIksan/data");
        lastUpdate = prefs.get("lastUpdate", null);
    }

    public String toString() {
        return String.format("tpsPath : %s, dataPath : %s, lastUpdate : %s", tpsPath, dataPath, lastUpdate);
    }

    /**
     * csv directory is under the data path
     * csv directory is generated if needed
     * @return full path of csv
     */
    public String getCsvPath() {
        if (tpsPath.isEmpty() || dataPath.isEmpty()) return null;
        String csvPath = dataPath + "/csv";
        File file = new File(csvPath);
        file.mkdirs();
        return csvPath;
    }

    public String getDbFile() {
        if (dataPath.isEmpty()) return null;
        String dbPath = dataPath + "/database";
        File file = new File(dbPath);
        file.mkdirs();
        return dbPath + "/clara_iksan.db";
    }

    public void store() {
        prefs.put("tpsPath", this.tpsPath);
        prefs.put("dataPath", this.dataPath);
        prefs.put("lastUpdate", this.lastUpdate);
    }

    public String getTpsPath() {
        return tpsPath;
    }

    public void setTpsPath(String tpsPath) {
        this.tpsPath = tpsPath;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;

        // create csv and database directory
        File file = new File(dataPath + "/csv");
        file.mkdirs();
        file = new File(dataPath + "/database");
        file.mkdirs();
    }

    public LocalDateTime getLastUpdate() {
        return LocalDateTime.parse(this.lastUpdate);
    }

    public void setLastUpdate() {
        this.lastUpdate = LocalDateTime.now().toString();
    }
}
