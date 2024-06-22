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


    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

//       Static Data
        binding.loginText.setText(loginViewModel.getLoginText());

//      Dynamic  Data

        loginViewModel.getLoginCheck().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s){
                    case "Pass":
                        startActivity(new Intent(getApplicationContext(),AddressPicker.class));
                        finish();
                        break;
                    case "Fail":
                        notifyHelpText("Wrong Credentials");
                        break;
                    default:
                }
            }
        });


//        OnClicks

        binding.buttonForgotPassword.setOnClickListener(v->{
            notifyHelpText("Check your email to reset the password.");
        });


        binding.buttonRegister.setOnClickListener(v->{

            startActivity(new Intent(getApplicationContext(), Register.class));
        });


        binding.buttonLogin.setOnClickListener(v->{
            if(!binding.editTextUsername.getEditableText().toString().isEmpty() && !binding.editTextPassword.getEditableText().toString().isEmpty()) {
                loginViewModel.setUserCredentialText(binding.editTextUsername.getEditableText().toString());
                loginViewModel.setUserPassword(binding.editTextPassword.getEditableText().toString());
                loginViewModel.checkLogin();
            }else{
                notifyHelpText("Please fill the fields!");
            }
        });

    }

    private void notifyHelpText(String Message){
        binding.helpText.setText(Message);
        binding.helpText.setVisibility(View.VISIBLE);
    }

}
