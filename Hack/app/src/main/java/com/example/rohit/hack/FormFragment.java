package com.example.rohit.hack;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*class AmenitiesPost {
    String heading,description,address,pid;
    Double lat,lon;

    AmenitiesPost() {

    }
    AmenitiesPost(String h,String d,String a) {
        heading = h;
        description = d;
        address = a;
    } */


//}

public class FormFragment extends Fragment {

    DatabaseReference db;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.form_fragment_layout,null);

        TextView formName = (TextView) rootView.findViewById(R.id.form_name);

        if(ProfileActivity.fabOptionClicked == 1) {
            formName.setText("Amenities");
            db = FirebaseDatabase.getInstance().getReference("Amenities");
        }

        else if(ProfileActivity.fabOptionClicked == 2) {
            formName.setText("SOS");
            db = FirebaseDatabase.getInstance().getReference("SOS");
        }

        else if(ProfileActivity.fabOptionClicked == 3) {
            formName.setText("Volunteer");
            db = FirebaseDatabase.getInstance().getReference("Volunteer");
        }




        final EditText description = (EditText) rootView.findViewById(R.id.amenities_form_description);
        final EditText address = (EditText) rootView.findViewById(R.id.amenities_form_address);

        final EditText heading = (EditText) rootView.findViewById(R.id.amenities_form_heading);
        Button submit = rootView.findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Post post = new Post();
                post.heading = heading.getEditableText().toString();
                post.description = description.getEditableText().toString();
                post.address = address.getEditableText().toString();

                //AmenitiesPost amenitiesPost = new AmenitiesPost(heading.getEditableText().toString(),description.getEditableText().toString(),address.getEditableText().toString());

                Geocoder geocoder = new Geocoder(getContext());
                List<Address> addresses = new ArrayList<>();
                try {
                    addresses = geocoder.getFromLocationName(post.address,5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address location = addresses.get(0);
                Log.i("COMEON",String.valueOf(location.getLatitude()));
                post.lat = location.getLatitude();
                post.lon = location.getLongitude();

                post.pid = db.push().getKey();
                db.child(post.pid).setValue(post);
            }
        });

        return rootView;
    }
}
