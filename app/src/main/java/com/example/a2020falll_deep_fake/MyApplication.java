package com.example.a2020falll_deep_fake;

import android.app.Application;

import com.example.a2020falll_deep_fake.fragments.GpsTracker;
import com.example.a2020falll_deep_fake.login.LoginResponse;
import com.google.android.gms.maps.GoogleMap;

public class MyApplication extends Application {
    private String user_id;
    private String user_name;
    private String user_type;
    private String login_status;

    GoogleMap g_googleMap;
    /*public int getGlobalValue(){
        return gValue;
    }

    public void setGlobalValue(int mValue){
        this.gValue = mValue;
    }*/

    public void gset_id(String id){
        this.user_id = id;
    }
    public void gset_name(String name){
        this.user_name = name;
    }
    public void gset_type(String type){
        this.user_type = type;
    }
    public void gset_status(String status){
        this.login_status = status;
    }

    public void gset_map(GoogleMap map){
        this.g_googleMap = map;
    }

    public String gget_id() {
        return this.user_id;
    }
    public String gget_name() {
        return this.user_name;
    }
    public String gget_type() {
        return this.user_type;
    }
    public String gget_status(){
        return this.login_status;
    }

    public GoogleMap gget_map(){
        return this.g_googleMap;
    }

}
