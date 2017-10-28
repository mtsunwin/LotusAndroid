package com.iuh.minhthanghuunghia.lotusflowernt.EnterApp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;
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
        binding.textViewHadAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void buttonRegister() {
        if (checkData()) {
            Intent intent = new Intent();
            intent.putExtra(KEY_USERNAME, binding.editTextUsername.getText().toString());
            intent.putExtra(KEY_PASSWORD, binding.editTextPassword.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean checkData() {
        User user = new User();
        if (!binding.editTextPassword.getText().toString().equals(binding.editTextPasswordSeccond.getText().toString())) {
            binding.editTextPasswordSeccond.setError(getApplicationContext().getResources().getString(R.string.errPasswordSeccon));
            binding.editTextPasswordSeccond.setFocusable(true);
            return false;
        }
        if (binding.editTextMail.getText().toString().length() < 0) {
            binding.editTextMail.setError(getApplicationContext().getResources().getString(R.string.errEmailNull));
            binding.editTextMail.setFocusable(true);
            return false;
        }
        if (binding.editTextUsername.getText().toString().length() < 0) {
            binding.editTextUsername.setError(getApplicationContext().getResources().getString(R.string.errUsernameNull));
            binding.editTextUsername.setFocusable(true);
            return false;
        }
        user.setEmail(binding.editTextMail.getText().toString());
        user.setPassword(binding.editTextPassword.getText().toString());
        user.setUsername(binding.editTextUsername.getText().toString());
        return true;
    }
}
