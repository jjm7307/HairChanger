package com.example.a2020falll_deep_fake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import com.example.a2020falll_deep_fake.adapters.ViewPagerAdapter;
import com.example.a2020falll_deep_fake.fragments.fragment_1;
import com.example.a2020falll_deep_fake.fragments.fragment_2;
import com.example.a2020falll_deep_fake.fragments.fragment_3;
import com.example.a2020falll_deep_fake.fragments.fragment_4;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public fragment_1 fragmentA = new fragment_1();
    public fragment_2 fragmentB = new fragment_2();
    public fragment_3 fragmentC = new fragment_3();
    public fragment_4 fragmentD = new fragment_4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent intent = new Intent();

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(fragmentA, "Page1");
        adapter.addFragment(fragmentB, "Page2");
        adapter.addFragment(fragmentC, "Page3");
        adapter.addFragment(fragmentD, "Page4");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // do nothing
            }
        });
    }

    private void changeView(int index) {
        switch (index) {
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 1:
                viewPager.setCurrentItem(1);
                break;
            case 2:
                viewPager.setCurrentItem(2);
                break;
            case 3:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
