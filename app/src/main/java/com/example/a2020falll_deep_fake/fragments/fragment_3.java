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

import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.adapters.RecyclerViewAdapter_3;
import com.example.a2020falll_deep_fake.adapters.Store_data;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


public class fragment_3 extends Fragment implements OnMapReadyCallback {
    public static Object mContext;
    View v;
    MapView mapView;
    private GpsTracker gpsTracker;
    GoogleMap g_googleMap;
    private FloatingActionButton updateLocation_button;

    private double now_lat = 36.372160;
    private double now_lon = 127.360374;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter_3 adapter;

    Store_data store1 = new Store_data("태울 헤어숍", "★ 5.0", "커트 10,000원", 36.373032, 127.360013, "010-9013-5968");
    Store_data store2 = new Store_data("노천 헤어숍", "★ 4.8", "커트 9,000원", 36.370849, 127.357951, "010-7664-7307");
    Store_data store3 = new Store_data("교수회관 헤어숍", "★ 4.6", "커트 8,000원", 36.3737728,127.3616844, "010-3222-9847");
    Store_data store4 = new Store_data("메디컬 헤어숍", "★ 4.4", "커트 7,000원", 36.3691996,127.3683631, "042-350-1234");
    Store_data store5 = new Store_data("한빛 헤어숍", "★ 4.2", "커트 6,000원", 36.363650, 127.356481, "042-350-1234");

    private List<Store_data> store_list = new ArrayList<Store_data>(){{
        add(store1);
        add(store2);
        add(store3);
        add(store4);
        add(store5);
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



        updateLocation_button = (FloatingActionButton) v.findViewById(R.id.myLocationButton);
        gpsTracker = new GpsTracker(this.getActivity());

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        updateLocation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLoc();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        g_googleMap = googleMap;

        MyApplication myApp = (MyApplication) getActivity().getApplication();
        myApp.gset_map(g_googleMap);

        adapter = new RecyclerViewAdapter_3(getContext(),store_list, g_googleMap);
        recyclerView.setAdapter(adapter);

        MapsInitializer.initialize(this.getActivity());
        goToLocationZoom(now_lat, now_lon, 15);
        add_markers();
    }

    public void add_markers(){
        addMarker(36.373032, 127.360013, "태울 헤어숍");
        addMarker(36.370849, 127.357951, "노천 헤어숍");
        addMarker(36.3737728,127.3616844, "교수회관 헤어숍");
        addMarker(36.3691996,127.3683631, "메디컬 헤어숍");
        addMarker(36.363650, 127.356481, "한빛 헤어숍");
    }

    public void goToLocationZoom(double lat, double lng, float zoom) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom);
        g_googleMap.animateCamera(cameraUpdate);
    }

    private void updateLoc(){
        now_lat = gpsTracker.getLatitude();
        now_lon = gpsTracker.getLongitude();
        System.out.println(now_lat);
        System.out.println(now_lon);
        goToLocationZoom(now_lat,now_lon,15);
    }

    private void addMarker(double lat, double lng, String store_name) {
        g_googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(store_name));
    }

}