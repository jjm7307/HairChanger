package com.example.a2020falll_deep_fake.Class;

import java.util.Random;

public class ClassPhoto {

    private Random random = new Random();
    private Boolean like;
    private String name,gender,length;
    private Integer like_num;

    public ClassPhoto(String gender, String length, Integer num){
        this.name = "@drawable/"+gender+"_"+length+"_"+String.valueOf(num);
        this.gender = gender;
        this.length = length;
        this.like = false;
        this.like_num = random.nextInt(100);
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
