package com.example.mysimplenew.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 红超 on 2017/5/2.
 */

public class ToastUtil {
    public static void MyToast(Context context,String msg){
        if (true)
        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
