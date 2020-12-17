package com.example.a2020falll_deep_fake.picture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.hardware.Camera;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.login.LoginData;
import com.example.a2020falll_deep_fake.login.LoginResponse;
import com.example.a2020falll_deep_fake.login.JoinData;
import com.example.a2020falll_deep_fake.login.JoinResponse;
import com.example.a2020falll_deep_fake.login.RetrofitClient;
import com.example.a2020falll_deep_fake.login.ServiceApi;

public class PictureAcivity extends AppCompatActivity  {

    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView check_1,check_2,check_3;
    public ImageView back_1,back_2,back_3;
    public ImageView lock_1,lock_2;
    public ImageView guideline;
    public CameraSurfaceView surfaceView;
    File file1;
    File file2;
    File file3;
    File storage;
    String fileName1;
    String fileName2;
    String fileName3;

    int picture_count;
    Boolean user_premium;
    private MyApplication myApp;

    private Uri uri1;
    private Uri uri2;
    private Uri uri3;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("check1");
        super.onCreate(savedInstanceState);
        //System.out.println("check2");
        setContentView(R.layout.activity_picture);
        //System.out.println("check3");
        //File sdcard = Environment.getExternalStorageDirectory();
        //file1 = new File(sdcard, "capture.jpg");

        storage = getCacheDir();
        fileName1 = "face1.jpg";
        fileName2 = "face2.jpg";
        fileName3 = "face3.jpg";
        picture_count = 1;

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        lock_1 = findViewById(R.id.image_2_lock);
        lock_2 = findViewById(R.id.image_3_lock);

        back_1 = findViewById(R.id.image_1_back);
        back_2 = findViewById(R.id.image_2_back);
        back_3 = findViewById(R.id.image_3_back);

        check_1 = findViewById(R.id.icon_check1);
        check_2 = findViewById(R.id.icon_check2);
        check_3 = findViewById(R.id.icon_check3);

        guideline = findViewById(R.id.guide_line);

        surfaceView = findViewById(R.id.surfaceview);
        ImageView button1 = findViewById(R.id.bt_picture);
        ImageView button2 = findViewById(R.id.bt_send);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        myApp = (MyApplication) getApplication();
        if (myApp.gget_type() == "프리미엄 회원"){
            user_premium = true;
            lock_1.setVisibility(View.INVISIBLE);
            lock_2.setVisibility(View.INVISIBLE);
        } else{
            user_premium = false;
            lock_1.setVisibility(View.VISIBLE);
            lock_2.setVisibility(View.VISIBLE);
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (picture_count){
                    case 1:
                        back_1.setVisibility(View.INVISIBLE);
                        check_1.setVisibility(View.INVISIBLE);
                        check_2.setVisibility(View.VISIBLE);
                        guideline.setImageResource(R.drawable.guide_line_2);
                        capture(image1, 1);
                        picture_count++;
                        if (!user_premium) {
                            guideline.setVisibility(View.INVISIBLE);
                            check_2.setVisibility(View.INVISIBLE);
                            button2.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 2:
                        if (user_premium){
                            guideline.setImageResource(R.drawable.guide_line_3);
                            check_2.setVisibility(View.INVISIBLE);
                            check_3.setVisibility(View.VISIBLE);
                            back_2.setVisibility(View.INVISIBLE);
                            capture(image2, 2);
                            picture_count++;
                        } else{
                            Toast.makeText(PictureAcivity.this, "사진을 더 찍으려면 프리미엄 회원으로 전환해주세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if (user_premium){
                            guideline.setVisibility(View.INVISIBLE);
                            check_3.setVisibility(View.INVISIBLE);
                            back_3.setVisibility(View.INVISIBLE);
                            capture(image3, 3);
                            picture_count++;
                            button2.setVisibility(View.VISIBLE);
                        } else{
                            Toast.makeText(PictureAcivity.this, "사진을 더 찍으려면 프리미엄 회원으로 전환해주세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(PictureAcivity.this, "더 이상 사진을 찍을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_premium){
                    Toast.makeText(PictureAcivity.this, "사진 업로드 시작!", Toast.LENGTH_SHORT).show();
                    upload3Files();
                } else{
                    Toast.makeText(PictureAcivity.this, "사진 업로드 시작!", Toast.LENGTH_SHORT).show();
                    uploadFile();
                }
            }
        });
    }

    public void capture(ImageView now_image, int num){
        surfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                Bitmap bitmap1 = rotateImage(bitmap, -90);
                Bitmap new_bitmap = InversionBitmap(bitmap1, 1);
                Uri new_uri = getImageUri(getApplicationContext(), new_bitmap, num);
                if (num == 1){
                    uri1 = new_uri;
                } else if (num == 2){
                    uri2 = new_uri;
                } else if (num == 3){
                    uri3 = new_uri;
                }
                now_image.setImageBitmap(new_bitmap);
                camera.startPreview();
            }
        });
    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public Bitmap InversionBitmap(Bitmap bitmap, int inverse) {

        Matrix sideInversion = new Matrix();

        if(inverse ==0 )
            sideInversion.setScale(1, 1); // 원본
        else if(inverse == 1)
            sideInversion.setScale(-1, 1);  // 좌우반전
        else if(inverse == 2)
            sideInversion.setScale(1, 1); // 원본
        else
            sideInversion.setScale(1, -1);  // 상하반전

        Bitmap sideInversionImg = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), sideInversion, false);

        return sideInversionImg;
    }

    private Uri getImageUri(Context context, Bitmap inImage, int file_num) {
        //System.out.println("come1");
        OutputStream Fpath = null;
        try {
            if (file_num == 1){
                file1 = new File(context.getCacheDir(), fileName1);
                file1.createNewFile();
                Fpath = new FileOutputStream(file1);
            } else if (file_num == 2){
                file2 = new File(context.getCacheDir(), fileName2);
                file2.createNewFile();
                Fpath = new FileOutputStream(file2);
            } else if (file_num == 3){
                file3 = new File(context.getCacheDir(), fileName3);
                file3.createNewFile();
                Fpath = new FileOutputStream(file3);
            }
            //System.out.println("come2");
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, Fpath);
            Fpath.close();
        } catch (FileNotFoundException e) {
            Log.e("MyTag","FileNotFoundException : " + e.getMessage());
        } catch (IOException e) {
            Log.e("MyTag","IOException : " + e.getMessage());
        }
        System.out.println("come3");
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "IMG_"+ Calendar.getInstance().getTime(), null);
        System.out.println("come4");
        return Uri.parse(path);
    }


    private void upload3Files() {

        RequestBody requestFile1 =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri1)),
                        file1
                );
        RequestBody requestFile2 =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri2)),
                        file2
                );
        RequestBody requestFile3 =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri3)),
                        file3
                );

        MultipartBody.Part body1 =
                MultipartBody.Part.createFormData("face1", file1.getName(), requestFile1);
        MultipartBody.Part body2 =
                MultipartBody.Part.createFormData("face2", file2.getName(), requestFile2);
        MultipartBody.Part body3 =
                MultipartBody.Part.createFormData("face3", file3.getName(), requestFile3);


        // add another part within the multipart request
        String descriptionString = "face";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        myApp = (MyApplication) getApplication();
        String id = myApp.gget_id();
        Intent getIntent = getIntent();
        String code = getIntent.getStringExtra("code");

        //String id = "geon";
        //String code = "123123";
        //Picturedata user_data = new Picturedata(id, code);
        System.out.println(id);
        System.out.println(code);
        service.uploadPicture(description, body1, body2, body3, id, code).enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call,
                                   Response<PictureResponse> response) {
                PictureResponse result = response.body();
                if (result != null) {
                    //Log.v(result.string());
                    Log.v("Upload", "success");
                    if (result.check_status()) {
                        Log.v("Upload", "success");
                        Toast.makeText(PictureAcivity.this, "업로드 성공!", Toast.LENGTH_SHORT).show();
                        file1.delete();
                        file2.delete();
                        file3.delete();
                        finish();
                    } else {
                        Log.v("Upload", "fail");
                        Toast.makeText(PictureAcivity.this, "업로드 실패!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(PictureAcivity.this, "답변이 읎어요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    private void uploadFile() {

        RequestBody requestFile1 =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri1)),
                        file1
                );

        MultipartBody.Part body1 =
                MultipartBody.Part.createFormData("face1", file1.getName(), requestFile1);

        // add another part within the multipart request
        String descriptionString = "face";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        myApp = (MyApplication) getApplication();
        String id = myApp.gget_id();
        Intent getIntent = getIntent();
        String code = getIntent.getStringExtra("code");

        //String id = "geon";
        //String code = "123123";
        //Picturedata user_data = new Picturedata(id, code);
        System.out.println(id);
        System.out.println(code);
        service.uploadPicture(description, body1, body1, body1, id, code).enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call,
                                   Response<PictureResponse> response) {
                PictureResponse result = response.body();
                if (result != null) {
                    //Log.v(result.string());
                    Log.v("Upload", "success");
                    if (result.check_status()) {
                        Log.v("Upload", "success");
                        Toast.makeText(PictureAcivity.this, "업로드 성공!", Toast.LENGTH_SHORT).show();
                        file1.delete();
                        finish();
                    } else {
                        Log.v("Upload", "fail");
                        Toast.makeText(PictureAcivity.this, "업로드 실패!", Toast.LENGTH_SHORT).show();

                    }
                } else{
                    Toast.makeText(PictureAcivity.this, "답변이 읎어요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

}
