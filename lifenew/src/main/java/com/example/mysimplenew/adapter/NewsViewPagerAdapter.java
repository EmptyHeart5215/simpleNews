package com.example.mysimplenew.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysimplenew.fragment.NewsContentFragment;

import java.util.List;

/**
 * Created by 红超 on 2017/3/25.
 */

public class NewsViewPagerAdapter extends FragmentPagerAdapter{
    String[] type ;
    List<Fragment> newsContentFragments;
    public NewsViewPagerAdapter(FragmentManager fm, String[] type, List<Fragment> newsContentFragments) {
        super(fm);
        this.type=type;
        this.newsContentFragments=newsContentFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return newsContentFragments.get(position);
    }

    @Override
    public int getCount() {
        return newsContentFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return type[position];
    }
}

