package com.example.android.tourguideapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;


class PlaceAdapter extends ArrayAdapter<Place> {

    PlaceAdapter(@NonNull Activity context, ArrayList<Place> places) {
        super(context, 0 ,places);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // check if the current view is reused else inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        //get the object located at position
        Place place_item = getItem(position);
        //find the textview in list_item with id default_text_view and price_text_view

        TextView placeNameTextView = ButterKnife.findById(listItemView,R.id.textView_place_name);
        TextView descriptionTextView = ButterKnife.findById(listItemView,R.id.textView_place_description);
        TextView priceTextView = ButterKnife.findById(listItemView,R.id.textView_place_price);
        TextView likesTextView = ButterKnife.findById(listItemView,R.id.textView_place_likes);
        ImageView mImageView = ButterKnife.findById(listItemView,R.id.imageView);

        assert place_item != null;
        placeNameTextView.setText(place_item.getPlaceName());
        descriptionTextView.setText(place_item.getDescription());
        likesTextView.setText("Likes ("+String.valueOf(place_item.getLikes())+")");
        priceTextView.setText(String.valueOf(place_item.getPrice()));
        mImageView.setImageResource(place_item.getImageResourceId());

        return listItemView;
    }
}
