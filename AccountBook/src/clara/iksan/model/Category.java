package clara.iksan.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Daeyoung Han on 2017. 5. 14..
 */
public class Category {
    private final IntegerProperty no;
    private final StringProperty name;

    public Category(Integer no, String name) {
        this.no = new SimpleIntegerProperty(no);
        this.name = new SimpleStringProperty(name);
    }

    public int getNo() {
        return no.get();
    }

    public IntegerProperty noProperty() {
        return no;
    }

    public void setNo(int no) {
        this.no.set(no);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
