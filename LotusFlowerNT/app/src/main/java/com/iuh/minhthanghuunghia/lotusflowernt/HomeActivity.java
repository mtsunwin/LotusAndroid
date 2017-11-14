package com.iuh.minhthanghuunghia.lotusflowernt;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.iuh.minhthanghuunghia.lotusflowernt.EnterApp.MainActivity;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.NewsTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityHomeBinding;

public class HomeActivity extends FragmentActivity {
    public static final int key_popNewsUp = 1;

    private ActivityHomeBinding binding;
    private User user;
    private NewsTable database_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        database_news = new NewsTable(getApplicationContext());
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(MainActivity.KEY);
        user = new User(bundle.getString(MainActivity.KEY_USERNAME));
        binding.viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager(), user));
        binding.viewPager.setOffscreenPageLimit(3);
        binding.tableLayout.setTabMode(TabLayout.MODE_FIXED);
        binding.tableLayout.setupWithViewPager(binding.viewPager);
        binding.tableLayout.getTabAt(0).setIcon(getApplicationContext().getDrawable(R.drawable.ic_house));
        binding.tableLayout.getTabAt(1).setIcon(getApplicationContext().getDrawable(R.drawable.ic_newspaper));
        binding.tableLayout.getTabAt(2).setIcon(getApplicationContext().getDrawable(R.drawable.ic_menu_options));
        LinearLayout linearLayout = (LinearLayout) binding.tableLayout.getChildAt(0);
        // CHeck
//        for (int i = 0; i < linearLayout.getChildCount(); i++) {
//            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }

        binding.buttonUpNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionUpNews();
            }
        });
    }

    private void actionUpNews() {
        Intent intent = new Intent(this, Pop_UpNews.class);
        startActivityForResult(intent, key_popNewsUp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == key_popNewsUp) {
            Bundle bundle = data.getBundleExtra(Pop_UpNews.key_popsend);
            News news = new News();
            news.setLike(false);
            news.setContent(bundle.getString(Pop_UpNews.key_popsend));
            news.setUserName(user.getUsername());
            news.setNickName(user.getNickname() == null ? user.getUsername() : user.getNickname());
            database_news.insert(news);
        }
    }

}