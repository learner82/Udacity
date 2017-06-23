package com.example.android.musicapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view that shows the radio
        TextView radio = (TextView) findViewById(R.id.radio);

        // Set a click listener on that view

        radio.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the radio View is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent ti open the {@link RadioActivity}
                Intent radioIntent = new Intent(MainActivity.this, RadioActivity.class);
                //Start the new activity
                startActivity(radioIntent);
            }
        });

        // Find the view that shows the search
        TextView search = (TextView) findViewById(R.id.search);

        // Set a click listener on that view

        search.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the search View is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent ti open the {@link SearchActivity}
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                //Start the new activity
                startActivity(searchIntent);
            }
        });

        // Find the view that shows the store
        TextView store = (TextView) findViewById(R.id.store);

        // Set a click listener on that view

        store.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the store View is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the site that describes how to store data
                Uri webpage = Uri.parse("https://www.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) !=null){
                    startActivity(intent);
                }

            }
        });


    }

}
