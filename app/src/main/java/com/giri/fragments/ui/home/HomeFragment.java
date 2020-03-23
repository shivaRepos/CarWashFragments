package com.giri.fragments.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.giri.fragments.R;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ViewPager pager;

    ImageAdapter adapter;

    int currentItem=0;

    int[] images={R.drawable.welcome,R.drawable.wash,R.drawable.wash1,R.drawable.wash2};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        pager = root.findViewById(R.id.pager);
         adapter = new ImageAdapter(getActivity(),images);
         pager.setAdapter(adapter);

         final Handler handler=new Handler();
         final Runnable runnable=new Runnable() {
             @Override
             public void run()
             {
                 if (currentItem == images.length)
                     currentItem=0;
                 pager.setCurrentItem(currentItem++,true);
             }
         };

        TimerTask task= new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        };

        Timer timer= new Timer();
        timer.schedule(task,500,3000);
        return root;
    }
}