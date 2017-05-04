package clara.iksan.manager;

import clara.iksan.MainApp;

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
        tpsPath = prefs.get("tpsPath", null);
        dataPath = prefs.get("dataPath", null);
        lastUpdate = prefs.get("lastUpdate", null);
    }

    public String toString() {
        return String.format("tpsPath : %s, dataPath : %s, lastUpdate : %s", tpsPath, dataPath, lastUpdate);
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
        prefs.put("tpsPath", this.tpsPath);
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
        prefs.put("dataPath", this.dataPath);
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
        prefs.put("lastUpdate", this.lastUpdate);
    }
}
