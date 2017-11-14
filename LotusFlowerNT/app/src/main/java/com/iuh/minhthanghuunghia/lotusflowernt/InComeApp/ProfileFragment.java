package com.iuh.minhthanghuunghia.lotusflowernt.InComeApp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iuh.minhthanghuunghia.lotusflowernt.AdapterRecyclerView.AdapterProfile;
import com.iuh.minhthanghuunghia.lotusflowernt.EnterApp.MainActivity;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;
import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.NewsTable;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.UserTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityProfileFragmentBinding;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ThinkPad on 11/7/2017.
 */

public class ProfileFragment extends Fragment implements AdapterProfile.callBack {
    public static final String key_check = "TEST001";
    private ActivityProfileFragmentBinding binding;

    private User user;

    private UserTable database_table;
    private NewsTable database_news;
    private AdapterProfile adapterProfile;

    private ArrayList<News> dsNews;
    private ArrayList<News> dsCompleted;

    private String str = "";
    private static final int CAMERA_REQUES_Avatar = 1111;
    private static final int CAMERA_REQUES_Cover = 2222;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_profile_fragment, container,
                false);
        database_table = new UserTable(getActivity().getApplicationContext());
        database_news = new NewsTable(getActivity().getApplicationContext());
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            user = database_table.getInfor(bundle.getString(MainActivity.KEY_USERNAME));
        }
        Log.e("tmt username", user.getUsername());
        dsNews = database_news.getMyList(user.getUsername());
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
        dsCompleted.add(0, new News(key_check, key_check, ""));
        adapterProfile = new AdapterProfile(dsCompleted, getContext(), database_news, user, this);
        binding.ListItemStatus.setAdapter(adapterProfile);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL,
                        false);
        binding.ListItemStatus.setLayoutManager(layoutManager);
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
                        dsCompleted.add(1, nt1);
                    adapterProfile.notifyDataSetChanged();
                }
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
        for (News news : dsCompleted) {
            LinearLayout linearLayoutContainer = new LinearLayout(getContext());
            linearLayoutContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayoutContainer.setOrientation(LinearLayout.VERTICAL);
        }
        adapterProfile.notifyDataSetChanged();
        return binding.getRoot();
    }

    @Override
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUES_Avatar);
    }

    @Override
    public void openCameraBig() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUES_Cover);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUES_Avatar && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            user.setBitmapAvatar(converBitMap(bitmap));
            adapterProfile.notifyDataSetChanged();
        }
        if (requestCode == CAMERA_REQUES_Cover && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            user.setBitmapCover(converBitMap(bitmap));
            adapterProfile.notifyDataSetChanged();
        }
    }

    public byte[] converBitMap(Bitmap imageView) {
        Bitmap bitmap = imageView;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] bytes = outputStream.toByteArray();
        return bytes;
    }
}
