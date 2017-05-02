package com.example.mysimplenew.Utils;

import android.util.Log;

/**
 * Created by 红超 on 2017/5/2.
 */

public class LogUtil  {
    public static void  MyLog(Object ob,String msg){
        if (true)
        Log.e(ob.getClass().toString(), msg );
    }
}
