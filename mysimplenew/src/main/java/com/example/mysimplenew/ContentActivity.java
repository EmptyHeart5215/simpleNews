package com.example.mysimplenew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.entity.NewsContentEntity;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by 红超 on 2017/3/29.
 */

public class ContentActivity extends AppCompatActivity{
    //http://tech.163.com/17/0328/08/CGJOE73D00098GJ5.html
    private static final String TAG = "ContentActivity";
    private WebView webView;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webView = (WebView) findViewById(R.id.tv);
        image = (ImageView) findViewById(R.id.iv);
        Intent intent = getIntent();
        String imagesrc = intent.getStringExtra("imagesrc");
        Glide.with(this).load(imagesrc).into(image);
        String title = intent.getStringExtra("title");
        final String url = intent.getStringExtra("url");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.cententtitle);
        collapsingToolbarLayout.setTitle(title);

        GetDataUtils.GetHTML("http://c.m.163.com/nc/article/"+url+"/full.html", new NewInterface() {
            @Override
            public void Secuss(String a) {
                CharSequence charSequence = a.subSequence(2, 18);
                String s = charSequence.toString();

                try {
                    JSONObject object =new JSONObject(a);
                    JSONObject body = object.getJSONObject(s);
                    String body1 = body.getString("body");
                    Log.e(TAG, "Secuss: "+a );



                    Log.e(TAG, "Secuss: "+body1 );
                    Document document = Jsoup.parse(body1);
                    final Elements content = document.getElementsByTag("p");
                    final Elements allElements = document.getAllElements();
                    for (int i = 0; i < content.size(); i++) {

                    }
                    webView.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run: "+content.toString() );
                            webView.loadDataWithBaseURL(null, content.html(), "text/html", "UTF-8", null);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void Error() {

            }
        });
    }
}
