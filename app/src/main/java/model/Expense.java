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
    private Double value;


    /**
     * 누적 금액
     */
    private Double accrueValue;

    /**
     * 장소 좌표
     */
    private String coordinate;

    /**
     * 메모
     */
    private String memo;


    /**
     * sms 원본 메세지
     */
    private String originData;



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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getAccrueValue() {
        return accrueValue;
    }

    public void setAccrueValue(Double accrueValue) {
        this.accrueValue = accrueValue;
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

    public String getOriginData() {
        return originData;
    }

    public void setOriginData(String originData) {
        this.originData = originData;
    }


}
