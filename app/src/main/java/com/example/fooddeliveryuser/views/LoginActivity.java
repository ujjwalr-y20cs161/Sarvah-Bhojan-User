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
                if (user != null){
                    if(user.getPasswordHash().equals(password)) {
                    loginViewModel.setSharedPreferences(user.getUserId(),user.getUserName(),user.getPasswordHash());
                    startActivity(new Intent(getApplicationContext(), AddressPicker.class));
                    finish();
                    }else notifyHelpText("Wrong Password");
                } else {
                    loginViewModel.getUserByEmail(username).observe(this, user1 -> {
                        if (user1 != null){
                            if(user1.getPasswordHash().equals(password)) {
                                loginViewModel.setSharedPreferences(user1.getUserId(),user1.getUserName(), user1.getPasswordHash());
                                startActivity(new Intent(getApplicationContext(), AddressPicker.class));
                                finish();
                            }else notifyHelpText("Wrong Password");
                        } else {
                            loginViewModel.getUserByPhoneNumber(username).observe(this, user2 -> {
                                if (user2 != null) {
                                    if( user2.getPasswordHash().equals(password)){
                                        loginViewModel.setSharedPreferences(user2.getUserId(),user2.getUserName(),user2.getPasswordHash());
                                        startActivity(new Intent(getApplicationContext(), AddressPicker.class));
                                        finish();
                                    }else notifyHelpText("Wrong Password");
                                }else{
                                    notifyHelpText("Enter valid User Credentials");
                                }
                            });
                        }
                    });
                }
            });
        });

        binding.buttonForgotPassword.setOnClickListener(v -> {
//          firebase call
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
