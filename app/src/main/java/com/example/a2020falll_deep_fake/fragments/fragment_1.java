package com.example.a2020falll_deep_fake.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a2020falll_deep_fake.R;
import com.gun0912.tedpermission.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class fragment_1 extends Fragment {

//    /* --- Geon Code --- */
//    private View v;
//    /* --- Geon Code --- */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//
//        v = inflater.inflate(R.layout.fragment_1, container, false);
//        /* --- Geon for camera --- */
//        getView().findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (intent.resolveActivity(getActivity().getPackageManager()) != null){
//                    File photoFile = null;
//                    try {
//                        photoFile = createImageFile();
//                    }
//                    catch (IOException e){
//
//                    }
//                    if (photoFile != null){
//                        photoUri = FileProvider.getUriForFile(getActivity().getApplicationContext(), getActivity().getPackageName(), photoFile);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
//                    }
//                }
//            }
//        });
//        /* --- Geon for camera --- */

//        v.findViewById(R.id.iv_result).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // hint 버튼 누르면 BFragment로 replace
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                CameraFragment cameraFragment = new CameraFragment();
//                fragmentTransaction.replace(R.id.fragmenta, cameraFragment);
//                fragmentTransaction.commitAllowingStateLoss();
//            }
//        });


        return inflater.inflate(R.layout.fragment_1, container, false);
    }

//    /* --- Geon for camera --- */
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "Test_" + timeStamp + "_";
//        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,
//                ".jpg",
//                storageDir
//        );
//        imageFilePath = image.getAbsolutePath();
//        return image;
//    }

//    @SuppressLint("MissingSuperCall")
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
//            ExifInterface exif = null;
//            try {
//                exif = new ExifInterface(imageFilePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            int exifOrientation;
//            int exifDegree;
//
//            if (exif != null) {
//                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//                exifDegree = exifOrientationDegress(exifOrientation);
//
//            } else {
//                exifDegree = 0;
//            }
//            ((ImageView) getView().findViewById(R.id.iv_result)).setImageBitmap(rotate(bitmap, exifDegree));
//
//        }
//    }
//
//    private int exifOrientationDegress(int exifOrientation){
//        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
//            return 90;
//        }
//        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
//            return 180;
//        }
//        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
//            return 270;
//        }
//        return 0;
//    }
//
//    private Bitmap rotate(Bitmap bitmap, float degree) {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(degree);
//        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//    }
//
//    PermissionListener permissionListener = new PermissionListener() {
//        @Override
//        public void onPermissionGranted() {
//            Toast.makeText(getActivity().getApplicationContext(), "allow the permission", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//            Toast.makeText(getActivity().getApplicationContext(), "deny the permission", Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    /* --- Geon for camera --- */


}
