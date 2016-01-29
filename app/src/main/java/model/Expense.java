package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yonghak on 2016-01-25.
 */
public class Expense extends RealmObject {
    @PrimaryKey
    private int id;

    /**
     * 장소명
     */
    private String place;
    /**
     * 카드 종류
     */
    private String card;
    /**
     * 항목
     */
    private String category;
    /**
     * 날짜
     */
    private String date;
    /**
     * 지출 금액
     */
    private String value;

    /**
     * 장소 좌표
     */
    private String coordinate;

    /**
     * 메모
     */
    private String memo;






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
