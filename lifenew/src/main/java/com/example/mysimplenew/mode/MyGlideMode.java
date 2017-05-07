package com.example.mysimplenew.mode;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by 红超 on 2017/3/27.
 */

public class MyGlideMode implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //TODO
        builder.setMemoryCache(new LruResourceCache(10*1024*1024));
        //配置图片池大小   20MB
        builder.setBitmapPool(new LruBitmapPool(20*1024*1024));
        Log.e("TAG", "applyOptions: " );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
