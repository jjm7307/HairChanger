package com.example.a2020falll_deep_fake;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

public class SplashActivity extends AppCompatActivity{
    String[] PERMISSIONS = {
            //List for get permission
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private Button bt_login;
    private Button bt_signup;
    private String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        askPermission();
        while(!hasPermissions(this,PERMISSIONS)){}

        bt_login = (Button)findViewById(R.id.bt_login);
        bt_signup = (Button)findViewById(R.id.bt_signup);



        bt_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View v_signup = inflater.inflate(R.layout.dialog_signup, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(SplashActivity.this, R.style.MyAlertDialogStyle);
                dialog.setTitle("새 계정 만들기")
                        .setView(v_signup)
                        .setPositiveButton("회원가입", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText edt_name = v_signup.findViewById(R.id.edt_name);
                                Name = edt_name.getText().toString();////////////////////////////////////
                                EditText edt_id = v_signup.findViewById(R.id.edt_id);
                                EditText edt_password_1 = v_signup.findViewById(R.id.edt_password_1);
                                EditText edt_password_2 = v_signup.findViewById(R.id.edt_password_2);
                                if(TextUtils.isEmpty(edt_id.getText().toString())){
                                    Toast.makeText(SplashActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                //이제 회원가입하면 됨.
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(SplashActivity.this, "취소되었습니다", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create()
                        .show();
            }});

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SplashActivity.this, Name+"(님)으로 로그인하셨습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
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
}
