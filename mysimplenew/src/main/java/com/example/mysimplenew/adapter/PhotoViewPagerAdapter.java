package com.example.mysimplenew.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysimplenew.PhotoContent;
import com.example.mysimplenew.entity.AllNewsContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/31.
 */

public class PhotoViewPagerAdapter extends PagerAdapter {
    List<AllNewsContent.PhotosBean> photos;
    Context context;
    TextView title,describe;

    public PhotoViewPagerAdapter(Context context, List<AllNewsContent.PhotosBean> photos, TextView title, TextView describe) {
        this.context=context;
        this.photos=photos;
        this.title=title;
        this.describe=describe;

    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        title.setText(photos.get(position).getImgtitle());
        describe.setText(photos.get(position).getNote());
        ImageView imageView =new ImageView(context);
        Glide.with(context).load(photos.get(position).getImgurl()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
