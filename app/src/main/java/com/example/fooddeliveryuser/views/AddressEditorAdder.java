package com.example.fooddeliveryuser.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.databinding.ActivityAddressEditorAdderBinding;

public class AddressEditorAdder extends AppCompatActivity {

    private ActivityAddressEditorAdderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressEditorAdderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BackButton.setOnClickListener(v->onBackPressed());



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}