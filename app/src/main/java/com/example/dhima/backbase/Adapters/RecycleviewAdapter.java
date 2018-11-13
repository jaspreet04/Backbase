package com.example.dhima.backbase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.citiname.setText(mDataset.get(position).getName()+", " +mDataset.get(position).getCountry());


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