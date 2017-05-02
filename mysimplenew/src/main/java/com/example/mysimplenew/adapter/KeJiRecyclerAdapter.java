package com.example.mysimplenew.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysimplenew.ContentActivity;
import com.example.mysimplenew.ContentActivityTwo;
import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.entity.KeJiNews;


import java.util.List;

/**
 * Created by 红超 on 2017/3/25.
 */

public class KeJiRecyclerAdapter extends BaseAdapter {

    Context context;
    List<KeJiNews.T1351233117091Bean> newslist;

    public KeJiRecyclerAdapter(Context context, List<KeJiNews.T1351233117091Bean> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(context, R.layout.newscontentitem, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.MyToast(context, "dianji ");
                Intent intent =new Intent(context, ContentActivityTwo.class);
                intent.putExtra("title",newslist.get(position).getTitle());
                intent.putExtra("url",newslist.get(position).getDocid());
                intent.putExtra("imagesrc",newslist.get(position).getImgsrc());
                context.startActivity(intent);
            }
        });
            MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.title.setText(newslist.get(position).getTitle());
        String ctime = newslist.get(position).getLmodify();
            String substring = ctime.substring(5, 16);
            myHolder.time.setText(substring);
            String url = newslist.get(position).getImgsrc();
            LogUtil.MyLog("TAG", "onBindViewHolder: " + url);
            Glide.with(context).load(url).into(myHolder.photo);

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

}
