package com.example.android.mynews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {
    private static final String GUARDIAN_API_URL =
            "https://content.guardianapis.com/search?show-fields=thumbnail";
    private static final int ARTICLE_LOADER_ID = 1;
    private static final String GUARDIAN_KEY = "&api-key=55124c24-c8c0-49b4-9b03-9ca01cf7e42e";
    private static final String LOG_TAG = MainActivity.class.getName();
    private ArticleAdapter mAdapter;
    private String query;
    private Bundle bundle;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i(LOG_TAG, "  OnCreate() run");

        ListView articleListView = (ListView) findViewById(R.id.list);
        progressBar = findViewById(R.id.progress_bar);
        TextView emptyState = (TextView) findViewById(R.id.empty_view);
        progressBar.setVisibility(View.VISIBLE);
        mAdapter = new ArticleAdapter(this, new ArrayList<Article>());

        articleListView.setAdapter(mAdapter);
        articleListView.setEmptyView(emptyState);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article currentArticleRead = mAdapter.getItem(position);
                Uri articleUri = Uri.parse(currentArticleRead.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                startActivity(websiteIntent);
            }
        });

        query = GUARDIAN_API_URL + GUARDIAN_KEY;
        LoaderManager loaderManager = getLoaderManager();

        if (loaderManager.getLoader(ARTICLE_LOADER_ID) != null) {
            loaderManager.initLoader(ARTICLE_LOADER_ID, bundle, MainActivity.this);
        }

        NetworkInfo networkInfo = checkInternetConnection();
        if (networkInfo != null && networkInfo.isConnected()) {
            bundle = new Bundle();
            bundle.putString("GUARDIAN_API", query);
            if (loaderManager.getLoader(ARTICLE_LOADER_ID) != null) {
                loaderManager.restartLoader(ARTICLE_LOADER_ID, bundle, MainActivity.this);
            }
            loaderManager.initLoader(ARTICLE_LOADER_ID, bundle, MainActivity.this);
            Log.i(LOG_TAG, "initloader() ");

        } else {

            mAdapter.clear();
            progressBar.setVisibility(View.GONE);
            emptyState.setVisibility(View.VISIBLE);
            emptyState.setText(R.string.no_internet);

        }
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        Log.i(LOG_TAG, "OnLoadFinished() run");

        mAdapter.clear();

        if (data != null && !data.isEmpty()) {

            mAdapter.addAll(data);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {

        Log.i(LOG_TAG, " OnCreateLoader run ");

        return new ArticleLoader(this, query);
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {

        Log.i(LOG_TAG, "OnLoaderReset() run");

        mAdapter.clear();
    }

    private NetworkInfo checkInternetConnection() {

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {

            Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_LONG).show();

        }
        return info;
    }
}