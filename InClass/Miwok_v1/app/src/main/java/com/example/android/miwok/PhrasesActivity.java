package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion (MediaPlayer mediaPlayer){
            //Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    private AudioManager mAudiomanager;

    private MediaPlayer mMediaPlayer;

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new
            AudioManager.OnAudioFocusChangeListener(){
                @Override
                public void onAudioFocusChange (int focusChange){
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        //The AUDIOFOCUS_LOSS_TRANSIENT case means that weve lost audio focus for a
                        //short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means
                        //that our app is allowed to continue playing sound but at a lower volume. We'll
                        //treat both cases the same way because our app is playing short sound files.

                        //Pause playback and reset player to the start of the file. That way, we can
                        // play the word from the beginning when we resume playback.
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                        // Stop playback and clean up resources
                        releaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create and setup the {@ link AudioManager} to request audio focus.
        mAudiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

       final ArrayList<Word> words = new ArrayList<Word>();
        //words.add("one"); for an ArrayList<Strings>
        words.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?","tinn┴ oyaase'n┴",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","mich┴ks┴s?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","┴┴n┴s'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I'm coming.","h┴┴' ┴┴n┴m",R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming.","┴┴n┴m",R.raw.phrase_im_coming));
        words.add(new Word("Let's go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.","┴nni'nem",R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long l){
                // Get the {@Word} object at the given position the user clicked on
                Word word = words.get(position);
                // Request audio focus so in order to play the audio file. The app needsto play
                //a short audio file, so we will request audio focus with a short amount of time
                //with AUDIOFOCUS_GAIN_TRANSIENT
                int result = mAudiomanager.requestAudioFocus(mAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    //with the current word
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());

                    //Start the audio file
                    mMediaPlayer.start();

                    //Setup a listener on the media player, so that we can stop and release the
                    //media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);


                }
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        //When the activity is stopped, release the media player resources because
        //we won't be playing any more sounds
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        //if the media player is not null, then it may be currently playing a sound.
        if(mMediaPlayer!=null){
            // Regardless of the current state if the media player, release its resources
            //because we no longer need it.
            mMediaPlayer.release();
            //Set the media player back to null. For our code, we've decided that setting
            //the media player to null is easy way to tell that the media player is not
            //configured to play an audio file at the moment.
            mMediaPlayer=null;
            mAudiomanager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
