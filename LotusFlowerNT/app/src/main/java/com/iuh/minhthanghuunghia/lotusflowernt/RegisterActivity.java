package com.iuh.minhthanghuunghia.lotusflowernt;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityRegisterBinding;

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
        intent.putExtra("username", binding.editTextUsername.getText().toString());
        intent.putExtra("password", binding.editTextPassword.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
