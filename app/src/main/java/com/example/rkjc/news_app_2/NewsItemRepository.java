package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;

    NewsItemRepository(Application application) {
        NewsItemRoomDatabase db = NewsItemRoomDatabase.getDatabase(application);
        mNewsItemDao = db.newsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    LiveData<List<NewsItem>> loadAllNewsItems() {
        return mAllNewsItems;
    }

    public void insert (List<NewsItem> newsItems) {
        new insertAsyncTask(mNewsItemDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao mAsyncTaskDao;
        private List<NewsItem> newsItems;
        insertAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... voids) {

            mAsyncTaskDao.clearAll();

            URL url = NetworkUtils.buildUrl();
            String JSONString = "";
            try {
                JSONString = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            newsItems = JsonUtils.parseNews(JSONString);

            mAsyncTaskDao.insert(newsItems);
            return null;
        }
    }
}
