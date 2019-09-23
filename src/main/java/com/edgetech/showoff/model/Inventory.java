package com.edgetech.showoff.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String make;
    private String model;
    private int year;
    private BigDecimal price;
    private boolean hot;
    private boolean notSoHot;
    private  int customer_ID;
    private  int mileage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isNotSoHot() {
        return notSoHot;
    }

    public void setNotSoHot(boolean notSoHot) {
        this.notSoHot = notSoHot;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Inventory() {
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", hot=" + hot +
                ", notSoHot=" + notSoHot +
                ", customer_Id=" + customer_ID +
                ", mileage=" + mileage +
                '}';
    }

    public Inventory(String make, String model, int year, BigDecimal price, boolean hot, boolean notSoHot, int customer_ID, int mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.hot = hot;
        this.notSoHot = notSoHot;
        this.customer_ID = customer_ID;
        this.mileage = mileage;
    }
}
