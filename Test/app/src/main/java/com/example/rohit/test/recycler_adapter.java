package com.example.rohit.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.ProductViewHolder> {

    Context c;
    ArrayList<MainActivity.data> ad = new ArrayList<>();

    public recycler_adapter(Context c,ArrayList<MainActivity.data> ad) {
        this.c = c;
        this.ad = ad;

    }

    @NonNull
    @Override
    public recycler_adapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater l = LayoutInflater.from(c);
        View view = l.inflate(R.layout.recycler_layout,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_adapter.ProductViewHolder productViewHolder, int i) {
        MainActivity.data d = ad.get(i);

        productViewHolder.ht.setText(d.heading);
        productViewHolder.bytext.setText(d.bytext);
        productViewHolder.location.setText(d.location);

    }

    @Override
    public int getItemCount() {
        return ad.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView ht,bytext,location;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            ht = itemView.findViewById(R.id.head);
            bytext = itemView.findViewById(R.id.bytext);
            location = itemView.findViewById(R.id.location);
        }
    }
}

