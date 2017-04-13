package com.example.admin.cinema2nf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Admin on 4/12/2017.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment;
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> listFragment){
        super(fm);
        this.listFragment=listFragment;
    }
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
