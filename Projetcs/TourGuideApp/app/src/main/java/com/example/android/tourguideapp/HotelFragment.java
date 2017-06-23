package com.example.android.tourguideapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.places_list, container, false);
        final ArrayList<Place> hotels = new ArrayList<>();
        hotels.add(new Place(R.string.divani, R.string.divani_desc, 350, 30, R.drawable.divani));
        hotels.add(new Place(R.string.hilton, R.string.hilton_desc, 250, 170, R.drawable.hilton));
        hotels.add(new Place(R.string.grandResort, R.string.grandResort_desc, 400, 21, R.drawable.grandresort));
        hotels.add(new Place(R.string.megalhBretania, R.string.megalhBretania_desc, 220, 84, R.drawable.megalhbreatania));

        PlaceAdapter itemsAdapter = new PlaceAdapter(getActivity(), hotels);

        final ListView listView = (ListView) rootView.findViewById(R.id.places_list);

        listView.setAdapter(itemsAdapter);


        return rootView;
    }
}
