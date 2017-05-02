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
import com.example.mysimplenew.entity.JuShiNews;
import com.example.mysimplenew.entity.NewsEntity;

import java.util.List;

/**
 * Created by 红超 on 2017/3/25.
 */

public class JuShiRecyclerAdapter extends BaseAdapter{

    Context context;
    List<JuShiNews.T1348648141035Bean> newslist;

    public JuShiRecyclerAdapter(Context context, List<JuShiNews.T1348648141035Bean> newslist) {
        this.context = context;
        this.newslist = newslist;
    }
    @Override
    public int getItemViewType(int position) {
        if (newslist.get(position).getImgextra()!=null){
            return 1;
        }else {
            return 0;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            return new MyViewHolder(View.inflate(context, R.layout.newscontentitem, null));
        }else {
            return new MyViewHolderThree(View.inflate(context,R.layout.threephotos,null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.MyToast(context,"点击");
                Intent intent =new Intent(context, ContentActivityTwo.class);
                intent.putExtra("title",newslist.get(position).getTitle());
                intent.putExtra("url",newslist.get(position).getDocid());
                intent.putExtra("imagesrc",newslist.get(position).getImgsrc());
                context.startActivity(intent);
            }
        });
        if (itemViewType==0){
            MyViewHolder myHolder = (MyViewHolder) holder;
            myHolder.title.setText(newslist.get(position).getTitle());
            String ctime = newslist.get(position).getLmodify();
            String substring = ctime.substring(5, 16);
            myHolder.time.setText(substring);
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
