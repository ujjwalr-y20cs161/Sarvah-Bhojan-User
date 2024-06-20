package com.example.fooddeliveryuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fooddeliveryuser.databinding.ActivityAddressPickerBinding;

public class AddressPicker extends AppCompatActivity {

    ActivityAddressPickerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_picker);
        binding = ActivityAddressPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addAddress.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), AddressEditorAdder.class));
        });

    }
}