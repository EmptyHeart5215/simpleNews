package com.example.mysimplenew;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.util.LogTime;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.entity.WeatherEntity;
import com.example.mysimplenew.myinterface.WeatherInterface;

import java.util.List;

/**
 * Created by 红超 on 2017/3/26.
 */

public class WeatherActivity extends AppCompatActivity {

    private TextView today;
    private TextView todaytianqi;
    private ImageView todaytupian;
    private TextView todaywendu;
    private TextView tomm;
    private TextView tommtianqi;
    private ImageView tommtupian;
    private TextView tommwendu;
    private TextView localtext;
    private ImageView localimage;
    private TextView wendutext;
    private TextView tianqi;
    private TextView jiangyu;
    private TextView fengli;
    private LinearLayout jiangyuall;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private LinearLayout linearLayout;

    private static final String TAG = "WeatherActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weathercontent);


        initView();
        if (Build.VERSION.SDK_INT < 23)
            initData();
        else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        200);//自定义的code
                Log.e(TAG, "onCreate: 权限申请中" );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            initData();
        }
    }

    private void initData() {
        //声明AMapLocationClient类对象
        mLocationClient = null;
//声明定位回调监听器
        Log.e(TAG, "onCreate: 权限申请中" );

        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                        amapLocation.getCountry();//国家信息
                        String sheng = amapLocation.getProvince();//省信息
                        String city = amapLocation.getCity();//城市信息
                        String chengqu = amapLocation.getDistrict();//城区信息
                        String jiedao = amapLocation.getStreet();//街道信息
                        getWeather(city, chengqu, jiedao);
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };
//初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

//声明AMapLocationClientOption对象
        mLocationOption = null;
//初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);

//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }

    private void getWeather(String city, final String chengqu, final String jiedao) {
        GetDataUtils.GetWeather(city, new WeatherInterface() {
            @Override
            public void Secuss(final WeatherEntity weatherEntity) {
                wendutext.post(new Runnable() {
                    @Override
                    public void run() {
                        linearLayout.setVisibility(View.VISIBLE);
                        List<WeatherEntity.ResultsBean> results = weatherEntity.getResults();
                        List<WeatherEntity.ResultsBean.DailyBean> daily = results.get(0).getDaily();
                        WeatherEntity.ResultsBean.DailyBean todaycon = daily.get(0);
                        WeatherEntity.ResultsBean.DailyBean tommcon = daily.get(1);
                        wendutext.setText(todaycon.getHigh());
                        todaywendu.setText(todaycon.getLow() + "/" + todaycon.getHigh() + "℃");
                        todaytianqi.setText(todaycon.getText_day());
                        tommwendu.setText(tommcon.getLow() + "/" + tommcon.getHigh() + "℃");
                        tommtianqi.setText(tommcon.getText_day());
                        if (todaycon.getText_day().contains("晴")) {
                            todaytupian.setImageResource(R.drawable.qing);
                        } else if (todaycon.getText_day().contains("雨")) {
                            todaytupian.setImageResource(R.drawable.xiayu);

                        } else {
                            todaytupian.setImageResource(R.drawable.duoyun);

                        }

                        if (tommcon.getText_day().contains("晴")) {
                            tommtupian.setImageResource(R.drawable.qing);
                        } else if (tommcon.getText_day().contains("雨")) {
                            tommtupian.setImageResource(R.drawable.xiayu);

                        } else {
                            tommtupian.setImageResource(R.drawable.duoyun);

                        }
                        tianqi.setText(todaycon.getText_day());
                        fengli.setText(todaycon.getWind_scale() + "级");
                        localtext.setText(chengqu + "  " + jiedao);

                    }
                });

            }

            @Override
            public void Error() {
                Toast.makeText(WeatherActivity.this, "数据获取失败请检查网络!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        today = (TextView) findViewById(R.id.today);
        todaytianqi = (TextView) findViewById(R.id.todaytianqi);
        todaytupian = (ImageView) findViewById(R.id.todaytupian);
        todaywendu = (TextView) findViewById(R.id.todaywendu);
        tomm = (TextView) findViewById(R.id.tomm);
        tommtianqi = (TextView) findViewById(R.id.tommtianqi);
        tommtupian = (ImageView) findViewById(R.id.tommtupian);
        tommwendu = (TextView) findViewById(R.id.tommwendu);
        localtext = (TextView) findViewById(R.id.localtext);
        localimage = (ImageView) findViewById(R.id.localimage);
        wendutext = (TextView) findViewById(R.id.wendutext);
        tianqi = (TextView) findViewById(R.id.tianqi);
        jiangyu = (TextView) findViewById(R.id.jiangyu);
        fengli = (TextView) findViewById(R.id.fengli);
        jiangyuall = (LinearLayout) findViewById(R.id.jiangyuall);

    }

    @Override
    protected void onDestroy() {
        if (mLocationClient!=null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }

        super.onDestroy();
    }
}
