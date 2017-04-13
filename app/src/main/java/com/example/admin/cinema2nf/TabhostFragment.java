package com.example.admin.cinema2nf;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

import com.example.admin.cinema2nf.MyFragmentAdapter;
import com.example.admin.cinema2nf.R;
import com.example.admin.cinema2nf.SecondFragment;
import com.example.admin.cinema2nf.ThirdFragment;


public class TabhostFragment extends Fragment implements OnTabChangeListener,
        OnPageChangeListener {

    private TabHost tabHost;
    private ViewPager viewPager;
    private MyFragmentAdapter myViewPagerAdapter;
    int i = 0;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.tabhost_fragment_layout, container, false);

        i++;

        this.initializeTabHost(savedInstanceState);
        this.initializeViewPager();

        return v;
    }

    // fake content for tabhost
    class FakeContent implements TabContentFactory {
        private final Context mContext;

        public FakeContent(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }

    private void initializeViewPager() {
        List<Fragment> fragments = new Vector<Fragment>();

        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new SecondFragment());


        this.myViewPagerAdapter = new MyFragmentAdapter(
                getChildFragmentManager(), fragments);
        this.viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        this.viewPager.setAdapter(this.myViewPagerAdapter);
        this.viewPager.setOnPageChangeListener(this);

    }

    private void initializeTabHost(Bundle args) {

        tabHost = (TabHost) v.findViewById(android.R.id.tabhost);
        tabHost.setup();

        for (int i = 1; i <= 3; i++) {

            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec("Tab " + i);
            tabSpec.setIndicator("Tab " + i);
            tabSpec.setContent(new FakeContent(getActivity()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);

        HorizontalScrollView hScrollView = (HorizontalScrollView) v
                .findViewById(R.id.hScrollView);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()
                - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        this.tabHost.setCurrentTab(position);
    }
}
