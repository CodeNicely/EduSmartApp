package com.codenicely.edusmart.splash_screen.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.SharedPrefs;
import com.codenicely.edusmart.home.view.HomeActivity;
import com.codenicely.edusmart.splash_screen.model.RetrofitSplashScreenProvider;
import com.codenicely.edusmart.splash_screen.model.data.SplashScreenData;
import com.codenicely.edusmart.splash_screen.presenter.SplashScreenPresenter;
import com.codenicely.edusmart.splash_screen.presenter.SplashScreenPresenterImpl;
import com.codenicely.edusmart.welcome_screen.view.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends Activity implements SplashScreenView {
    private Handler handler;
    private SplashScreenPresenter splashScreenPresenter;
    private SharedPrefs sharedPrefs;
    @BindView(R.id.codeNicelyLogo)
    ImageView code_nicely_logo;

    @BindView(R.id.edusmart_logo)
    ImageView edu_smart_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.code_nicely_logo_small_colored).into(code_nicely_logo);
        Glide.with(this).load(R.drawable.edusmart_logo).into(edu_smart_logo);
/*

        Intent intent=new Intent(SplashScreenActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
*/

        splashScreenPresenter = new SplashScreenPresenterImpl(new RetrofitSplashScreenProvider(), this);
        splashScreenPresenter.sendFcm();
        sharedPrefs = new SharedPrefs(this);

    }

    @Override
    public void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {
        if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode < splashScreenData.getVersion_code() && !splashScreenData.isCompulsory_update()) {


            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            ad.setMessage("Please update the app for better experience");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "Not Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                    if (sharedPrefs.isLoggedIn()) {
                        startActivity(new Intent(SplashScreenActivity.this, SplashScreenPresenter.class));
                        finish();
                    } else if (sharedPrefs.isTeacherLoggedIn()) {
                        startActivity(new Intent(SplashScreenActivity.this, SplashScreenPresenter.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, SplashScreenPresenter.class));
                        finish();
                    }

                }
            });
            ad.show();


        } else if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode < splashScreenData.getVersion_code() && splashScreenData.isCompulsory_update()) {

            final AlertDialog ad = new AlertDialog.Builder(this)

                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            ad.setMessage("This is a compulsory Update . Please Update the app to enjoy our services");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.show();


        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (sharedPrefs.isLoggedIn() || sharedPrefs.isTeacherLoggedIn()) {
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        finish();
                    } else{
                        startActivity(new Intent(SplashScreenActivity.this,WelcomeActivity.class));
                        finish();
                    }

                }
            }, 1000);
        }
    }

    @Override
    public void onFailed() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPrefs.isLoggedIn()) {
                    startActivity(new Intent(SplashScreenActivity.this, SplashScreenPresenter.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, SplashScreenPresenter.class));
                    finish();
                }

            }
        }, 3000);

    }

}

