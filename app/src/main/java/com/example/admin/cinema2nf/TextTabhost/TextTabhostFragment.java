package com.example.admin.cinema2nf.TextTabhost;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.cinema2nf.R;

/**
 * Created by Admin on 4/13/2017.
 */

public class TextTabhostFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.text_tahost,container,false);

        Fragment showingMovieFragment = new ShowingMovieFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_text_tabhost, showingMovieFragment).addToBackStack(null).commit();


        return myView;
    }


}

