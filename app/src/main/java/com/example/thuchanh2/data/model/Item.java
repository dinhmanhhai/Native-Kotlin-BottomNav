package com.example.thuchanh2.data.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name;
    private String detail;
    private String status;
    private String date;
    private boolean isCoop;


    public Item(int id, String name, String detail, String status, String date, boolean isCoop) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.status = status;
        this.date = date;
        this.isCoop = isCoop;
    }

    public boolean isCoop() {
        return isCoop;
    }

    public void setCoop(boolean coop) {
        isCoop = coop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
