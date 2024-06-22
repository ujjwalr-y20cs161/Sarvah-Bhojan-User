package com.example.fooddeliveryuser.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.adapters.AddressPickerAdapter;
import com.example.fooddeliveryuser.databinding.ActivityAddressPickerBinding;
import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.viewmodels.AddressPickerViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddressPicker extends AppCompatActivity {

    private ActivityAddressPickerBinding binding;
    private AddressPickerAdapter addressAdapter;

    private AddressPickerViewModel addressPickerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_picker);
        binding = ActivityAddressPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addAddress.setOnClickListener(v->{
            if(addressPickerViewModel.getAddressCount().getValue() <= 5 ) {
                Log.e("APV : AddressCount",String.valueOf(addressPickerViewModel.getAddressCount().getValue()));
                startActivity(new Intent(getApplicationContext(), AddressEditorAdder.class));
            }else{
                binding.addAddress.setEnabled(false);
                Toast.makeText(getApplicationContext(),"No more than 5 Address Allowed",Toast.LENGTH_SHORT).show();
            }
        });



        binding.AddressCatalouge.setLayoutManager(new LinearLayoutManager(this));


        addressAdapter = new AddressPickerAdapter(new ArrayList<>(),new AddressPickerAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Address address) {
                // Handle edit click
                Toast.makeText(getApplicationContext(),"We will edit this "+address.getAddressId(),Toast.LENGTH_SHORT).show();
            }
        });

        binding.AddressCatalouge.setAdapter(addressAdapter);

        addressPickerViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(AddressPickerViewModel.class);
        addressPickerViewModel.getAllAddress().observe(this, new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> addresses) {
                if(addresses != null) {
                    Log.e("AddressList-Size", String.valueOf(addresses.size()));
                    addressAdapter.setAddressList(addressPickerViewModel.getAllAddress().getValue());
                }
            }
        });

        addressPickerViewModel.getAddressCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer.equals(0)){
                    binding.AddressCatalougeLoad.setVisibility(View.VISIBLE);
                    binding.AddressCatalougeLoad.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.AddressCatalougeLoad.setVisibility(View.GONE);
                            binding.NoCatalogue.setVisibility(View.VISIBLE);
                            binding.AddressCatalouge.setVisibility(View.GONE);
                        }
                    },3000);
                    binding.AddressCatalouge.setVisibility(View.GONE);
                }else{
                    binding.AddressCatalougeLoad.setVisibility(View.VISIBLE);
                    binding.AddressCatalougeLoad.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.AddressCatalougeLoad.setVisibility(View.GONE);
                            binding.NoCatalogue.setVisibility(View.GONE);
                            binding.AddressCatalouge.setVisibility(View.VISIBLE);
                        }
                    },3000);
                }
            }
        });



        binding.BackButton.setOnClickListener(v->onBackPressed());

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addressPickerViewModel.deleteAll();
    }
}