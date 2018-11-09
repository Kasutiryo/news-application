# News Application - (GNN - Gaming News Now)
## Part I - Preliminary

Create a new class called NetworkUtils. 
Define the appropriate base_url and any needed parameter constants (make sure they are Java constants) here as static class members.
Create a public static method in NetworkUtils that uses Uri.Builder to build the appropriate url, the same url as above, and returns a Java URL object. 
Name it buildURL. Put this method in your NetworkUtils class: (tested with NetworkUnitTest)
Set up permissions to use the internet in the manifest. (if you don't, you will lose a lot of points)
Extend and implement a subclass of AsyncTask, call it NewsQueryTask, to handle the http request, and have its doInBackground. 
Use NeworkUtils.getRsponsFromHttpUrl to get the json result string. Return the string.
Implement a menu item called get_news in your res directory. Have its title read "Refresh".  
Implement the menu item in your activity so that it executes the AsyncTask.

## Part II

In this part, you will add to part 1 by parsing the JSON you got from newsapi.org into NewsItem objects. 
These will be displayed in a RecyclerView. 
When an item in the RecyclerView is clicked, a browser will be opened to the url of the news item.
Create a NewsItem model class to store information about each news story. You need to include fields 
(make them of type String) for all of the information in each item (see JSON for what those items are 
(they will include the article's title and description, and url, and other things), you need to figure out 
what the information is from the JSON). 
Create a new class JsonUtils. Add a static method public static ArrayList<NewsItem> parseNews(String JSONString) that
will parse the JSON you received into an ArrayList<NewsItem>.
Add a RecyclerView named news_recyclerview to your activity's xml layout. Add a new layout for your list item. 
It should have a LinearLayout with three child TextViews, title, description, date. 
Implement the RecyclerView's adapter, call it NewsAdapter, along with the Holder as an inner class (call it NewsItemViewHolder). 
Set up the RecyclerView so that it displays, in each item, the item's title, description, and date (it doesn't have to be formatted, just put it in raw). 
The view's textviews shall also begin as follows:
Title: ...
Description: ...
Date: ...
Implement click listeners for the RecyclerView's items so that, when clicked, a browser is opened to the url for that news item. 
