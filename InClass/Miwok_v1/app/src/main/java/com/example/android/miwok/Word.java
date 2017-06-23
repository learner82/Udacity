package com.example.android.miwok;

/**
 * Created by trifon on 30/4/2017.
 * {@link Word} represents a vacabulary work that the user wants to learn.
 * It contains a default translation, a Miwok translation and an image for that word
 */

public class Word {

    /** Default translation for the word*/
    private String mDeafaultTranslation;

    /**Miwok translation for the word*/ /** to m to bazw mprostaapo tis variables gia na deixnw oti einai private*/
    private String mMiwokTranslation;
    /** Image resource ID for the word*/
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudioResourceId;
    /**
     * Create a new Word object
     * @param defaultTranslation is the word in a lang that hte user is already familiar with
     * @param miwokTranslation is the word in the Miwok lang
     */
    public Word (String defaultTranslation, String miwokTranslation, int audioResourceId){
        mDeafaultTranslation = defaultTranslation;
        mAudioResourceId=audioResourceId;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * Create a new Word object
     * @param defaultTranslation is the word in a lang that hte user is already familiar with
     * @param miwokTranslation is the word in the Miwok lang
     * @param imageResourceId is the drawable resource id for the image asset
     */
    public Word (String defaultTranslation, String miwokTranslation, int imageResourceId,
                 int audioResourceId){
        mAudioResourceId=audioResourceId;
        mDeafaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the default translation of the word
     */
    public  String getDefaultTranslation () {
         return  mDeafaultTranslation;
    }
    /**
     * Get the miwok translation of the word
     */
    public String getMiwokTranslation ()
    {
        return  mMiwokTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
    // Return the audio Resource Id of the word.
    public  int getAudioResourceId(){
        return mAudioResourceId;
    }
}
