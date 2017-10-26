package com.iuh.minhthanghuunghia.lotusflowernt.EnterApp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityRegisterBinding;

import static com.iuh.minhthanghuunghia.lotusflowernt.EnterApp.MainActivity.KEY_PASSWORD;
import static com.iuh.minhthanghuunghia.lotusflowernt.EnterApp.MainActivity.KEY_USERNAME;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRegister();
            }
        });
    }

    private void buttonRegister() {
        Intent intent = new Intent();
        intent.putExtra(KEY_USERNAME, binding.editTextUsername.getText().toString());
        intent.putExtra(KEY_PASSWORD, binding.editTextPassword.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
