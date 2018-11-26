package com.example.rohit.hack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //Firebase auth object //
    private FirebaseAuth firebaseAuth;

    //View objects //
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private int fab_clicked = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    textViewUserEmail.setText("Clicled on Amenities");
                    return true;
                case R.id.navigation_dashboard:
                    textViewUserEmail.setText("Clicked on SOS");
                    return true;
                case R.id.navigation_notifications:
                    textViewUserEmail.setText("Clicked on Voluenteer");
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        firebaseAuth = FirebaseAuth.getInstance();

        ////getCurrentUser returns null if user is not logged in //
        if(firebaseAuth.getCurrentUser() == null){
            // Close Activity //
            finish();
            //Start Login Activity //
            startActivity(new Intent(this, LoginActivity.class));
        }

        //user is now the currentUser //
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //Initialise Views //
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //Display Email //
        textViewUserEmail.setText("Hola "+user.getEmail());

        //Add Listener to Button //
        buttonLogout.setOnClickListener(this);

        //Creating button Navigation object //
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // FAB objects Created //
        FloatingActionButton fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        final FloatingActionButton fab_amenities = (FloatingActionButton) findViewById(R.id.fab_amenities);
        final FloatingActionButton fab_sos = (FloatingActionButton) findViewById(R.id.fab_sos);
        final FloatingActionButton fab_volunteer = (FloatingActionButton) findViewById(R.id.fab_volunteer);
        //FAB Layouts created //
        final LinearLayout fab_amenities_layout = (LinearLayout) findViewById(R.id.fab_amenities_layout);
        final LinearLayout fab_sos_layout = (LinearLayout) findViewById(R.id.fab_sos_layout);
        final LinearLayout fab_volunteer_layout = (LinearLayout) findViewById(R.id.fab_volunteer_layout);

        //Translucent Background Created //
        final View trans_bg = findViewById(R.id.translucent_bg);
        trans_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fab_clicked==1)
                {
                    trans_bg.setVisibility(View.GONE);
                    fab_amenities_layout.animate().translationYBy(+250);
                    fab_amenities_layout.setVisibility(View.INVISIBLE);

                    fab_sos_layout.animate().translationYBy(500);
                    fab_sos_layout.setVisibility(View.INVISIBLE);

                    fab_volunteer_layout.animate().translationYBy(750);
                    fab_volunteer_layout.setVisibility(View.INVISIBLE);
                    fab_clicked = 0;
                }
            }
        });

        //Action Listener for FAB ADD //

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fab_clicked == 0) {
                    trans_bg.setVisibility(View.VISIBLE);
                    fab_amenities_layout.animate().translationYBy(-250);
                    fab_amenities_layout.setVisibility(View.VISIBLE);

                    fab_sos_layout.animate().translationYBy(-500);
                    fab_sos_layout.setVisibility(View.VISIBLE);

                    fab_volunteer_layout.animate().translationYBy(-750);
                    fab_volunteer_layout.setVisibility(View.VISIBLE);
                    fab_clicked = 1;
                } else {
                    trans_bg.setVisibility(View.GONE);
                    fab_amenities_layout.animate().translationYBy(+250);
                    fab_amenities_layout.setVisibility(View.INVISIBLE);

                    fab_sos_layout.animate().translationYBy(500);
                    fab_sos_layout.setVisibility(View.INVISIBLE);

                    fab_volunteer_layout.animate().translationYBy(750);
                    fab_volunteer_layout.setVisibility(View.INVISIBLE);
                    fab_clicked = 0;

                }
            }
        });
        fab_amenities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FAB","CLICKED");
            }
        });
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed //
        if(view == buttonLogout){
            //SignOut //
            firebaseAuth.signOut();
            //Close ProfileActivity
            finish();
            //Start LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}