package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private List<NewsItem> mNewsItems;
    private LayoutInflater mInflater;

    public NewsRecyclerViewAdapter(Context context/*, LinkedList<NewsItem> mNewsItems*/) {
        mInflater = LayoutInflater.from(context);
//        this.mNewsItems = mNewsItems;
    }

    @NonNull
    @Override
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.news_item, parent, false);
        NewsViewHolder holder = new NewsViewHolder(mItemView, this);
        mItemView.setOnClickListener(holder);
        return holder;

//        return new NewsViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.NewsViewHolder holder, int position) {
        if (mNewsItems != null) {
            NewsItem mCurrent = mNewsItems.get(position);
            holder.mTitle.setText("Title: " + mCurrent.getTitle());
            holder.mDescription.setText(mCurrent.getDescription());
            holder.mDate.setText(mCurrent.getDate());
            holder.mNewsLink = mCurrent.getURL();
        } else {
            holder.mDescription.setText("Oops! There is no news to show!");
        }
    }

    void setNewsItems(List<NewsItem> newsItems) {
        mNewsItems = newsItems;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mNewsItems != null) {
            return mNewsItems.size();
        }
        else return 0;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String LOG_TAG =
                NewsViewHolder.class.getSimpleName();

        public final TextView mTitle;
        public final TextView mDescription;
        public final TextView mDate;
        public String mNewsLink;
        final NewsRecyclerViewAdapter mAdapter;

        public NewsViewHolder(View itemView, NewsRecyclerViewAdapter adapter) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.news_item_title);
            mDescription = itemView.findViewById(R.id.news_item_description);
            mDate = itemView.findViewById(R.id.news_item_date);
            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Open Website", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mNewsLink));

            if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                v.getContext().startActivity(intent);
            } else {
                Log.d(LOG_TAG, "Can't handle this");
            }
        }
    }
}
