package com.example.android.cookies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

/**
 * Called when the cookie should be eaten
 */

    public void eatCookie(View view){
    //Find reference to the ImageView inthe layout.Change theimage.
        ImageView imageView = (ImageView) findViewById(R.id.android_cookie_image_view);
        imageView.setImageResource(R.drawable.after_cookie);
    //Find a reference to the TextView in the layout. Change the text.
        TextView textView = (TextView) findViewById( R.id.status_text_view);
        textView.setText("I'm so full");
    }
}