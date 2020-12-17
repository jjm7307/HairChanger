package com.example.a2020falll_deep_fake.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.a2020falll_deep_fake.SplashActivity;
import com.example.a2020falll_deep_fake.MainActivity;
import com.example.a2020falll_deep_fake.MyApplication;

public class fragment_4 extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_4 adapter;
    private List<String> setting = new ArrayList<String>(){{
        add("찜한사진");
        add("예약내역");
        add("멤버쉽");
        add("공지사항");
        add("알림");
        add("이용약관");
        add("고객센터");
        add("도움말");
        add("로그아웃");
        add("회원탈퇴");
    }};

    public fragment_4(){}
    private final Handler handler = new Handler();
    private TextView user_name;
    private TextView user_type,user_type_premium;
    private ImageView premium1,premium2;
    private ImageView user_photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_4, container, false);
        recyclerView = v.findViewById(R.id.rv_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter_4(getContext(),setting,this, getActivity());
        recyclerView.setAdapter(adapter);

        user_name = (TextView) v.findViewById(R.id.user_name);
        user_type_premium = (TextView) v.findViewById(R.id.user_type_premium);
        user_type = (TextView) v.findViewById(R.id.user_type);
        premium1 = (ImageView) v.findViewById(R.id.premium1);
        premium2 = (ImageView) v.findViewById(R.id.premium2);
        user_photo = (ImageView) v.findViewById(R.id.user_photo);

        MyApplication myApp = (MyApplication) getActivity().getApplication();
        if (myApp.gget_has_uri()){
            user_photo.setImageURI(myApp.gget_uri());
            //user_photo.setImageResource(R.drawable.male_long_0);
        }

        update_info();
        return v;
    }
    public void update_info(){
        MyApplication myApp = (MyApplication) getActivity().getApplication();
        user_name.setText(myApp.gget_name());
        if (myApp.gget_type() == "프리미엄 회원"){
            user_type.setVisibility(View.INVISIBLE);
            user_type_premium.setVisibility(View.VISIBLE);
            premium1.setVisibility(View.VISIBLE);
            premium2.setVisibility(View.VISIBLE);
        } else{
            user_type.setVisibility(View.VISIBLE);
            user_type_premium.setVisibility(View.INVISIBLE);
            premium1.setVisibility(View.INVISIBLE);
            premium2.setVisibility(View.INVISIBLE);
        }
        if (myApp.gget_has_uri()){
            user_photo.setImageURI(myApp.gget_uri());
        }
    }
}