package com.example.mysimplenew.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mysimplenew.R;
import com.example.mysimplenew.Utils.GetDataUtils;
import com.example.mysimplenew.entity.WeatherEntity;
import com.example.mysimplenew.myinterface.WeatherInterface;

import java.util.List;

/**
 * Created by 红超 on 2017/3/25.
 */

public class WeatherFragment extends Fragment {

    private ViewGroup root;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {

            root = (ViewGroup) View.inflate(getContext(), R.layout.weathercontent, null);
            initView();
            initData();
        }
        return root;
    }

    private void initData() {
        GetDataUtils.GetWeather("武汉", new WeatherInterface() {
            @Override
            public void Secuss(final WeatherEntity weatherEntity) {
                root.post(new Runnable() {
                    @Override
                    public void run() {
                        List<WeatherEntity.ResultsBean> results = weatherEntity.getResults();
                        List<WeatherEntity.ResultsBean.DailyBean> daily = results.get(0).getDaily();
                        WeatherEntity.ResultsBean.DailyBean todaycon = daily.get(0);
                        WeatherEntity.ResultsBean.DailyBean tommcon = daily.get(1);
                        wendutext.setText(todaycon.getHigh());
                        todaywendu.setText(todaycon.getLow() + "/" + todaycon.getHigh() + "℃");
                        todaytianqi.setText(todaycon.getText_day());
                        tommwendu.setText(tommcon.getLow() + "/" + tommcon.getHigh() + "℃");
                        tommtianqi.setText(tommcon.getText_day());
                        tianqi.setText(todaycon.getText_day());
                        fengli.setText(todaycon.getWind_scale() + "级");
                        if (todaycon.getPrecip().equals("")) {
                            jiangyuall.setVisibility(View.INVISIBLE);
                        } else
                            jiangyu.setText(todaycon.getPrecip());
                    }
                });

            }

            @Override
            public void Error() {

            }
        });
    }

    private void initView() {
        today = (TextView) root.findViewById(R.id.today);
        todaytianqi = (TextView) root.findViewById(R.id.todaytianqi);
        todaytupian = (ImageView) root.findViewById(R.id.todaytupian);
        todaywendu = (TextView) root.findViewById(R.id.todaywendu);
        tomm = (TextView) root.findViewById(R.id.tomm);
        tommtianqi = (TextView) root.findViewById(R.id.tommtianqi);
        tommtupian = (ImageView) root.findViewById(R.id.tommtupian);
        tommwendu = (TextView) root.findViewById(R.id.tommwendu);
        localtext = (TextView) root.findViewById(R.id.localtext);
        localimage = (ImageView) root.findViewById(R.id.localimage);
        wendutext = (TextView) root.findViewById(R.id.wendutext);
        tianqi = (TextView) root.findViewById(R.id.tianqi);
        jiangyu = (TextView) root.findViewById(R.id.jiangyu);
        fengli = (TextView) root.findViewById(R.id.fengli);
        jiangyuall = (LinearLayout) root.findViewById(R.id.jiangyuall);

    }
}
