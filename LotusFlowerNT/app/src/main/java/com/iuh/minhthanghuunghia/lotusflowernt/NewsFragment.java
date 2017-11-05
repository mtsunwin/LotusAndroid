package com.iuh.minhthanghuunghia.lotusflowernt;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityNewsFragmentBinding;

import java.util.ArrayList;
import java.util.Date;

public class NewsFragment extends Fragment {
    private ActivityNewsFragmentBinding binding;
    private ArrayAdapter listNews;
    private ArrayList<News> dsNews;
    private AdapterNews adapterNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_news_fragment, container, false);
        RecyclerView recyclerView = binding.recyclerView;
        dsNews = new ArrayList<News>();
        dsNews.add(new News("A001", new Date(), "Thắng đẹp trai"));
        dsNews.add(new News("A001", new Date(), "Thắng đẹp trai"));
        dsNews.add(new News("A001", new Date(), "Thắng đẹp trai"));
        dsNews.add(new News("A001", new Date(), "Thắng đẹp trai"));
        dsNews.add(new News("A001", new Date(), "Thắng đẹp trai"));
        dsNews.add(new News("A001", new Date(), "Thắng đẹp trai"));
        adapterNews = new AdapterNews(dsNews);
        recyclerView.setAdapter(adapterNews);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return binding.getRoot();
    }
}
