package com.example.mysimplenew.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysimplenew.R;
import com.example.mysimplenew.adapter.NewsViewPagerAdapter;
import com.example.mysimplenew.fragment.news.JuShiFragment;
import com.example.mysimplenew.fragment.news.KeJiFragment;
import com.example.mysimplenew.fragment.news.LiShiFragment;
import com.example.mysimplenew.fragment.news.QingGanFragment;
import com.example.mysimplenew.fragment.news.SheHuiFragment;
import com.example.mysimplenew.fragment.news.TiYuFragment;
import com.example.mysimplenew.fragment.news.YunKeTangFraagment;

import com.example.mysimplenew.viewpageranimator.ZoomOutPageTransformer;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by 红超 on 2017/3/25.
 */


public class NewsFragment extends Fragment {
    Context context = getActivity();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewGroup newsRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (newsRoot == null) {
            newsRoot = (ViewGroup) View.inflate(getContext(), R.layout.newsfragmentcontent, null);
            initView(newsRoot);
        }
        return newsRoot;
    }

    private void initView(ViewGroup newsRoot) {
        tabLayout = (TabLayout) newsRoot.findViewById(R.id.tablayout_news);
        viewPager = (ViewPager) newsRoot.findViewById(R.id.viewpager_news);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());

        String[] cType = new String[]{
               "社会百事",  "云课堂", "热门焦点","军事天地", "科技大事", "历史回顾", "体育频道"
        };
        //T1348649145984     T1348648037603    T1368497029546    T1348648141035   T1467284926140  T1421997195219  T1350383429665
        //T1351233117091
        String [] eType=new String[]{
                "T1348649503389", "T1348648037603", "T1348648141035","T1467284926140", "T1421997195219","T1350383429665", "T1351233117091"

//                "T1348649503389","T1348648037603","T1368497029546","T1348649503389","T1348649503389","T1348649503389","T1348649503389","T1348649503389"

        };
        List<Fragment> newsContentFragments = new ArrayList<>();
        newsContentFragments.add(new FaZhiFragment());
        newsContentFragments.add(new YunKeTangFraagment());
        newsContentFragments.add(new SheHuiFragment());
        newsContentFragments.add(new JuShiFragment());
        newsContentFragments.add(new KeJiFragment());
        newsContentFragments.add(new LiShiFragment());
        newsContentFragments.add(new TiYuFragment());
        for (int i = 0; i < cType.length; i++) {
//            NewsContentFragment<JuShiNews> fragment = new NewsContentFragment();
//            fragment.type=eType[i];
//            newsContentFragments.add(fragment);
        }
        NewsViewPagerAdapter adapter = new NewsViewPagerAdapter(getFragmentManager(), cType, newsContentFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
