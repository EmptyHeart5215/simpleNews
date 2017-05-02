package com.example.mysimplenew;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
