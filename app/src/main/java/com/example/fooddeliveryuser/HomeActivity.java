package com.example.fooddeliveryuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.fooddeliveryuser.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        placeFragment(new Home());
        binding.homeBottomMenu.setOnItemSelectedListener(item -> {
            switch (item.getTitle() + ""){
                case "Home" : placeFragment(new Home());
                    break;
                case "Orders" : placeFragment(new Orders());
                    break;
                case "Account" : placeFragment(new Account());
                    break;
                case "Cart": placeFragment(new CartFragment());
                break;
                default: placeFragment(new Home());
            }

            return true;
        });
    }

    private void placeFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }
}