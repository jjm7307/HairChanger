package com.example.a2020falll_deep_fake;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.example.a2020falll_deep_fake.login.LoginData;
import com.example.a2020falll_deep_fake.login.LoginResponse;
import com.example.a2020falll_deep_fake.login.JoinData;
import com.example.a2020falll_deep_fake.login.JoinResponse;
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
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public LoginResponse current_user_data = null;

    private ConstraintLayout screen;
    private EditText mIdView;
    private EditText mPasswordView;
    private Button mIdLoginButton;
    private Button mSignupButton;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        askPermission();
        while(!hasPermissions(this,PERMISSIONS)){}

        mIdView = (EditText) findViewById(R.id.edit_id);
        mPasswordView = (EditText) findViewById(R.id.edit_password);
        mIdLoginButton = (Button) findViewById(R.id.bt_login);
        mSignupButton = (Button) findViewById(R.id.bt_signup);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                attemptSignup();
                }
            });

        mIdLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
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

    private void attemptLogin() {
        mIdView.setError(null);
        mPasswordView.setError(null);

        String ID = mIdView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (ID.isEmpty()) {
            mIdView.setError("아이디를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        }

        if (password.isEmpty()) {
            mPasswordView.setError("비밀번호를 입력해주세요.");
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
                if (result != null) {
                    if (result.check_status()) {
                        System.out.println(result.get_status());
                        System.out.println(result.check_status());
                        String login_status = result.get_name() + "님 어서오고.";
                        Toast.makeText(SplashActivity.this, login_status, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);

                        MyApplication myApp = (MyApplication) getApplication();
                        myApp.gset_id(result.get_id());
                        myApp.gset_name(result.get_name());
                        if (result.get_type() == 0) {
                            myApp.gset_type("일반 회원");
                        } else if (result.get_type() == 1) {
                            myApp.gset_type("프리미엄 회원");
                        } else {
                            myApp.gset_type("핵쟁이");
                        }
                        myApp.gset_status(result.get_status());
                        finish();
                    } else {
                        Toast.makeText(SplashActivity.this, "아이디 또는 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(SplashActivity.this, "서버가 꺼져있습니다. 관리자에게 문의하세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "로그인 실패.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void attemptSignup() {
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View v_signup = inflater.inflate(R.layout.dialog_signup, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(SplashActivity.this, R.style.MyAlertDialogStyle);
        dialog.setTitle("새 계정 만들기")
                .setView(v_signup)
                .setPositiveButton("회원가입", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edt_name = v_signup.findViewById(R.id.edt_name);
                        //Name = edt_name.getText().toString();////////////////////////////////////
                        EditText edt_id = v_signup.findViewById(R.id.edt_id);
                        EditText edt_password_1 = v_signup.findViewById(R.id.edt_password_1);
                        EditText edt_password_2 = v_signup.findViewById(R.id.edt_password_2);
                        /*if(TextUtils.isEmpty(edt_id.getText().toString())){
                            Toast.makeText(SplashActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                            return;
                        }*/
                        //이제 회원가입하면 됨.

                        edt_name.setError(null);
                        edt_id.setError(null);
                        edt_password_1.setError(null);
                        edt_password_2.setError(null);

                        String name = edt_name.getText().toString();
                        String id = edt_id.getText().toString();
                        String password1 = edt_password_1.getText().toString();
                        String password2 = edt_password_1.getText().toString();

                        boolean cancel = false;
                        View focusView = null;

                        if (name.isEmpty()) {
                            //edt_name.setError("이름을 입력해주세요.");
                            //focusView = edt_name;
                            //cancel = true;
                            Toast.makeText(SplashActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                        if (id.isEmpty()) {
                            //edt_id.setError("아이디를 입력해주세요.");
                            //focusView = edt_id;
                            //cancel = true;
                            Toast.makeText(SplashActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                        if (password1.isEmpty()) {
                            //edt_password_1.setError("비밀번호를 입력해주세요.");
                            //focusView = edt_password_1;
                            //cancel = true;
                            Toast.makeText(SplashActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }

                        if (password2.isEmpty()) {
                            //edt_password_2.setError("비밀번호를 다시 입력해주세요.");
                            //focusView = edt_password_2;
                            //cancel = true;
                            Toast.makeText(SplashActivity.this, "비밀번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }

                        if (!(password1.equals(password2))) {
                            //edt_password_2.setError("비밀번호가 다릅니다.");
                            //focusView = edt_password_2;
                            //cancel = true;
                            Toast.makeText(SplashActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                        }

                        if (cancel) {
                            focusView.requestFocus();
                        } else {
                            startJoin(new JoinData(name, id, password1));
                        }

                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SplashActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                System.out.println(result.get_status());
                System.out.println(result.check_status());
                if (result.check_status()){
                    String signin_status = data.get_name() + "님 환영합니다! 로그인을 진행해주세요.";
                    Toast.makeText(SplashActivity.this,signin_status , Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(SplashActivity.this, "중복된 아이디입니다. 다른 아이디를 사용해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "회원가입 실패.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
