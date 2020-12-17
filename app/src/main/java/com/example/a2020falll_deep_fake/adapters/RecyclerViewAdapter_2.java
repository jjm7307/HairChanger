package com.example.a2020falll_deep_fake.adapters;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.Class.ClassGallery;
import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.login.RetrofitClient;
import com.example.a2020falll_deep_fake.login.ServiceApi;
import com.example.a2020falll_deep_fake.picture.CompletePictureData;
import com.example.a2020falll_deep_fake.picture.CompletePictureResponse;
import com.example.a2020falll_deep_fake.picture.DeleteData;
import com.example.a2020falll_deep_fake.picture.DeleteResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.viewpager.widget.PagerAdapter.POSITION_NONE;

public class RecyclerViewAdapter_2 extends RecyclerView.Adapter<RecyclerViewAdapter_2.ViewHolder>{
    private Context mContext;
    private List<ClassGallery> mList;
    private LayoutInflater inflater;
    private String uid;
    private ServiceApi service;
    private MyApplication myApp;
    private Bitmap download_picture;
    private Fragment mFragment;

    public RecyclerViewAdapter_2(Context context, List<ClassGallery> list, String user_id, Fragment fragment) {
        mList = list;
        mContext = context;
        uid = user_id;
        mFragment = fragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImageView photo;
        ImageView loading_background;
        TextView loading_1, loading_2;
        FrameLayout bt_photo;

        photo = holder.photo;
        loading_1 = holder.loading_1;
        loading_2 = holder.loading_2;
        loading_background = holder.loading_background;
        bt_photo = holder.bt_photo;
        photo.setImageResource(mContext.getResources().getIdentifier(mList.get(position).getName(), "drawable", mContext.getPackageName()));

        loading_1.setText(mList.get(position).getStepName());
        loading_2.setText(mList.get(position).getStepNum());
        loading_background.setImageResource(mContext.getResources().getIdentifier(mList.get(position).getStepBackground(), "drawable", mContext.getPackageName()));

        System.out.println("check click");
        System.out.println(mList.get(position).isFinished());
        if (mList.get(position).isFinished()){
            bt_photo.setClickable(true);
        }
        else{
            bt_photo.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView loading_1, loading_2;
        ImageView loading_background;
        FrameLayout bt_photo;


        public ViewHolder(View itemView) {
            super(itemView);

            bt_photo = itemView.findViewById(R.id.bt_photo);
            photo = itemView.findViewById(R.id.photo);
            loading_1 = itemView.findViewById(R.id.loading_text);
            loading_2 = itemView.findViewById(R.id.loading_step);
            loading_background = itemView.findViewById(R.id.loading_background);

            bt_photo.setClickable(false);
            bt_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Toast.makeText(mContext, "서버에서 파일을 가져오는 중입니다...", Toast.LENGTH_SHORT).show();
                        request_and_update(position, v);
                    }
                }
            });
        }
    }

    private void request_and_update(int pos, View v) {

        CompletePictureData user_data = new CompletePictureData(uid, mList.get(pos).getRid());
        service.requestPicture(user_data).enqueue(new Callback<CompletePictureResponse>() {
            @Override
            public void onResponse(Call<CompletePictureResponse> call,
                                   Response<CompletePictureResponse> response) {
                CompletePictureResponse result = response.body();
                if (result != null) {
                    //Log.v(result.string());
                    Log.v("Download", "success");
                    if (result.check_status()) {
                        download_picture = StringToBitmap(result.get_picture());

                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_gallery, null);

                        ImageView dialog_photo = (ImageView) dialogView.findViewById(R.id.dialog_photo);
                        //dialog_photo.setImageResource(mContext.getResources().getIdentifier(mList.get(position).getName(), "drawable", mContext.getPackageName()));
                        dialog_photo.setImageBitmap(download_picture);

                        ImageView dialog_download = (ImageView) dialogView.findViewById(R.id.dialog_download);
                        ImageView dialog_share = (ImageView) dialogView.findViewById(R.id.dialog_share);
                        ImageView dialog_delete = (ImageView) dialogView.findViewById(R.id.dialog_delete);

                        dialog.setView(dialogView);
                        final AlertDialog alertDialog = dialog.create();

                        dialog_download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v){
                                save_to_gallary();
                            }
                        });
                        dialog_delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v){
                                delete_picture(pos, alertDialog);
                                //fragment2.updateGalleryList()
                            }
                        });

                        alertDialog.show();

                        Toast.makeText(mContext, "최종 이미지 다운로드 성공!", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(mContext, "최종 이미지 다운로드 실패!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "답변이 읎어요.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CompletePictureResponse> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    public void delete_picture(int pos, AlertDialog alertDialog){
        DeleteData user_data = new DeleteData(uid, mList.get(pos).getRid());
        service.requestPicture(user_data).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call,
                                   Response<DeleteResponse> response) {
                DeleteResponse result = response.body();
                if (result != null) {
                    //Log.v(result.string());
                    Log.v("Download", "success");
                    if (result.check_status()) {

                        System.out.println(pos);
                        System.out.println(mList.size());
                        mList.remove(pos);
                        System.out.println(mList.size());
                        notifyItemChanged(pos);
                        //notifyItemRangeChanged(pos, mList.size());
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                        Toast.makeText(mContext, "이미지가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(mContext, "이미지 삭제 실패!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "답변이 읎어요.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    public void save_to_gallary(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date nowdate = new Date();
        String dateString = formatter.format(nowdate);
        String file_name = dateString + ".jpg";

        saveFile(file_name, download_picture);

        Toast.makeText(mContext, "이미지가 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

    public static byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        return baos.toByteArray();
    }


    private void saveFile(String file_name, Bitmap bitmap) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, file_name);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/*");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.IS_PENDING, 1);
        }

        ContentResolver contentResolver = mContext.getContentResolver();
        Uri item = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        try {
            ParcelFileDescriptor pdf = contentResolver.openFileDescriptor(item, "w", null);

            if (pdf == null) {
                Log.d("fail", "null");
            } else {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                FileOutputStream fos = new FileOutputStream(pdf.getFileDescriptor());

                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                byte[] bitmapdata = bos.toByteArray();
                fos.write(bitmapdata);
                fos.close();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    values.clear();
                    values.put(MediaStore.Images.Media.IS_PENDING, 0);
                    contentResolver.update(item, values, null, null);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
