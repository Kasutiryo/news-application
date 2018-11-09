package com.example.rkjc.news_app_2;

public class NewsItem {

    private String
        title,
        description,
        date,
        URL;

    public NewsItem (String title, String description, String date, String URL) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.URL = URL;
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

    public String getUrl() {
        return URL;
    }
}
