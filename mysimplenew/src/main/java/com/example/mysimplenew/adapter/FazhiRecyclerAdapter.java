package com.example.mysimplenew.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ScrollingTabContainerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysimplenew.ContentActivity;
import com.example.mysimplenew.ContentActivityTwo;
import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.entity.FaZhiNews;
import com.example.mysimplenew.entity.NewsEntity;

import java.util.List;

/**
 * Created by 红超 on 2017/3/25.
 */

public class FazhiRecyclerAdapter extends BaseAdapter {

    Activity context;
    List<FaZhiNews.T1348648037603Bean> newslist;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate;
        if (viewType==0){
            inflate = View.inflate(context, R.layout.newscontentitem, null);

            return new MyViewHolder(inflate);
        }else {
            inflate = View.inflate(context, R.layout.threephotos, null);

            return new MyViewHolderThree(inflate);
        }
    }

    public FazhiRecyclerAdapter(Activity context,  List<FaZhiNews.T1348648037603Bean> newslist) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.MyToast(context,"dianji");
                Intent intent =new Intent(context, ContentActivityTwo.class);
                intent.putExtra("title",newslist.get(position).getTitle());
                intent.putExtra("url",newslist.get(position).getDocid());
                intent.putExtra("imagesrc",newslist.get(position).getImgsrc());
                context.startActivity(intent);
            }
        });
        if (itemViewType==0){
            MyViewHolder myHolder = (MyViewHolder) holder;
            String ctime = newslist.get(position).getLmodify();
            String substring = ctime.substring(5, 16);
            myHolder.title.setText(newslist.get(position).getTitle());
            myHolder.time.setText(substring);
            String url = newslist.get(position).getImgsrc();
            LogUtil.MyLog(this,url);
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
}
