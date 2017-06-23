package com.example.android.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Find the view that shows the top10_europe
        Button top10_europe = (Button) findViewById(R.id.top10_europe);

        // Set a click listener on that view

        top10_europe.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the radio View is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent ti open the {@link Top10EuropeActivity}
                Intent top10EIntent = new Intent(SearchActivity.this, Top10EuropeActivity.class);
                //Start the new activity
                startActivity(top10EIntent);
            }
        });

        // Find the view that shows the top10_country
        Button top10_country = (Button) findViewById(R.id.top10_country);

        // Set a click listener on that view

        top10_country.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the radio View is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent ti open the {@link Top10Country}
                Intent top10CIntent = new Intent(SearchActivity.this, Top10Country.class);
                //Start the new activity
                startActivity(top10CIntent);
            }
        });

    }

    public void searchResults() {
        EditText searchEngine = (EditText) findViewById(R.id.search_bar);
        String searchResults = searchEngine.getText().toString();
    }
}
