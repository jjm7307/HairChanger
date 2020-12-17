package com.example.a2020falll_deep_fake.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.Class.ClassPhoto;
import com.example.a2020falll_deep_fake.MainActivity;
import com.example.a2020falll_deep_fake.MyApplication;
import com.example.a2020falll_deep_fake.R;
import com.example.a2020falll_deep_fake.picture.PictureAcivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.List;

public class RecyclerViewAdapter_1 extends RecyclerView.Adapter<RecyclerViewAdapter_1.ViewHolder>{
    private Context mContext;
    private Activity mActivity;
    private List<ClassPhoto> mList;
    private LayoutInflater inflater;
    private Boolean look_ad = false;

    private RewardedAd rewardedAd;
    private boolean isLoading;
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";//"ca-app-pub-9136842473787253/3944248747";


    public RecyclerViewAdapter_1(Context context, List<ClassPhoto> list, Activity activity) {
        mList = list;
        mContext = context;
        mActivity = activity;

        rewardedAd = new RewardedAd(mContext, AD_UNIT_ID);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
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
                        MyApplication myApp = (MyApplication) mContext.getApplicationContext();
                        myApp.setLike(mList.get(position).getGender(),mList.get(position).getLength());

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
                        TextView bt_start = (TextView) dialogView.findViewById(R.id.bt_start);

                        TextView tag_1 = (TextView) dialogView.findViewById(R.id.tag_1);
                        TextView tag_2 = (TextView) dialogView.findViewById(R.id.tag_2);

                        int photo_drawableResourceId = mContext.getResources().getIdentifier(mList.get(position).getName(), "drawable", mContext.getPackageName());
                        dialog_photo.setImageResource(photo_drawableResourceId);
                        if (mList.get(position).getLike()){
                            dialog_heart.setImageResource(R.drawable.heart_2);
                        }
                        else {
                            dialog_heart.setImageResource(R.drawable.heart_empty_2);

                        }
                        dialog_num.setText(Integer.toString(mList.get(position).getLikeNum()));
                        tag_1.setText(mList.get(position).getTag1());
                        tag_2.setText(mList.get(position).getTag2());

                        dialog_heart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v){
                                mList.get(position).setLike();
                                MyApplication myApp = (MyApplication) mContext.getApplicationContext();
                                myApp.setLike(mList.get(position).getGender(),mList.get(position).getLength());
                                dialog_num.setText(Integer.toString(mList.get(position).getLikeNum()));
                                if (mList.get(position).getLike()){
                                    dialog_heart.setImageResource(R.drawable.heart_2);
                                }
                                else {
                                    dialog_heart.setImageResource(R.drawable.heart_empty_2);

                                }
                                notifyItemChanged(getAdapterPosition());

                            }
                        });
                        bt_start.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyApplication myApp = (MyApplication) mContext.getApplicationContext();
                                if (myApp.gget_type() == "일반 회원") {
                                    showRewardedVideo(v, position);
                                }
                                else {
                                    Intent intent1 = new Intent(v.getContext(), PictureAcivity.class);
                                    intent1.putExtra("code", mList.get(position).getName());
                                    //System.out.println("check01");
                                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    //System.out.println("check10");
                                    v.getContext().startActivity(intent1);
                                }
                            }
                        });

                        dialog.setView(dialogView);
                        final AlertDialog alertDialog = dialog.create();
                        alertDialog.show();
                    }
                }
            });
        }
    }
    public RewardedAd createAndLoadRewardedAd() {
        RewardedAd rewardedAd = new RewardedAd(mContext,AD_UNIT_ID);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }
    private void showRewardedVideo(View v, Integer position) {
        if (rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {
                    // Ad opened.
                }

                @Override
                public void onRewardedAdClosed() {
                    rewardedAd = createAndLoadRewardedAd();
                    if (look_ad){
                        look_ad = false;
                        Intent intent1 = new Intent(v.getContext(), PictureAcivity.class);
                        intent1.putExtra("code", mList.get(position).getName());
                        //System.out.println("check01");
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //System.out.println("check10");
                        v.getContext().startActivity(intent1);
                    }
                }

                @Override
                public void onUserEarnedReward(RewardItem rewardItem) {
                    // User earned reward.)
                    look_ad = true;
                }

                @Override
                public void onRewardedAdFailedToShow(int errorCode) {
                    // Ad failed to display
                }
            };
            rewardedAd.show(mActivity, adCallback);
        }
    }

}
