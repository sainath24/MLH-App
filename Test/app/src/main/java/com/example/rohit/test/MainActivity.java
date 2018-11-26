package com.example.rohit.test;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static class data {
        String heading,bytext,location;
        public data(){
            heading = "hello";
            bytext = "byebye";
            location = "earth";

        }
    }



    public static class Frag2 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.frag2,null);
        }
    }

    Fragment fragment = null;

    private TextView mTextMessage;

    public void loadFragment(Fragment f) {
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag,fragment).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new frag1();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new Frag2();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    public void openFAB() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        final FloatingActionButton fab_amenities = (FloatingActionButton) findViewById(R.id.fab_amenities);
        final FloatingActionButton fab_sos = (FloatingActionButton) findViewById(R.id.fab_sos);
        final FloatingActionButton fab_volunteer = (FloatingActionButton) findViewById(R.id.fab_volunteer);

        final LinearLayout fab_amenities_layout = (LinearLayout) findViewById(R.id.fab_amenities_layout);
        final LinearLayout fab_sos_layout = (LinearLayout) findViewById(R.id.fab_sos_layout);
        final LinearLayout fab_volunteer_layout = (LinearLayout) findViewById(R.id.fab_volunteer_layout);

        final View trans_bg = findViewById(R.id.translucent_bg);
        fragment = new frag1();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag,fragment).commit();





        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fab_amenities.animate().translationYBy(-250);
                trans_bg.setVisibility(View.VISIBLE);
                fab_amenities_layout.animate().translationYBy(-250);
                fab_amenities_layout.setVisibility(View.VISIBLE);

                fab_sos_layout.animate().translationYBy(-500);
                fab_sos_layout.setVisibility(View.VISIBLE);

                fab_volunteer_layout.animate().translationYBy(-750);
                fab_volunteer_layout.setVisibility(View.VISIBLE);

                //fab_sos.animate().translationYBy(-500);
                //fab_volunteer.animate().translationYBy(-750);
            }
        });



    }

}
