package com.example.a2020falll_deep_fake.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_4;

import java.util.ArrayList;
import java.util.List;

import com.example.a2020falll_deep_fake.SplashActivity;
import com.example.a2020falll_deep_fake.MainActivity;
import com.example.a2020falll_deep_fake.MyApplication;

public class fragment_4 extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_4 adapter;
    private List<String> setting = new ArrayList<String>(){{
        add("프로필 설정");
        add("찜한 사진");
        add("멤버쉽");
        add("공지사항");
        add("알림");
        add("이용약관");
        add("고객센터/도움말");
        add("로그아웃");
        add("회원탈퇴");
    }};

    public fragment_4(){}

    private TextView user_name;
    private TextView user_type;
    private ImageView premium;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_4, container, false);
        recyclerView = v.findViewById(R.id.rv_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter_4(getContext(),setting);
        recyclerView.setAdapter(adapter);

        user_name = (TextView) v.findViewById(R.id.user_name);
        user_type = (TextView) v.findViewById(R.id.user_type);
        premium = (ImageView) v.findViewById(R.id.premium);
        update_info();

        return v;
    }

    public void update_info(){
        MyApplication myApp = (MyApplication) getActivity().getApplication();
        user_name.setText(myApp.gget_name());
        user_type.setText(myApp.gget_type());

        if (myApp.gget_type() == "프리미엄 회원"){
            premium.setVisibility(View.VISIBLE);
        } else{
            premium.setVisibility(View.INVISIBLE);
        }
    }
}