package com.example.a2020falll_deep_fake.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.Class.ClassPhoto;
import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_1;
import java.util.ArrayList;
import java.util.List;

public class fragment_1 extends Fragment {
    View v;

    private Boolean is_male = true;//true : male, false : female
    private Boolean is_long = true;//true : long, false : short
    private String st_gender = "male";
    private String st_length = "long";

    private ImageButton bt_gender;
    private TextView gender;
    private RadioGroup rg;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter_1 adapter;
    private List<ClassPhoto> photo_list = new ArrayList<>();

    public fragment_1(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_1, container, false);
        rg = v.findViewById(R.id.radioGroup);
        bt_gender = v.findViewById(R.id.bt_gender);
        gender = v.findViewById(R.id.gender);



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.bt_long){
                    is_long = true;
                    st_length = "long";
                }
                else{
                    is_long = false;
                    st_length = "short";
                }
                setPhotoList();
            }
        });

        bt_gender.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (is_male){
                    gender.setText("여성");
                    st_gender = "female";
                    is_male = false;
                }
                else{
                    gender.setText("남성");
                    st_gender = "male";
                    is_male = true;
                }
                setPhotoList();
            }
        });

        recyclerView = v.findViewById(R.id.rv_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        RecyclerView.LayoutManager layoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter_1(getContext(),photo_list, getActivity());
        recyclerView.setAdapter(adapter);

        if(photo_list.size() == 0){
            setPhotoList();
        }

        return v;
    }

    public void setPhotoList() {
        MyApplication myApp = (MyApplication) getActivity().getApplication();
        photo_list.clear();
        for (int i=0; i<myApp.get_photo_list(st_gender,st_length).size(); i++){
            photo_list.add(myApp.get_photo_list(st_gender,st_length).get(i));
        }
        adapter.notifyDataSetChanged();
    }
}