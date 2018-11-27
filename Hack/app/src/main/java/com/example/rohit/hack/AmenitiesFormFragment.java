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

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AmenitiesPost {
    String heading,description,address,pid;
    Double lat,lon;

    AmenitiesPost() {

    }
    AmenitiesPost(String h,String d,String a) {
        heading = h;
        description = d;
        address = a;
    }

    public void getLocation(Context c) {

        Geocoder geocoder = new Geocoder(c);
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocationName(address,5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address location = addresses.get(0);
        Log.i("COMEON",String.valueOf(location.getLatitude()));
        lat = location.getLatitude();
        lon = location.getLongitude();
        //latLng = new LatLng(location.getLatitude(),location.getLongitude());

    }
}

public class AmenitiesFormFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.amenities_form_fragment_layout,null);


        final DatabaseReference db = FirebaseDatabase.getInstance().getReference("Amenities");

        final EditText description = (EditText) rootView.findViewById(R.id.amenities_form_description);
        final EditText address = (EditText) rootView.findViewById(R.id.amenities_form_address);

        final EditText heading = (EditText) rootView.findViewById(R.id.amenities_form_heading);
        Button submit = rootView.findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AmenitiesPost amenitiesPost = new AmenitiesPost(heading.getEditableText().toString(),description.getEditableText().toString(),address.getEditableText().toString());

                amenitiesPost.getLocation(getContext());

                amenitiesPost.pid = db.push().getKey();
                db.child(amenitiesPost.pid).setValue(amenitiesPost);
            }
        });

        return rootView;
    }
}
