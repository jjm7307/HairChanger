package com.example.a2020falll_deep_fake.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.Class.ClassPhoto;
import com.example.a2020falll_deep_fake.Class.ClassPhotoList;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.SplashActivity;

import java.util.List;

public class RecyclerViewAdapter_1 extends RecyclerView.Adapter<RecyclerViewAdapter_1.ViewHolder>{
    private Context mContext;
    private List<ClassPhoto> mList;
    private LayoutInflater inflater;

    public RecyclerViewAdapter_1(Context context, List<ClassPhoto> list) {
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImageView heart;
        ImageView photo;

        photo = holder.photo;
        heart = holder.heart;

        if(mList.get(position).getLike()){
            heart.setVisibility(View.VISIBLE);
        }
        else{
            heart.setVisibility(View.INVISIBLE);
        }
        int drawableResourceId = mContext.getResources().getIdentifier(mList.get(position).getName(), "drawable", mContext.getPackageName());
        photo.setImageResource(drawableResourceId);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        ImageView heart;
        FrameLayout bt_heart;


        public ViewHolder(View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            heart = itemView.findViewById(R.id.heart);
            bt_heart = itemView.findViewById(R.id.bt_heart);

            bt_heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        //Implementation
                        mList.get(position).setLike();
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });

            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_photo, null);
                        ImageView dialog_photo = (ImageView) dialogView.findViewById(R.id.dialog_photo);
                        ImageView dialog_heart = (ImageView) dialogView.findViewById(R.id.dialog_heart);
                        TextView dialog_num = (TextView) dialogView.findViewById(R.id.dialog_num);
                        TextView tag_gender = (TextView) dialogView.findViewById(R.id.tag_gender);
                        TextView tag_length = (TextView) dialogView.findViewById(R.id.tag_length);
                        int photo_drawableResourceId = mContext.getResources().getIdentifier(mList.get(position).getName(), "drawable", mContext.getPackageName());
                        dialog_photo.setImageResource(photo_drawableResourceId);
                        if (mList.get(position).getLike()){
                            dialog_heart.setImageResource(R.drawable.heart_2);
                        }
                        else{
                            dialog_heart.setImageResource(R.drawable.heart_empty_2);

                        }
                        dialog_num.setText(Integer.toString(mList.get(position).getLikeNum()));
                        if (mList.get(position).getGender() == "male"){
                            tag_gender.setText("#투블럭컷");
                        }
                        else{
                            tag_gender.setText("#레이어드컷");
                        }
                        if (mList.get(position).getLength() == "long"){
                            tag_length.setText("#내츄럴펌");
                        }
                        else{
                            tag_length.setText("#레이어드펌");
                        }

                        dialog.setView(dialogView);
                        final AlertDialog alertDialog = dialog.create();
                        alertDialog.show();
                    }

                }});
        }
    }
}
