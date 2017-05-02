package com.example.mysimplenew.fragment;


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

import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.adapter.NewsRecyclerAdapter;
import com.example.mysimplenew.entity.NewsEntity;
import com.example.mysimplenew.myinterface.NewInterface;

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
    GestureDetector mGestureDetector=new GestureDetector(new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            ToastUtil.MyToast(getActivity(),"长按点击");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    });
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = 1;
        if (viewGroup == null) {
            viewGroup = (ViewGroup) View.inflate(getActivity(), R.layout.newsblewcontent, null);
            recyclerView = (RecyclerView) viewGroup.findViewById(R.id.newscontentrecycler);
            refreshLayout = (SwipeRefreshLayout) viewGroup.findViewById(R.id.swip);
            manager = new GridLayoutManager(getContext(), 1);
            recyclerView.setLayoutManager(manager);
            initData(page, refreshLayout, recyclerView);
        }

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if(mGestureDetector.onTouchEvent(e)){
                    return true;
                }else
                    return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {


            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
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
                if (refreshLayout.isRefreshing()) {
                    page++;
                    initData(page, refreshLayout, recyclerView);
                }
            }
        });

        return viewGroup;
    }


    public abstract void initData(final int page, SwipeRefreshLayout refreshLayout, RecyclerView recyclerView);


}
