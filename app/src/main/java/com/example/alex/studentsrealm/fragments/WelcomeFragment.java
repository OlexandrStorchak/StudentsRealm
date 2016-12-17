package com.example.alex.studentsrealm.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.alex.studentsrealm.R;

/**
 * Created by Alex on 15.12.2016.
 */

public class WelcomeFragment extends Fragment {
    private static final String TAG ="log" ;

    ImageView nextBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.welcome_screen, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nextBtn = (ImageView)view.findViewById(R.id.welcome_screen_image_next_id);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseRecyclerFragment dbFragment = new DataBaseRecyclerFragment();

                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame_id, dbFragment);
                fragmentTransaction.commit();
                Log.d(TAG, "onClick: NEXT");
            }
        });
    }
}
