package com.example.a2020falll_deep_fake;

import android.app.Application;
import com.example.a2020falll_deep_fake.login.LoginResponse;

public class MyApplication extends Application {
    private String user_id;
    private String user_name;
    private String user_type;
    private String login_status;
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

    public String gget_id() {
        return user_id;
    }
    public String gget_name() {
        return user_name;
    }
    public String gget_type() {
        return user_type;
    }
    public String gget_status(){
        return login_status;
    }

}
