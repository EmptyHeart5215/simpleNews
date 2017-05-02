package com.example.mysimplenew.Utils;

import android.util.Log;

import com.example.mysimplenew.entity.NewsEntity;
import com.example.mysimplenew.entity.PhotoEntity;
import com.example.mysimplenew.entity.WeatherEntity;
import com.example.mysimplenew.myinterface.NewInterface;
import com.example.mysimplenew.myinterface.PhotoInterface;
import com.example.mysimplenew.myinterface.WeatherInterface;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 红超 on 2017/3/25.
 */

public class GetDataUtils  {
    private static String base="https://api.tianapi.com/";
    private static String num= "/?key=1137d63765a2e70f4edb949af9a4c7d6&num=";
    private static String page= "&page=";
    private static String location;
    private static String other;
    private static String photobase;
    private static String type;
    //天气预报
    //https://api.seniverse.com/v3/weather/daily.json?key=rhakycd2p2h2ns5l&location=武汉&language=zh-Hans&unit=c&start=0&days=5
    //体育
    //***
    //T1348649145984     T1348648037603    T1368497029546    T1348648141035   T1467284926140
    // http://c.m.163.com/recommend/getSubDocPic?tid=T1348647909107&offset=4&size=10&fn=1&passport=&devId=%2FggZfgE%2FO2PR5zNnBHO3jA%3D%3D&lat=C8Z3%2FMQbaSd%2FwK6sS5lg%2Bg%3D%3D&lon=jJE0Vl3QV%2F19nxLmTTCn8Q%3D%3D&version=21.0&net=wifi&ts=1490765272&sign=BVoNJkmqIs8P7UK7saMlCS8T5jRVIBHBQBJGOEzmEqt48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=news_wx91zm&open=&openpath=
    //http://c.m.163.com/recommend/getChanListNews?tid=T1419316284722&offset=6&size=10&fn=1&passport=&devId=%2FggZfgE%2FO2PR5zNnBHO3jA%3D%3D&lat=C8Z3%2FMQbaSd%2FwK6sS5lg%2Bg%3D%3D&lon=jJE0Vl3QV%2F19nxLmTTCn8Q%3D%3D&version=21.0&net=wifi&ts=1490767087&sign=mf%2B1ncOKbF7nQ%2FbYjD%2FAyRlFL02TMIFj4FaAaUDONqh48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=news_wx91zm&open=&openpath=
    //http://c.m.163.com/dlist/article/dynamic?from=T1348649145984&offset=5&size=10&fn=1&passport=&devId=%2FggZfgE%2FO2PR5zNnBHO3jA%3D%3D&lat=C8Z3%2FMQbaSd%2FwK6sS5lg%2Bg%3D%3D&lon=jJE0Vl3QV%2F19nxLmTTCn8Q%3D%3D&version=21.0&net=wifi&ts=1490767597&sign=q63%2BVC6IRynD6d8zqfhK3ZPSks9aOJ2WyYlEqfyVZhB48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=news_wx91zm&&open=&openpath=
    // http://c.m.163.com/dlist/article/dynamic?from=T1348648141035&offset=0&size=10&fn=2&passport=&devId=%2FggZfgE%2FO2PR5zNnBHO3jA%3D%3D&lat=C8Z3%2FMQbaSd%2FwK6sS5lg%2Bg%3D%3D&lon=jJE0Vl3QV%2F19nxLmTTCn8Q%3D%3D&version=21.0&net=wifi&ts=1490768458&sign=XBjrUdYJYLjD6kqXCeNmi%2BBxUjUrfhmxGtvdjA3QQ3l48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=news_wx91zm&&open=&openpath=
    // http://c.m.163.com/dlist/article/dynamic?from=T1370583240249&offset=0&size=10&fn=2&passport=&devId=%2FggZfgE%2FO2PR5zNnBHO3jA%3D%3D&lat=C8Z3%2FMQbaSd%2FwK6sS5lg%2Bg%3D%3D&lon=jJE0Vl3QV%2F19nxLmTTCn8Q%3D%3D&version=21.0&net=wifi&ts=1490768458&sign=XBjrUdYJYLjD6kqXCeNmi%2BBxUjUrfhmxGtvdjA3QQ3l48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=news_wx91zm&mac=ldQIvB6yc7U8MbpC2pQNo7NhceueLwdP2RJjZMm07oo%3D&open=&openpath=
    //笑话
    //https://api.tianapi.com/txapi/joke/?key=1137d63765a2e70f4edb949af9a4c7d6&num=50&page=11
    //国际新闻
    //http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
    //养眼美女
    //https://api.tianapi.com/meinv/?key=1137d63765a2e70f4edb949af9a4c7d6&num=50&page=100
    //汽车
    //http://c.m.163.com/nc/auto/list/5qCq5rSy/60-20.html
    //社会新闻
    //http://c.m.163.com/nc/article/house/5qCq5rSy/0-20.html

    public static void GetNews(String type, int num, int page, final NewInterface newInterface){
        OkHttpClient client =new OkHttpClient();

        Request request = new Request.Builder().url("http://c.m.163.com/dlist/article/dynamic?from=" + type + "&offset="+page+"&size=10&fn=2&passport=&devId=%2FggZfgE%2FO2PR5zNnBHO3jA%3D%3D&lat=C8Z3%2FMQbaSd%2FwK6sS5lg%2Bg%3D%3D&lon=jJE0Vl3QV%2F19nxLmTTCn8Q%3D%3D&version=21.0&net=wifi&ts=1490768458&sign=XBjrUdYJYLjD6kqXCeNmi%2BBxUjUrfhmxGtvdjA3QQ3l48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=news_wx91zm&&open=&openpath=").get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()  {
            @Override
            public void onFailure(Request request, IOException e) {

                newInterface.Error();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();


                newInterface.Secuss(string);
            }
        });
    }
    public static void GetWeather( String local, final WeatherInterface weatherInterface){
        try {
            local= URLEncoder.encode(local,"UTF-8");

        OkHttpClient client =new OkHttpClient();
        location = "https://api.seniverse.com/v3/weather/daily.json?key=rhakycd2p2h2ns5l&location=";
        other = "&language=zh-Hans&unit=c&start=0&days=5";
        Request request = new Request.Builder().url(location +local + other).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                weatherInterface.Error();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                WeatherEntity weatherEntity = new Gson().fromJson(string, WeatherEntity.class);
                Log.e("TAG", "onResponse: "+weatherEntity );
                weatherInterface.Secuss(weatherEntity);
            }
        });} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static void GetPhoto( String page, final PhotoInterface newInterface){
        OkHttpClient client =new OkHttpClient();
        //          http://c.m.163.com/photo/api/list/0096/54GI0096.json
        //http://c.m.163.com/photo/api/morelist/0096/54GI0096/123456.json111
        photobase = "http://c.m.163.com/photo/api/morelist/0096/54GI0096/";
        String other=".json";
//        Request request = new Request.Builder().url(photobase + page+other).get().build();
        //http://c.m.163.com/photo/api/morelist/0096/54GI0096/.json

        Request request = new Request.Builder().url("http://c.m.163.com/photo/api/morelist/0096/54GI0096/"+page+".json").get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()  {
            @Override
            public void onFailure(Request request, IOException e) {

                newInterface.Error();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                string="{\"tag\":"+string+"}";
                PhotoEntity photoEntity = new Gson().fromJson(string, PhotoEntity.class);
                newInterface.Secuss(photoEntity);
            }
        });
    }

    public static void GetHTML(String url, final NewInterface newInterface){
        OkHttpClient client =new OkHttpClient();
        Log.e("TAG", "GetHTML: "+url );
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()  {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG", "onFailure: "+e.toString() );
                newInterface.Error();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Log.e("TAG", "onResponse: "+string );

                newInterface.Secuss(string);
            }
        });
    }

    public static void GetPhotoContent(String url, final NewInterface newInterface){
        OkHttpClient client =new OkHttpClient();

        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()  {
            @Override
            public void onFailure(Request request, IOException e) {
                newInterface.Error();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                newInterface.Secuss(string);
            }
        });

    }




}
