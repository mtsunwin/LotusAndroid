package com.iuh.minhthanghuunghia.lotusflowernt.EnterApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iuh.minhthanghuunghia.lotusflowernt.HomeActivity;
import com.iuh.minhthanghuunghia.lotusflowernt.Model.User;
import com.iuh.minhthanghuunghia.lotusflowernt.R;
import com.iuh.minhthanghuunghia.lotusflowernt.SQLHepler.UserTable;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Context context;
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final int KEY_OVER_REGISTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        binding.textViewCreateAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runActivityProfile();
            }
        });
        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSignin();
            }
        });
    }

    private void buttonSignin() {
        if (binding.editTextPassword.getText().toString().length() >= 3
                && binding.editTextUsername.getText().toString().length() >= 3) {
            User user = new User(binding.editTextUsername.getText().toString(),
                    binding.editTextPassword.getText().toString());
            UserTable table = new UserTable(getApplicationContext());
            if (table.login(user)) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                binding.editTextUsername.setFocusable(true);
                binding.editTextUsername.setError(
                        getApplicationContext().getResources().getString(R.string.errLoginFalse));
                binding.editTextUsername.setText("");
                binding.editTextPassword.setText("");
            }
        } else {
            if (binding.editTextPassword.getText().toString().length() == 0) {
                binding.editTextPassword.setError(
                        getApplicationContext().getResources().getString(R.string.errPasswordFirtsNull));
                binding.editTextPassword.setFocusable(true);
            } else if (binding.editTextPassword.getText().toString().length() < 3) {
                binding.editTextPassword.setError(
                        getApplicationContext().getResources().getString(R.string.errPasswordFirts));
                binding.editTextPassword.setFocusable(true);
            }
            if (binding.editTextUsername.getText().toString().length() == 0) {
                binding.editTextUsername.setError(
                        getApplicationContext().getResources().getString(R.string.errPasswordFirtsNull));
                binding.editTextUsername.setFocusable(true);
            } else if (binding.editTextUsername.getText().toString().length() < 3) {
                binding.editTextUsername.setError(
                        getApplicationContext().getResources().getString(R.string.errUsername));
                binding.editTextUsername.setFocusable(true);
            }
            binding.editTextUsername.setText("");
            binding.editTextPassword.setText("");
        }
    }

    private void runActivityProfile() {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivityForResult(intent, KEY_OVER_REGISTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KEY_OVER_REGISTER && resultCode == Activity.RESULT_OK) {
            binding.editTextUsername.setText(data.getStringExtra(KEY_USERNAME));
            binding.editTextPassword.setText(data.getStringExtra(KEY_PASSWORD));
        }
    }
}