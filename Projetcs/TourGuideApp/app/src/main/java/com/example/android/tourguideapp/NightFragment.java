package com.example.android.tourguideapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NightFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.places_list, container, false);

        final ArrayList<Place> bars = new ArrayList<>();
        bars.add(new Place(R.string.brettos_bar, R.string.brettos_bar_desc, 10, 30948, R.drawable.brettos_bar));
        bars.add(new Place(R.string.dalliance_house, R.string.dalliance_house_desc, 10, 170984, R.drawable.dalliance_house));
        bars.add(new Place(R.string.baba_au_rum, R.string.baba_desc, 10, 21433, R.drawable.au_rum));
        bars.add(new Place(R.string.the_clumsies, R.string.the_clumsies_desc, 10, 84732, R.drawable.clumsies));

        PlaceAdapter itemsAdapter = new PlaceAdapter(getActivity(), bars);

        final ListView listView = (ListView) rootView.findViewById(R.id.places_list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}});

        return rootView;
    }
}
