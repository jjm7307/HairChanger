package com.example.a2020falll_deep_fake.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_3;

import java.util.ArrayList;
import java.util.List;

public class fragment_3 extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_3 adapter;
    private List<String> store_list = new ArrayList<String>(){{
        add("store_1");
        add("store_2");
        add("store_3");
        add("store_4");
        add("store_5");
        add("store_6");
        add("store_7");
        add("store_8");
    }};

    public fragment_3(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_3, container, false);
        recyclerView = v.findViewById(R.id.rv_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter_3(getContext(),store_list);
        recyclerView.setAdapter(adapter);

        return v;
    }
}