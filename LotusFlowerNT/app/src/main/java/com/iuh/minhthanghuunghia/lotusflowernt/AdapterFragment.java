package com.iuh.minhthanghuunghia.lotusflowernt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iuh.minhthanghuunghia.lotusflowernt.EnterApp.MainActivity;
import com.iuh.minhthanghuunghia.lotusflowernt.InComeApp.NewsFragment;
import com.iuh.minhthanghuunghia.lotusflowernt.InComeApp.ProfileFragment;
import com.iuh.minhthanghuunghia.lotusflowernt.InComeApp.SetupFragment;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 11/9/2017.
 */

class AdapterFragment extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    private User user;
    private FragmentManager fragmentManager;

    public AdapterFragment(FragmentManager fm, User user) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(new ProfileFragment());
        fragments.add(new SetupFragment());
        // Chỉ có giá trị Username
        this.user = user;
        this.fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.KEY_USERNAME, user.getUsername());
        fragments.get(position).setArguments(bundle);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
