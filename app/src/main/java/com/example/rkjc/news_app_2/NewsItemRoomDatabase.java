package com.example.rkjc.news_app_2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

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
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
