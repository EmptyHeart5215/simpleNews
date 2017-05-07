package com.example.mysimplenew.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 红超 on 2017/3/27.
 */

public class SimpViewPagerAdapter extends PagerAdapter {
    Context context;
    List<ImageView> v;
    public SimpViewPagerAdapter(Context context, List<ImageView> v) {
        this.context=context;
        this.v=v;
    }

    @Override
    public int getCount() {
        return v.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       ImageView imageView = v.get(position);
       container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(v.get(position));
    }
}
