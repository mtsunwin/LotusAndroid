package com.iuh.minhthanghuunghia.lotusflowernt;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityMainBinding;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityRegisterBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static final int REQUEST_CODE_REGISTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    private void runActivityProfile() {

    }


}
