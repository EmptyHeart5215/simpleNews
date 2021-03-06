package com.example.mysimplenew.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysimplenew.MainActivity;
import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.adapter.NewsRecyclerAdapter;
import com.example.mysimplenew.entity.NewsEntity;
import com.example.mysimplenew.myinterface.NewInterface;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 红超 on 2017/3/25.
 */

public abstract class NewsContentFragment extends Fragment {
    private static final String TAG = "NewsContentFragment";
    private RecyclerView recyclerView;
    public String type;
    private ViewGroup viewGroup;
    private SwipeRefreshLayout refreshLayout;
    private int page;
    private GridLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = 1;
        if (viewGroup == null) {
            Log.e("hengping", "onCreateView: 横屏了" );
            viewGroup = (ViewGroup) View.inflate(getActivity(), R.layout.newsblewcontent, null);
            recyclerView = (RecyclerView) viewGroup.findViewById(R.id.newscontentrecycler);
            refreshLayout = (SwipeRefreshLayout) viewGroup.findViewById(R.id.swip);
            manager = new GridLayoutManager(getContext(), 1);
            recyclerView.setLayoutManager(manager);
            initData(page, refreshLayout, recyclerView);
        }else {
            return viewGroup;
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    int lastVisibleItemPosition = manager.findLastVisibleItemPosition();

                    if (recyclerView.getAdapter()!=null&&lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                        page++;
                        initData(page, refreshLayout, recyclerView);
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (viewGroup==null)return;
                if (refreshLayout.isRefreshing()) {
                    page++;
                    initData(page, refreshLayout, recyclerView);
                }
            }
        });

        return viewGroup;
    }


    public abstract void initData(final int page, SwipeRefreshLayout refreshLayout, RecyclerView recyclerView);

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

}
