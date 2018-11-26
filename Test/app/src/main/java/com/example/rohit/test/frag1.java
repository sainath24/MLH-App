package com.example.rohit.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class frag1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.frag1,null);
        ArrayList<MainActivity.data> ad = new ArrayList<>();
        ad.add(new MainActivity.data());
        ad.add(new MainActivity.data());

        RecyclerView r = (RecyclerView) root.findViewById(R.id.recycler);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler_adapter ra = new recycler_adapter(getActivity(),ad);
        r.setAdapter(ra);

        return root;
    }
}