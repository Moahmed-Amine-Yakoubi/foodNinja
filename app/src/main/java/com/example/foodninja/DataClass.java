package com.example.foodninja;

public class DataClass {
    String data_title, data_price,data_category;

    public DataClass() {
    }

    public DataClass(String data_category, String data_price,String data_title ) {
        this.data_title = data_title;
        this.data_price = data_price;
        this.data_category = data_category;
    }

    public String getData_title() {
        return data_title;
    }

    public void setData_title(String data_title) {
        this.data_title = data_title;
    }

    public String getData_price() {
        return data_price;
    }

    public void setData_price(String data_price) {
        this.data_price = data_price;
    }

    public String getData_category() {
        return data_category;
    }

    public void setData_category(String data_category) {
        this.data_category = data_category;
    }
}
