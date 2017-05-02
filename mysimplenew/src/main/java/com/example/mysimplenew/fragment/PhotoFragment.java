package com.example.mysimplenew.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.adapter.NewsRecyclerAdapter;
import com.example.mysimplenew.adapter.NewsViewPagerAdapter;
import com.example.mysimplenew.entity.JuShiNews;
import com.example.mysimplenew.entity.NewsEntity;
import com.example.mysimplenew.entity.PhotoEntity;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/25.
 */

public class PhotoFragment extends Fragment{

    private RecyclerView recyclerView;
    public String type;
    private ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewGroup == null) {
            viewGroup = (ViewGroup) View.inflate(getActivity(), R.layout.newsblewcontent, null);
            recyclerView = (RecyclerView) viewGroup.findViewById(R.id.newscontentrecycler);
            initData();
//        recyclerView.setAdapter();
        }
        return viewGroup;
    }
    private void initData() {
        GetDataUtils.GetNews("meinv", 20, 1, new NewInterface()  {


            @Override
            public void Secuss(String a) {
//                PhotoEntity photoEntity = new Gson().fromJson(a, PhotoEntity.class);
//
//                NewsEntity a1 = (NewsEntity) a;
//                final List<NewsEntity.T1348649503389Bean> newslist = photoEntity.getT1348649503389();
//                Log.e("TAG", "Secuss: " + newslist);
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
//                        NewsRecyclerAdapter adapter = new NewsRecyclerAdapter(getContext(), newslist);
//                        recyclerView.setLayoutManager(manager);
//                        recyclerView.setAdapter(adapter);
//                    }
//                });
            }

            @Override
            public void Error() {

            }
        });
    }
}
