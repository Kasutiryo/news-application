package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private NewsItemViewModel mNewsItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //  new NewsQueryTask().execute();

        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.news_recyclerview);
        mAdapter = new NewsRecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNewsItemViewModel.loadAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                mAdapter.setNewsItems(newsItems);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_search) {
            mNewsItemViewModel.loadAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
                @Override
                public void onChanged(@Nullable List<NewsItem> newsItems) {
                    mAdapter.setNewsItems(newsItems);
                }
            });            Toast.makeText(this, "News updated!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public class NewsQueryTask extends AsyncTask<Void, Void, String> {
//
//        private final String LOG_TAG =
//                NewsQueryTask.class.getSimpleName();
//        @Override
//        protected String doInBackground(Void... voids) {
//            URL url = NetworkUtils.buildUrl();
//            String JSONString = "";
//            try {
//                JSONString = NetworkUtils.getResponseFromHttpUrl(url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return JSONString;
//        }
//
//        @Override
//        protected void onPostExecute(String JSONString) {
//            super.onPostExecute(JSONString);
//            Log.d(LOG_TAG, JSONString);
//            mAdapter = new NewsRecyclerViewAdapter(MainActivity.this, JsonUtils.parseNews(JSONString));
//            mRecyclerView.setAdapter(mAdapter);
//        }
//    }
}

