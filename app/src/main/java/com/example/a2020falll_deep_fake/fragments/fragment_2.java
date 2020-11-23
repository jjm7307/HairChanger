package com.example.a2020falll_deep_fake.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_2;

import java.util.ArrayList;
import java.util.List;

public class fragment_2 extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_2 adapter;
    private List<String> photo_list = new ArrayList<String>(){{
        add("photo1");
        add("photo2");
        add("photo3");
        add("photo4");
        add("photo5");
        add("photo6");
        add("photo7");
        add("photo8");
    }};

    public fragment_2(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView = v.findViewById(R.id.rv_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        RecyclerView.LayoutManager layoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter_2(getContext(),photo_list);
        recyclerView.setAdapter(adapter);

        return v;
    }
}