package com.codenicely.edusmart.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.details.ItemDetailsFragment;
import com.codenicely.edusmart.helper.Keys;
import com.codenicely.edusmart.helper.SharedPrefs;
import com.codenicely.edusmart.home.model.data.HomeListData;
import com.codenicely.edusmart.login.view.LoginActivity;
import com.codenicely.edusmart.spot_upload.view.UploadFragment;
import com.codenicely.edusmart.thread.view.ThreadFragment;
import com.codenicely.edusmart.welcome_screen.view.WelcomeActivity;

import java.util.List;

import butterknife.BindView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private SharedPrefs sharedPrefs;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPrefs = new SharedPrefs(getApplicationContext());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addFragment(new HomeFragment(), "HomeFragment");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action
            addFragment(new HomeFragment(), "HomeFragment");
            getSupportActionBar().hide();

        } else if (id == R.id.nav_logout) {

//            addFragment(new UploadFragment(), "Upload");
            sharedPrefs.setLogin(false);
            sharedPrefs.setTeacherLogin(false);
            Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            getSupportActionBar().hide();

        } else if (id == R.id.nav_threads) {

            setFragment(new ThreadFragment(), "Thread Fragment");
            getSupportActionBar().hide();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment, String title) {

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }

    }

    public void addFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            //     getSupportActionBar().setTitle(title);
        }

    }

    protected void showDetails(String file_url, String deadline, String description, String timestamp,
                               String title, int file_type) {
        Bundle bundle = new Bundle();
        bundle.putString("file_url", file_url);
        bundle.putString("deadline", deadline);
        bundle.putString("description", description);
        bundle.putString("timestamp", timestamp);
        bundle.putString("title", title);
        bundle.putInt("file_type", file_type);
        ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
        itemDetailsFragment.setArguments(bundle);
        addFragment(itemDetailsFragment, "Item Details Fragement");
    }
}
