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

public class RecyclerViewAdapter_3 extends RecyclerView.Adapter<RecyclerViewAdapter_3.ViewHolder>{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public RecyclerViewAdapter_3(Context context, List<String> list) {
        mList = list;
        mContext = context;
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

        store_name = holder.store_name;

        store_name.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView store_name;


        public ViewHolder(View itemView) {
            super(itemView);

            store_name = itemView.findViewById(R.id.store_name);

            store_name.setOnClickListener(new View.OnClickListener() {
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
