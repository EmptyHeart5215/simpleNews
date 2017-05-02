package com.example.mysimplenew.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.adapter.FazhiRecyclerAdapter;
import com.example.mysimplenew.entity.FaZhiNews;
import com.example.mysimplenew.fragment.NewsContentFragment;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/29.
 */

public class FaZhiFragment extends NewsContentFragment {
    List<FaZhiNews.T1348648037603Bean> allNewsList=new ArrayList<>();
    private FazhiRecyclerAdapter adapter;
    FaZhiNews faZhiNews ;

    @Override
    public void initData(final int page, final SwipeRefreshLayout refreshLayout, final RecyclerView recyclerView) {
        GetDataUtils.GetNews("T1348648037603", 20, page,
                new NewInterface() {
                @Override
                public void Secuss(String newsEntity) {

                    LogUtil.MyLog("TAG", "Secuss: "+newsEntity );
                    faZhiNews= new Gson().fromJson(newsEntity,FaZhiNews.class);
                    if (refreshLayout.isRefreshing())
                        allNewsList.clear();
                    final List<FaZhiNews.T1348648037603Bean> newslist = faZhiNews.getT1348648037603();
                    allNewsList.addAll(newslist);
                    LogUtil.MyLog("TAG", "Secuss: " + newslist);
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (page == 1) {
                                adapter = new FazhiRecyclerAdapter(getActivity(), allNewsList);
                                recyclerView.setAdapter(adapter);
                            } else {
                                recyclerView.getAdapter().notifyDataSetChanged();
                                refreshLayout.setRefreshing(false);
                            }
                        }
                    });
                }


                @Override
                public void Error() {

                }

        });
    }




}
