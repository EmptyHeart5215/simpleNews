package com.example.mysimplenew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysimplenew.R;

/**
 * Created by 红超 on 2017/3/29.
 */

public class BaseAdapter extends RecyclerView.Adapter {

    Context context;
    public Context getContext(Context context){
        this.context=context;
        return this.context;
    };
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, time;
        ImageView photo;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            title = (TextView) itemView.findViewById(R.id.itemtitle);
            photo = (ImageView) itemView.findViewById(R.id.itemimage);
            time = (TextView) itemView.findViewById(R.id.itemtime);
        }
    }
    public class MyViewHolderThree extends RecyclerView.ViewHolder {
        View view;
        TextView title;
        ImageView photoone,phototwo,photothree;

        public MyViewHolderThree(View itemView) {
            super(itemView);
            view=itemView;
            title = (TextView) itemView.findViewById(R.id.item_three_text);
            photoone = (ImageView) itemView.findViewById(R.id.item_three_image_one);
            phototwo = (ImageView) itemView.findViewById(R.id.item_three_image_two);
            photothree = (ImageView) itemView.findViewById(R.id.item_three_image_three);
        }
    }
}
