package com.example.a2020falll_deep_fake.Class;

import java.util.Random;

public class ClassPhoto {

    private Random random = new Random();
    private Boolean like;
    private String name,gender,length;
    private Integer like_num;
    private String tag1, tag2;

    public ClassPhoto(String gender, String length, Integer num, String tag1, String tag2){
        this.name = "@drawable/"+gender+"_"+length+"_"+String.valueOf(num);
        this.gender = gender;
        this.length = length;
        this.like = false;
        this.like_num = (10-num)*23 + 27;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }
    public String getGender() {
        return this.gender;
    }
    public String getLength() {
        return this.length;
    }
    public String getName() {
        return this.name;
    }
    public String getTag1() {
        return this.tag1;
    }
    public String getTag2() {
        return this.tag2;
    }

    public Integer getLikeNum(){
        return this.like_num;
    }

    public Boolean getLike() {
        return this.like;
    }

    public void setLike() {
        if (this.like){
            this.like_num -= 1;
        }
        else{
            this.like_num += 1;
        }
        this.like = !this.like;
    }
}
