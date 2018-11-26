package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<NewsItem> item);

    @Query("SELECT * FROM news_item ORDER BY ID ASC")
    LiveData<List<NewsItem>> loadAllNewsItems();

    @Query("DELETE FROM news_item")
    void clearAll();
}
