package com.example.dhima.backbase.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhima.backbase.Adapters.RecycleviewAdapter;
import com.example.dhima.backbase.MainActivity;
import com.example.dhima.backbase.Model.Cities;
import com.example.dhima.backbase.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitiesFragment extends Fragment {
    private static View view;
    RecyclerView citiesRecyclerview;
    EditText searchBox;
    RecycleviewAdapter mAdapter;
    ArrayList<Cities> AllcitiesList = new ArrayList<Cities>();
    ArrayList<Cities>  subcities = new ArrayList<Cities>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DownloadFilesTask().execute();
      /*  try {
            JSONArray obj = new JSONArray(loadJSONFromAsset(getActivity()));
            //ArrayList<Cities> cities = Cities.fromJson(obj);
            Type type = new TypeToken<List<Cities>>(){}.getType();
            AllcitiesList = new Gson().fromJson(loadJSONFromAsset(getActivity()),type);


            Collections.sort(AllcitiesList, new Comparator<Cities>()
            {
                @Override
                public int compare(Cities lhs, Cities rhs) {

                    return lhs.getName().compareToIgnoreCase(rhs.getName());
                }
            });

            //Toast.makeText(getActivity(), String.valueOf(AllcitiesList.size()), Toast.LENGTH_SHORT).show();

            subcities  = new ArrayList<>(AllcitiesList.subList(0,200));


        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    private void init() {

        citiesRecyclerview = view.findViewById(R.id.cities_recyclerview);
        searchBox = view.findViewById(R.id.searchbox);


        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {

                final List<Cities> filteredModelList = filter(AllcitiesList, query);
                mAdapter.replaceAll(filteredModelList);
                citiesRecyclerview.scrollToPosition(0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private List<Cities> filter(ArrayList<Cities> allcitiesList, CharSequence query) {


        final List<Cities> filteredCitiesList = new ArrayList<>();
        for (Cities city : allcitiesList) {
            final String text = city.getName().toLowerCase();
            if (text.startsWith(String.valueOf(query).toLowerCase())) {
                filteredCitiesList.add(city);
            }
        }
        return filteredCitiesList;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.citieslayout, container, false);
        init();
        citiesRecyclerview.setHasFixedSize(true);
        // use a linear layout manager

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        citiesRecyclerview.setLayoutManager(mLayoutManager);

        citiesRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1))
                    onScrolledToBottom();
            }
        });
        // Here specify the Adapter for the RecyclerView
        mAdapter = new RecycleviewAdapter(subcities);
        citiesRecyclerview.setAdapter(mAdapter);


        return view;
    }

    private void onScrolledToBottom() {
        //Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();

        if (subcities.size() < AllcitiesList.size()) {
            int x, y;
            if ((AllcitiesList.size() - subcities.size()) >= 200) {
                x = subcities.size();
                y = x + 200;
            } else {
                x = subcities.size();
                y = x + AllcitiesList.size() - subcities.size();
            }
            for (int i = x; i < y; i++) {
                subcities.add(AllcitiesList.get(i));
            }
            mAdapter.notifyDataSetChanged();
        }
    }


   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("cities.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }*/

    private class DownloadFilesTask extends AsyncTask<String, String, String > {

        private ProgressDialog dialog;
        public DownloadFilesTask() {
            dialog = new ProgressDialog(getActivity());
        }

        @Override
        protected String doInBackground(String... strings) {
            String json = null;
            try {
                InputStream is = getActivity().getAssets().open("cities.json");

                int size = is.available();

                byte[] buffer = new byte[size];

                is.read(buffer);

                is.close();

                json = new String(buffer, "UTF-8");

                return json;


            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Fetching data please wait...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            //Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
            Type type = new TypeToken<List<Cities>>(){}.getType();
            AllcitiesList = new Gson().fromJson(json,type);


            Collections.sort(AllcitiesList, new Comparator<Cities>()
            {
                @Override
                public int compare(Cities lhs, Cities rhs) {

                    return lhs.getName().compareToIgnoreCase(rhs.getName());
                }
            });

           // Toast.makeText(getActivity(), String.valueOf(AllcitiesList.size()), Toast.LENGTH_SHORT).show();

            subcities  = new ArrayList<>(AllcitiesList.subList(0,200));
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            mAdapter = new RecycleviewAdapter(subcities);
            citiesRecyclerview.setAdapter(mAdapter);


        }


    }
}
