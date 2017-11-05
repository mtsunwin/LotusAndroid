package com.iuh.minhthanghuunghia.lotusflowernt;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityProfileFragmentBinding;

/**
 * Created by ThinkPad on 11/1/2017.
 */

public class ProfileFragment extends Fragment {
    private ActivityProfileFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_profile_fragment, container, false);
        return binding.getRoot();
    }
}
