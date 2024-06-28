package com.example.fooddeliveryuser.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.adapters.AddressPickerAdapter;
import com.example.fooddeliveryuser.databinding.ActivityAddressPickerBinding;
import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.viewmodels.AddressPickerViewModel;

import java.util.Collections;
import java.util.List;

public class AddressPicker extends AppCompatActivity {

    private ActivityAddressPickerBinding binding;
    private AddressPickerViewModel viewModel;
    private AddressPickerAdapter adapter, allAddressAdapater;

    private AddressPickerAdapter.OnItemClickListener editListener;

    private Boolean isPrimaryAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding
        binding = ActivityAddressPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ViewModel Initialisation
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(AddressPickerViewModel.class);

        // RecyclerView setup
        adapter = new AddressPickerAdapter(Collections.emptyList());
        allAddressAdapater = new AddressPickerAdapter(Collections.emptyList());

        binding.currentAddress.setLayoutManager(new LinearLayoutManager(this));
        binding.currentAddress.setAdapter(adapter);

        binding.AddressCatalouge.setLayoutManager(new LinearLayoutManager(this));
        binding.AddressCatalouge.setAdapter(allAddressAdapater);

        editListener = new AddressPickerAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Address address) {
                Intent intent = new Intent(getApplicationContext(), AddressEditorAdder.class);
                intent.putExtra("address", address);
                startActivity(intent);

                // For now, just show a toast message
                Toast.makeText(getApplicationContext(), "Edit " + address.getAddressLabel(), Toast.LENGTH_SHORT).show();
            }
        };


//        Default Configurations:
        isPrimaryAvailable = false;


        // Observe the primary address
        viewModel.getPrimaryAddress().observe(this, new Observer<Address>() {
            @Override
            public void onChanged(Address address) {


                if (address != null) {
                    adapter = new AddressPickerAdapter(Collections.singletonList(address));
                    binding.currentAddress.setAdapter(adapter);
                    adapter.setOnItemClickListener(editListener);
                    binding.currentAddressLoad.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(()->{
                        binding.currentAddressLoad.setVisibility(View.GONE);
                        binding.currentAddress.setVisibility(View.VISIBLE);},1000);
                    binding.popUp.setVisibility(View.VISIBLE);
                    isPrimaryAvailable = true;
                    binding.BackButton.setEnabled(true);
                    binding.NoneSelected.setVisibility(View.GONE);

                } else {
                    binding.currentAddress.setVisibility(View.GONE);
                    binding.currentAddressLoad.setVisibility(View.GONE);
                    binding.NoneSelected.setVisibility(View.VISIBLE);
                    binding.popUp.setVisibility(View.GONE);
                    binding.BackButton.setEnabled(false);
                    isPrimaryAvailable = false;

                }
            }
        });
        // Observe all Addresses
        viewModel.getAllAddresses().observe(this, new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> addresses) {
                if(!addresses.isEmpty()){
                    allAddressAdapater = new AddressPickerAdapter(addresses);
                    binding.AddressCatalouge.setAdapter(allAddressAdapater);
                    allAddressAdapater.setOnItemClickListener(editListener);
                    binding.NoCatalogue.setVisibility(View.GONE);
                    binding.AddressCatalougeLoad.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(()->{
                        binding.AddressCatalouge.setVisibility(View.VISIBLE);
                        binding.AddressCatalougeLoad.setVisibility(View.GONE);
                    },1000);


                }else{
                    binding.NoCatalogue.setVisibility(View.VISIBLE);
                    binding.AddressCatalouge.setVisibility(View.GONE);
                    binding.AddressCatalougeLoad.setVisibility(View.GONE);

                }
            }
        });



        // OnClickListeners
        binding.BackButton.setOnClickListener(view -> onBackPressed());
        binding.addAddress.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddressEditorAdder.class)));
        binding.continueButton.setOnClickListener(v->{
            try {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("primary_address", viewModel.getPrimaryAddress().getValue());
                startActivity(intent);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(isPrimaryAvailable) super.onBackPressed();
        else{
            Toast.makeText(getApplicationContext(),"Select a Primary Address",Toast.LENGTH_SHORT).show();
        }
    }
}
