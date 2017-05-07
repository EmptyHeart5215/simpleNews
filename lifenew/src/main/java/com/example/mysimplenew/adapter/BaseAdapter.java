package com.example.mysimplenew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.ToastUtil;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

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
        setOnLongClickListener(holder);
    }
    public boolean setOnLongClickListener(RecyclerView.ViewHolder holder){
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("itemonLongClick", "onLongClick: " );
                return true;
            }
        });
        return true;
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, time;
        ImageView photo;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.itemtitle);
            photo = (ImageView) itemView.findViewById(R.id.itemimage);
            time = (TextView) itemView.findViewById(R.id.itemtime);
        }
    }
    public class MyViewHolderThree extends RecyclerView.ViewHolder {

        TextView title;
        ImageView photoone,phototwo,photothree;

        public MyViewHolderThree(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_three_text);
            photoone = (ImageView) itemView.findViewById(R.id.item_three_image_one);
            phototwo = (ImageView) itemView.findViewById(R.id.item_three_image_two);
            photothree = (ImageView) itemView.findViewById(R.id.item_three_image_three);

        }
    }
    public UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

//            Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };
}
