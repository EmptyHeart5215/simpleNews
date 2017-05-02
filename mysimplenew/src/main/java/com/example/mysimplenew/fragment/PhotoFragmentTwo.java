package com.example.mysimplenew.fragment;


import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.async.MyIntentService;
import com.example.mysimplenew.entity.NewsEntity;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 红超 on 2017/3/26.
 */

public class PhotoFragmentTwo extends Fragment {
    private ViewGroup root;
    private ScrollView scrollView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        root = (ViewGroup) View.inflate(getActivity(), R.layout.photocontent, null);
        scrollView = (ScrollView) root.findViewById(R.id.scroll);
        initView();
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
        return root;
    }



    List<ImageView> imageViews = new ArrayList<>();
    Map<Integer, ImageView> imageMap = new HashMap<>();

    private void initView() {
//        GetDataUtils.GetNews("meinv", 40, 9, new NewInterface() {
//
//            @Override
//            public void Secuss(final Object newsEntity) {
//                final NewsEntity a= (NewsEntity) newsEntity;
//                Log.e("TAG", "Secuss:" + Thread.currentThread());
//                root.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (NewsEntity.T1348649503389Bean newslistBean : a.getT1348649503389()) {
//                            final String picUrl = newslistBean.getImgsrc();
//                            Log.e("TAG", "run: " + picUrl);
//
//                            //获取到布局
//                            FlexboxLayout flexboxLayout = (FlexboxLayout) root.findViewById(R.id.flexbox);
//                            //设置布局方向
////                            flexboxLayout.setFlexDirection(FlexboxLayout.FLEX_DIRECTION_COLUMN);
//
//                            final ImageView imageView = new ImageView(getContext());
//                            SimpleTarget<Bitmap> simpleTarget =new SimpleTarget<Bitmap>() {
//                                @Override
//                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                                    imageView.setImageBitmap(resource);
//                                }
//                            };
//                            Glide.with(getContext()).load(picUrl).asBitmap().skipMemoryCache(true).into(simpleTarget);
//
//                            flexboxLayout.addView(imageView);
//                            FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) imageView.getLayoutParams();
//                            lp.flexShrink = 1;
//                            lp.leftMargin = 5;
//                            lp.rightMargin = 5;
//                            imageView.setLayoutParams(lp);
//                            imageView.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                MyIntentService myIntentService=    new MyIntentService(root,getContext());
//                                myIntentService.execute(picUrl);
//
//                                }
//                            });
//
//                        }
//                    }
//
//                });
//
//
//            }
//
//            @Override
//            public void Error() {
//
//            }
//        });
    }

}
