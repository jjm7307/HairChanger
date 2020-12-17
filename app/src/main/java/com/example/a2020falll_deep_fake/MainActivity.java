package com.example.a2020falll_deep_fake;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;

import com.example.a2020falll_deep_fake.adapters.ViewPagerAdapter;
import com.example.a2020falll_deep_fake.fragments.fragment_1;
import com.example.a2020falll_deep_fake.fragments.fragment_2;
import com.example.a2020falll_deep_fake.fragments.fragment_3;
import com.example.a2020falll_deep_fake.fragments.fragment_4;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView icon_1;
    private ImageView icon_2;
    private ImageView icon_3;
    private ImageView icon_4;

    public fragment_1 fragmentA = new fragment_1();
    public fragment_2 fragmentB = new fragment_2();
    public fragment_3 fragmentC = new fragment_3();
    public fragment_4 fragmentD = new fragment_4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        Intent intent = new Intent();

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(fragmentA, " ");
        adapter.addFragment(fragmentB, " ");
        adapter.addFragment(fragmentC, " ");
        adapter.addFragment(fragmentD, " ");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        icon_1 = findViewById(R.id.icon_1);
        icon_2 = findViewById(R.id.icon_2);
        icon_3 = findViewById(R.id.icon_3);
        icon_4 = findViewById(R.id.icon_4);

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == this.RESULT_OK) {
            MyApplication myApp = (MyApplication) getApplication();
            Uri imageUri = data.getData();
            myApp.gset_uri(imageUri);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(fragmentD).attach(fragmentD).commit();
        }
    }

    public void showSelect(View view){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(intent, 2); //
    }

    public void showSelectProfile(View view){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(intent,1); //
    }

    public void share(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String shareBody = "제 새로운 헤어스타일 어때요?";
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }


    private void changeView(int index) {
        System.out.println("changeview called");
        switch (index) {
            case 0:
                icon_1.setImageResource(R.drawable.page_1_fill);
                icon_2.setImageResource(R.drawable.page_2_empty);
                icon_3.setImageResource(R.drawable.page_3_empty);
                icon_4.setImageResource(R.drawable.page_4_empty);
                viewPager.setCurrentItem(0);
                break;
            case 1:
                System.out.println("change start");
                fragmentB.updateGalleryList();
                icon_1.setImageResource(R.drawable.page_1_empty);
                icon_2.setImageResource(R.drawable.page_2_fill);
                icon_3.setImageResource(R.drawable.page_3_empty);
                icon_4.setImageResource(R.drawable.page_4_empty);
                viewPager.setCurrentItem(1);
                System.out.println("change finish");
                break;
            case 2:
                icon_1.setImageResource(R.drawable.page_1_empty);
                icon_2.setImageResource(R.drawable.page_2_empty);
                icon_3.setImageResource(R.drawable.page_3_fill);
                icon_4.setImageResource(R.drawable.page_4_empty);
                viewPager.setCurrentItem(2);
                break;
            case 3:
                icon_1.setImageResource(R.drawable.page_1_empty);
                icon_2.setImageResource(R.drawable.page_2_empty);
                icon_3.setImageResource(R.drawable.page_3_empty);
                icon_4.setImageResource(R.drawable.page_4_fill);
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
