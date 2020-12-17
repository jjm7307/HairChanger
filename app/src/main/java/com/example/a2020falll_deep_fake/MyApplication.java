package com.example.a2020falll_deep_fake;

import android.app.Application;
import android.net.Uri;

import com.example.a2020falll_deep_fake.Class.ClassPhoto;
import com.example.a2020falll_deep_fake.fragments.GpsTracker;
import com.example.a2020falll_deep_fake.login.LoginResponse;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private String user_id;
    private String user_name;
    private String user_type;
    private String login_status;
    private Uri user_URI;
    private Boolean has_URI = false;

    private List<ClassPhoto> photo_list = new ArrayList<ClassPhoto>();
    private List<ClassPhoto> tmp_photo_list = new ArrayList<ClassPhoto>();
    private Integer num_photo_list = 0;
    private Integer male_short = 10;
    private Integer male_long = 10;
    private Integer female_short = 10;
    private Integer female_long = 10;

    GoogleMap g_googleMap;
    /*public int getGlobalValue(){
        return gValue;
    }

    public void setGlobalValue(int mValue){
        this.gValue = mValue;
    }*/

    public Boolean gget_has_uri(){ return this.has_URI; }
    public void gset_uri(Uri uri){
        this.has_URI = true;
        this.user_URI = uri;
    }
    public Uri gget_uri(){ return this.user_URI; }

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

    public void add_photo_list(){
        this.photo_list.clear();
        for (int i=0; i<this.male_long; i++){
            String tag1 = "#내츄럴펌";
            if (i%3 == 0){
                tag1 = "#볼륨펌";
            }
            else if (i%3 == 1){
                tag1 = "#가르마펌";
            }
            String tag2 = "#투블럭컷";
            if (i%2 == 0){
                tag2 = "#댄디컷";
            }
            this.photo_list.add(new ClassPhoto("male", "long", i, tag1, tag2));
        }
        for (int i=0; i<this.male_short; i++){
            String tag1 = "#내츄럴펌";
            if (i%3 == 0){
                tag1 = "#볼륨펌";
            }
            else if (i%3 == 1){
                tag1 = "#가르마펌";
            }
            String tag2 = "#투블럭컷";
            if (i%2 == 0){
                tag2 = "#댄디컷";
            }
            this.photo_list.add(new ClassPhoto("male", "short", i, tag1, tag2));
        }
        for (int i=0; i<this.female_long; i++){
            String tag1 = "#셋팅펌";
            if (i%3 == 0){
                tag1 = "#S컬펌";
            }
            else if (i%3 == 1){
                tag1 = "#C컬펌";
            }
            String tag2 = "#레이어드컷";
            this.photo_list.add(new ClassPhoto("female", "long", i, tag1, tag2));
        }
        for (int i=0; i<this.female_short; i++){
            String tag1 = "#C컬펌";
            if (i%3 == 0){
                tag1 = "#볼륨펌";
            }
            else if (i%3 == 1){
                tag1 = "#C컷펌";
            }
            String tag2 = "#보브컷";
            if (i%2 == 0){
                tag2 = "#픽시컷";
            }
            this.photo_list.add(new ClassPhoto("female", "short", i, tag1, tag2));
        }
    }
    public List<ClassPhoto> get_photo_list(String tmp_gender, String tmp_length) {
        this.tmp_photo_list.clear();
        for (int i = 0; i < this.photo_list.size(); i++) {
            if (((this.photo_list.get(i).getGender() == tmp_gender) && (this.photo_list.get(i).getLength() == tmp_length))) {
                this.tmp_photo_list.add(this.photo_list.get(i));
            }
        }
        return this.tmp_photo_list;
    }
    public List<ClassPhoto> get_like_photo_list(String tmp_gender, String tmp_length) {
        List<ClassPhoto> tmp_list = new ArrayList<ClassPhoto>();

        for (int i = 0; i < this.num_photo_list; i++) {
            if (photo_list.get(i).getLike()) {
                tmp_list.add(photo_list.get(i));
            }
        }
        return tmp_list;
    }
    public void setLike(String tmp_gender, String tmp_length) {
        for(int i = 0; i<num_photo_list; i++){
            if ((photo_list.get(i).getGender()==tmp_gender && photo_list.get(i).getLength()==tmp_length)){
                photo_list.get(i).setLike();
                return;
            }
        }
    }

}
