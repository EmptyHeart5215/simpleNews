package com.example.mysimplenew.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysimplenew.PhotoContent;
import com.example.mysimplenew.R;
import com.example.mysimplenew.entity.PhotoEntity;

import java.security.KeyStore;
import java.util.List;

/**
 * Created by 红超 on 2017/3/28.
 */

public class PhotoThreeRecyclerAdapter extends RecyclerView.Adapter {
    Activity context;
    List<PhotoEntity.TagBean> tag;
    View root;
    public PhotoThreeRecyclerAdapter(View root,Activity context, List<PhotoEntity.TagBean> tag) {
        this.context =context;
        this.tag=tag;
this.root=root;
    }


    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new MyViewHolderone(View.inflate(context, R.layout.photothreeitemone, null));
        } else  if (viewType==1){
            return new MyViewHoldertwo(View.inflate(context, R.layout.photothreeitemtwo, null));

        }else {
            return new MyViewHolderthree(View.inflate(context, R.layout.photothreeitemthree, null));

        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final int itemViewType = getItemViewType(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setid = tag.get(position).getSetid();
                Intent intent =new Intent(context, PhotoContent.class);
                intent.putExtra("setid",setid);
                context.startActivity(intent);
            }
        });
        switch (itemViewType) {
            case 0:
                MyViewHolderone holderone = (MyViewHolderone) holder;
                holderone.textView.setText(tag.get(position).getSetname());
                Glide.with(context).load(tag.get(position).getPics().get(0)).into(holderone.imageView);
                break;
            case 1:
                MyViewHoldertwo holdertwo = (MyViewHoldertwo) holder;
                holdertwo.textView.setText(tag.get(position).getSetname());
                tag.get(position).getPics().get(0);
                Glide.with(context).load(tag.get(position).getPics().get(0)).into(holdertwo.one);

                Glide.with(context).load(tag.get(position).getPics().get(1)).into(holdertwo.two);
                Glide.with(context).load(tag.get(position).getPics().get(2)).into(holdertwo.three);
                break;
            case 2:

                MyViewHolderthree holderthree = (MyViewHolderthree) holder;
                holderthree.textView.setText(tag.get(position).getSetname());
                tag.get(position).getPics().get(0);
                Glide.with(context).load(tag.get(position).getPics().get(0)).into(holderthree.one);

                Glide.with(context).load(tag.get(position).getPics().get(1)).into(holderthree.two);
                Glide.with(context).load(tag.get(position).getPics().get(2)).into(holderthree.three);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return tag.size();
    }

    public class MyViewHolderone extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolderone(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_one_image);
        }
    }

    public class MyViewHoldertwo extends RecyclerView.ViewHolder {
        ImageView one,two,three;
        TextView textView ;
        public MyViewHoldertwo(View itemView) {
            super(itemView);
            one= (ImageView) itemView.findViewById(R.id.item_one);
            two= (ImageView) itemView.findViewById(R.id.item_two);
            three= (ImageView) itemView.findViewById(R.id.item_three);
            textView= (TextView) itemView.findViewById(R.id.item_text);
        }
    }
    public class MyViewHolderthree extends RecyclerView.ViewHolder {
        ImageView one,two,three;
        TextView textView ;
        public MyViewHolderthree(View itemView) {
            super(itemView);
            one= (ImageView) itemView.findViewById(R.id.item_one);
            two= (ImageView) itemView.findViewById(R.id.item_two);
            three= (ImageView) itemView.findViewById(R.id.item_three);
            textView= (TextView) itemView.findViewById(R.id.item_text);
        }
    }


}
