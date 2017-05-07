package com.example.mysimplenew.fragment.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.adapter.LiShiRecyclerAdapter;
import com.example.mysimplenew.entity.LiShiNews;
import com.example.mysimplenew.fragment.NewsContentFragment;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/29.
 */

public class LiShiFragment extends NewsContentFragment {

    List<LiShiNews.T1368497029546Bean> allNewsList=new ArrayList<>();

    LiShiNews faZhiNews ;
    @Override
    public void initData(final int page, final SwipeRefreshLayout refreshLayout, final RecyclerView recyclerView) {
        GetDataUtils.GetNews("T1368497029546", 20, page,
                new NewInterface() {

                    private LiShiRecyclerAdapter adapter;

                    @Override
                    public void Secuss(String newsEntity) {
                        faZhiNews= new Gson().fromJson(newsEntity,LiShiNews.class);

                        Log.e("TAG", "Secuss: "+newsEntity );
                        if (refreshLayout.isRefreshing())
                            allNewsList.clear();

                        final List<LiShiNews.T1368497029546Bean> newslist = faZhiNews.getT1368497029546();
                        allNewsList.addAll(newslist);
                        Log.e("TAG", "Secuss: " + newslist);
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (page == 1) {

                                    adapter = new LiShiRecyclerAdapter(getContext(), allNewsList);

                                    recyclerView.setAdapter(adapter);

                                } else {
//                                    ToastUtil.MyToast(getActivity(), "数据返回");
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
