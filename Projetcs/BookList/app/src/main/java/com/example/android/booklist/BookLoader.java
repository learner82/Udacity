package com.example.android.booklist;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by learner on 21/6/2017.
 */

class BookLoader extends AsyncTaskLoader<List<Book>> {

    private static final String LOG_TAG = BookLoader.class.getName();
    private final String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, " onStartLoading,called");
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        Log.i(LOG_TAG, " loadInBackground,called");
        if (mUrl == null) {
            return null;
        }
        return QueryUtils.fetchBookData(mUrl);
    }
}