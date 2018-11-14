package com.example.dhima.backbase.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dhima.backbase.Fragments.MapFragment;
import com.example.dhima.backbase.MainActivity;
import com.example.dhima.backbase.Model.Cities;
import com.example.dhima.backbase.R;


import java.util.List;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.MyViewHolder> {
    private List<Cities> mDataset;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView citiname;
        public MyViewHolder(View v) {
            super(v);
            citiname = v.findViewById(R.id.citiname);

        }
    }

    // Constructor
    public RecycleviewAdapter(List<Cities> myDataset) {


        mDataset = myDataset;
    }


    @Override
    public RecycleviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // child view added
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.citiname.setText(mDataset.get(position).getName()+", " +mDataset.get(position).getCountry());

        holder.citiname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragment mapfragment = new MapFragment();
                Bundle args= new Bundle();
                args.putDouble("lat",mDataset.get(position).getCoord().getLat());
                args.putDouble("long",mDataset.get(position).getCoord().getLon());
                args.putString("name",mDataset.get(position).getName());
                mapfragment.setArguments(args);
                MainActivity activity = (MainActivity) v.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, mapfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public void replaceAll(List<Cities> cities) {
        mDataset.clear();
        mDataset=cities;
        notifyDataSetChanged();


    }

}