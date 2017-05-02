package com.example.mysimplenew;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.entity.NewsContentEntity;
import com.example.mysimplenew.myinterface.NewInterface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.security.auth.login.LoginException;

/**
 * Created by 红超 on 2017/3/29.
 */

public class ContentActivityTwo extends AppCompatActivity{
    //http://tech.163.com/17/0328/08/CGJOE73D00098GJ5.html
    private static final String TAG = "ContentActivity";
    private WebView webView;
    private ImageView image;
    private int height;
    private int width;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webView = (WebView) findViewById(R.id.tv);
        image = (ImageView) findViewById(R.id.iv);
        Intent intent = getIntent();
        final String imagesrc = intent.getStringExtra("imagesrc");
        Glide.with(this).load(imagesrc).into(image);
        String title = intent.getStringExtra("title");
        final String url = intent.getStringExtra("url");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.cententtitle);
        collapsingToolbarLayout.setTitle(title);
        WindowManager windowManager =this.getWindowManager();
        width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();
        GetDataUtils.GetHTML("http://c.m.163.com/nc/article/"+url+"/full.html", new NewInterface() {
            @Override
            public void Secuss(String a) {
                Log.e(TAG, "Secuss: "+a );
                int begin = a.indexOf("\"");
                Log.e(TAG, "Secuss:啊打发打发 "+begin );
                int i = a.indexOf("\"", begin+1);
                Log.e(TAG, "Secuss: "+i );
                String substring = a.substring(i+2);
                Log.e(TAG, "Secuss: "+substring );
                String endstring="{\"message\":"+substring;
                Log.e(TAG, "Secuss: 拼接" +endstring );

                NewsContentEntity newsContentEntity = new Gson().fromJson(endstring, NewsContentEntity.class);
                List<NewsContentEntity.MessageBean.ImgBean> img = newsContentEntity.getMessage().getImg();
                String body1 = newsContentEntity.getMessage().getBody();
                String lastString = null;
                if (img!=null){
                    for (int i1 = 0; i1 < img.size(); i1++) {
                        NewsContentEntity.MessageBean.ImgBean imgBean = img.get(i1);
                        String imgsrc = imgBean.getSrc();
                        String ref = imgBean.getRef();
                        Log.e(TAG, "Secuss: "+ref );
//<img src="http://cms-bucket.nosdn.127.net/catchpic/8/8c/8c435cd52ed02ed67e50a51ed061c0ce.jpg" width="100%"/>
                        String image="<img src="+"\""+imgsrc+"\""+ "width =\"100%\"" +"/>";
                        Log.e(TAG, "Secuss:========================= "+image );
                        lastString = body1.replace(ref, image);
                        body1=lastString;
                    }

                }
//


                final Document document = Jsoup.parse(body1);
                Elements allElements = document.getAllElements();
                final Element element = allElements.get(0);

                webView.post(new Runnable() {

                    @Override
                    public void run() {
                        Log.e(TAG, "run: "+document );
                        webView.loadDataWithBaseURL(null,document.html(), "text/html", "UTF-8", null);
//                        webView.loadUrl("http://cms-bucket.nosdn.127.net/catchpic/3/3e/3efdca002f4798c4c6a44397da2062df.jpg");
                    }
                });

            }

            @Override
            public void Error() {

            }
        });
    }
}
