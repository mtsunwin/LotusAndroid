package com.iuh.minhthanghuunghia.lotusflowernt.InComeApp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivitySetupFragmentBinding;

public class SetupFragment extends Fragment {
    private ActivitySetupFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_setup_fragment,
                container, false);
        return binding.getRoot();
    }
}
