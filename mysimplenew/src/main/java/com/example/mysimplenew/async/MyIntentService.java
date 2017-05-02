package com.example.mysimplenew.async;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.DrawableContainer;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by 红超 on 2017/3/27.
 */

public class MyIntentService extends AsyncTask<String ,File,Character> {

    Context context;
    ViewGroup root;
    private final ImageView imageView;

    public MyIntentService(ViewGroup root, Context context) {
        imageView = new ImageView(context);
        this.context=context;
        this.root=root;
    }

    @Override
    protected Character doInBackground(String... params) {
        try {
            File file = Glide.with(context).load(params[0]).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            publishProgress(file);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(File... values) {
        Glide.with(context).load(values[0]).into(imageView);
        FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        final PopupWindow popWnd = new PopupWindow(context);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWnd.isShowing()) {
                    popWnd.dismiss();
                }
            }
        });
        popWnd.setContentView(imageView);
        popWnd.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popWnd.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//相对某个控件的位置（正左下方），无偏移

//相对某个控件的位置，有偏移;xoff表示x轴的偏移，正值表示向左，负值表示向右；yoff表示相对y轴的偏移，正值是向下，负值是向上；

//相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popWnd.showAtLocation(root, Gravity.CENTER, 0, 50);

    }
}
