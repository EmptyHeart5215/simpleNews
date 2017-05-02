package com.example.mysimplenew.fragment;

import android.app.Activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.adapter.PhotoThreeRecyclerAdapter;
import com.example.mysimplenew.entity.PhotoEntity;
import com.example.mysimplenew.myinterface.PhotoInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 红超 on 2017/3/28.
 */

public class PhotoThree extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private String page = "";
    //    http://c.m.163.com/photo/api/list/0096/54GI0096.json
    private GridLayoutManager manager;
    private ViewGroup root;
    int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root == null) {
            root = (ViewGroup) View.inflate(getActivity(), R.layout.photothree, null);
            refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.photo_refresh);
            recyclerView = (RecyclerView) root.findViewById(R.id.photothree_recycler);

            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.bottom = 30;
                    outRect.right = 30;
                    outRect.left = 30;
                }
            });
            manager = new GridLayoutManager(getActivity(), 1);
            recyclerView.setLayoutManager(manager);
            if (tag)
                refreshLayout.setRefreshing(true);
            initData();
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    initData();
                }
            });
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    if (newState == recyclerView.SCROLL_STATE_IDLE) {
                        int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                        if (lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
//                            Toast.makeText(getActivity(), "zuihouyitiao", Toast.LENGTH_SHORT).show();
                            initData();
                        }
                    }

                }
            });
        }
        return root;
    }

    boolean tag = true;
    List<PhotoEntity.TagBean> tagall = new ArrayList<>();

    private void initData() {
        if (!tag) {
            id = id - 10;
            page = id + "";
        }
        GetDataUtils.GetPhoto(page, new PhotoInterface() {
            @Override
            public void Secuss(final PhotoEntity newsEntity) {
                Log.e("TAG", "Secuss: " + newsEntity.toString());

                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        List<PhotoEntity.TagBean> tag = newsEntity.getTag();
                        //TODO


//                        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//                            @Override
//                            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                                outRect.top = 100;
//                                super.getItemOffsets(outRect, view, parent, state);
//                            }
//                        });
                        if (refreshLayout.isRefreshing()) {
                            tagall.clear();
                            tagall.addAll(tag);
                            if (PhotoThree.this.tag) {
                                PhotoThreeRecyclerAdapter adapter = new PhotoThreeRecyclerAdapter(root, getActivity(), tagall);
                                recyclerView.setAdapter(adapter);
                                PhotoThree.this.tag = !PhotoThree.this.tag;
                                id = Integer.parseInt(newsEntity.getTag().get(0).getSetid());
                            } else {
                                recyclerView.getAdapter().notifyDataSetChanged();

                            }
                            refreshLayout.setRefreshing(false);
                        } else {
                            Log.e("TAG", "run: " + "shuaxingshuju ");
                            tagall.addAll(tag);
                            recyclerView.getAdapter().notifyDataSetChanged();
//                            refreshLayout.setRefreshing(false);
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
