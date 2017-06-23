package com.example.android.mynews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by learner on 21/6/2017.
 */
class ArticleLoader extends AsyncTaskLoader<List<Article>> {
    private static final String LOG_TAG = ArticleLoader.class.getName();
    private final String mUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, " onStartLoading, called");
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        Log.i(LOG_TAG, " loadInBackground, called");
        if (mUrl == null) {
            return null;
        }
        return QueryUtils.fetchArticleData(mUrl);
    }

}