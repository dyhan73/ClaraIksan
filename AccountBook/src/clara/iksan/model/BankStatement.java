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
    private final StringProperty className;
    private final StringProperty subClassName;
    private final IntegerProperty price;
    private final StringProperty remark;
    private final StringProperty note;

    // constructors
    //public BankStatement() { this(null, null, null, null, null, null, null); }

    public BankStatement(String entryDate, String account, String className, String subClassName, Integer price, String remark, String note) {
        this.entryDate = new SimpleStringProperty(entryDate);
        this.account = new SimpleStringProperty(account);
        this.className = new SimpleStringProperty(className);
        this.subClassName = new SimpleStringProperty(subClassName);
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

    public String getClassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public String getSubClassName() {
        return subClassName.get();
    }

    public StringProperty subClassNameProperty() {
        return subClassName;
    }

    public void setSubClassName(String subClassName) {
        this.subClassName.set(subClassName);
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
