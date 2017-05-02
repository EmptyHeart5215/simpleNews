package com.example.mysimplenew.viewpageranimator;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.test.LoaderTestCase;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 红超 on 2017/5/2.
 */

public class MyDrawerLayout extends DrawerLayout {

    private float x;

    public MyDrawerLayout(Context context) {
        super(context);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        return super.onTouchEvent(ev);
    }

    private static final String TAG = "MyDrawerLayout";

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:

                x = ev.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                if (x > 80) {
                    return false;
                } else {


                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
