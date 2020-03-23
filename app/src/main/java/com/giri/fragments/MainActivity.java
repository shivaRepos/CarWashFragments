package com.giri.fragments;

import android.os.Bundle;

import com.giri.fragments.ui.gallery.GalleryFragment;
import com.giri.fragments.ui.home.HomeFragment;
import com.giri.fragments.ui.send.SendFragment;
import com.giri.fragments.ui.share.ShareFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FrameLayout nav_host_fragment;
    BottomNavigationView bottomnav;
    String TAG;
    FragmentManager manager=getSupportFragmentManager();
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav_host_fragment = findViewById(R.id.nav_host_fragment);

        getIds();

        setToolbar();

        bottomnav.setOnNavigationItemSelectedListener(this);

        addFragment();

    }

    private void addFragment() {
        transaction=manager.beginTransaction();
        transaction.add(R.id.nav_host_fragment,new HomeFragment());
        transaction.commit();
    }

    private void setToolbar() {

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_black_24dp));

        toolbar.setTitle(getResources().getString(R.string.app_name));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void getIds() {
        bottomnav = findViewById(R.id.bottomnav);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.home :
                Log.e(TAG,"home");
                menuItem.setChecked(true);
                replaceFragment(new HomeFragment());
                break;
            case R.id.account :
                Log.e(TAG,"account");
                replaceFragment(new GalleryFragment());
                menuItem.setChecked(true);
                break;
            case R.id.cart:
                Log.e(TAG,"cart");
                replaceFragment(new SendFragment());
                menuItem.setChecked(true);
                break;
            case R.id.chat :
                Log.e(TAG,"chat");
                replaceFragment(new ShareFragment());
                menuItem.setChecked(true);
                break;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment,fragment);
        transaction.commit();
    }
}
