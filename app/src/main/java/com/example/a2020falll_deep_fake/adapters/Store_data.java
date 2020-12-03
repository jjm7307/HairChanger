package com.example.a2020falll_deep_fake.adapters;

import android.widget.TextView;

public class Store_data {

    private String store_name;
    private String store_star;
    private String store_price;
    private double lat;
    private double lng;
    private String phone_number;

    public Store_data(String name, String star, String cut, double lat, double lng, String num){
        this.store_name = name;
        this.store_star = star;
        this.store_price = cut;
        this.lat = lat;
        this.lng = lng;
        this.phone_number = num;
    }

    public String get_name(){
        return this.store_name;
    }
    public String get_star(){
        return this.store_star;
    }
    public String get_price(){
        return this.store_price;
    }
    public double get_lat(){
        return this.lat;
    }
    public double get_lng(){
        return this.lng;
    }
    public String get_num(){
        return this.phone_number;
    }
}



