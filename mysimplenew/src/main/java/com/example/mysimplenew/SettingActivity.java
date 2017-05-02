package com.example.mysimplenew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysimplenew.Utils.ToastUtil;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UmengTool;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by 红超 on 2017/3/28.
 */

public class SettingActivity extends AppCompatActivity {

    private ImageView QQimage;
    private UMShareAPI mShareAPI1;
    private View layout;
    private Button button;
    private ImageView weiXinImage;
    private ImageView settingimage;
    private TextView settingname;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window =this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);
        layout = findViewById(R.id.settinglayout);
        mShareAPI1 = UMShareAPI.get( SettingActivity.this );
        final SharedPreferences sharedPreferences =getSharedPreferences("user",MODE_APPEND);
        button = (Button) findViewById(R.id.tuichu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.getVisibility()!= View.VISIBLE) {
                    return;
                }
                mShareAPI1.deleteOauth(SettingActivity.this,SHARE_MEDIA.QQ,umAuthListener);
                findViewById(R.id.settinglayout).setVisibility(View.GONE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.clear().commit();
                button.setText("请登录");

            }
        });
        settingimage = (ImageView) findViewById(R.id.settingimage);

        settingname = (TextView) findViewById(R.id.settingname);
        String name = sharedPreferences.getString("name", "");
        String iconurl = sharedPreferences.getString("iconurl", "");
        setUserPerform(settingimage, settingname, name, iconurl);
        QQimage = (ImageView) findViewById(R.id.qqimage);
        weiXinImage = (ImageView) findViewById(R.id.weixinimage);
        weiXinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareAPI1.getPlatformInfo(SettingActivity.this, SHARE_MEDIA.SINA, umAuthListener);
            }
        });


        QQimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareAPI1.getPlatformInfo(SettingActivity.this, SHARE_MEDIA.QQ, umAuthListener);

            }
        });



    }

    private void setUserPerform(ImageView settingimage, TextView settingname, String name, String iconurl) {
        if (!name.equals(iconurl)){
            layout.setVisibility(View.VISIBLE);
            Glide.with(SettingActivity.this).load(iconurl).into(settingimage);
            settingname.setText(name);
        }
        if (layout.getVisibility()==View.VISIBLE){
            button.setText("退出登录");
        }else {
            button.setText("请登录");
        }
    }

    private static final String TAG = "SettingActivity";
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调

        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(SettingActivity.this, "shouquan ", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences=SettingActivity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            Intent intent =new Intent(SettingActivity.this, MainActivity.class);

            if (data!=null) {
               String name = data.get("name");
               String iconurl = data.get("iconurl");
               editor.putString("name", name);
               editor.putString("iconurl", iconurl);
               editor.commit();
               intent.putExtra("name",name);
               intent.putExtra("iconurl",iconurl);
                setUserPerform(settingimage,settingname,name,iconurl);
               layout.setVisibility(View.VISIBLE);


           }else {
               editor.putString("name", "");
               editor.putString("iconurl", "");
               editor.commit();
               intent.putExtra("name","");
               intent.putExtra("iconurl","");
           }
            setResult(101,intent);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(SettingActivity.this, "授权失败，请检查是否安装该应用", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
//            Log.e(TAG, "onCancel: " );
            Intent intent =new Intent(SettingActivity.this, MainActivity.class);
            setResult(101,intent);
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
