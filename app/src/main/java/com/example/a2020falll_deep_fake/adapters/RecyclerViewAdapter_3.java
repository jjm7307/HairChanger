package com.example.a2020falll_deep_fake.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.Class.Store_data;
import com.example.a2020falll_deep_fake.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class RecyclerViewAdapter_3 extends RecyclerView.Adapter<RecyclerViewAdapter_3.ViewHolder> {
    private Context mContext;
    private List<Store_data> mList;
    private LayoutInflater inflater;
    private GoogleMap googleMap;
    private Activity mActivity;

    private static final int REQUEST_CODE = 0;
    /*EventListener listener;

    public interface EventListener {
        void goToLocationZoom(double lat, double lng, float zoom);
    }*/

    public RecyclerViewAdapter_3(Context context, List<Store_data> list, GoogleMap g_googleMap, Activity activity) {
        mList = list;
        mContext = context;
        googleMap = g_googleMap;
        mActivity = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_3, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView store_name;
        TextView store_star;
        TextView store_cut;
        ImageView store_picture;

        store_name = holder.store_name;
        store_star = holder.store_star;
        store_cut = holder.store_cut;
        store_picture = holder.store_picture;

        store_name.setText(mList.get(position).get_name());
        store_star.setText(mList.get(position).get_star());
        store_cut.setText(mList.get(position).get_price());

        switch (position){
            case 0:
                store_picture.setImageResource(R.drawable.hairshop_1);
                break;
            case 1:
                store_picture.setImageResource(R.drawable.hairshop_2);
                break;
            case 2:
                store_picture.setImageResource(R.drawable.hairshop_3);
                break;
            case 3:
                store_picture.setImageResource(R.drawable.hairshop_4);
                break;
            case 4:
                store_picture.setImageResource(R.drawable.hairshop_5);
                break;
            default:
                store_picture.setImageResource(R.drawable.test_photo);
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView store_name;
        TextView store_star;
        TextView store_cut;

        ConstraintLayout store_block;
        ImageView store_picture;

        public ViewHolder(View itemView) {
            super(itemView);

            store_name = itemView.findViewById(R.id.store_name);
            store_star = itemView.findViewById(R.id.store_star);
            store_cut = itemView.findViewById(R.id.store_cut);

            store_block = itemView.findViewById(R.id.store_block);
            store_picture = itemView.findViewById(R.id.store_picture);

            //store_name.setText(mList.get(getAdapterPosition()).get_name());

            store_picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        //Implementation
                        goToLocationZoom(mList.get(position).get_lat(), mList.get(position).get_lng(), 16);
                    }
                }
            });

            store_block.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        //Implementation
                        //notifyItemChanged(getAdapterPosition());
                        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View v_dialog = inflater.inflate(R.layout.dialog_storecall, null);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);

                        ImageView dialog_photo = v_dialog.findViewById(R.id.d_store_picture);
                        TextView dialog_name = v_dialog.findViewById(R.id.d_store_name);
                        TextView dialog_num = v_dialog.findViewById(R.id.d_store_phone);
                        TextView dialog_star = v_dialog.findViewById(R.id.d_store_star);

                        switch (position){
                            case 0:
                                dialog_photo.setImageResource(R.drawable.hairshop_1);
                                break;
                            case 1:
                                dialog_photo.setImageResource(R.drawable.hairshop_2);
                                break;
                            case 2:
                                dialog_photo.setImageResource(R.drawable.hairshop_3);
                                break;
                            case 3:
                                dialog_photo.setImageResource(R.drawable.hairshop_4);
                                break;
                            case 4:
                                dialog_photo.setImageResource(R.drawable.hairshop_5);
                                break;
                            default:
                                dialog_photo.setImageResource(R.drawable.test_photo);
                                break;
                        }

                        dialog_name.setText(mList.get(position).get_name());
                        dialog_num.setText(mList.get(position).get_num());
                        dialog_star.setText(mList.get(position).get_star());

                        ImageView bt_3 = v_dialog.findViewById(R.id.bt_3);
                        TextView text_3 = v_dialog.findViewById(R.id.text_3);
                        ImageView bt_4 = v_dialog.findViewById(R.id.bt_4);
                        TextView text_4 = v_dialog.findViewById(R.id.text_4);
                        ImageView bt_6 = v_dialog.findViewById(R.id.bt_6);
                        TextView text_6 = v_dialog.findViewById(R.id.text_6);
                        CheckBox checkBox = v_dialog.findViewById(R.id.check_photo);
                        CardView bt_reserve = v_dialog.findViewById(R.id.bt_reserve);
                        bt_reserve.setEnabled(false);
                        bt_3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bt_reserve.setEnabled(true);
                                bt_3.setImageResource(R.drawable.bt_reserve_2);
                                text_3.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                                bt_4.setImageResource(R.drawable.bt_reserve);
                                text_4.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                                bt_6.setImageResource(R.drawable.bt_reserve);
                                text_6.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                            }
                        });
                        bt_4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bt_reserve.setEnabled(true);
                                bt_4.setImageResource(R.drawable.bt_reserve_2);
                                text_4.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                                bt_3.setImageResource(R.drawable.bt_reserve);
                                text_3.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                                bt_6.setImageResource(R.drawable.bt_reserve);
                                text_6.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                            }
                        });
                        bt_6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bt_reserve.setEnabled(true);
                                bt_6.setImageResource(R.drawable.bt_reserve_2);
                                text_6.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                                bt_3.setImageResource(R.drawable.bt_reserve);
                                text_3.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                                bt_4.setImageResource(R.drawable.bt_reserve);
                                text_4.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                            }
                        });

                        AlertDialog alertDialog = dialog.setView(v_dialog).create();

                        bt_reserve.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });

                        alertDialog.show();
                    }
                }
            });
        }
    }
    public void goToLocationZoom(double lat, double lng, float zoom) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom);
        googleMap.animateCamera(cameraUpdate);
    }
}
