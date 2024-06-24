package com.example.fooddeliveryuser.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.databinding.ActivitySplashScreenBinding;
import com.example.fooddeliveryuser.services.Tokens;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(Tokens.getSharedPrefName(), Context.MODE_PRIVATE);

        binding.shimmerLoad.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferences.getBoolean(Tokens.getLogged(),false)){
                    startActivity(new Intent(getApplicationContext(), AddressPicker.class));
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 1000);

    }
}