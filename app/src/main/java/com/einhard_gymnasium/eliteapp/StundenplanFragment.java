package com.einhard_gymnasium.eliteapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;



public class StundenplanFragment extends Fragment {

    Spinner select_day;
    ArrayAdapter adapter;

    public StundenplanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stundenplan, container, false);
        view.findViewById(R.id.select_day);
        select_day = view.findViewById(R.id.select_day);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.select_day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_day.setAdapter(adapter);
        return view;
    }

}
