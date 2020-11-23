package com.example.a2020falll_deep_fake.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_4;

import java.util.ArrayList;
import java.util.List;

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

        return v;
    }
}