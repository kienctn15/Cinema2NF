package com.example.admin.cinema2nf;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 4/13/2017.
 */

public class TabLayoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.tablayoutfragment, container, false);
        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Campusplan"));
        tabLayout.addTab(tabLayout.newTab().setText("Raumplan"));

        viewPager.setAdapter(new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Campusplan");
        tabLayout.getTabAt(1).setText("Raumplan");
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return inflatedView;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    SecondFragment tab1 = new SecondFragment();
                    return tab1;
                case 1:
                    ThirdFragment tab2 = new ThirdFragment();
                    return tab2;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
