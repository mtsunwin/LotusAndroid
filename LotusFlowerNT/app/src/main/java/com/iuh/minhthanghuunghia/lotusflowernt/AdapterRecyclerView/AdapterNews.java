package com.iuh.minhthanghuunghia.lotusflowernt.AdapterRecyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.NewsTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ItemNewsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 10/25/2017.
 */

public class AdapterNews extends RecyclerView.Adapter {
    private ArrayList<News> dsNews;
    private Context context;
    private NewsTable database_news;
    private List<Integer> selectedList = new ArrayList<>();


    public AdapterNews(ArrayList<News> dsNews, Context context, NewsTable database_news) {
        Log.e("tmt", dsNews.toString());
        this.dsNews = dsNews;
        this.context = context;
        this.database_news = database_news;
    }

    private class myHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageButton button;

        public myHolder(View itemView) {
            super(itemView);
            button = (ImageButton) itemView.findViewById(R.id.button_Like);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            News news = dsNews.get(position);
            Log.e("tmt get update 1", news.toString());
            if (news.isLike()) {
                button.setBackground(context.getDrawable(R.drawable.ic_likenormal));
                selectedList.remove(Integer.valueOf(position));
            } else {
                button.setBackground(context.getDrawable(R.drawable.ic_like));
                selectedList.add(position);
            }
            news.setLike(!news.isLike());
            Log.e("tmt get update", news.toString());
            database_news.update(news);
            Log.e("tml return update", database_news.update(news)+"");

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding itemNewsBinding = DataBindingUtil.inflate(inflater, R.layout.item_news,
                parent, false);
        View view = itemNewsBinding.getRoot();
        myHolder myHolder = new myHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        News news = dsNews.get(position);
        Log.e("tml news:",news.toString());
        ItemNewsBinding itemNewsBinding = DataBindingUtil.findBinding(holder.itemView);
        itemNewsBinding.textViewContent.setText(news.getContent());
        itemNewsBinding.textViewPostTime.setText(news.getTime());
        itemNewsBinding.textViewPostAccountname.setText(news.getNickName());
        Log.e("tml like",  String.valueOf(news.isLike()));
        if(String.valueOf(news.isLike()).equals("true")){
            itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_like));
        } else {
            itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_likenormal));
        }
        for (int i : selectedList) {
            if (i == position) {
                itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_like));
            }
        }
        /*if (news.isLike()) {
            itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_like));
        } else {
            itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_likenormal));
        }
        for (int i : selectedList) {
            if (i == position) {
                itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_like));
            }
        }*/
    }

    @Override
    public int getItemCount() {
        return dsNews.size();
    }
}
