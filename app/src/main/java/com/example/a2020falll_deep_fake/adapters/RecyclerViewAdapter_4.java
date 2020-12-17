package com.example.a2020falll_deep_fake.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.MainActivity;
import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.SplashActivity;
import com.example.a2020falll_deep_fake.picture.PictureAcivity;

import java.util.List;

public class RecyclerViewAdapter_4 extends RecyclerView.Adapter<RecyclerViewAdapter_4.ViewHolder>{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;
    private Fragment mFragment;
    private Activity mActivity;
    private Boolean trial = true;

    public RecyclerViewAdapter_4(Context context, List<String> list, Fragment fragment, Activity activity) {
        mList = list;
        mContext = context;
        mActivity = activity;
        mFragment = fragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_4, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView setting;

        setting = holder.setting;

        setting.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView setting;


        public ViewHolder(View itemView) {
            super(itemView);

            setting = itemView.findViewById(R.id.setting);

            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        if(position == 2){
                            set_membership(v);
                        }
                        else if(position == 8) {
                            Intent intent = new Intent(mActivity, SplashActivity.class);
                            mActivity.startActivity(intent);
                            mActivity.finish();
                        }
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });
        }
    }
    public void set_membership(View v) {
        FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext(),R.style.MyAlertDialogStyle);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_membership, null);
        dialog.setView(dialogView);
        final AlertDialog alertDialog = dialog.create();
        MyApplication myApp = (MyApplication) mContext.getApplicationContext();
        CheckBox checkBox = (CheckBox) dialogView.findViewById(R.id.check_7day);
        CheckBox checkBox2 = (CheckBox) dialogView.findViewById(R.id.check_7day_2);
        TextView bt_start = (TextView) dialogView.findViewById(R.id.bt_start);
        bt_start.setEnabled(false);
        if (myApp.gget_type()=="프리미엄 회원") {
            checkBox.setVisibility(View.INVISIBLE);
            checkBox2.setVisibility(View.VISIBLE);
            bt_start.setText("일반 회원으로 전환");
            checkBox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox2.isChecked()){
                        bt_start.setEnabled(true);
                    }
                    else{
                        bt_start.setEnabled(false);
                    }
                }
            });
            bt_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myApp.gset_type("일반 회원");
                    Toast.makeText(mContext, "일반 회원으로 전환되었습니다.", Toast.LENGTH_SHORT).show();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.detach(mFragment).attach(mFragment).commit();
                    alertDialog.dismiss();
                }
            });
        }
        else{
            checkBox2.setVisibility(View.INVISIBLE);
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()){
                        bt_start.setEnabled(true);
                        bt_start.setText("일주일간 무료 체험하기");
                    }
                    else{
                        bt_start.setEnabled(false);
                        bt_start.setText("프리미엄 회원으로 전환");
                    }
                }
            });
            bt_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myApp.gset_type("프리미엄 회원");
                    Toast.makeText(mContext, "프리미엄 회원으로 전환되었습니다!", Toast.LENGTH_SHORT).show();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.detach(mFragment).attach(mFragment).commit();
                    alertDialog.dismiss();
                }
            });
        }


        alertDialog.show();
    }
}
