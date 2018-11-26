package com.example.rkjc.news_app_2;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
public abstract class NewsItemRoomDatabase extends RoomDatabase {

    public abstract NewsItemDao newsItemDao();

    public static volatile NewsItemRoomDatabase INSTANCE;

    public static NewsItemRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (NewsItemRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsItemRoomDatabase.class, "news_database")
                            .fallbackToDestructiveMigration()
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            new PopulateDbAsync(INSTANCE).execute();
//        }
//    };
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final NewsItemDao mDao;
//        List<NewsItem> newsItems;
//
//        PopulateDbAsync(NewsItemRoomDatabase db) {
//            mDao = db.newsItemDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            mDao.clearAll();
//
//            URL url = NetworkUtils.buildUrl();
//            String JSONString = "";
//            try {
//                JSONString = NetworkUtils.getResponseFromHttpUrl(url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            newsItems = JsonUtils.parseNews(JSONString);
//
//            for (NewsItem item : newsItems) {
//                mDao.insert(item);
//            }
//            return null;
//        }
//    }
}
