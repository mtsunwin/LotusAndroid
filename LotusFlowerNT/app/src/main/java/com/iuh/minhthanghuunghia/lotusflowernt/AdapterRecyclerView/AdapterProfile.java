package com.iuh.minhthanghuunghia.lotusflowernt.AdapterRecyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuh.minhthanghuunghia.lotusflowernt.InComeApp.ProfileFragment;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.News;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;
import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.NewsTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ItemNewsBinding;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ItemProfileBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 11/11/2017.
 */

public class AdapterProfile extends RecyclerView.Adapter {
    private ArrayList<News> dsNews;
    private Context context;
    private NewsTable database_news;
    private List<Integer> selectedList = new ArrayList<>();
    private User user;

    public interface callBack {
        void openCamera();

        void openCameraBig();
    }

    private callBack callBack;

    public AdapterProfile(ArrayList<News> dsNews, Context context, NewsTable database_news, User user, callBack callBack) {
        this.dsNews = dsNews;
        this.context = context;
        this.database_news = database_news;
        this.user = user;
        this.callBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        News news = dsNews.get(position);
        if (news.getId().equals(ProfileFragment.key_check) && news.getTime().equals(ProfileFragment.key_check)) {
            return 0;
        }
        return 1;
    }

    /**
     * HOLDER
     */
    private class myHolderProfile extends RecyclerView.ViewHolder {
        public myHolderProfile(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        switch (viewType) {
            case 0:
                ItemProfileBinding profile = DataBindingUtil.inflate(inflater, R.layout.item_profile,
                        parent, false);
                view = profile.getRoot();
                break;
            case 1:
                ItemNewsBinding item = DataBindingUtil.inflate(inflater, R.layout.item_news,
                        parent, false);
                view = item.getRoot();
                break;
        }
        myHolderProfile myHolder = new myHolderProfile(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e("tmt positio", position + "");
        if (position == 0) {
            ItemProfileBinding itemProfileBinding = DataBindingUtil.findBinding(holder.itemView);
            Log.e("tmt", user.toString());
            String nick = user.getNickname().length() == 0 ? user.getUsername() : user.getNickname();
            itemProfileBinding.txtNickname.setText(nick);
            itemProfileBinding.txtSummaryInfor.setText(user.getAbout());
            if (user.getBitmapAvatar() instanceof byte[]) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user.getBitmapAvatar(), 0, user.getBitmapAvatar().length);
                itemProfileBinding.imgAvatar.setImageBitmap(bitmap);
            }
            if (user.getBitmapCover() instanceof byte[]) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user.getBitmapCover(), 0, user.getBitmapCover().length);
                itemProfileBinding.imgCover.setImageBitmap(bitmap);
                itemProfileBinding.imgCover.setScaleX(100);
            }
            itemProfileBinding.imgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.openCamera();
                }
            });
            itemProfileBinding.imgCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.openCameraBig();
                }
            });
        }
        if (position > 0) {
            final News news = dsNews.get(position);
            final ItemNewsBinding itemNewsBinding = DataBindingUtil.findBinding(holder.itemView);
            itemNewsBinding.textViewContent.setText(news.getContent());
            itemNewsBinding.textViewPostTime.setText(news.getTime());
            itemNewsBinding.textViewPostAccountname.setText(news.getNickName());
            if (news.isLike()) {
                itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_like));
            } else {
                itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_likenormal));
            }
            itemNewsBinding.buttonLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (news.isLike()) {
                        itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_likenormal));
                        selectedList.remove(Integer.valueOf(position));
                    } else {
                        itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_like));
                        selectedList.add(position);
                    }
                    news.setLike(!news.isLike());
                    database_news.update(news);
                }
            });
            for (int i : selectedList) {
                if (i == position) {
                    itemNewsBinding.buttonLike.setBackground(context.getDrawable(R.drawable.ic_like));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return dsNews.size();
    }
}
