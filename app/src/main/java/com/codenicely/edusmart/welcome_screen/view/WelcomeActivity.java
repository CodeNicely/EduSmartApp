package com.codenicely.edusmart.welcome_screen.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.Keys;
import com.codenicely.edusmart.helper.SharedPrefs;
import com.codenicely.edusmart.login.view.LoginActivity;
import com.codenicely.edusmart.welcome_screen.model.data.WelcomePageDetails;
import com.codenicely.edusmart.welcome_screen.model.RetrofitScreenSlider;
import com.codenicely.edusmart.welcome_screen.presenter.Slider;
import com.codenicely.edusmart.welcome_screen.presenter.SliderImp;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView {

     private ViewPager viewPager;
     private ProgressBar progressBar;
    private WelcomeViewPagerAdapter welcomeViewPagerAdapter;
    private Slider slider;
    private Button button;
    private SharedPrefs sharedPrefs;
     private WelcomeView welcomeView;

    @BindView(R.id.page_number)
    TextView page_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        ButterKnife.bind(this);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        initialise();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                page_number.setText("Page -  "+String.valueOf(position+1));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public void initialise(){
        welcomeViewPagerAdapter =new WelcomeViewPagerAdapter(this);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        slider=new SliderImp(this,new RetrofitScreenSlider());
        slider.getSlides();
        welcomeViewPagerAdapter =new WelcomeViewPagerAdapter(this);
        viewPager.setAdapter(welcomeViewPagerAdapter);
        button=(Button)findViewById(R.id.button_login);


    }
    public  void login(View v) {
        if (v.getId() == R.id.button_login_teacher) {
            Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
            sharedPrefs.setLoginType(0);
            i.putExtra(Keys.KEY_LOGIN,"0");
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
            sharedPrefs.setLoginType(1);
            i.putExtra(Keys.KEY_LOGIN,"1");
            startActivity(i);
            finish();
        }
    }


    @Override
    public void showProgressBar(boolean show) {
        if (show)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void setSlides(List<WelcomePageDetails> welcomePageDetailsList) {
        welcomeViewPagerAdapter.setImageList(welcomePageDetailsList);
        welcomeViewPagerAdapter.notifyDataSetChanged();
        pageSwitcher(3);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }

    Timer timer;
    int page = 1;

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    // this is an inner class
    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (page > 3) { // In my case the number of pages are 5
                        timer.cancel();
                        // Showing a toast for just testing purpose
/*
                        Toast.makeText(getApplicationContext(), "Timer stoped",
                                Toast.LENGTH_LONG).show();
*/
                    } else {
                        viewPager.setCurrentItem(page++);
                    }
                }
            });

        }
    }

}
