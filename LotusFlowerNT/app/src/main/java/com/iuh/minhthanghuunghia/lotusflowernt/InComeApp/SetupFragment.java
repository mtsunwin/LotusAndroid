package com.iuh.minhthanghuunghia.lotusflowernt.InComeApp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.iuh.minhthanghuunghia.lotusflowernt.EnterApp.MainActivity;
import com.iuh.minhthanghuunghia.lotusflowernt.HomeActivity;
import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.NewsTable;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.UserTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivitySetupFragmentBinding;

import java.util.Locale;

public class SetupFragment extends Fragment{
    private ActivitySetupFragmentBinding binding;
    private boolean isDefault= true;
    private NewsTable Databases_News;
    private UserTable Databases_User;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState)
    {
        Databases_User= new UserTable(getContext());
        Databases_News= new NewsTable(getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_setup_fragment,
                container, false);
       binding.btnViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganNgonNgu("vi");

            }
        });
        binding.btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganNgonNgu("en");
            }
        });
        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Databases_User.clearList();
                //Nho them ham deleteAll() vào trong NewsTable
                Databases_News.deleteAll();

                Intent i = getContext().getPackageManager()
                        .getLaunchIntentForPackage( getContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                getActivity().finish();
                startActivity(i);
            }
        });
        return binding.getRoot();
    }
    public void ganNgonNgu(String language){
        Locale locale= new Locale(language);
        Configuration config= new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config,getContext().getResources().getDisplayMetrics());
        Intent intent = new Intent(getContext(),SetupFragment.class);
        Intent i = getContext().getPackageManager()
                .getLaunchIntentForPackage( getContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}
