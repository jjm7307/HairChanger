package com.example.a2020falll_deep_fake.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.SplashActivity;
import com.example.a2020falll_deep_fake.fragments.fragment_3;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.EventListener;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.a2020falll_deep_fake.fragments.fragment_3.*;

public class RecyclerViewAdapter_3 extends RecyclerView.Adapter<RecyclerViewAdapter_3.ViewHolder> {
    private Context mContext;
    private List<Store_data> mList;
    private LayoutInflater inflater;
    private GoogleMap googleMap;

    /*EventListener listener;

    public interface EventListener {
        void goToLocationZoom(double lat, double lng, float zoom);
    }*/

    public RecyclerViewAdapter_3(Context context, List<Store_data> list, GoogleMap g_googleMap) {
        mList = list;
        mContext = context;
        googleMap = g_googleMap;
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

        TextView dialog_name;
        TextView dialog_num;

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
                        final View v_signup = inflater.inflate(R.layout.dialog_storecall, null);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                        dialog_name = v_signup.findViewById(R.id.d_store_name);
                        dialog_num = v_signup.findViewById(R.id.d_store_phone);
                        dialog_name.setText(mList.get(position).get_name());
                        dialog_num.setText(mList.get(position).get_num());
                        dialog.setView(v_signup)
                                .setPositiveButton("예약하기", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mContext, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .create()
                                .show();
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
