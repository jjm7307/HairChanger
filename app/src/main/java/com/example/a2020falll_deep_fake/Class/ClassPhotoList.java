package com.example.a2020falll_deep_fake.Class;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ClassPhotoList extends Application {

    private Integer num_list = 9;

    private List<ClassPhoto> PhotoList = new ArrayList<ClassPhoto>(){{
        add(new ClassPhoto("male", "long", 0));
        add(new ClassPhoto("male", "long", 1));
        add(new ClassPhoto("male", "long", 2));
        add(new ClassPhoto("male", "long", 3));
        add(new ClassPhoto("male", "short", 0));
        add(new ClassPhoto("male", "short", 1));
        add(new ClassPhoto("female", "long", 0));
        add(new ClassPhoto("female", "long", 1));
        add(new ClassPhoto("female", "short", 0));
        add(new ClassPhoto("female", "short", 1));
    }};

    public List<ClassPhoto> getList(String tmp_gender, String tmp_length) {
        List<ClassPhoto> tmp_list = new ArrayList<ClassPhoto>();

        for(int i = 0; i<num_list; i++){
            if ((PhotoList.get(i).getGender()==tmp_gender && PhotoList.get(i).getLength()==tmp_length)){
                tmp_list.add(PhotoList.get(i));
            }
        }
        return tmp_list;
    }

    public void setLike(String tmp_gender, String tmp_length) {
        for(int i = 0; i<num_list; i++){
            if ((PhotoList.get(i).getGender()==tmp_gender && PhotoList.get(i).getLength()==tmp_length)){
                PhotoList.get(i).setLike();
                return;
            }
        }
    }

}
