package com.example.mysimplenew.fragment.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.adapter.KeJiRecyclerAdapter;
import com.example.mysimplenew.entity.KeJiNews;
import com.example.mysimplenew.fragment.NewsContentFragment;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/29.
 */

public class KeJiFragment extends NewsContentFragment {
    List<KeJiNews.T1351233117091Bean> allNewsList=new ArrayList<>();

    KeJiNews faZhiNews ;
    @Override
    public void initData(final int page, final SwipeRefreshLayout refreshLayout, final RecyclerView recyclerView) {
        GetDataUtils.GetNews("T1351233117091", 20, page,
                new NewInterface() {

                    private KeJiRecyclerAdapter adapter;

                    @Override
                    public void Secuss(String newsEntity) {
                        faZhiNews= new Gson().fromJson(newsEntity,KeJiNews.class);

                        LogUtil.MyLog("TAG", "Secuss: "+newsEntity );
                        if (refreshLayout.isRefreshing())
                            allNewsList.clear();

                        final List<KeJiNews.T1351233117091Bean> newslist = faZhiNews.getT1351233117091();
                        allNewsList.addAll(newslist);
                        LogUtil.MyLog("TAG", "Secuss: " + newslist);
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (page == 1) {

                                    adapter = new KeJiRecyclerAdapter(getContext(), allNewsList);

                                    recyclerView.setAdapter(adapter);

                                } else {
                                    ToastUtil.MyToast(getActivity(), "数据获取成功");
                                    recyclerView.getAdapter().notifyDataSetChanged();
                                    refreshLayout.setRefreshing(false);
                                }
                            }
                        });
                    }


                    @Override
                    public void Error() {
                        Toast.makeText(getActivity(), "数据获取失败请重新获取或检查数据连接！", Toast.LENGTH_SHORT).show();
                    }

                });
    }
}
