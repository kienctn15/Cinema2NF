package com.example.admin.cinema2nf;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.cinema2nf.TextTabhost.TextTabhostFragment;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    TextView tv_header_name, tv_header_name_1;
    TextView nav_logout;
    TabHost tabHost;
    FragmentManager fragmentManager;
    public final static String ACTION_UPDATE = "actionUpdate";
    private BroadcastReceiver updateReceiver;

    UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         fragmentManager = getSupportFragmentManager();

        session = new UserSessionManager(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (session.checkLogin()) {
            HashMap<String, String> user = session.getUserDetails();
            String username = user.get(UserSessionManager.KEY_USERNAME);
            String password = user.get(UserSessionManager.KEY_PASSWORD);

            View headerView = navigationView.getHeaderView(0);
            tv_header_name = (TextView) headerView.findViewById(R.id.tv_header_name);
            tv_header_name_1 = (TextView) headerView.findViewById(R.id.tv_header_name_1);
            tv_header_name.setText("username: " + username);
            tv_header_name_1.setText("password: " + password);
            nav_logout = (TextView) headerView.findViewById(R.id.nav_logout);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_signin_layout).setVisible(false);
        }


        //auto run Text TabhostFragment
        fragmentManager.beginTransaction().replace(R.id.content_frame, new TextTabhostFragment())
                .addToBackStack(null)
                .commit();

// get data from fragment send to activity
//        updateReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                View headerView = navigationView.getHeaderView(0);
//                tv_header_name = (TextView) headerView.findViewById(R.id.tv_header_name);
//                tv_header_name_1 = (TextView) headerView.findViewById(R.id.tv_header_name_1);
//                tv_header_name.setText(intent.getStringExtra("username"));
//                tv_header_name_1.setText(intent.getStringExtra("username"));
//
//            }
//        };
//        registerReceiver(updateReceiver, new IntentFilter(ACTION_UPDATE));


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
        getMenuInflater().inflate(R.menu.main, menu);
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

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.nav_signin_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SigninFragment())
                    .addToBackStack(null)
                    .commit();


        } else if (id == R.id.nav_second_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SecondFragment())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_third_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ThirdFragment())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_movies) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MovieAllFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_share) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new TabLayoutFragment())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_logout) {

            session.logoutUser();
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_signin_layout).setVisible(true);
            tv_header_name.setText("GUEST");
            tv_header_name_1.setText("Please Login for your benefit. Thanks You!");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
