package com.example.mysimplenew.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.entity.NewsEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by 红超 on 2017/3/25.
 */

public class NewsRecyclerAdapter extends BaseAdapter{

    Context context;
    List<NewsEntity.T1348649503389Bean> newslist;

    public NewsRecyclerAdapter(Context context, List<NewsEntity.T1348649503389Bean> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.newscontentitem, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==0){
            MyViewHolder myHolder = (MyViewHolder) holder;
            myHolder.title.setText(newslist.get(position).getTitle());
            String ctime = newslist.get(position).getImgsrc();
            String substring = ctime.substring(5, 16);
            myHolder.time.setText(substring);

//        holder.time.setText("shijian");
            String url = newslist.get(position).getImgsrc();
            LogUtil.MyLog("TAG", "onBindViewHolder: " + url);
            Glide.with(context).load(url).into(myHolder.photo);
        }else {
            MyViewHolderThree holderThree = (MyViewHolderThree) holder;
            holderThree.title.setText(newslist.get(position).getTitle());
            Glide.with(context).load(newslist.get(position).getImgsrc()).into(holderThree.photoone);
            Glide.with(context).load(newslist.get(position).getImgextra().get(0).getImgsrc()).into(holderThree.phototwo);
            Glide.with(context).load(newslist.get(position).getImgextra().get(1).getImgsrc()).into(holderThree.photothree);
        }
    }

    @Override
    public int getItemCount() {
        return newslist.size();
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
}
