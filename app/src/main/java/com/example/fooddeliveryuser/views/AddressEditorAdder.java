package com.example.fooddeliveryuser.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddeliveryuser.databinding.ActivityAddressEditorAdderBinding;
import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.viewmodels.AddressEditorViewModel;

public class AddressEditorAdder extends AppCompatActivity {

    private ActivityAddressEditorAdderBinding binding;
    private AddressEditorViewModel viewModel;

    private Address addressReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressEditorAdderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ViewModel INIT
        viewModel = new ViewModelProvider(this).get(AddressEditorViewModel.class);

        // Check if we are editing an existing address or creating a new one
        Intent intent = getIntent();
        if (intent.hasExtra("address")) {
            addressReference = (Address) intent.getSerializableExtra("address");
            viewModel.setReferenceAddress(addressReference);
            binding.deleteLocation.setVisibility(View.VISIBLE);
//            populateUI(addressReference);
        }

        // Click Listeners
        binding.pickLocation.setOnClickListener(v -> saveAddress());
        binding.BackButton.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
        binding.deleteLocation.setOnClickListener(v->deleteAddress());

        // Observe ViewModel for changes
        if (addressReference != null) {
            Log.i(this.getClass().getSimpleName(),"Address Populated by Fetching from Database");
            viewModel.getReferenceAddress(addressReference.getAddressId()).observe(this, this::populateUI);
        }
    }
    private void deleteAddress(){
        new AlertDialog.Builder(this)
                .setTitle("Delete Address")
                .setMessage("Are you sure you want to delete this address?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.deleteAddress(addressReference);
                        finish(); // Close the activity
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void populateUI(Address address) {
        if (address != null) {
            binding.addressPickerLabel.setText("Address Editor");
            binding.Label.setText(address.getAddressLabel());
            binding.DoorNo.setText(address.getAddressLine());
            binding.city.setText(address.getCityState());
            binding.Pincode.setText(address.getPincode());
            binding.ReceiverName.setText(address.getReceiverName());
            binding.ReceiverPhone.setText(address.getReceiverPhoneNumber());
            binding.primaryCheck.setChecked(address.getIsPrimaryAddress());
        }
    }

    private void notifyHelpText(String msg){
        binding.helpText.setText(msg);
        binding.helpText.setVisibility(View.VISIBLE);
    }


    private void saveAddress() {
        String label = binding.Label.getText().toString();
        String doorNoStreet = binding.DoorNo.getText().toString();
        String cityState = binding.city.getText().toString();
        String pincode = binding.Pincode.getText().toString();
        String receiverName = binding.ReceiverName.getText().toString();
        String receiverPhone = binding.ReceiverPhone.getText().toString();
        boolean isPrimary = binding.primaryCheck.isChecked();

        if(label.isEmpty() || doorNoStreet.isEmpty() || cityState.isEmpty() || pincode.isEmpty() || receiverName.isEmpty() || receiverPhone.isEmpty()){
            notifyHelpText("Fill in the details");
        }else {
            Address address = new Address("User101", label, doorNoStreet, cityState, pincode, receiverName, receiverPhone, isPrimary);

            if (addressReference != null) {
                address.setAddressId(addressReference.getAddressId());
                Log.i(this.getClass().getSimpleName(), "Updation of : " + String.valueOf(address.getAddressId()));
                viewModel.updateAddress(address);
            } else {
                Log.i(this.getClass().getSimpleName(), "Saving of : " + String.valueOf(address.getAddressId()));
                viewModel.saveAddress(address);
            }

            finish(); // Close the activity
        }
    }
}
