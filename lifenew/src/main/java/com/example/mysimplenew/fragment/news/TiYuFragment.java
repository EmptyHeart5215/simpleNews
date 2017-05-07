package com.example.mysimplenew.fragment.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.LogUtil;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.adapter.TiYuRecyclerAdapter;
import com.example.mysimplenew.entity.TiYuNews;
import com.example.mysimplenew.fragment.NewsContentFragment;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/29.
 */

public class TiYuFragment extends NewsContentFragment {

    List<TiYuNews.T1348649503389Bean> allNewsList=new ArrayList<>();

    TiYuNews faZhiNews ;
    @Override
    public void initData(final int page, final SwipeRefreshLayout refreshLayout, final RecyclerView recyclerView) {
        GetDataUtils.GetNews("T1348649503389", 20, page,
                new NewInterface() {

                    private TiYuRecyclerAdapter adapter;

                    @Override
                    public void Secuss(String newsEntity) {
                        faZhiNews= new Gson().fromJson(newsEntity,TiYuNews.class);

                        LogUtil.MyLog("TAG", "Secuss: "+newsEntity );
                        if (refreshLayout.isRefreshing())
                            allNewsList.clear();

                        final List<TiYuNews.T1348649503389Bean> newslist = faZhiNews.getT1348649503389();
                        allNewsList.addAll(newslist);
                        LogUtil.MyLog("TAG", "Secuss: " + newslist);
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (page == 1) {

                                    adapter = new TiYuRecyclerAdapter(getContext(), allNewsList);

                                    recyclerView.setAdapter(adapter);

                                } else {
//                                    ToastUtil.MyToast(getActivity(), "返回数据");
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
