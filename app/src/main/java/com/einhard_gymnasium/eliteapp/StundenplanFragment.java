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

    Spinner select_day_spinner;
    ArrayAdapter<CharSequence> adapter;

    public StundenplanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stundenplan, container, false);
        view.findViewById(R.id.select_day);
        select_day_spinner = view.findViewById(R.id.select_day);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.select_day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_day_spinner.setAdapter(adapter);
        select_day_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity(), "" + "ausgewählt.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;

    }
}

/*   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        select_day_spinner = getActivity().findViewById(R.id.select_day);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.select_day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_day_spinner.setAdapter(adapter);
        select_day_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getContext(), "" + "ausgewählt.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        }); */
//}

//}
