package com.example.mysimplenew.fragment.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.adapter.YunKeTangRecyclerAdapter;
import com.example.mysimplenew.entity.YunKeTang;
import com.example.mysimplenew.fragment.NewsContentFragment;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/29.
 */

public class YunKeTangFraagment extends NewsContentFragment {

    List<YunKeTang.T1421997195219Bean> allNewsList = new ArrayList<>();

    YunKeTang faZhiNews;

    @Override
    public void initData(final int page, final SwipeRefreshLayout refreshLayout, final RecyclerView recyclerView) {
        GetDataUtils.GetNews("T1421997195219", 20, page,
                new NewInterface() {

                    private YunKeTangRecyclerAdapter adapter;

                    @Override
                    public void Secuss(String newsEntity) {
                        faZhiNews= new Gson().fromJson(newsEntity,YunKeTang.class);

                        LogUtil.MyLog("TAG", "Secuss: " + newsEntity);
                        if (refreshLayout.isRefreshing())
                            allNewsList.clear();

                        final List<YunKeTang.T1421997195219Bean> newslist = faZhiNews.getT1421997195219();
                        allNewsList.addAll(newslist);
                        LogUtil.MyLog("TAG", "Secuss: " + newslist);
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (page == 1) {

                                    adapter = new YunKeTangRecyclerAdapter(getContext(), allNewsList);

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
