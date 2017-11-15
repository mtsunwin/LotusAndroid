package com.iuh.minhthanghuunghia.lotusflowernt;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.iuh.minhthanghuunghia.lotusflowernt.EnterApp.MainActivity;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.NewsTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityHomeBinding;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class HomeActivity extends FragmentActivity {
    public static final int key_popNewsUp = 1;
    public static final String cutString = "<thang>";
    public static final String port = "http://192.168.16.103:4567";
    private ActivityHomeBinding binding;
    private User user;
    private NewsTable database_news;
    private Socket socket;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            String getContent = message.obj.toString();
            String[] str = getContent.split(cutString);
            return insertNews(str[0], str[1]);
        }
    });

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
        binding.buttonUpNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionUpNews();
            }
        });
        try {
            socket = IO.socket(port);

            socket.connect();
            while (!socket.connected()) {
                Thread.sleep(100);
            }
            socket.on("message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String data = args[0].toString();
                    Message message = new Message();
                    message.obj = data;
                    handler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if (insertNews(user.getNickname() == null ? user.getUsername() : user.getNickname(),
                    bundle.getString(Pop_UpNews.key_popsend))) {
                String sendContent = "";
                if (user.getNickname() instanceof String) {
                    sendContent += user.getNickname();
                } else {
                    sendContent += user.getUsername();
                }
                sendContent += cutString + bundle.getString(Pop_UpNews.key_popsend).toString();
                socket.emit("message", sendContent);
            }
        }
    }

    private boolean insertNews(String name, String content) {
        News news = new News();
        news.setLike(false);
        news.setContent(content.trim());
        news.setUserName(name.trim());
        news.setNickName(name.trim());
        Log.e("tmt", news.toString());
        return database_news.insert(news) > 0;
    }
}