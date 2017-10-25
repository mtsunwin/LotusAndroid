package com.iuh.minhthanghuunghia.lotusflowernt;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ItemNewsBinding;

import java.util.ArrayList;

/**
 * Created by ThinkPad on 10/25/2017.
 */

public class AdapterNews extends RecyclerView.Adapter {
    private ArrayList<News> dsNews;

    public AdapterNews(ArrayList<News> dsNews) {
        this.dsNews = dsNews;
    }

    private class myHolder extends RecyclerView.ViewHolder {
        public myHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding itemNewsBinding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        View view = itemNewsBinding.getRoot();
        myHolder myHolder = new myHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        News news = dsNews.get(position);
        ItemNewsBinding itemNewsBinding = DataBindingUtil.findBinding(holder.itemView);
        itemNewsBinding.textViewContent.setText(news.getContent());
        itemNewsBinding.textViewPostTime.setText(news.getTime().toString());

    }

    @Override
    public int getItemCount() {
        return dsNews.size();
    }
}
