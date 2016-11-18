package com.example.ok.madicalalatheer.Main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ok.madicalalatheer.AddGoal.AddGoal;
import com.example.ok.madicalalatheer.R;

public class Login extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        // get the display mode
      //  int displaymode = getResources().getConfiguration().orientation;
     //   if (displaymode == 1) { // it portrait mode
            LoginFragment f1 = new LoginFragment();
            fragmentTransaction.replace(android.R.id.content, f1);
     /*   } else {// its landscape
            Fragment2 f2 = new Fragment2();
            fragmentTransaction.replace(android.R.id.content, f2);
        }*/
        fragmentTransaction.commit();


    }



}
