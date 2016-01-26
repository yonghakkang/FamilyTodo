package model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by yonghak on 2016-01-26.
 */
public class AutoIncrement extends RealmObject {

    private String className;

    private int index;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
