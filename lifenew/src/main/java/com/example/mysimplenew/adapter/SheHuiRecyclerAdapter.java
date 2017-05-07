package com.example.mysimplenew.adapter;

import android.app.Activity;
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
import com.example.mysimplenew.entity.NewsEntity;
import com.example.mysimplenew.entity.SheHuiNews;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by 红超 on 2017/3/25.
 */

public class SheHuiRecyclerAdapter extends BaseAdapter{

    Context context;
    List<SheHuiNews.T1467284926140Bean> newslist;

    public SheHuiRecyclerAdapter(Context context, List<SheHuiNews.T1467284926140Bean> newslist) {
        this.context = context;
        this.newslist = newslist;
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
                Intent intent =new Intent(context, ContentActivityTwo.class);
                intent.putExtra("imagesrc",newslist.get(position).getImgsrc());
                intent.putExtra("title",newslist.get(position).getTitle());
                intent.putExtra("url",newslist.get(position).getDocid());
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String url="http://c.m.163.com/news/a/"+newslist.get(position).getDocid()+".html";

                UMWeb web = new UMWeb(url);

                UMImage image = new UMImage(context, newslist.get(position).getImgsrc());//网络图片
                UMImage image2 = new UMImage(context, newslist.get(position).getImgsrc());//网络图片
                web.setTitle(newslist.get(position).getTitle());//标题
                web.setThumb(image);  //缩略图
                web.setDescription(newslist.get(position).getDigest());//描述

                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.SINA)
                        .withText(newslist.get(position).getTitle())
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();

                return true;
            }
        });


        if (itemViewType==0){
            MyViewHolder myHolder = (MyViewHolder) holder;
            myHolder.title.setText(newslist.get(position).getTitle());
            String ctime = newslist.get(position).getLmodify();
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


}
