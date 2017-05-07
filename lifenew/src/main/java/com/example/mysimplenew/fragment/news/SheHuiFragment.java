package com.example.mysimplenew.fragment.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.adapter.SheHuiRecyclerAdapter;
import com.example.mysimplenew.entity.SheHuiNews;
import com.example.mysimplenew.fragment.NewsContentFragment;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/29.
 */

public class SheHuiFragment  extends NewsContentFragment {

    List<SheHuiNews.T1467284926140Bean> allNewsList=new ArrayList<>();

    SheHuiNews faZhiNews ;
    @Override
    public void initData(final int page, final SwipeRefreshLayout refreshLayout, final RecyclerView recyclerView) {
        GetDataUtils.GetNews("T1467284926140", 20, page,
                new NewInterface() {

                    private SheHuiRecyclerAdapter adapter;

                    @Override
                    public void Secuss(String newsEntity) {
                        faZhiNews= new Gson().fromJson(newsEntity,SheHuiNews.class);

                        LogUtil.MyLog("TAG", "Secuss: "+newsEntity );
                        if (refreshLayout.isRefreshing())
                            allNewsList.clear();

                        final List<SheHuiNews.T1467284926140Bean> newslist = faZhiNews.getT1467284926140();
                        allNewsList.addAll(newslist);
                        LogUtil.MyLog("TAG", "Secuss: " + newslist);
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (page == 1) {

                                    adapter = new SheHuiRecyclerAdapter(getContext(), allNewsList);

                                    recyclerView.setAdapter(adapter);

                                } else {
//                                    ToastUtil.MyToast(getActivity(), "数据获取成功");
                                    recyclerView.getAdapter().notifyDataSetChanged();
                                    refreshLayout.setRefreshing(false);
                                }
                            }
                        });
                    }


                    @Override
                    public void Error() {
                        ToastUtil.MyToast(getActivity(),"数据获取失败请重新获取数据或检查网络连接");
                    }

                });
    }
}
