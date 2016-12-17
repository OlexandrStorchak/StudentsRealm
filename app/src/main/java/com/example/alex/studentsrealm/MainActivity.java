package com.example.alex.studentsrealm;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.alex.studentsrealm.fragments.DataBaseRecyclerFragment;
import com.example.alex.studentsrealm.fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            WelcomeFragment fragmentWelcome = new WelcomeFragment();
            fragmentTransaction.add(R.id.main_frame_id, fragmentWelcome);
            fragmentTransaction.commit();

            getSupportActionBar().setBackgroundDrawable(
                    getResources().getDrawable(R.mipmap.action_bar_bg));




        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
