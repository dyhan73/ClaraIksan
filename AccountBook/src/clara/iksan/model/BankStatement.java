package clara.iksan.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Daeyoung Han on 2017. 4. 30..
 */
public class BankStatement {

    private final StringProperty entryDate;
    private final StringProperty account;
    private final StringProperty groupName;
    private final StringProperty groupDetailName;
    private final IntegerProperty price;
    private final StringProperty remark;
    private final StringProperty note;

    // constructors
    //public BankStatement() { this(null, null, null, null, null, null, null); }

    public BankStatement(String entryDate, String account, String groupName, String groupDetailName, Integer price, String remark, String note) {
        this.entryDate = new SimpleStringProperty(entryDate);
        this.account = new SimpleStringProperty(account);
        this.groupName = new SimpleStringProperty(groupName);
        this.groupDetailName = new SimpleStringProperty(groupDetailName);
        this.price = new SimpleIntegerProperty(price);
        this.remark = new SimpleStringProperty(remark);
        this.note = new SimpleStringProperty(note);
    }

    // Getter & setter
    public String getEntryDate() {
        return entryDate.get();
    }

    public StringProperty entryDateProperty() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate.set(entryDate);
    }

    public String getAccount() {
        return account.get();
    }

    public StringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public String getGroupName() {
        return groupName.get();
    }

    public StringProperty groupNameProperty() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }

    public String getGroupDetailName() {
        return groupDetailName.get();
    }

    public StringProperty groupDetailNameProperty() {
        return groupDetailName;
    }

    public void setGroupDetailName(String groupDetailName) {
        this.groupDetailName.set(groupDetailName);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getRemark() {
        return remark.get();
    }

    public StringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }
}
