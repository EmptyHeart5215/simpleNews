package com.example.mysimplenew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.adapter.PhotoViewPagerAdapter;
import com.example.mysimplenew.entity.AllNewsContent;
import com.example.mysimplenew.entity.PhotoEntity;
import com.example.mysimplenew.myinterface.NewInterface;
import com.example.mysimplenew.myinterface.PhotoInterface;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 红超 on 2017/3/31.
 */

public class PhotoContent extends AppCompatActivity {

    private ViewPager pager;
    private TextView title, describe;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photocontent);
        Log.e("TAG", "onCreate: ");
        context = this;
        pager = (ViewPager) findViewById(R.id.photoviewpager);
        title = (TextView) findViewById(R.id.phototitle);
        describe = (TextView) findViewById(R.id.photodescribe);

        Intent intent = getIntent();

        String setid = intent.getStringExtra("setid");
        GetDataUtils.GetPhotoContent("http://c.m.163.com/photo/api/set/0096/" + setid + ".json", new NewInterface() {
            @Override
            public void Secuss(final String a) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AllNewsContent allNewsContent = new Gson().fromJson(a, AllNewsContent.class);
                        List<AllNewsContent.PhotosBean> photos = allNewsContent.getPhotos();
                        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(context, photos, title, describe);

                        if (pager == null) {
                            Log.e("TAG", "run: ");
                        }
                        pager.setAdapter(adapter);

                    }
                });
            }

            @Override
            public void Error() {

            }
        });
    }
}
