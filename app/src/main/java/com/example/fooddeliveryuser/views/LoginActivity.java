package com.example.fooddeliveryuser.views;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddeliveryuser.databinding.ActivityLoginBinding;
import com.example.fooddeliveryuser.viewmodels.LoginViewModel;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(LoginViewModel.class);

        binding.buttonLogin.setOnClickListener(v -> {
            String username = binding.editTextUsername.getText().toString();
            String password = binding.editTextPassword.getText().toString();
//            Checks
            if (username.isEmpty() || password.isEmpty()) {
                notifyHelpText("Please fill the fields!");
                return;
            }

            // Verify the user
            loginViewModel.getUserByName(username).observe(this, user -> {
                if (user != null && user.getPasswordHash().equals(password)) {
                    startActivity(new Intent(getApplicationContext(), AddressPicker.class));
                    finish();
                } else {
                    notifyHelpText("Invalid username or password!");
                }
            });
        });

        binding.buttonForgotPassword.setOnClickListener(v -> {
            notifyHelpText("Check your email to reset the password.");
        });

        binding.buttonRegister.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Register.class));
        });
    }

    private void notifyHelpText(String message) {
        binding.helpText.setText(message);
        binding.helpText.setVisibility(View.VISIBLE);
    }
}
