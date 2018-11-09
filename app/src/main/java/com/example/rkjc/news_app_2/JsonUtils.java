package com.example.rkjc.news_app_2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class JsonUtils {

    public static LinkedList<NewsItem> parseNews(String JSONString) {
        LinkedList<NewsItem> newsItems = new LinkedList<>();
        try {
            JSONObject jObject = new JSONObject(JSONString);
            JSONArray jArray = jObject.getJSONArray("articles");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);
                newsItems.add(
                        new NewsItem(
                                obj.getString("title"),
                                obj.getString("description"),
                                obj.getString("publishedAt"),
                                obj.getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItems;
    }
}


