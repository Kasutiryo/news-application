package com.example.rkjc.news_app_2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * News Item Model, Entity for room ORM and MVVM Architecture
 */
@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    private String title;
    private String description;
    private String date;
    private String URL;
    private String URLToImage;

    public NewsItem (@NonNull int ID, String title, String description, String date, String URL, String URLToImage) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.date = date;
        this.URL = URL;
        this.URLToImage = URLToImage;
    }

    @Ignore
    public NewsItem (String title, String description, String date, String URL, String URLToImage) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.URL = URL;
        this.URLToImage = URLToImage;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getURL() {
        return URL;
    }

    public int getID() { return ID; }

    public String getURLToImage() { return URLToImage; }
}
