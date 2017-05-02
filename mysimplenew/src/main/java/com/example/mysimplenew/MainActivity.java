package com.example.mysimplenew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysimplenew.Utils.ToastUtil;
import com.example.mysimplenew.fragment.NewsFragment;
import com.example.mysimplenew.fragment.PhotoFragment;
import com.example.mysimplenew.fragment.PhotoFragmentTwo;
import com.example.mysimplenew.fragment.PhotoThree;
import com.example.mysimplenew.fragment.SettingFragment;
import com.example.mysimplenew.fragment.WeatherFragment;
import com.example.mysimplenew.viewpageranimator.MyDrawerLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView menu;
    private MyDrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NewsFragment newsFragment;
    private FrameLayout frameLayoutAll;
    private PhotoFragment photoFragment;
    private SettingFragment settingFragment;
    private WeatherFragment weatherFragment;
    private AppBarLayout appbar;
    private PhotoFragmentTwo photoFragmentTwo;
    private String iconurl;
    private String name;
    private PhotoThree photoThree;
    boolean tag = true;
    private CircleImageView view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean networkConnected = MyApplication.isNetworkConnected(this);
        initView();

        if (networkConnected != true) {
            Toast.makeText(this, "当前网络不可用，请检查网络连接", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = getIntent();
            name = intent.getStringExtra("name");
            iconurl = intent.getStringExtra("iconurl");
            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_APPEND);
            if (sharedPreferences.getString("name", "登录错误") != "登录错误" && sharedPreferences.getString("iconurl", "error") != "error") {
                String name = sharedPreferences.getString("name", "登录错误");
                String iconurl = sharedPreferences.getString("iconurl", "error");
                ImageView headphoto = (ImageView) menu.getHeaderView(0).findViewById(R.id.headphoto);
                TextView username = (TextView) menu.getHeaderView(0).findViewById(R.id.name);
                Glide.with(MainActivity.this).load(iconurl).into(headphoto);
                username.setText(name);


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentall, newsFragment).commit();

        }


    }

    private static final String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (MyDrawerLayout) findViewById(R.id.activity_main);
        menu = (NavigationView) findViewById(R.id.navigation);
        frameLayoutAll = (FrameLayout) findViewById(R.id.fragmentall);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        menu.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        photoThree = new PhotoThree();
        photoFragmentTwo = new PhotoFragmentTwo();
        photoFragment = new PhotoFragment();
        newsFragment = new NewsFragment();
        settingFragment = new SettingFragment();
        weatherFragment = new WeatherFragment();

        if (tag) {
            CircleImageView view = (CircleImageView) menu.getHeaderView(0).findViewById(R.id.headphoto);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivityForResult(intent, 100);
                    tag = !tag;
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 101 && data.getStringExtra("name") != null) {
            String name = data.getStringExtra("name");
            String iconurl = data.getStringExtra("iconurl");
            ImageView headphoto = (ImageView) menu.getHeaderView(0).findViewById(R.id.headphoto);
            TextView viewById = (TextView) menu.getHeaderView(0).findViewById(R.id.name);
            if (name.equals("") && iconurl.equals("")) {
                headphoto.setImageResource(R.mipmap.ic_launcher);
                viewById.setText("请点击登录");
            } else {
                Glide.with(MainActivity.this).load(iconurl).into(headphoto);
                viewById.setText(name);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        itemClick(item);
        return true;
    }

    int[] chose = new int[2];
    boolean selecktag = true;

    private void itemClick(MenuItem item) {

        chose[1] = chose[0];//上次的id

        chose[0] = item.getItemId();//本次的id
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (chose[0]) {
            case R.id.news:
                fragmentTransaction.replace(R.id.fragmentall, newsFragment, "newsFragment");
                toolbar.setTitle("新闻");
                selecktag = true;
                break;
            case R.id.photos:
                fragmentTransaction.replace(R.id.fragmentall, photoThree, "photoThree");
                toolbar.setTitle("图片");
                selecktag = false;
                break;
            case R.id.setting:
                Intent settingintent = new Intent(this, SettingActivity.class);
                startActivityForResult(settingintent, 100);
                break;
            case R.id.weather:
                Intent intent = new Intent(this, WeatherActivity.class);
                startActivity(intent);
                break;
        }
        fragmentTransaction.commit();
        drawerLayout.closeDrawer(Gravity.LEFT);

    }



    @Override
    protected void onResume() {
        super.onResume();
        if (MyApplication.isNetworkConnected(this) == true) {
            if (selecktag) {
                menu.setCheckedItem(R.id.news);
            } else {
                menu.setCheckedItem(R.id.photos);
            }
        }
    }

}
