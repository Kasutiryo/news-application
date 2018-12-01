package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private NewsItemViewModel mNewsItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate our ViewModel variable
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        //Store our RecyclerView from our layout
        mRecyclerView = findViewById(R.id.news_recyclerview);

        //Instantiate our ViewAdapter
        mAdapter = new NewsRecyclerViewAdapter(this);

        //Give our RecyclerView a reference to our ViewAdapter
        mRecyclerView.setAdapter(mAdapter);

        //Give our RecyclerView a reference to our LinearLayout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Update our ViewModel with any news that may be cached on the device
        mNewsItemViewModel.loadAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                mAdapter.setNewsItems(newsItems);
            }
        });

        //Schedule our annoying sync Job Service
        NewsSyncUtil.scheduleFireBaseJobDispatcherSync(this);
    }

    /**
     *
     * @param menu
     * @return True if a Menu was created successfully
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Creates the menu button to "Refresh"
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Executes a certain action based on the menu item that was clicked
     * @param item, refers to menu button that was clicked
     * @return True an action was executed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //If the "Refresh" button was pressed
        if(item.getItemId() == R.id.action_search) {
            //Call the news API and update the local database with new information
            mNewsItemViewModel.loadAllNewsItems();
            //Calls a toast item to give user the satisfaction that update worked
            Toast.makeText(this, "News updated!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

