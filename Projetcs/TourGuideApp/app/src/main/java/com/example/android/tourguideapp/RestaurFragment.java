package com.example.android.tourguideapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.places_list, container, false);
        final ArrayList<Place> rest = new ArrayList<>();
        rest.add(new Place(R.string.barbaLazaros, R.string.barbaLazaros_desc, 20, 3000, R.drawable.barbalazaros));
        rest.add(new Place(R.string.lastrega, R.string.lastrega_desc, 18, 1705, R.drawable.lastrega));
        rest.add(new Place(R.string.psarades, R.string.psarades_desc, 20, 21225, R.drawable.psarades));
        rest.add(new Place(R.string.trigwno, R.string.trigwno_desc, 19, 84, R.drawable.trigwno));

        PlaceAdapter itemsAdapter = new PlaceAdapter(getActivity(), rest);

        final ListView listView = (ListView) rootView.findViewById(R.id.places_list);

        listView.setAdapter(itemsAdapter);


        return rootView;
    }
}
