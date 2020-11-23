package com.example.a2020falll_deep_fake.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2020falll_deep_fake.R;

import java.util.List;

public class RecyclerViewAdapter_4 extends RecyclerView.Adapter<RecyclerViewAdapter_4.ViewHolder>{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public RecyclerViewAdapter_4(Context context, List<String> list) {
        mList = list;
        mContext = context;
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
                        //Implementation
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });
        }
    }
}
