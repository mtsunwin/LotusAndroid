package com.iuh.minhthanghuunghia.lotusflowernt.InComeApp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iuh.minhthanghuunghia.lotusflowernt.AdapterRecyclerView.AdapterNews;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.NewsTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityNewsFragmentBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsFragment extends Fragment {
    private ActivityNewsFragmentBinding binding;
    private ArrayAdapter listNews;

    private AdapterNews adapterNews;
    private NewsTable database_news;


    private ArrayList<News> dsNews;
    private ArrayList<News> dsCompleted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*Reflection*/
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_news_fragment,
                container, false);

        RecyclerView recyclerView = binding.recyclerView;
        database_news = new NewsTable(getContext());
        /*Reflection END*/
        dsNews = database_news.getList();
        dsCompleted = new ArrayList<News>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        News minNews;
        while (dsNews.size() > 0) {
            minNews = dsNews.get(0);
            try {
                Date date = simpleDateFormat.parse(minNews.getTime());
                for (int i = 1; i < dsNews.size(); i++) {
                    Date date2 = simpleDateFormat.parse(dsNews.get(i).getTime());
                    if (date.before(date2)) {
                        minNews = dsNews.get(i);
                    }
                }
                dsCompleted.add(minNews);
                dsNews.remove(minNews);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // Truyền tham số cho NEWS RECYCLE VIEW
        adapterNews = new AdapterNews(dsCompleted, getContext(), database_news);
        recyclerView.setAdapter(adapterNews);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL,
                        false);
        recyclerView.setLayoutManager(layoutManager);
        adapterNews.notifyDataSetChanged();
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (News nt1 : database_news.getList()) {
                    boolean g1 = false;
                    for (News nt2 : dsCompleted) {
                        if (nt1.equals(nt2))
                            g1 = true;
                    }
                    if (!g1)
                        dsCompleted.add(0, nt1);
                    adapterNews.notifyDataSetChanged();
                }
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        return binding.getRoot();
    }

}