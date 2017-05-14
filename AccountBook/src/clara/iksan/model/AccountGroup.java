package clara.iksan.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Daeyoung Han on 2017. 5. 14..
 */
public class AccountGroup {
    private final IntegerProperty grpNo;
    private final StringProperty grpName;
    private final StringProperty claShort;
    private final IntegerProperty catNo;
    private final StringProperty catName;
    private final IntegerProperty easy;  // 0:무시, 1:수익, 2:비용, 3:자산
    private final IntegerProperty clUse; // 1:입출금계정, 2:입출고계정


    public AccountGroup(Integer grpNo, String claName, String claShort, Integer catNo, String catName, Integer easy, Integer clUse) {
        this.grpNo = new SimpleIntegerProperty(grpNo);
        this.grpName = new SimpleStringProperty(claName);
        this.claShort = new SimpleStringProperty(claShort);
        this.catNo = new SimpleIntegerProperty(catNo);
        this.catName = new SimpleStringProperty(catName);
        this.easy = new SimpleIntegerProperty(easy);
        this.clUse = new SimpleIntegerProperty(clUse);
    }

    public int getGrpNo() {
        return grpNo.get();
    }

    public IntegerProperty grpNoProperty() {
        return grpNo;
    }

    public void setGrpNo(int grpNo) {
        this.grpNo.set(grpNo);
    }

    public int getCatNo() {
        return catNo.get();
    }

    public IntegerProperty catNoProperty() {
        return catNo;
    }

    public void setCatNo(int catNo) {
        this.catNo.set(catNo);
    }

    public String getGrpName() {
        return grpName.get();
    }

    public StringProperty grpNameProperty() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName.set(grpName);
    }

    public String getClaShort() {
        return claShort.get();
    }

    public StringProperty claShortProperty() {
        return claShort;
    }

    public void setClaShort(String claShort) {
        this.claShort.set(claShort);
    }


    public String getCatName() {
        return catName.get();
    }

    public StringProperty catNameProperty() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName.set(catName);
    }

    public int getEasy() {
        return easy.get();
    }

    public IntegerProperty easyProperty() {
        return easy;
    }

    public void setEasy(int easy) {
        this.easy.set(easy);
    }

    public int getClUse() {
        return clUse.get();
    }

    public IntegerProperty clUseProperty() {
        return clUse;
    }

    public void setClUse(int clUse) {
        this.clUse.set(clUse);
    }
}
