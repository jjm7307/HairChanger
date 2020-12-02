package com.example.a2020falll_deep_fake;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.a2020falll_deep_fake.login.LoginData;
import com.example.a2020falll_deep_fake.login.LoginResponse;
import com.example.a2020falll_deep_fake.login.RetrofitClient;
import com.example.a2020falll_deep_fake.login.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity{
    String[] PERMISSIONS = {
            //List for get permission
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET
    };

    private ConstraintLayout screen;
    private EditText mIdView;
    private EditText mPasswordView;
    private Button mIdLoginButton;
    private Button mJoinButton;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIdView = (EditText) findViewById(R.id.edit_id);
        mPasswordView = (EditText) findViewById(R.id.edit_password);
        mIdLoginButton = (Button) findViewById(R.id.bt_login);
        mJoinButton = (Button) findViewById(R.id.bt_signup);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        askPermission();
        while(!hasPermissions(this,PERMISSIONS)){}

        mIdLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        /*
        mJoinButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                //startActivity(intent);
            }
        });*/
        /*
        screen = (ConstraintLayout)findViewById(R.id.screen);

        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SplashActivity.this, "조재민(님)으로 로그인하셨습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        );
        */
    }

    private void askPermission() {
        int PERMISSION_ALL = 1;

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void attemptLogin() {
        mIdView.setError(null);
        mPasswordView.setError(null);

        String ID = mIdView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (password.isEmpty()) {
            mIdView.setError("비밀번호를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        }

        if (ID.isEmpty()) {
            mIdView.setError("아이디를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            System.out.println(ID);
            System.out.println(password);
            startLogin(new LoginData(ID, password));
        }
    }

    private void startLogin(LoginData data) {
        System.out.println("start login");
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                System.out.println(result.get_status());
                System.out.println(result.check_status());
                if (result.check_status()){
                    String login_status = result.get_name() + "님 어서오고.";
                    Toast.makeText(SplashActivity.this, login_status, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(SplashActivity.this, "아이디 또는 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
