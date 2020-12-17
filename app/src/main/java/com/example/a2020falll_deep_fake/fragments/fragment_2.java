package com.example.a2020falll_deep_fake.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a2020falll_deep_fake.Class.ClassGallery;
import com.example.a2020falll_deep_fake.MainActivity;
import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_2;
import com.example.a2020falll_deep_fake.login.LoginData;
import com.example.a2020falll_deep_fake.login.RetrofitClient;
import com.example.a2020falll_deep_fake.login.ServiceApi;
import com.example.a2020falll_deep_fake.picture.CompletePictureResponse;
import com.example.a2020falll_deep_fake.picture.GallaryData;
import com.example.a2020falll_deep_fake.picture.GallaryResponse;
import com.google.android.gms.common.util.NumberUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_2 extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_2 adapter;
    private ServiceApi service;
    private MyApplication myApp;
    private ImageView gallary_photo;
    private SwipeRefreshLayout swipe_layout;
    private List<ClassGallery> gallery_list = new ArrayList<ClassGallery>();
    /*{{
        add("photo1");
        add("photo2");
        add("photo3");
        add("photo4");
        add("photo5");
        add("photo6");
        add("photo7");
        add("photo8");
    }};*/

    public fragment_2(){
        //System.out.println("frag2");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        System.out.println("oncreateview");
        v = inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView = v.findViewById(R.id.rv_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        RecyclerView.LayoutManager layoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        myApp = (MyApplication) getActivity().getApplication();

        adapter = new RecyclerViewAdapter_2(getContext(),gallery_list, myApp.gget_id(), this);
        recyclerView.setAdapter(adapter);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        gallary_photo = (ImageView) v.findViewById(R.id.dialog_photo);
        swipe_layout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_layout);
        /*if (gallery_list.size() == 0){
            setGalleryList();
        }*/

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(){
                updateGalleryList();
            }
        });

        return v;
    }

    /*public void setGalleryList() {
        gallery_list.add(new ClassGallery("female", "long", 0, 0, 1));
        //gallery_list.add(new ClassGallery("female", "long", 1, "1"));
        //gallery_list.add(new ClassGallery("female", "short", 0, "2"));
        //gallery_list.add(new ClassGallery("female", "short", 1, "3"));
        adapter.notifyDataSetChanged();
    }*/

    public void updateGalleryList() {
        System.out.println("update");
        String user_id = myApp.gget_id();
        System.out.println(user_id);
        GallaryData user_data = new GallaryData(user_id);
        service.updateResponse(user_data).enqueue(new Callback<GallaryResponse>() {
            @Override
            public void onResponse(Call<GallaryResponse> call,
                                   Response<GallaryResponse> response) {
                GallaryResponse result = response.body();
                System.out.println(result.check_status());
                if (result != null) {
                    if (result.check_status()) {
                        //Log.v(result.string());
                        //Log.v("update", "success");
                        int gal_len = result.get_len();
                        System.out.println(gal_len);
                        int i = 0 ;
                        /*for (i = 0 ; i < gal_len ; i++) {
                            gallery_list.remove(i);
                        }*/
                        gallery_list = new ArrayList<ClassGallery>();
                        System.out.println("make new fin");
                        for (i = 0 ; i < gal_len ; i++){
                            String hair_image_name = result.get_hairs().get(i);
                            System.out.println(hair_image_name);
                            String[] hair_parse = hair_image_name.split("_");
                            System.out.println(hair_parse[0]);
                            System.out.println(hair_parse[1]);
                            System.out.println(hair_parse[2]);
                            System.out.println(result.get_progress().get(i));
                            gallery_list.add(new ClassGallery(hair_parse[0], hair_parse[1], Integer.parseInt(hair_parse[2]), result.get_progress().get(i), result.get_rids().get(i)));
                        }
                        adapter = new RecyclerViewAdapter_2(getContext(),gallery_list, myApp.gget_id(), getParentFragment());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        swipe_layout.setRefreshing(false);
                        //Toast.makeText(getContext(), "갤러리 업데이트 완료!", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext(), "갤러리 업데이트 실패!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "업데이트 답변이 읎어요.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GallaryResponse> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }
}