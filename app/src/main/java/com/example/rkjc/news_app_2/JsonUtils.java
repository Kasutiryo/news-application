package com.example.rkjc.news_app_2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Helper class to parse a JSON String from the News API into a LinkedList of NewsItems
 */
public class JsonUtils {

    /**
     *
     * @param JSONString A JSON String from www.newsapi.org
     * @return A list of parsed NewsItems in the form of a List<NewsItem>
     */
    public static List<NewsItem> parseNews(String JSONString) {
        List<NewsItem> newsItems = new LinkedList<>();
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
                                obj.getString("url"),
                                obj.getString("urlToImage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItems;
    }
}


