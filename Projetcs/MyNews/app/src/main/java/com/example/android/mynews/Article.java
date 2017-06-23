package com.example.android.mynews;

import android.graphics.drawable.Drawable;

/**
 * Created by learner on 21/6/2017.
 */

public class Article {

    private final String mWebTitle;
    private final String mSectionName;
    private final Drawable mArticleThumbnail;
    private final String mUrl;

    public Article(String webTitle, String sectionName, Drawable articleThumbnail, String url) {
        mWebTitle = webTitle;
        mSectionName = sectionName;
        mArticleThumbnail = articleThumbnail;
        mUrl = url;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public Drawable getArticleThumbnail() {
        return mArticleThumbnail;
    }

    public String getUrl() {
        return mUrl;
    }
}
