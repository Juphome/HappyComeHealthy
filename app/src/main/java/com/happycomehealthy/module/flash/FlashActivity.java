package com.happycomehealthy.module.flash;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.module.login.LoginActivity;
import com.happycomehealthy.module.main.MainActivity;
import com.happycomehealthy.utils.PreferenceUtils;

public class FlashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        ConstraintLayout layoutSplash=findViewById(R.id.activity_flash);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(1000);//设置动画播放时长1000毫秒（1秒）
        layoutSplash.startAnimation(alphaAnimation);
        //设置动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {

                boolean logined = isLogined();
                if(logined){

                    Intent intent=new Intent(FlashActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {

                    Intent intent=new Intent(FlashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private boolean isLogined() {
        String account = PreferenceUtils.getString(Constants.WORKER_ACCOUNT, "");
        String password = PreferenceUtils.getString(Constants.WORKER_PASSWORD, "");
        if(TextUtils.isEmpty(account) || TextUtils.isEmpty(password)){
            return false;
        }else {
            return true;
        }
    }

}
