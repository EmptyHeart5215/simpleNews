package com.example.mysimplenew;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by 红超 on 2017/3/27.
 */

public class MyApplication extends Application {
    {

        PlatformConfig.setQQZone("1106068092", "sz4ahGouRy88rvj6");
        PlatformConfig.setSinaWeibo("428048573", "785c933d846a6c75f3ca719a75fc05bd", "http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        Config.DEBUG = true;
        UMShareAPI.get(this);

        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("deviceToken", "onSuccess: "+deviceToken );
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

    }
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
